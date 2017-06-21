/*DAO voert alle queries uit door de tabel ronde/resultaat en haalt of voert informatie in/uit
 * */

package nl.hu.ipass.gameHistory.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import nl.hu.ipass.gameHistory.model.Ronde;
import nl.hu.ipass.gameHistory.model.Speler;

public class RondeDAO extends BaseDAO {
	
	//method return arrayList alle rondes uit database maakt gebruik van getDeelnemersByRonde, getWinnaarByRondeId, spelById uit de spelersDAO en spelDAO
	public ArrayList<Ronde> alleRondes() throws SQLException {

		Connection con = super.getConnection();
		SpelDAO speldao = new SpelDAO();
		SpelerDAO spelerdao = new SpelerDAO();

		ArrayList<Ronde> rondes = new ArrayList<Ronde>();
		String querySelect = "SELECT o.id_ronde,o.id_spel,o.tijd,o.notities,o.naam FROM ronde o";
		PreparedStatement stmt = con.prepareStatement(querySelect);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			rondes.add(new Ronde(rs.getInt("id_ronde"), speldao.spelById(rs.getInt("id_spel")), rs.getString("naam"),
					spelerdao.getDeelnemersByRonde(rs.getInt("id_ronde")), rs.getTime("tijd"),
					spelerdao.getWinnaarByRondeId(rs.getInt("id_ronde")), rs.getString("notities")));
		}
		con.close();
		return rondes;
	}

	//method return ronde uit database door middel van id, maakt gebruik van getDeelnemersByRonde, getWinnaarByRondeId, spelById uit de spelersDAO en spelDAO
	public Ronde rondeById(int id) throws SQLException {
		Connection con = super.getConnection();
		SpelDAO speldao = new SpelDAO();
		SpelerDAO spelerdao = new SpelerDAO();

		Ronde ronde = null;
		String querySelect = "SELECT o.id_ronde,o.id_spel,o.tijd,o.notities,o.naam FROM ronde o WHERE o.id_ronde = ?";
		PreparedStatement stmt = con.prepareStatement(querySelect);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			ronde = new Ronde(rs.getInt("id_ronde"), speldao.spelById(rs.getInt("id_spel")), rs.getString("naam"),
					spelerdao.getDeelnemersByRonde(rs.getInt("id_ronde")), rs.getTime("tijd"),
					spelerdao.getWinnaarByRondeId(rs.getInt("id_ronde")), rs.getString("notities"));
		}
		con.close();
		return ronde;
	}

	//method return ronde die laatst gespeeld is, maakt gebruik van getDeelnemersByRonde, getWinnaarByRondeId, spelById uit de spelersDAO en spelDAO
	public Ronde laatsteRonde() throws SQLException {
		Connection con = super.getConnection();
		SpelDAO speldao = new SpelDAO();
		SpelerDAO spelerdao = new SpelerDAO();

		Ronde ronde = null;
		String querySelect = "SELECT o.id_ronde,o.id_spel,o.tijd,o.notities,o.naam FROM ronde o ORDER BY o.id_ronde DESC LIMIT 1";
		PreparedStatement stmt = con.prepareStatement(querySelect);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			ronde = new Ronde(rs.getInt("id_ronde"), speldao.spelById(rs.getInt("id_spel")), rs.getString("naam"),
					spelerdao.getDeelnemersByRonde(rs.getInt("id_ronde")), rs.getTime("tijd"),
					spelerdao.getWinnaarByRondeId(rs.getInt("id_ronde")), rs.getString("notities"));
		}
		con.close();
		return ronde;
	}

	//method return arraylist ronde door middel van spel id, maakt gebruik van getDeelnemersByRonde, getWinnaarByRondeId, spelById uit de spelersDAO en spelDAO
	public ArrayList<Ronde> getRondesBySpelID(int id) throws SQLException {
		Connection con = super.getConnection();
		SpelDAO speldao = new SpelDAO();
		SpelerDAO spelerdao = new SpelerDAO();

		ArrayList<Ronde> rondes = new ArrayList<Ronde>();
		String querySelect = "SELECT o.id_ronde,o.id_spel,o.tijd,o.notities,o.naam FROM ronde o WHERE id_spel = ?";
		PreparedStatement stmt = con.prepareStatement(querySelect);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			rondes.add(new Ronde(rs.getInt("id_ronde"), speldao.spelByIdForRonde(rs.getInt("id_spel")),
					rs.getString("naam"), spelerdao.getDeelnemersByRonde(rs.getInt("id_ronde")), rs.getTime("tijd"),
					spelerdao.getWinnaarByRondeId(rs.getInt("id_ronde")), rs.getString("notities")));
		}
		con.close();
		return rondes;
	}

	//method return arrayList ronde door middel van spelers id
	public ArrayList<Integer> getRondeIdByGebruikerId(int id) throws SQLException {
		Connection con = super.getConnection();

		ArrayList<Integer> rondes = new ArrayList<Integer>();
		String querySelect = "SELECT id_ronde FROM resultaat WHERE id_speler = ?";
		PreparedStatement stmt = con.prepareStatement(querySelect);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			rondes.add(rs.getInt("id_ronde"));
		}
		con.close();
		return rondes;
	}

	//method return boolean of de query insert into resultaat gelukt is, id_speler, id_ronde en winnaar
	public boolean insertResultaatRonde(Ronde ronde) throws SQLException {
		Ronde laatsteronde = laatsteRonde();

		Connection con = super.getConnection();
		SpelerDAO spelerdao = new SpelerDAO();
		boolean gelukt = false;

		String queryInsertResultaat = "INSERT INTO resultaat(id_speler, id_ronde, winnaar)VALUES(?,?,?)";

		ArrayList<Speler> spelerLijstObj = spelerdao.getSpelersByNamen(ronde.getDeelnemersNamen());

		for (Speler spelerObj : spelerLijstObj) {
			PreparedStatement stmt = con.prepareStatement(queryInsertResultaat);

			stmt.setInt(1, spelerObj.getId_speler());
			stmt.setInt(2, laatsteronde.getId_ronde());
			stmt.setInt(3, ronde.getWinnaarId());

			int rs = stmt.executeUpdate();
			if (rs > 0) {
				gelukt = true;
				stmt.close();
			} else {
				break;
			}

		}
		con.close();
		return gelukt;

	}
	//method return boolean of de insert into query gelukt is, id_spel, tijd, notities en naam 
	public boolean nieuwRonde(Ronde ronde) throws SQLException {

		Connection con = super.getConnection();
		boolean gelukt = false;

		String queryInsert = "INSERT INTO ronde (id_spel,tijd, notities, naam) VALUES(?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(queryInsert);
		stmt.setInt(1, ronde.getSpelId());
		stmt.setTime(2, ronde.getTijd());
		stmt.setString(3, ronde.getNotities());
		stmt.setString(4, ronde.getNaam());
		int rs = stmt.executeUpdate();

		if (rs > 0) {

			gelukt = true;
			con.close();
			stmt.close();
			insertResultaatRonde(ronde);
		}
		return gelukt;
	}

	//method return boolean of de query update gelukt is, eindtijd
	public boolean updateEindTijd(Ronde ronde) throws SQLException {

		Connection con = super.getConnection();
		boolean gelukt = false;

		String updateQuery = "UPDATE ronde SET tijd = ? WHERE id_ronde = ?";
		PreparedStatement stmt = con.prepareStatement(updateQuery);

		stmt.setTime(1, ronde.getTijd());
		stmt.setInt(2, ronde.getId_ronde());

		int rs = stmt.executeUpdate();
		if (rs > 0) {
			gelukt = true;
			stmt.close();
			con.close();
		}

		return gelukt;
	}

	//method return booelean of de update query gelukt is
	public boolean updateNotities(Ronde ronde) throws SQLException {

		Connection con = super.getConnection();
		Ronde laatsteronde = laatsteRonde();
		boolean gelukt = false;

		String updateQuery = "UPDATE ronde SET notities = ? WHERE id_ronde = ?";

		PreparedStatement stmt = con.prepareStatement(updateQuery);
		stmt.setString(1, ronde.getNotities());
		stmt.setInt(2, laatsteronde.getId_ronde());

		int rs = stmt.executeUpdate();
		if (rs > 0) {
			gelukt = true;
			stmt.close();
			con.close();
		}

		return gelukt;
	}

	//method return boolean of de update query gelukt is, winnaar en voer ook de method updateNotities uit
	public boolean updatePostRonde(Ronde ronde) throws SQLException {

		Connection con = super.getConnection();
		boolean gelukt = false;

		String updateQuery = "UPDATE resultaat SET winnaar = ? where id_ronde = ?";
		PreparedStatement stmt = con.prepareStatement(updateQuery);

		stmt.setInt(1, ronde.getWinnaarId());
		stmt.setInt(2, ronde.getId_ronde());

		int rs = stmt.executeUpdate();
		if (rs > 0) {
			gelukt = true;
			stmt.close();
			con.close();
			updateNotities(ronde);
		}
		return gelukt;
	}
}
