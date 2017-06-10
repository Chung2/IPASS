package nl.hu.ipass.gameHistory.model;

import java.sql.Timestamp;
import java.sql.Array;
import java.util.ArrayList;

public class Ronde {
	
	private int id_ronde;
	private Spel spel;
	private ArrayList<Speler> deelnemers;
	private Timestamp tijd;
	private Speler winnaar;
	private String notities;
	
	public Ronde(int id_ronde, Spel spel, ArrayList<Speler> deelnemers, Timestamp tijd, Speler winnaar,String notities) {
		super();
		this.id_ronde = id_ronde;
		this.spel = spel;
		this.deelnemers = deelnemers;
		this.tijd = tijd;
		this.winnaar = winnaar;
		this.notities = notities;
	}

	public int getId_ronde() {
		return id_ronde;
	}

	public void setId_ronde(int id_ronde) {
		this.id_ronde = id_ronde;
	}	

	public Spel getSpel() {
		return spel;
	}

	public void setSpel(Spel spel) {
		this.spel = spel;
	}

	public ArrayList<Speler> getDeelnemers() {
		return deelnemers;
	}

	public void setDeelnemers(ArrayList<Speler> deelnemers) {
		this.deelnemers = deelnemers;
	}

	public Timestamp getTijd() {
		return tijd;
	}

	public void setTijd(Timestamp tijd) {
		this.tijd = tijd;
	}

	public Speler getWinnaar() {
		return winnaar;
	}

	public void setWinnaar(Speler winnaar) {
		this.winnaar = winnaar;
	}

	public String getNotities() {
		return notities;
	}

	public void setNotities(String notities) {
		this.notities = notities;
	}

	@Override
	public String toString() {
		return "Ronde [id_ronde=" + id_ronde + ", spel=" + spel + ", deelnemers=" + deelnemers
				+ ", tijd=" + tijd + ", winnaar=" + winnaar + ", notities=" + notities + "]";
	}
	
}
