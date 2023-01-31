package javademos.hrms.business.abstracts;

import javademos.hrms.core.results.Result;

public interface EmployerConfirmService {

	Result confirmEmployerById(int employerId, int staffId);
}
