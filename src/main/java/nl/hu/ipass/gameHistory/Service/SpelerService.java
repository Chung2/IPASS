package nl.hu.ipass.gameHistory.Service;

import java.sql.SQLException;
import java.util.ArrayList;

import nl.hu.ipass.gameHistory.model.Speler;
import nl.hu.ipass.gameHistory.model.Test;
import nl.hu.ipass.gameHistory.persistence.SpelerDAO;


public class SpelerService {
	
	SpelerDAO spelerdao = new SpelerDAO();
	
	public ArrayList<Speler> getAllSpelers() throws SQLException {
		return spelerdao.alleSpelers();
	}
	
	public Speler findSpelerById(int id) throws SQLException{
		return spelerdao.getSpeler(id);
	}
	
	public ArrayList<Speler> getDeelnemersByRonde(int id ) throws SQLException{
		return spelerdao.getDeelnemersByRonde(id);
	}
	
	public ArrayList<Test> getTest() throws SQLException{
		return spelerdao.getTest();
	}

}
