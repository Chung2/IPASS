/*hier wordt de connectie gemaakt tussen de resource files en de dao voor ronde
 * */
package nl.hu.ipass.gameHistory.Service;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import nl.hu.ipass.gameHistory.model.Ronde;
import nl.hu.ipass.gameHistory.persistence.RondeDAO;

public class RondeService {
	
	RondeDAO rondedao = new RondeDAO();
	
	//method return arrayList ronde
	public ArrayList<Ronde> getAlleRonde() throws SQLException{
		return rondedao.alleRondes();
	}
	
	//method return ronde krijgt ronde id mee
	public Ronde getRondeById(int id) throws SQLException{
		return rondedao.rondeById(id);
		
	}
	
	//method return arraylist integer voor rondes krijgt speler id mee
	public ArrayList<Integer> getRondeBySpelerId(int id) throws SQLException{
		return rondedao.getRondeIdByGebruikerId(id);
	}
	
	//method return arraylist rondes door id spel krijgt spel id mee
	public ArrayList<Ronde> getRondeBySpelId(int id) throws SQLException{
		return rondedao.getRondesBySpelID(id);
		
	}
	
	//method return laatste ronde
	public Ronde getLaatsteRonde() throws SQLException{
		return rondedao.laatsteRonde();
	}
	
	//method return boolean toevoegen nieuwe ronde krijgt ronde object mee
	public boolean addRonde(Ronde ronde) throws SQLException{
		return rondedao.nieuwRonde(ronde);
	}
	
	//method return boolean update eindtijd krijgt ronde object mee
	public boolean updateEindTijd(Ronde ronde) throws SQLException{
		return rondedao.updateEindTijd(ronde);
	}
	
	//method return boolean updatePostround krijgt ronde object mee
	public boolean updatePostRound(Ronde ronde) throws SQLException{
		return rondedao.updatePostRonde(ronde);
	}
}
