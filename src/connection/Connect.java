package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
	public static Connection connect() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/ooad", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
}
