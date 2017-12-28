package mp.videorental;

public class Credentials {
	
	private String username;
	private String password;
	
	public Credentials(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Credentials) {
			Credentials c = (Credentials) obj;
			if(c.username.equals(username) && c.password.equals(password)) return true;
		}
		return false;
	}

}
