package nl.hu.ipass.gameHistory.Service;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

public class MySecurityContext implements SecurityContext {

	private String naam;
	private String rol;
	private boolean isSecure;

	public MySecurityContext(String naam, String rol, boolean isSecure) {
		this.naam = naam;
		this.rol = rol;
	}

	public Principal getUserPrincipal() {
		return new Principal() {
			public String getName() {
				return naam;
			}
		};
	}

	public boolean isUserInRole(String rol) {
		return rol.equals(this.rol);
	}

	public boolean isSecure() {
		return isSecure;
	}

	public String getAuthenticationScheme() {
		return "Bearer";
	}

}
