package javademos.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javademos.hrms.business.abstracts.StaffService;
import javademos.hrms.core.results.DataResult;
import javademos.hrms.core.service.ServiceBase;
import javademos.hrms.dataAccess.abstracts.StaffRepository;
import javademos.hrms.entities.Staff;

@Service
public class StaffManager extends ServiceBase implements StaffService {

	private StaffRepository staffRepository;
	
	@Autowired
	public StaffManager(StaffRepository staffRepository) {
		this.staffRepository = staffRepository;
	}
	
	@Override
	public DataResult<Staff> getById(int id) {

		return success(this.staffRepository.getById(id));
	}

}
