package javademos.hrms.business.concretes;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javademos.hrms.business.abstracts.EmailVerificationService;
import javademos.hrms.business.abstracts.EmployerService;
import javademos.hrms.business.requests.employer.EmployerRequest;
import javademos.hrms.core.results.DataResult;
import javademos.hrms.core.results.Result;
import javademos.hrms.core.rules.RuleEvaluater;
import javademos.hrms.core.service.ServiceBase;
import javademos.hrms.dataAccess.abstracts.EmployerRepository;
import javademos.hrms.entities.Employer;

@Service
public class EmployerManager extends ServiceBase implements EmployerService {

	private EmployerRepository employerRepository;
	private EmailVerificationService emailVerificationService;
	private ModelMapper mapper;
	
	@Autowired
	public EmployerManager(EmployerRepository employerRepository, 
			EmailVerificationService emailVerificationService,
			ModelMapper mapper) {

		this.employerRepository = employerRepository;
		this.emailVerificationService = emailVerificationService;
		this.mapper = mapper;
	}

	@Override
	public DataResult<Employer> getById(int id) {

		Employer item = this.employerRepository.getById(id);
		return success(item);
	}

	@Override
	public DataResult<List<Employer>> getList() {

		List<Employer> list = this.employerRepository.findAll();
		return success(list);
	}

	@Override
	public DataResult<Integer> create(EmployerRequest request) {

		Employer employer = this.mapper.map(request, Employer.class);
		
		Result ruleResult = RuleEvaluater.of(employer)
				.bindRule(this::emailMustNotBeExists)
				.evaluate();
		
		if(!ruleResult.isSuccess()) return dataResult(ruleResult);
		
		Result emailResult = this.emailVerificationService.sendVerificationCodeToUser(employer);
		
		if (!emailResult.isSuccess()) return dataResult(ruleResult);
		
		Employer created = this.employerRepository.save(employer);
		return success(created.getId(), emailResult.getMessage());
	}

	@Override
	public DataResult<Integer> update(int id, EmployerRequest request) {

		Employer employer = this.employerRepository.getById(id);
		this.mapper.map(request, employer);
		
		Result ruleResult = RuleEvaluater.of(employer)
				.bindRule(this::emailMustNotBeExists)
				.evaluate();
		
		if(!ruleResult.isSuccess()) return dataResult(ruleResult);
		
		Employer updated = this.employerRepository.save(employer);		
		return success(updated.getId(), "Employer updated successfully.");
	}

	@Override
	public DataResult<Integer> delete(int id) {

		this.employerRepository.deleteById(id);
		return success(id, "Employer deleted successfully.");
	}
	
	private Result emailMustNotBeExists(Employer employer) {
		
		if (this.employerRepository.existsByEmail(employer.getEmail())) {
			return failure("This email already exists.");
		}
		return success();
	}
}
