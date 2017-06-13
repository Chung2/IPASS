package nl.hu.ipass.gameHistory.Service;

import java.sql.SQLException;
import java.util.ArrayList;

import nl.hu.ipass.gameHistory.model.Spel;
import nl.hu.ipass.gameHistory.persistence.SpelDAO;

public class SpelService {
	
	SpelDAO speldao = new SpelDAO();
	
	public ArrayList<Spel> getSpellen() throws SQLException{
		return speldao.alleSpellen();
	}
	
	public Spel getSpel(int id) throws SQLException{
		return speldao.spelById(id);
	}
	
	public boolean addSpel(Spel spel) throws SQLException{
		return speldao.insertSpel(spel);
	}
	
	public boolean deleteSpel(Spel spel) throws SQLException{
		return speldao.deleteSpel(spel);
	}
}
