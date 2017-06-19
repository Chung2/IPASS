package nl.hu.ipass.gameHistory.Service;

import java.security.Key;
import java.sql.SQLException;
import java.util.Calendar;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import nl.hu.ipass.gameHistory.persistence.SpelerDAO;

@Path("/authentication")
public class AuthenticationResource {
	final static public Key key = MacProvider.generateKey();
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response authenticateUser(@FormParam("naam") String naam, @FormParam("wachtwoord") String wachtwoord) throws SQLException{
		
		try{
			SpelerDAO spelerdao = new SpelerDAO();
			String role = spelerdao.findRolForNaamAndWachtwoord(naam, wachtwoord);

			if(role == null){throw new IllegalArgumentException("No user Found!");}
			
			Calendar expiration = Calendar.getInstance();
			expiration.add(Calendar.HOUR, 2);
			
			String token = Jwts.builder()
					.setSubject(naam)
					.claim("role",role)
					.setExpiration(expiration.getTime())
					.signWith(SignatureAlgorithm.HS512,key)
					.compact();
			return Response.ok(token).build();
		}catch(JwtException | IllegalArgumentException e){
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}
}
