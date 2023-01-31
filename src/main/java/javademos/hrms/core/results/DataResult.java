package javademos.hrms.core.results;

public class DataResult<T> extends Result {

	private T data;

	protected DataResult(T data, boolean isSuccess) {
		super(isSuccess);
		this.data = data;
	}

	protected DataResult(T data, boolean isSuccess, String message) {
		super(isSuccess, message);
		this.data = data;
	}
	
	public DataResult(Result result) {
		this(null, result.isSuccess(), result.getMessage());
	}

	public T getData() {
		return this.data;
	}
}
