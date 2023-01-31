package javademos.hrms.core.results;

public class FailureResult extends Result {

	public FailureResult() {
		super(false);
	}
	
	public FailureResult(String message) {
		super(false, message);
	}
}