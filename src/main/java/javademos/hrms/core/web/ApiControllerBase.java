package javademos.hrms.core.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javademos.hrms.core.results.DataResult;
import javademos.hrms.core.results.FailureDataResult;
import javademos.hrms.core.results.Result;

public class ApiControllerBase {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public DataResult<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException exception) {

		Map<String, String> errors = new HashMap<>();

		exception.getFieldErrors().forEach(error -> {

			errors.put(error.getField(), error.getDefaultMessage());
		});
		return new FailureDataResult<>(errors, "Validation error(s).");
	}

	protected ResponseEntity<?> resultToResponse(Result result) {

		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}

	protected ResponseEntity<?> resultToResponse(DataResult<?> result) {

		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}
}
