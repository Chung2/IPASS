package nl.hu.ipass.gameHistory.Service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import nl.hu.ipass.gameHistory.model.Ronde;
import nl.hu.ipass.gameHistory.model.Spel;
import nl.hu.ipass.gameHistory.model.Speler;

@Path("/rondes")
public class RondeResource {

	@SuppressWarnings("deprecation")
	@GET
	@RolesAllowed({"admin","user"})
	@Produces("application/json")
	public String alleRondes() throws SQLException {
		RondeService service = ServiceProvider.getRondeService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		JsonArrayBuilder sJab = Json.createArrayBuilder();

		for (Ronde r : service.getAlleRonde()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			JsonObjectBuilder sJob = Json.createObjectBuilder();

			job.add("id_ronde", r.getId_ronde());
			job.add("id_spel", r.getSpel().getId_spel());
			job.add("spel", r.getSpel().getNaam());
			job.add("naam", r.getNaam());
			job.add("tijdUren", r.getTijd().getHours());
			job.add("tijdMinuten", r.getTijd().getMinutes());
			job.add("tijdSecondes", r.getTijd().getSeconds());
			job.add("winnaar", r.getWinnaar().getNaam());
			job.add("notities", r.getNotities());

			for (Speler s : r.getDeelnemers()) {
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
	@RolesAllowed({"admin","user"})
	@Produces("application/json")
	public String findRonde(@PathParam("id") int id) throws SQLException {
		RondeService service = ServiceProvider.getRondeService();
		Ronde rondeObj = service.getRondeById(id);

		JsonObjectBuilder job = Json.createObjectBuilder();

		JsonArrayBuilder sJab = Json.createArrayBuilder();

		JsonObjectBuilder sJob = Json.createObjectBuilder();

		job.add("id_ronde", rondeObj.getId_ronde());
		job.add("id_spel", rondeObj.getSpel().getId_spel());
		job.add("spel", rondeObj.getSpel().getNaam());
		job.add("naam", rondeObj.getNaam());
		job.add("tijdUren", rondeObj.getTijd().getHours());
		job.add("tijdMinuten", rondeObj.getTijd().getMinutes());
		job.add("tijdSecondes", rondeObj.getTijd().getSeconds());
		job.add("winnaar", rondeObj.getWinnaar().getNaam());
		job.add("notities", rondeObj.getNotities());

		for (Speler s : rondeObj.getDeelnemers()) {
			sJob.add("id", s.getId_speler());
			sJob.add("naam", s.getNaam());

			sJab.add(sJob);
		}
		job.add("Spelers", sJab);

		return job.build().toString();

	}

	@GET
	@Path("/laatsteronde")
	@RolesAllowed({"admin","user"})
	@Produces("application/json")
	public String laatsteRonde() throws SQLException {

		RondeService service = ServiceProvider.getRondeService();
		Ronde rondeObj = service.getLaatsteRonde();

		JsonObjectBuilder job = Json.createObjectBuilder();

		JsonArrayBuilder sJab = Json.createArrayBuilder();

		JsonObjectBuilder sJob = Json.createObjectBuilder();

		job.add("id_ronde", rondeObj.getId_ronde());
		job.add("id_spel", rondeObj.getSpel().getId_spel());
		job.add("spel", rondeObj.getSpel().getNaam());
		job.add("naam", rondeObj.getNaam());
		job.add("tijdUren", rondeObj.getTijd().getHours());
		job.add("tijdMinuten", rondeObj.getTijd().getMinutes());
		job.add("tijdSecondes", rondeObj.getTijd().getSeconds());
		job.add("winnaar", rondeObj.getWinnaar().getNaam());
		job.add("notities", rondeObj.getNotities());

		for (Speler s : rondeObj.getDeelnemers()) {
			sJob.add("id", s.getId_speler());
			sJob.add("naam", s.getNaam());

			sJab.add(sJob);
		}
		job.add("Spelers", sJab);

		return job.build().toString();

	}

	@POST
	@Path("/newround")
	@RolesAllowed({"admin","user"})
	@Produces("application/json")
	public Response addRonde(InputStream is) throws SQLException, IOException {

		ArrayList<String> deelnemersNamen = new ArrayList<String>();

		RondeService service = ServiceProvider.getRondeService();
		JsonObject object = Json.createReader(is).readObject();

		String naam = object.getString("naam");
		String spel = object.getString("spel");
		String spelernaam1 = object.getString("speler1");
		String spelernaam2 = object.getString("speler2");
		String spelernaam3 = object.getString("speler3");
		int spelNummerFix = Integer.parseInt(spel);
		deelnemersNamen.add(spelernaam1);
		deelnemersNamen.add(spelernaam2);
		deelnemersNamen.add(spelernaam3);
		// String tijd = "00:00:00";
		// DateFormat formatter = new SimpleDateFormat("hh:mm:ss");
		Time time = new Time(00, 00, 00);

		Ronde nieuwRonde = new Ronde(999, naam, deelnemersNamen, spelNummerFix, 5, "nog geen notities", time);
		service.addRonde(nieuwRonde);

		return Response.ok().build();
	}

	@POST
	@Path("/updateendtime")
	@RolesAllowed({"admin","user"})
	@Produces("application/json")
	public Response updateTime(InputStream is) throws SQLException, IOException {

		RondeService service = ServiceProvider.getRondeService();
		JsonObject object = Json.createReader(is).readObject();

		String uren = object.getString("uren");
		String minuten = object.getString("minuten");
		String secondes = object.getString("secondes");

		int urenFix = Integer.parseInt(uren);
		int minutenFix = Integer.parseInt(minuten);
		int secondesFix = Integer.parseInt(secondes);
		Time eindTijd = new Time(urenFix, minutenFix, secondesFix);
		Ronde laatsteronde = service.getLaatsteRonde();
		service.updateEindTijd(laatsteronde);
		return Response.ok().build();
	}

	@POST
	@RolesAllowed({"admin","user"})
	@Path("/updatepostround")
	@Produces("application/json")
	public Response updatePostRound(InputStream is) throws SQLException, IOException {
		RondeService service = ServiceProvider.getRondeService();
		JsonObject object = Json.createReader(is).readObject();

		String notities = object.getString("notities");
		String winnaar = object.getString("winnaar");

		int winnaarFix = Integer.parseInt(winnaar);
		Ronde laatsteronde = service.getLaatsteRonde();
		laatsteronde.setNotities(notities);
		laatsteronde.setWinnaarId(winnaarFix);
		service.updatePostRound(laatsteronde);
		return Response.ok().build();
	}

}
