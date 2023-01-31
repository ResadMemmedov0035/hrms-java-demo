package javademos.hrms.business.concretes;

import java.text.MessageFormat;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import javademos.hrms.business.abstracts.JobPostService;
import javademos.hrms.business.requests.jobPost.JobPostRequest;
import javademos.hrms.business.responses.JobPostListResponse;
import javademos.hrms.core.results.DataResult;
import javademos.hrms.core.results.Result;
import javademos.hrms.core.service.ServiceBase;
import javademos.hrms.dataAccess.abstracts.JobPostRepository;
import javademos.hrms.entities.JobPost;

@Service
public class JobPostManager extends ServiceBase implements JobPostService {

	private JobPostRepository jobPostRepository;
	private ModelMapper mapper;

	@Autowired
	public JobPostManager(JobPostRepository jobPostRepository, ModelMapper mapper) {
		this.jobPostRepository = jobPostRepository;
		this.mapper = mapper;
	}

	@Override
	public DataResult<List<JobPostListResponse>> getList() {

		var list = this.jobPostRepository.findAllActives().stream()
				.map(j -> this.mapper.map(j, JobPostListResponse.class))
				.toList();		
		return success(list);

	}

	@Override
	public DataResult<List<JobPostListResponse>> getOrderedListByDate(Direction direction) {

		Sort sort = Sort.by(direction, "createdAt");
		
		var list = this.jobPostRepository.findAllActives(sort).stream()
				.map(j -> this.mapper.map(j, JobPostListResponse.class))
				.toList();
		return success(list);
	}

	@Override
	public DataResult<List<JobPostListResponse>> getListByEmployerId(int employerId) {
		
		Sort sort = Sort.by(Direction.DESC, "createdAt");
		
		var list = this.jobPostRepository.findAllActivesByEmployerId(employerId, sort).stream()
				.map(j -> this.mapper.map(j, JobPostListResponse.class))
				.toList();
		return success(list);
	}
	
	
	@Override
	public DataResult<List<JobPostListResponse>> getAllList() {

		var list = this.jobPostRepository.findAll().stream()
				.map(j -> this.mapper.map(j, JobPostListResponse.class))
				.toList();
		return success(list);
	}

	@Override
	public Result setActiveById(int id, boolean status) {
				
		if(this.jobPostRepository.updateStatusById(id, status) > 0) {
			
			var message = MessageFormat.format("Status change to ({0})", status);
			return success(message);
		}
		return failure("Something went wrong while change status.");
	}

	@Override
	public DataResult<Integer> create(JobPostRequest request) {

		JobPost post = this.mapper.map(request, JobPost.class);
		JobPost created = this.jobPostRepository.save(post);
		return success(created.getId(), "Job post created successfully.");
	}

	@Override
	public DataResult<Integer> update(int id, JobPostRequest request) {

		JobPost post = this.jobPostRepository.getById(id);
		this.mapper.map(request, post);
		JobPost updated = this.jobPostRepository.save(post);
		return success(updated.getId(), "Job post updated successfully.");
	}

	@Override
	public DataResult<Integer> delete(int id) {

		this.jobPostRepository.deleteById(id);
		return success(id, "Job post deleted successfully.");
	}
}
