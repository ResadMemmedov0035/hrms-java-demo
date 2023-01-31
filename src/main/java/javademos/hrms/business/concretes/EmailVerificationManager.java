package javademos.hrms.business.concretes;

import java.util.Random;

import org.springframework.stereotype.Service;

import javademos.hrms.business.abstracts.EmailVerificationService;
import javademos.hrms.business.adapters.emailService.EmailService;
import javademos.hrms.core.results.Result;
import javademos.hrms.core.rules.RuleEvaluater;
import javademos.hrms.core.service.ServiceBase;
import javademos.hrms.dataAccess.abstracts.VerificationCodeRepository;
import javademos.hrms.dataAccess.abstracts.VerificationRepository;
import javademos.hrms.entities.User;
import javademos.hrms.entities.Verification;
import javademos.hrms.entities.VerificationCode;

@Service
public class EmailVerificationManager extends ServiceBase implements EmailVerificationService {

	private VerificationCodeRepository verificationCodeRepository;
	private VerificationRepository verificationRepository;
	private EmailService emailService;

	public EmailVerificationManager(VerificationCodeRepository verificationCodeRepository,
			VerificationRepository verificationRepository,
			EmailService emailService) {

		this.verificationCodeRepository = verificationCodeRepository;
		this.verificationRepository = verificationRepository;
		this.emailService = emailService;		
	}

	@Override
	public Result sendVerificationCodeToUser(User user) {

		String code = generateCode();
		this.verificationCodeRepository.save(new VerificationCode(code, user));
		
		this.emailService.send(user.getEmail(), "Your verification code", code);
		
		return success("Code sent to the email. Please, check your inbox for url.");
	}

	@Override
	public Result verifyUserByEmail(String email, String code) {
		
		var verification = this.verificationRepository.getByUserEmail(email);
		var verificationCode = this.verificationCodeRepository.getLatestByUserEmail(email);
		
		Result ruleResult = RuleEvaluater.of(null)
				.bindRule(x -> userMustNotBeVerified(verification))
				.bindRule(x -> verificationCodeMustBeMatch(verificationCode, code))
				.evaluate();
		
		if(!ruleResult.isSuccess()) return ruleResult;		
		
		this.verificationRepository.save(new Verification(verificationCode.getUser()));
		return success("User verified.");
	}
	
	private Result userMustNotBeVerified(Verification verification) {
		
		if (verification != null && verification.isVerified()) {
			return failure("This user already verified.");
		}
		return success();
	}

	private Result verificationCodeMustBeMatch(VerificationCode verificationCode, String code) {
		
		if (verificationCode == null || !verificationCode.getCode().equals(code)) {
			return failure("The given code does not match.");
		}
		return success();
	}
	
	private String generateCode() {
		
		var random = new Random();
		var sb = new StringBuilder(10);
		char[] chars = "qwertyuiopasdfghjklzxcvbnm1234567890".toCharArray();
		
		for (int i = 0; i < 10; i++) {
			sb.append(chars[random.nextInt(0, chars.length)]);
		}
		return sb.toString();
	}
}
