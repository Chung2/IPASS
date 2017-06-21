/*DAO voert alle queries uit door de tabel speler en haalt of voert informatie in/uit
 * */
package nl.hu.ipass.gameHistory.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nl.hu.ipass.gameHistory.model.Speler;
import nl.hu.ipass.gameHistory.model.Test;

public class SpelerDAO extends BaseDAO {
	
	private RondeDAO rondedao = new RondeDAO();
	//method return arrayList Test, voor testing
	public ArrayList<Test> getTest() throws SQLException{
		Connection con = super.getConnection();

		ArrayList<Test> testArray = new ArrayList<Test>();
		
		String querySelect = "SELECT id,naam FROM testtable";
		PreparedStatement stmt = con.prepareStatement(querySelect);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()){
			testArray.add(new Test(rs.getInt("id"),rs.getString("naam")));
		}
		con.close();
		return testArray;
	}

	//method return arrayList Speler, maakt gebruik van getRondeIdByGebruikerId uit rondedao
	public ArrayList<Speler> alleSpelers() throws SQLException {
		Connection con = super.getConnection();

		ArrayList<Speler> spelers = new ArrayList<Speler>();
		String querySelect = "SELECT id_speler,naam,wachtwoord,rol FROM Speler";
		PreparedStatement stmt = con.prepareStatement(querySelect);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			spelers.add(new Speler(rs.getInt("id_speler"), rs.getString("naam"), rs.getString("wachtwoord"),
					rondedao.getRondeIdByGebruikerId(rs.getInt("id_speler")),rs.getString("rol")));
		}
		con.close();
		return spelers;

	}

	//method return Speler door middel van id_speler,maakt gebruik van getRondeIdByGebruikerId uit rondedao
	public Speler getSpeler(int id) throws SQLException {
		Connection con = super.getConnection();

		Speler speler = null;
		String querySelect = "SELECT id_speler,naam, wachtwoord, rol FROM Speler WHERE id_speler = ?";
		PreparedStatement stmt = con.prepareStatement(querySelect);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			speler = new Speler(rs.getInt("id_speler"), rs.getString("naam"), rs.getString("wachtwoord"),
					rondedao.getRondeIdByGebruikerId(rs.getInt("id_speler")),rs.getString("rol"));
		}
		con.close();
		return speler;
	}

	//method return arrayList deelnemers/speler, door middel van id_ronde maakt gebruik van getSpeler method uit SpelerDAO
	public ArrayList<Speler> getDeelnemersByRonde(int id) throws SQLException {
		
		Connection con = super.getConnection();
		ArrayList<Speler> deelnemers = new ArrayList<Speler>();
		String querySelect = "SELECT r.id_speler FROM resultaat r JOIN Speler s ON r.id_speler =s.id_speler WHERE r.id_ronde = ? ";
		PreparedStatement stmt = con.prepareStatement(querySelect);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			deelnemers.add(getSpeler(rs.getInt("id_speler")));
		}
		con.close();
		return deelnemers;
	}
	
	//method return Speler/winnaar door middel van id_ronde, maakt gebruik van getSpeler method uit SpelerDAO
	public Speler getWinnaarByRondeId(int id)throws SQLException{
		Connection con = super.getConnection();
		Speler winnaar = null;
		String querySelect = "SELECT winnaar from resultaat where id_ronde = ? limit 1";
		PreparedStatement stmt = con.prepareStatement(querySelect);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()){
			winnaar = getSpeler(rs.getInt("winnaar"));
		}
		con.close();
		return winnaar;
	}
	
	//method return arrayList Speler door middel van namen maakt gebruik van method getSpeler uit SpelerDAO
	public ArrayList<Speler> getSpelersByNamen(ArrayList<String> deelnemersNamen) throws SQLException{
		
		Connection con = super.getConnection();
		ArrayList<Speler> deelnemers = new ArrayList<Speler>();
		String querySelect = "SELECT id_speler FROM speler where naam = ?";
		
		for(String s : deelnemersNamen){
			PreparedStatement stmt = con.prepareStatement(querySelect);
			stmt.setString(1, s);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				int idSpeler = rs.getInt("id_speler");
				deelnemers.add(getSpeler(idSpeler));
			}
		}
		con.close();
		return deelnemers;
	}
	
	//method return String rol door middel van username en password, nodig voor inloggen
	public String findRolForNaamAndWachtwoord(String username, String password) throws SQLException{
		
		Connection con = super.getConnection();
		String rol = null;
		String querySelect = "SELECT rol FROM speler WHERE naam = ? AND wachtwoord = ?";
		
			PreparedStatement pstmt = con.prepareStatement(querySelect);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
				rol = rs.getString("rol");
				
		con.close();
		return rol;
	}
	
}
