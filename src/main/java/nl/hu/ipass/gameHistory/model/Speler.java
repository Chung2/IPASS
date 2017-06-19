package nl.hu.ipass.gameHistory.model;

import java.util.ArrayList;

public class Speler {
	
	private int id_speler;
	private String naam;
	private String wachtwoord;
	private ArrayList<Integer> rondes;
	private String rol;
	
	public Speler(int id_speler, String naam, String wachtwoord, ArrayList<Integer> rondes, String rol) {
		super();
		this.id_speler = id_speler;
		this.naam = naam;
		this.wachtwoord = wachtwoord;
		this.rondes = rondes;
		this.rol = rol;
	}

	public int getId_speler() {
		return id_speler;
	}

	public void setId_speler(int id_speler) {
		this.id_speler = id_speler;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getWachtwoord() {
		return wachtwoord;
	}

	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}

	public ArrayList<Integer> getRondes() {
		return rondes;
	}

	public void setRondes(ArrayList<Integer> rondes) {
		this.rondes = rondes;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Speler [id_speler=" + id_speler + ", naam=" + naam + ", wachtwoord=" + wachtwoord + "]";
	}
	
}
