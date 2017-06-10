package nl.hu.ipass.gameHistory.model;

public class Test {
	
	private int id;
	private String naam;
	
	public Test(int id, String naam) {
		super();
		this.id = id;
		this.naam = naam;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	@Override
	public String toString() {
		return "test [id=" + id + ", naam=" + naam + "]";
	}

}
