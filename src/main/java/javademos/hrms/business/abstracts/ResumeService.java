package javademos.hrms.business.abstracts;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import javademos.hrms.business.requests.resume.ResumeRequest;
import javademos.hrms.business.responses.ResumeListResponse;
import javademos.hrms.business.responses.ResumeResponse;
import javademos.hrms.core.results.DataResult;

public interface ResumeService {
	
	DataResult<ResumeResponse> getById(int id);
	DataResult<List<ResumeListResponse>> getList(int candidateId);
	DataResult<Integer> create(ResumeRequest request);
	DataResult<Integer> update(int id, ResumeRequest request);
	DataResult<Integer> delete(int id);
	DataResult<String> uploadImage(int id, MultipartFile file);
}
