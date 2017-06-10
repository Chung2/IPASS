package nl.hu.ipass.gameHistory.Service;

import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import nl.hu.ipass.gameHistory.model.Ronde;
import nl.hu.ipass.gameHistory.model.Spel;

@Path("/spellen")
public class SpelResource {
	
	@GET
	@Produces("application/json")
	public String allSpellen() throws SQLException{
		SpelService service = ServiceProvider.getSpelService();
		
		JsonArrayBuilder jab = Json.createArrayBuilder();
		JsonArrayBuilder rJab = Json.createArrayBuilder();
		
		for(Spel s : service.getSpellen()){
			JsonObjectBuilder job = Json.createObjectBuilder();
			JsonObjectBuilder rJob = Json.createObjectBuilder();
			job.add("id_spel", s.getId_spel());
			job.add("naam", s.getNaam());
			job.add("Instructies", s.getInstructies());
			for(Ronde r : s.getRondes()){
				rJob.add("id_ronde", r.getId_ronde());
				rJob.add("winnaar", r.getWinnaar().getNaam());
				rJob.add("tijdUren", r.getTijd().getHours());
				rJob.add("tijdMinuten", r.getTijd().getMinutes());
				rJob.add("tijdSecondes", r.getTijd().getSeconds());
				rJob.add("notities", r.getNotities());
				rJab.add(rJob);
				
			}
			job.add("Rondes", rJab);
			jab.add(job);
		}
		
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public String getSpel(@PathParam("id") int id) throws SQLException{
		
		Spel spel = null;
		SpelService service = ServiceProvider.getSpelService();
		Spel spelObj = service.getSpel(id);
		JsonArrayBuilder rJab = Json.createArrayBuilder();		
		
		JsonObjectBuilder job = Json.createObjectBuilder();
		JsonObjectBuilder rJob = Json.createObjectBuilder();
		job.add("id_spel", spelObj.getId_spel());
		job.add("naam", spelObj.getNaam());
		job.add("Instructies", spelObj.getInstructies());
		for(Ronde r : spelObj.getRondes()){
			rJob.add("id_ronde", r.getId_ronde());
			rJob.add("winnaar", r.getWinnaar().getNaam());
			rJob.add("tijdUren", r.getTijd().getHours());
			rJob.add("tijdMinuten", r.getTijd().getMinutes());
			rJob.add("tijdSecondes", r.getTijd().getSeconds());
			rJob.add("notities", r.getNotities());
			rJab.add(rJob);
			
		}
		job.add("Rondes", rJab);
		
		return job.build().toString();
	}
	

}
