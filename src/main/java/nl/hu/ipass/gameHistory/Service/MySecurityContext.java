package nl.hu.ipass.gameHistory.Service;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

public class MySecurityContext implements SecurityContext {

	private String naam;
	private String role;
	private boolean isSecure;

	public MySecurityContext(String naam, String role, boolean isSecure) {
		this.naam = naam;
		this.role = role;
	}

	public Principal getUserPrincipal() {
		return new Principal() {
			public String getName() {
				return naam;
			}
		};
	}

	public boolean isUserInRole(String role) {
		return role.equals(this.role);
	}

	public boolean isSecure() {
		return isSecure;
	}

	public String getAuthenticationScheme() {
		return "Bearer";
	}

}
