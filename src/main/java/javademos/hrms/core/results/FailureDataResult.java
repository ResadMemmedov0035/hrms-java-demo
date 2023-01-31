package javademos.hrms.core.results;

public class FailureDataResult<T> extends DataResult<T> {

	public FailureDataResult(T data) {
		super(data, false);
	}

	public FailureDataResult(T data, String message) {
		super(data, false, message);
	}
}