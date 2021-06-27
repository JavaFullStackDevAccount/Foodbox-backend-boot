package com.foodbox.models;

public class AuthenticationResponseModel {

	private String token;

	private boolean admin;

	private String email;

	private long srno;

	public AuthenticationResponseModel(String token, boolean admin, String email, long srno) {
		super();
		this.token = token;
		this.admin = admin;
		this.email = email;
		this.srno = srno;
	}

	public AuthenticationResponseModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getSrno() {
		return srno;
	}

	public void setSrno(long srno) {
		this.srno = srno;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
