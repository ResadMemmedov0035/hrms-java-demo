package javademos.hrms.webApi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javademos.hrms.business.abstracts.JobPositionService;
import javademos.hrms.business.requests.jobPosition.JobPositionRequest;
import javademos.hrms.core.web.ApiControllerBase;
import javademos.hrms.webApi.constants.ControllerPaths;

@RestController
@RequestMapping(ControllerPaths.jobPositions)
public class JobPositionsController extends ApiControllerBase {

	private JobPositionService jobService;

	@Autowired
	public JobPositionsController(JobPositionService jobService) {
		this.jobService = jobService;
	}

	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<?> getById(@PathVariable int id) {		
		return resultToResponse(this.jobService.getById(id));
	}

	@GetMapping
	@ResponseBody
	public ResponseEntity<?> getList() {		
		return resultToResponse(this.jobService.getList());
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<?> create(@Valid @RequestBody JobPositionRequest request) {
		return resultToResponse(this.jobService.create(request));
	}

	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity<?> update(@PathVariable int id, @Valid @RequestBody JobPositionRequest request) {		
		return resultToResponse(this.jobService.update(id, request));
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<?> delete(@PathVariable int id) {
		return resultToResponse(this.jobService.delete(id));
	}
}
