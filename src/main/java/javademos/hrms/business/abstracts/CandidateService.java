package javademos.hrms.business.abstracts;

import java.util.List;

import javademos.hrms.business.requests.candidate.CandidateRequest;
import javademos.hrms.core.results.DataResult;
import javademos.hrms.entities.Candidate;

public interface CandidateService {
	
	DataResult<Candidate> getById(int id);
	DataResult<List<Candidate>> getList();
	DataResult<Integer> create(CandidateRequest request);
	DataResult<Integer> update(int id, CandidateRequest request);
	DataResult<Integer> delete(int id);
}
