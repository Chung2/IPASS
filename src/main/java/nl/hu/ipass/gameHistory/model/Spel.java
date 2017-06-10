package nl.hu.ipass.gameHistory.model;

import java.util.ArrayList;

public class Spel {
	
	private int id_spel;
	private String naam;
	private String instructies;
	private ArrayList<Ronde> rondes;

	public Spel(int id_spel, String naam, String instructies) {
		super();
		this.id_spel = id_spel;
		this.naam = naam;
		this.instructies = instructies;
	}

	public Spel(int id_spel, String naam, String instructies, ArrayList<Ronde> rondes) {
		this.id_spel = id_spel;
		this.naam = naam;
		this.instructies = instructies;
		this.rondes = rondes;
	}

	public int getId_spel() {
		return id_spel;
	}

	public void setId_spel(int id_spel) {
		this.id_spel = id_spel;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getInstructies() {
		return instructies;
	}

	public void setInstructies(String instructies) {
		this.instructies = instructies;
	}

	public ArrayList<Ronde> getRondes() {
		return rondes;
	}

	public void setRondes(ArrayList<Ronde> rondes) {
		this.rondes = rondes;
	}

	@Override
	public String toString() {
		return "Spel [id_spel=" + id_spel + ", naam=" + naam + ", instructies=" + instructies + "]";
	}
}
