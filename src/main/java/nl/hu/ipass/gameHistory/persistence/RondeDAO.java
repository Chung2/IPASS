package nl.hu.ipass.gameHistory.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nl.hu.ipass.gameHistory.model.Ronde;

public class RondeDAO extends BaseDAO {

	
	public ArrayList<Ronde> alleRondes() throws SQLException{
		
		Connection con = super.getConnection();
		SpelDAO speldao = new SpelDAO();
		SpelerDAO spelerdao = new SpelerDAO();

		ArrayList<Ronde> rondes = new ArrayList<Ronde>();
		String querySelect = "SELECT o.id_ronde,o.id_spel,o.tijd,o.notities FROM ronde o";
		PreparedStatement stmt = con.prepareStatement(querySelect);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			rondes.add(new Ronde(rs.getInt("id_ronde"), speldao.spelById(rs.getInt("id_spel")),
					spelerdao.getDeelnemersByRonde(rs.getInt("id_ronde")), rs.getTimestamp("tijd"),
					spelerdao.getWinnaarByRondeId(rs.getInt("id_ronde")), rs.getString("notities")));
		}
		con.close();
		return rondes;
	}

	public Ronde rondeById(int id) throws SQLException{
		Connection con = super.getConnection();
		SpelDAO speldao = new SpelDAO();
		SpelerDAO spelerdao = new SpelerDAO();

		
		Ronde ronde = null;
		String querySelect = "SELECT o.id_ronde,o.id_spel,o.tijd,o.notities,r.winnaar FROM resultaat r JOIN ronde o ON r.id_ronde = o.id_ronde where o.id_ronde = ?";
		PreparedStatement stmt = con.prepareStatement(querySelect);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			ronde = new Ronde(rs.getInt("id_ronde"), speldao.spelById(rs.getInt("id_spel")),
					spelerdao.getDeelnemersByRonde(rs.getInt("id_ronde")), rs.getTimestamp("tijd"),
					spelerdao.getSpeler(rs.getInt("winnaar")), rs.getString("notities"));
		}
		con.close();
		return ronde;
	}

	public ArrayList<Ronde> getRondesBySpelID(int id) throws SQLException {
		Connection con = super.getConnection();
		SpelDAO speldao = new SpelDAO();
		SpelerDAO spelerdao = new SpelerDAO();

		ArrayList<Ronde> rondes = new ArrayList<Ronde>();
		String querySelect = "SELECT o.id_ronde,o.id_spel,o.tijd,o.notities,r.winnaar FROM ronde o JOIN resultaat r ON o.id_ronde = r.id_ronde WHERE id_spel = ?";
		PreparedStatement stmt = con.prepareStatement(querySelect);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			rondes.add(new Ronde(rs.getInt("id_ronde"), speldao.spelByIdForRonde(rs.getInt("id_spel")),
					spelerdao.getDeelnemersByRonde(rs.getInt("id_ronde")), rs.getTimestamp("tijd"),
					spelerdao.getWinnaarByRondeId(rs.getInt("id_ronde")), rs.getString("notities")));
		}
		con.close();
		return rondes;
	}

	public ArrayList<Integer> getRondeIdByGebruikerId(int id) throws SQLException{
		Connection con = super.getConnection();
		
		ArrayList<Integer> rondes = new ArrayList<Integer>();
		String querySelect = "SELECT id_ronde FROM resultaat WHERE id_speler = ?";
		PreparedStatement stmt = con.prepareStatement(querySelect);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			rondes.add(rs.getInt("id_ronde"));
		}
		con.close();
		return rondes;
	}

}
