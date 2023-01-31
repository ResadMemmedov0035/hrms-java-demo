package javademos.hrms.business.concretes;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javademos.hrms.business.abstracts.CandidateService;
import javademos.hrms.business.abstracts.EmailVerificationService;
import javademos.hrms.business.requests.candidate.CandidateRequest;
import javademos.hrms.core.results.DataResult;
import javademos.hrms.core.results.Result;
import javademos.hrms.core.rules.RuleEvaluater;
import javademos.hrms.core.service.ServiceBase;
import javademos.hrms.dataAccess.abstracts.CandidateRepository;
import javademos.hrms.entities.Candidate;

@Service
public class CandidateManager extends ServiceBase implements CandidateService {

	private CandidateRepository candidateRepository;
	private EmailVerificationService emailVerificationService;
	private ModelMapper mapper;

	@Autowired
	public CandidateManager(CandidateRepository candidateRepository, EmailVerificationService emailVerificationService,
			ModelMapper mapper) {

		this.candidateRepository = candidateRepository;
		this.emailVerificationService = emailVerificationService;
		this.mapper = mapper;
	}

	@Override
	public DataResult<Candidate> getById(int id) {

		Candidate item = this.candidateRepository.getById(id);
		return success(item);
	}

	@Override
	public DataResult<List<Candidate>> getList() {

		List<Candidate> list = this.candidateRepository.findAll();
		return success(list);
	}

	@Override
	public DataResult<Integer> create(CandidateRequest request) {

		Candidate candidate = this.mapper.map(request, Candidate.class);

		Result ruleResult = RuleEvaluater.of(candidate)
				.bindRule(this::emailMustNotBeExists)
				.evaluate();

		if (!ruleResult.isSuccess()) return dataResult(ruleResult);
		
		Result emailResult = this.emailVerificationService.sendVerificationCodeToUser(candidate);
		
		if (!emailResult.isSuccess()) return dataResult(emailResult);

		Candidate created = this.candidateRepository.save(candidate);
		return success(created.getId(), emailResult.getMessage());
	}

	@Override
	public DataResult<Integer> update(int id, CandidateRequest request) {
		
		Candidate candidate = this.candidateRepository.getById(id);
		this.mapper.map(request, candidate);
		
		Result ruleResult = RuleEvaluater.of(candidate)
				.bindRule(this::emailMustNotBeExists)
				.evaluate();
		
		if(!ruleResult.isSuccess()) return dataResult(ruleResult);
		
		Candidate updated = this.candidateRepository.save(candidate);		
		return success(updated.getId(), "Candidate updated successfully.");
	}

	@Override
	public DataResult<Integer> delete(int id) {

		this.candidateRepository.deleteById(id);
		return success(id, "Candidate deleted successfully.");
	}
	
	private Result emailMustNotBeExists(Candidate candidate) {

		if (this.candidateRepository.existsByEmail(candidate.getEmail())) {
			return failure("This email already exists.");
		}
		return success();
	}
}
