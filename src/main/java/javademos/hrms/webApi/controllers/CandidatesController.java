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

import javademos.hrms.business.abstracts.CandidateService;
import javademos.hrms.business.requests.candidate.CandidateRequest;
import javademos.hrms.core.results.DataResult;
import javademos.hrms.core.web.ApiControllerBase;
import javademos.hrms.webApi.constants.ControllerPaths;

@RestController
@RequestMapping(ControllerPaths.candidates)
public class CandidatesController extends ApiControllerBase {

	private CandidateService candidateService;

	@Autowired
	public CandidatesController(CandidateService candidateService) {
		this.candidateService = candidateService;
	}

	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<?> getById(@PathVariable int id) {
		return ResponseEntity.ok(this.candidateService.getById(id));
	}

	@GetMapping
	@ResponseBody
	public ResponseEntity<?> getList() {
		return ResponseEntity.ok(this.candidateService.getList());
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<?> create(@Valid @RequestBody CandidateRequest request) {
				
		DataResult<Integer> result = this.candidateService.create(request);
		
		if (result.isSuccess()) {
			URI uri = URI.create("/api/candidates/" + result.getData());
			return ResponseEntity.created(uri).body(result);
		}
		return ResponseEntity.badRequest().body(result);
	}
}
