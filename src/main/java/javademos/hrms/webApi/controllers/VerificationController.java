package javademos.hrms.webApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javademos.hrms.business.abstracts.EmailVerificationService;
import javademos.hrms.core.results.Result;
import javademos.hrms.core.web.ApiControllerBase;
import javademos.hrms.webApi.constants.ControllerPaths;

@RestController
@RequestMapping(ControllerPaths.verification)
public class VerificationController extends ApiControllerBase {

	private EmailVerificationService emailVerificationService;

	@Autowired
	public VerificationController(EmailVerificationService emailVerificationService) {
		this.emailVerificationService = emailVerificationService;
	}
	
	@PostMapping("/byemail")
	@ResponseBody
	public ResponseEntity<?> verifyByEmail(String email, String code) {
		
		Result result = this.emailVerificationService.verifyUserByEmail(email, code);
		return resultToResponse(result);
	}
}
