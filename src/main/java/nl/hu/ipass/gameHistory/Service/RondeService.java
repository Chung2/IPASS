package nl.hu.ipass.gameHistory.Service;

import java.sql.SQLException;
import java.sql.Time;
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
	
	public Ronde getLaatsteRonde() throws SQLException{
		return rondedao.laatsteRonde();
	}
	
	public boolean addRonde(Ronde ronde) throws SQLException{
		return rondedao.nieuwRonde(ronde);
	}
	
	public boolean updateEindTijd(Ronde ronde) throws SQLException{
		return rondedao.updateEindTijd(ronde);
	}
	
	public boolean updatePostRound(Ronde ronde) throws SQLException{
		return rondedao.updatePostRonde(ronde);
	}
}
