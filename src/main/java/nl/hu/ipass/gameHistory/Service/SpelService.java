/*hier wordt de connectie gemaakt tussen de resource files en de dao voor spel
 * */

package nl.hu.ipass.gameHistory.Service;

import java.sql.SQLException;
import java.util.ArrayList;

import nl.hu.ipass.gameHistory.model.Spel;
import nl.hu.ipass.gameHistory.persistence.SpelDAO;

public class SpelService {
	
	SpelDAO speldao = new SpelDAO();
	
	//method return arrayList spel
	public ArrayList<Spel> getSpellen() throws SQLException{
		return speldao.alleSpellen();
	}
	//method return Spel krijgt mee spel id
	public Spel getSpel(int id) throws SQLException{
		return speldao.spelById(id);
	}
	
	//method return boolean voegt spel toe krijgt spel object mee
	public boolean addSpel(Spel spel) throws SQLException{
		return speldao.insertSpel(spel);
	}
	
	//method return boolean verwijderd spel krijgt spel object mee
	public boolean deleteSpel(Spel spel) throws SQLException{
		return speldao.deleteSpel(spel);
	}
}
