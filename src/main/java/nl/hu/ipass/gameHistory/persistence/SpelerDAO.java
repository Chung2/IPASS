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

	public ArrayList<Speler> alleSpelers() throws SQLException {
		Connection con = super.getConnection();

		ArrayList<Speler> spelers = new ArrayList<Speler>();
		String querySelect = "SELECT id_speler,naam,wachtwoord FROM Speler";
		PreparedStatement stmt = con.prepareStatement(querySelect);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			spelers.add(new Speler(rs.getInt("id_speler"), rs.getString("naam"), rs.getString("wachtwoord"),
					rondedao.getRondeIdByGebruikerId(rs.getInt("id_speler"))));
		}
		con.close();
		return spelers;

	}

	public Speler getSpeler(int id) throws SQLException {
		Connection con = super.getConnection();

		Speler speler = null;
		String querySelect = "SELECT id_speler,naam, wachtwoord FROM Speler WHERE id_speler = ?";
		PreparedStatement stmt = con.prepareStatement(querySelect);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			speler = new Speler(rs.getInt("id_speler"), rs.getString("naam"), rs.getString("wachtwoord"),
					rondedao.getRondeIdByGebruikerId(rs.getInt("id_speler")));
		}
		con.close();
		return speler;
	}

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
	
	public Speler getWinnaarByRondeId(int id)throws SQLException{
		Connection con = super.getConnection();
		Speler winnaar = null;
		String querySelect = "SELECT winnaar from resultaat where id_ronde = ? limit 1";
		PreparedStatement stmt = con.prepareStatement(querySelect);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()){
			winnaar = getSpeler(id);
		}
		con.close();
		return winnaar;
	}
	
	
}
