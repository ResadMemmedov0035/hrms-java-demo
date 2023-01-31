package javademos.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javademos.hrms.business.abstracts.EmployerConfirmService;
import javademos.hrms.business.abstracts.EmployerService;
import javademos.hrms.business.abstracts.StaffService;
import javademos.hrms.core.results.Result;
import javademos.hrms.core.service.ServiceBase;
import javademos.hrms.dataAccess.abstracts.EmployerConfirmRepository;
import javademos.hrms.entities.EmployerConfirm;

@Service
public class EmployerConfirmManager extends ServiceBase implements EmployerConfirmService {

	private EmployerConfirmRepository employerConfirmRepository;
	private EmployerService employerService;
	private StaffService staffService;
	
	@Autowired
	public EmployerConfirmManager(EmployerConfirmRepository employerConfirmRepository,
			EmployerService employerService,
			StaffService staffService) {
		
		this.employerConfirmRepository = employerConfirmRepository;
		this.employerService = employerService;
		this.staffService = staffService;
	}

	@Override
	public Result confirmEmployerById(int employerId, int staffId) {

		EmployerConfirm confirm = this.employerConfirmRepository.getByEmployerId(employerId);

		if (confirm != null && confirm.isConfirmed())
			return failure("This employer already confirmed.");
		
		if (confirm != null && !confirm.isConfirmed()) {
			confirm.setConfirmed(true);
			this.employerConfirmRepository.save(confirm);
			return success("Employee confirmed.");
		}		
		
		var employer = this.employerService.getById(employerId).getData();
		var staff = this.staffService.getById(staffId).getData();

		this.employerConfirmRepository.save(new EmployerConfirm(employer, staff));
		
		return success("Employer confirmed.");
	}

}
