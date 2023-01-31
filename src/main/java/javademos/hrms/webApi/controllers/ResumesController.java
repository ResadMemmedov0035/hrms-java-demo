package javademos.hrms.webApi.controllers;

import java.io.IOException;

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
import org.springframework.web.multipart.MultipartFile;

import javademos.hrms.business.abstracts.ResumeService;
import javademos.hrms.business.requests.resume.ResumeRequest;
import javademos.hrms.core.web.ApiControllerBase;
import javademos.hrms.webApi.constants.ControllerPaths;

@RestController
@RequestMapping(ControllerPaths.resumes)
public class ResumesController extends ApiControllerBase {

	private ResumeService resumeService;

	@Autowired
	public ResumesController(ResumeService resumeService) {
		this.resumeService = resumeService;
	}

	@PostMapping("/image/upload/{id}")
	@ResponseBody
	public ResponseEntity<?> uploadImage(@PathVariable int id, MultipartFile file) throws IOException {
		
		var result = this.resumeService.uploadImage(id, file);
		
		if(result.isSuccess()) {			
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<?> create(@Valid @RequestBody ResumeRequest request) {
		return ResponseEntity.ok(this.resumeService.create(request));
	}

	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<?> getById(@PathVariable int id) {
		return ResponseEntity.ok(this.resumeService.getById(id));
	}

	@GetMapping("/candidate/{candidateId}")
	@ResponseBody
	public ResponseEntity<?> getListByCandidateId(@PathVariable int candidateId) {
		return ResponseEntity.ok(this.resumeService.getList(candidateId));
	}
}
