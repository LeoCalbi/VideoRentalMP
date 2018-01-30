package mp.videorental;

import java.io.Serializable;

public class Credentials implements Serializable {
	
	private static final long serialVersionUID = 1912997897378986533L;
	private String username;
	private String password;
	
	public Credentials(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
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
