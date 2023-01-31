package javademos.hrms.core.results;

public class Result {
	
	private boolean isSuccess;
	private String message;

	protected Result(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	protected Result(boolean isSuccess, String message) {
		this(isSuccess);
		this.message = message;
	}
	
	public Result(DataResult<?> result) {
		this(result.isSuccess(), result.getMessage());
	}
	
	public boolean isSuccess() {
		return this.isSuccess;
	}
	
	public String getMessage() {
		return this.message;
	}
}
