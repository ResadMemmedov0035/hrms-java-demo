package javademos.hrms.webApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javademos.hrms.business.abstracts.EmployerConfirmService;
import javademos.hrms.core.results.Result;
import javademos.hrms.core.web.ApiControllerBase;
import javademos.hrms.webApi.constants.ControllerPaths;

@RestController
@RequestMapping(ControllerPaths.confirm)
public class ConfirmController extends ApiControllerBase {

	private EmployerConfirmService employerConfirmService;
	
	@Autowired
	public ConfirmController(EmployerConfirmService employerConfirmService) {
		this.employerConfirmService = employerConfirmService;
	}

	@PostMapping("/employer")
	@ResponseBody
	public ResponseEntity<?> confirmEmployer(int employerId, int staffId) {
		
		Result result = this.employerConfirmService.confirmEmployerById(employerId, staffId);
		return resultToResponse(result);
	}
}
