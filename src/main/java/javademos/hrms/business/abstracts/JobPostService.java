package javademos.hrms.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import javademos.hrms.business.requests.jobPost.JobPostRequest;
import javademos.hrms.business.responses.JobPostListResponse;
import javademos.hrms.core.results.DataResult;
import javademos.hrms.core.results.Result;

// All getList methods return active and deadline not passed job posts. Except getAllList()
public interface JobPostService {
	
	DataResult<List<JobPostListResponse>> getList();
	DataResult<List<JobPostListResponse>> getOrderedListByDate(Direction direction);
	DataResult<List<JobPostListResponse>> getListByEmployerId(int employerId);
	DataResult<List<JobPostListResponse>> getAllList();
	DataResult<Integer> create(JobPostRequest request);
	DataResult<Integer> update(int id, JobPostRequest request);
	DataResult<Integer> delete(int id);
	Result setActiveById(int id, boolean isActive);
}
