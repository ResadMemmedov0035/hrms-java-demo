package javademos.hrms.business.abstracts;

import java.util.List;

import javademos.hrms.business.requests.employer.EmployerRequest;
import javademos.hrms.core.results.DataResult;
import javademos.hrms.entities.Employer;

public interface EmployerService {

	DataResult<Employer> getById(int id);
	DataResult<List<Employer>> getList();
	DataResult<Integer> create(EmployerRequest request);
	DataResult<Integer> update(int id, EmployerRequest request);
	DataResult<Integer> delete(int id);
}
