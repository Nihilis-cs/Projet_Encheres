package dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JdbcTools {
	
	private static DataSource dataSource = null;
	
	static {
		Context context;
		try {
			context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx_encheres");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

}
