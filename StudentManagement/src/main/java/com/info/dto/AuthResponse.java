package com.info.dto;

public class AuthResponse {
	private String accessToken;
    private String tokenType = "Bearer";
    private String email;
   
	public AuthResponse(String accessToken, String email) {
		super();
		this.accessToken = accessToken;
		this.email = email;
		
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
