/*DAO voert alle queries uit door de tabel spel en haalt of voert informatie in/uit
 * */

package nl.hu.ipass.gameHistory.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nl.hu.ipass.gameHistory.model.Ronde;
import nl.hu.ipass.gameHistory.model.Spel;

public class SpelDAO extends BaseDAO {

	//method return arrayList alle spellen maakt gebruik van getRondesBySpelID uit rondedao
	public ArrayList<Spel> alleSpellen() throws SQLException {

		Connection con = super.getConnection();
		RondeDAO rondeDAO = new RondeDAO();

		ArrayList<Spel> spellen = new ArrayList<Spel>();
		String selectQuery = "SELECT id_spel,naam, instructies FROM spel";
		PreparedStatement stmt = con.prepareStatement(selectQuery);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			spellen.add(new Spel(rs.getInt("id_spel"), rs.getString("naam"), rs.getString("Instructies"),
					rondeDAO.getRondesBySpelID(rs.getInt("id_spel"))));
		}
		con.close();
		return spellen;

	}

	//method return Spel door middel van spel id maakt gebruik van getRondesBySpelID uit rondedao
	public Spel spelById(int id) throws SQLException {
		Connection con = super.getConnection();
		RondeDAO rondeDAO = new RondeDAO();

		Spel spel = null;
		String selectQuery = "SELECT id_spel,naam, instructies FROM spel WHERE id_spel = ?";
		PreparedStatement stmt = con.prepareStatement(selectQuery);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			spel = new Spel(rs.getInt("id_spel"), rs.getString("naam"), rs.getString("Instructies"),
					rondeDAO.getRondesBySpelID(id));
		}
		con.close();
		return spel;
	}

	//method return spel object door middel van id_spel voor ronde object
	public Spel spelByIdForRonde(int id) throws SQLException {
		Connection con = super.getConnection();

		Spel spel = null;
		String selectQuery = "SELECT id_spel,naam, instructies FROM spel WHERE id_spel = ?";
		PreparedStatement stmt = con.prepareStatement(selectQuery);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			spel = new Spel(rs.getInt("id_spel"), rs.getString("naam"), rs.getString("Instructies"));
		}
		con.close();
		return spel;
	}

	//method return boolean of het gelukt is om spel te verwijderen
	public Boolean deleteSpel(Spel spel) throws SQLException {
		Connection con = super.getConnection();

		Boolean gelukt = false;
		String queryDelete = "DELETE FROM spel where id_spel = ?";

		PreparedStatement stmt = con.prepareStatement(queryDelete);
		stmt.setInt(1, spel.getId_spel());
		int rs = stmt.executeUpdate();
		if (rs > 0) {
			gelukt = true;
			stmt.close();
		}
		con.close();
		return gelukt;
	}

	//method return boolean of het gelukt is om spel toe tevoegen
	public Boolean insertSpel(Spel spel) throws SQLException {
		Connection con = super.getConnection();

		boolean gelukt = false;
		String queryInsert = "INSERT INTO spel ( naam , instructies)VALUES( ? , ?)";
		PreparedStatement stmt = con.prepareStatement(queryInsert);
		stmt.setString(1, spel.getNaam());
		stmt.setString(2, spel.getInstructies());
		int rs = stmt.executeUpdate();
		
		if(rs > 0){
			gelukt = true;
			stmt.close();
		}
		
		con.close();
		return gelukt;
		
	}

}