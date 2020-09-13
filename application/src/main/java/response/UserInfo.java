package response;

public class UserInfo extends ApiResponse {
	private String username;
	private String token;
	private static UserInfo userInfo = null;

	private UserInfo() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public static UserInfo getInstance() {
		if (null == userInfo) {
			userInfo = new UserInfo();
		}
		return userInfo;
	}

}
