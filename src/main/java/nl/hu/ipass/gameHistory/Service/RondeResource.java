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
import nl.hu.ipass.gameHistory.model.Speler;

@Path("/rondes")
public class RondeResource {
	
	@SuppressWarnings("deprecation")
	@GET
	@Produces("application/json")
	public String alleRondes() throws SQLException{
		RondeService service = ServiceProvider.getRondeService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		JsonArrayBuilder sJab = Json.createArrayBuilder();
		
		for(Ronde r: service.getAlleRonde()){
			JsonObjectBuilder job = Json.createObjectBuilder();
			JsonObjectBuilder sJob = Json.createObjectBuilder();

			job.add("id_ronde", r.getId_ronde());
			job.add("spel", r.getSpel().getNaam());
			job.add("naam", r.getNaam());
			job.add("tijdUren", r.getTijd().getHours());
			job.add("tijdMinuten", r.getTijd().getMinutes());
			job.add("tijdSecondes", r.getTijd().getSeconds());
			job.add("winnaar", r.getWinnaar().getNaam());
			job.add("notities", r.getNotities());
			
			for (Speler s : r.getDeelnemers()){
				sJob.add("id", s.getId_speler());
				sJob.add("naam", s.getNaam());
				
				sJab.add(sJob);
			}
			
			job.add("Spelers", sJab);
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
		
	}

	@GET
	@Path("{id}")
	@Produces("application/json")
	public String findRonde(@PathParam("id") int id) throws SQLException{
		RondeService service = ServiceProvider.getRondeService();
		Ronde rondeObj = service.getRondeById(id);
		
		JsonObjectBuilder job = Json.createObjectBuilder();
		
		JsonArrayBuilder sJab = Json.createArrayBuilder();
		
		JsonObjectBuilder sJob = Json.createObjectBuilder();
		
		job.add("id_ronde", rondeObj.getId_ronde());
		job.add("spel", rondeObj.getSpel().getNaam());
		job.add("naam", rondeObj.getNaam());
		job.add("tijdUren", rondeObj.getTijd().getHours());
		job.add("tijdMinuten", rondeObj.getTijd().getMinutes());
		job.add("tijdSecondes", rondeObj.getTijd().getSeconds());
		job.add("winnaar", rondeObj.getWinnaar().getNaam());
		job.add("notities", rondeObj.getNotities());
		
		for (Speler s : rondeObj.getDeelnemers()){
			sJob.add("id", s.getId_speler());
			sJob.add("naam", s.getNaam());
			
			sJab.add(sJob);
		}
		job.add("Spelers", sJab);		
		
		return job.build().toString();
		
	}

}
