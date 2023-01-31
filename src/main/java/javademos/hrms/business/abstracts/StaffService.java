package javademos.hrms.business.abstracts;

import javademos.hrms.core.results.DataResult;
import javademos.hrms.entities.Staff;

public interface StaffService {
	
	DataResult<Staff> getById(int id);
}
