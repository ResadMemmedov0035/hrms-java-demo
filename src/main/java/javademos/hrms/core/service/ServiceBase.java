package javademos.hrms.core.service;

import javademos.hrms.core.results.*;

public abstract class ServiceBase {
	
	protected SuccessResult success() {
		return new SuccessResult();
	}
	
	protected SuccessResult success(String message) {
		return new SuccessResult(message);
	}
	
	protected <T> SuccessDataResult<T> success(T data){
		return new SuccessDataResult<T>(data);
	}

	protected <T> SuccessDataResult <T> success(T data, String message) {
		return new SuccessDataResult<T>(data, message);
	}
	
	protected FailureResult failure() {
		return new FailureResult();
	}
	
	protected FailureResult failure(String message) {
		return new FailureResult(message);
	}

	protected <T> FailureDataResult<T> failure(T data){
		return new FailureDataResult<T>(data);
	}

	protected <T> FailureDataResult <T> failure(T data, String message) {
		return new FailureDataResult<T>(data, message);
	}
	
	protected <T> DataResult<T> dataResult(Result result) {
		return new DataResult<T>(result);
	}
	
	protected Result result(DataResult<?> dataResult) {
		return new Result(dataResult);
	}
}
