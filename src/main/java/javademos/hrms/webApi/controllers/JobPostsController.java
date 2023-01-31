package javademos.hrms.webApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javademos.hrms.business.abstracts.JobPostService;
import javademos.hrms.core.web.ApiControllerBase;
import javademos.hrms.webApi.constants.ControllerPaths;

@RestController
@RequestMapping(ControllerPaths.jobPosts)
public class JobPostsController extends ApiControllerBase {
	
	private JobPostService jobPostService;

	@Autowired
	public JobPostsController(JobPostService jobPostService) {
		this.jobPostService = jobPostService;
	}
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<?> getList() {
		return ResponseEntity.ok(this.jobPostService.getList());
	}
	
	@GetMapping("/orderByDate")
	@ResponseBody
	public ResponseEntity<?> getOrderedListByDate(String dir) {
		return ResponseEntity.ok(this.jobPostService.getOrderedListByDate(Direction.fromString(dir)));
	}
	
	@GetMapping("/byEmployer/{id}")
	@ResponseBody
	public ResponseEntity<?> getListByEmployer(@PathVariable int id) {
		return ResponseEntity.ok(this.jobPostService.getListByEmployerId(id));
	}
	
	@PostMapping("/deactivate/{id}")
	@ResponseBody
	public ResponseEntity<?> deactivatePostById(@PathVariable int id) {
		return ResponseEntity.ok(this.jobPostService.setActiveById(id, false));
	}
}
