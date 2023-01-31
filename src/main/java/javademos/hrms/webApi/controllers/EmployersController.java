package javademos.hrms.webApi.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javademos.hrms.business.abstracts.EmployerService;
import javademos.hrms.business.requests.employer.EmployerRequest;
import javademos.hrms.core.results.DataResult;
import javademos.hrms.core.web.ApiControllerBase;
import javademos.hrms.webApi.constants.ControllerPaths;

@RestController
@RequestMapping(ControllerPaths.employers)
public class EmployersController extends ApiControllerBase {

	private EmployerService employerService;

	@Autowired
	public EmployersController(EmployerService employerService) {
		this.employerService = employerService;
	}

	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<?> getById(@PathVariable int id) {

		return ResponseEntity.ok(this.employerService.getById(id));
	}

	@GetMapping
	@ResponseBody
	public ResponseEntity<?> getList() {

		return ResponseEntity.ok(this.employerService.getList());
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<?> create(@Valid @RequestBody EmployerRequest request) {

		DataResult<Integer> result = this.employerService.create(request);
		
		if(result.isSuccess()) {
			URI uri = URI.create("/api/employers/" + result.getData());
			return ResponseEntity.created(uri).body(result);			
		}
		return ResponseEntity.badRequest().body(result);
	}
}
