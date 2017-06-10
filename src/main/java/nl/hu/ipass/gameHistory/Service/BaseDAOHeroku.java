package nl.hu.ipass.gameHistory.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import javax.management.RuntimeErrorException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BaseDAOHeroku {
	private DataSource connectionPool;

	public BaseDAOHeroku() {
		try {
			final String DATABASE_URL_PROP = System.getenv("DATABASE_URL");

			if ("DATABASE_URL_PROP" != null) {
				URI dbUri = new URI(DATABASE_URL_PROP);
				String dbUrl = "jdbc:mysql://localhost:3306/" + dbUri.getHost() + dbUri.getPath();
				BasicDataSource pool = new BasicDataSource();

				if (dbUri.getUserInfo() != null) {
					pool.setUsername(dbUri.getUserInfo().split(":")[0]);
					pool.setPassword(dbUri.getUserInfo().split(":")[1]);
				}
				pool.setDriverClassName("com.mysql.jdbc.Driver");
				pool.setUrl(dbUrl);
				pool.setInitialSize(1);

				connectionPool = pool;
			} else {
				InitialContext ic = new InitialContext();
				connectionPool = (DataSource) ic.lookup("java:comp/env/jdbc/Mysql");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected final Connection getConnection() {
		try {
			return connectionPool.getConnection();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
