package nl.hu.ipass.gameHistory.Service;

import java.sql.SQLException;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import nl.hu.ipass.gameHistory.model.Test;
import nl.hu.ipass.gameHistory.model.Ronde;
import nl.hu.ipass.gameHistory.model.Speler;

@Path("/spelers")
public class SpelerResource {

	@GET
	@Produces("application/json")
	public String getSpelers() throws SQLException {

		SpelerService service = ServiceProvider.getSpelerService();

		JsonObjectBuilder rJob = Json.createObjectBuilder();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		JsonArrayBuilder rJab = Json.createArrayBuilder();

		for (Speler spelerObj : service.getAllSpelers()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("id_gebruiker", spelerObj.getId_speler());
			job.add("naam", spelerObj.getNaam());
			//job.add("wachtwoord", spelerObj.getWachtwoord());
			for (Integer r : spelerObj.getRondes()) {
				rJob.add("id_ronde", r);
				
				rJab.add(rJob);
			}
			job.add("rondes", rJab);
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("{id}")
	@Produces("application/json")
	public String spelerById(@PathParam("id") int id) throws SQLException {
		SpelerService service = ServiceProvider.getSpelerService();
		Speler spelerObj = service.findSpelerById(id);

		JsonObjectBuilder job = Json.createObjectBuilder();
		JsonArrayBuilder rJab = Json.createArrayBuilder();
		JsonObjectBuilder rJob = Json.createObjectBuilder();
		
		job.add("id_gebruiker", spelerObj.getId_speler());
		job.add("naam", spelerObj.getNaam());
		//job.add("wachtwoord", spelerObj.getWachtwoord());
		for (Integer r : spelerObj.getRondes()) {
			
			rJob.add("id_ronde", r);
			
			rJab.add(rJob);
		}
		job.add("rondes", rJab);
		return job.build().toString();

	}

	@GET
	@Path("/testdatabase")
	@Produces("application/json")

	public String getTest() throws SQLException {

		SpelerService service = ServiceProvider.getSpelerService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Test testObj : service.getTest()) {

			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("id_gebruiker", testObj.getId());
			job.add("naam", testObj.getNaam());

			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}
}
