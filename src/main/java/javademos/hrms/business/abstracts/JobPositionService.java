package javademos.hrms.business.abstracts;

import java.util.List;

import javademos.hrms.business.requests.jobPosition.JobPositionRequest;
import javademos.hrms.core.results.DataResult;
import javademos.hrms.entities.JobPosition;

public interface JobPositionService {
	
	DataResult<JobPosition> getById(int id);
	DataResult<List<JobPosition>> getList();
	DataResult<Integer> create(JobPositionRequest request);
	DataResult<Integer> update(int id, JobPositionRequest request);
	DataResult<Integer> delete(int id);
}
