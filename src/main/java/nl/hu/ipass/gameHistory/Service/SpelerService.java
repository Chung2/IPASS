/*hier wordt de connectie gemaakt tussen de resource files en de dao voor speler
 * */
package nl.hu.ipass.gameHistory.Service;

import java.sql.SQLException;
import java.util.ArrayList;

import nl.hu.ipass.gameHistory.model.Speler;
import nl.hu.ipass.gameHistory.model.Test;
import nl.hu.ipass.gameHistory.persistence.SpelerDAO;


public class SpelerService {
	
	SpelerDAO spelerdao = new SpelerDAO();
	
	//method return arraylist speler
	public ArrayList<Speler> getAllSpelers() throws SQLException {
		return spelerdao.alleSpelers();
	}
	//method return speler krijgt speler id mee
	public Speler findSpelerById(int id) throws SQLException{
		return spelerdao.getSpeler(id);
	}
	//method return arraylist speler krijgt ronde id mee
	public ArrayList<Speler> getDeelnemersByRonde(int id ) throws SQLException{
		return spelerdao.getDeelnemersByRonde(id);
	}
	//method return arrayList test (voor testen)
	public ArrayList<Test> getTest() throws SQLException{
		return spelerdao.getTest();
	}

}
