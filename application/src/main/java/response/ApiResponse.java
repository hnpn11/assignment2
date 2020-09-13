package response;

public class ApiResponse {
	private int status;
	private String responseString;

	public String getResponseString() {
		return responseString;
	}

	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}

	public ApiResponse(int status, String responseString) {
		super();
		this.status = status;
		this.responseString = responseString;
	}

	public ApiResponse(int status) {
		super();
		this.status = status;
	}

	public ApiResponse() {
		super();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
