package nl.hu.ipass.gameHistory.Service;

import java.sql.SQLException;
import java.util.ArrayList;

import nl.hu.ipass.gameHistory.model.Ronde;
import nl.hu.ipass.gameHistory.persistence.RondeDAO;

public class RondeService {
	
	RondeDAO rondedao = new RondeDAO();
	
	public ArrayList<Ronde> getAlleRonde() throws SQLException{
		return rondedao.alleRondes();
	}
	
	public Ronde getRondeById(int id) throws SQLException{
		return rondedao.rondeById(id);
		
	}
	
	public ArrayList<Integer> getRondeBySpelerId(int id) throws SQLException{
		return rondedao.getRondeIdByGebruikerId(id);
	}
	
	public ArrayList<Ronde> getRondeBySpelId(int id) throws SQLException{
		return rondedao.getRondesBySpelID(id);
		
	}
	
}
