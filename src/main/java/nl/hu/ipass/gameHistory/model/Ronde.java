package nl.hu.ipass.gameHistory.model;

import java.sql.Timestamp;
import java.sql.Array;
import java.sql.Time;
import java.util.ArrayList;

public class Ronde {
	
	private int id_ronde;
	private String rondenaam;
	
	private Spel spel;
	private ArrayList<Speler> deelnemers;
	//private Timestamp tijd;
	private Speler winnaar;
	private String notities;
	private Time tijd;

	public Ronde(int id_ronde, Spel spel,String naam, String naam1 ,String naam2,String naam3, Time tijd,String notities){
		
	}
	
	public Ronde(int id_ronde, Spel spel,String naam, ArrayList<Speler> deelnemers, Time tijd, Speler winnaar,String notities) {
		super();
		this.rondenaam = naam;
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

	public String getNaam() {
		return rondenaam;
	}

	public void setNaam(String naam) {
		this.rondenaam = naam;
	}

	public ArrayList<Speler> getDeelnemers() {
		return deelnemers;
	}

	public void setDeelnemers(ArrayList<Speler> deelnemers) {
		this.deelnemers = deelnemers;
	}

	public Time getTijd() {
		return tijd;
	}

	public void setTijd(Time tijd) {
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
