package javademos.hrms.business.concretes;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javademos.hrms.business.abstracts.JobPositionService;
import javademos.hrms.business.requests.jobPosition.JobPositionRequest;
import javademos.hrms.core.results.DataResult;
import javademos.hrms.core.service.ServiceBase;
import javademos.hrms.dataAccess.abstracts.JobPositionRepository;
import javademos.hrms.entities.JobPosition;

@Service
public class JobPositionManager extends ServiceBase implements JobPositionService {
	
	private JobPositionRepository jobRepository;
	private ModelMapper mapper;

	@Autowired
	public JobPositionManager(JobPositionRepository jobRepository, ModelMapper mapper) {
		this.jobRepository = jobRepository;
		this.mapper = mapper;
	}

	@Override
	public DataResult<JobPosition> getById(int id) {
		
		JobPosition job = this.jobRepository.getById(id);
		return success(job);
	}

	@Override
	public DataResult<List<JobPosition>> getList() {
		
		List<JobPosition> list = this.jobRepository.findAll();
		return success(list);
	}

	@Override
	public DataResult<Integer> create(JobPositionRequest request) {

		JobPosition job = this.mapper.map(request, JobPosition.class);
		JobPosition created = this.jobRepository.save(job);
		return success(created.getId(), "Job position created successfully.");
	}

	@Override
	public DataResult<Integer> update(int id, JobPositionRequest request) {

		JobPosition job = this.jobRepository.getById(id);
		this.mapper.map(request, job);
		JobPosition updated = this.jobRepository.save(job);		
		return success(updated.getId(), "Job position updated successfully.");
	}

	@Override
	public DataResult<Integer> delete(int id) {

		this.jobRepository.deleteById(id);
		return success(id, "Job position deleted successfully.");
	}
	
	
}
