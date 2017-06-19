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
		if (con != null) {
			con.close();
		}
		return spellen;

	}

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
		if (con != null) {
			con.close();
		}
		return spel;
	}

	// getRondeBySpelId
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
		if (con != null) {
			con.close();
		}
		return spel;
	}

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
		if (con != null) {
			con.close();
		}
		return gelukt;
	}

	public Boolean insertSpel(Spel spel) throws SQLException {
		Connection con = super.getConnection();

		boolean gelukt = false;
		String queryInsert = "INSERT INTO spel ( naam , instructies)VALUES( ? , ?)";
		PreparedStatement stmt = con.prepareStatement(queryInsert);
		stmt.setString(1, spel.getNaam());
		stmt.setString(2, spel.getInstructies());
		int rs = stmt.executeUpdate();

		if (rs > 0) {
			gelukt = true;
			stmt.close();
			if (con != null) {
				con.close();
			}
		}

		return gelukt;

	}

}