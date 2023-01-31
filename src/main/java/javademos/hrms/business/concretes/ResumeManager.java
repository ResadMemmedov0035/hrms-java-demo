package javademos.hrms.business.concretes;

import java.io.IOException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javademos.hrms.business.abstracts.ResumeService;
import javademos.hrms.business.adapters.imageService.CloudImageService;
import javademos.hrms.business.requests.resume.ResumeRequest;
import javademos.hrms.business.responses.ResumeListResponse;
import javademos.hrms.business.responses.ResumeResponse;
import javademos.hrms.core.results.DataResult;
import javademos.hrms.core.results.Result;
import javademos.hrms.core.rules.RuleEvaluater;
import javademos.hrms.core.service.ServiceBase;
import javademos.hrms.dataAccess.abstracts.ResumeRepository;
import javademos.hrms.entities.Resume;

@Service
public class ResumeManager extends ServiceBase implements ResumeService {

	private ResumeRepository resumeRepository;
	private ModelMapper modelMapper;
	private CloudImageService imageService;

	@Autowired
	public ResumeManager(ResumeRepository resumeRepository, ModelMapper modelMapper, CloudImageService imageService) {

		this.resumeRepository = resumeRepository;
		this.modelMapper = modelMapper;
		this.imageService = imageService;
	}

	@Override
	public DataResult<ResumeResponse> getById(int id) {

		Resume resume = this.resumeRepository.getById(id);
		ResumeResponse res = this.modelMapper.map(resume, ResumeResponse.class);
		/**
		 * ModelMapper does not map these in proper order, so should be set explicitly.
		 */
		res.setEducations(resume.getEducations());
		res.setWorkExperiences(resume.getWorkExperiences());

		return success(res);
	}

	@Override
	public DataResult<List<ResumeListResponse>> getList(int candidateId) {

		List<ResumeListResponse> list = this.resumeRepository.findAllByCandidateId(candidateId).stream()
				.map(r -> this.modelMapper.map(r, ResumeListResponse.class)).toList();
		return success(list);
	}
	
	@Override
	public DataResult<Integer> create(ResumeRequest request) {

		Resume resume = this.modelMapper.map(request, Resume.class);
		Resume created = this.resumeRepository.save(resume);
		return success(created.getId(), "Resume created successfully.");
	}

	@Override
	public DataResult<Integer> update(int id, ResumeRequest request) {

		Resume resume = this.resumeRepository.getById(id);
		this.modelMapper.map(request, resume);
		Resume updated = this.resumeRepository.save(resume);
		return success(updated.getId(), "Resume created successfully.");
	}

	@Override
	public DataResult<Integer> delete(int id) {

		this.resumeRepository.deleteById(id);
		return success(id, "Resume created successfully.");
	}
	
	@Override
	public DataResult<String> uploadImage(int resumeId, MultipartFile file) {

		Result fileResult = RuleEvaluater.of(file)
				.bindRule(this::fileMustBeImageFormat)
				.evaluate();

		if (!fileResult.isSuccess()) return dataResult(fileResult);

		try (var stream = file.getInputStream()) {

			String path = this.imageService.upload(stream);
			this.resumeRepository.updateProfileImage(resumeId, path);
			return success(path, null);

		} catch (IOException e) {
			return failure(null, e.getMessage());
		}
	}

	private Result fileMustBeImageFormat(MultipartFile file) {

		boolean isImage = List.of("image/jpeg", "image/jpg", "image/png").contains(file.getContentType());

		if (!isImage)
			return failure("File format must be jpeg/jpg/png");
		return success();
	}	
}
