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
		con.close();
		return spellen;

	}

	public Spel spelById(int id) throws SQLException {
		Connection con = super.getConnection();
		RondeDAO rondeDAO = new RondeDAO();

		Spel spel = null;
		String selectQuery = "SELECT id_spel,naam, instructies FROM spel WHERE ?";
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
	
	//getRondeBySpelId
	public Spel spelByIdForRonde(int id) throws SQLException {
		Connection con = super.getConnection();

		Spel spel = null;
		String selectQuery = "SELECT id_spel,naam, instructies FROM spel WHERE ?";
		PreparedStatement stmt = con.prepareStatement(selectQuery);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			spel = new Spel(rs.getInt("id_spel"), rs.getString("naam"), rs.getString("Instructies"));
		}
		con.close();
		return spel;
	}

	public Spel updateSpel(Spel spel) throws SQLException {
		Connection con = super.getConnection();

		con.close();
		return spel;
	}

	public Spel insertSpel(Spel spel) throws SQLException {
		Connection con = super.getConnection();

		con.close();

		return spel;
	}

}