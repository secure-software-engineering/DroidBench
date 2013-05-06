package de.ecspride.data;

public class User {
	private final String username;
	private Password pwd;
	
	public User(String username, String password){
		this.username = username;
		this.pwd = new Password(password);
	}

	public Password getPwd() {
		return pwd;
	}

	public void setPwd(Password pwd) {
		this.pwd = pwd;
	}

	public String getUsername() {
		return username;
	}
}
