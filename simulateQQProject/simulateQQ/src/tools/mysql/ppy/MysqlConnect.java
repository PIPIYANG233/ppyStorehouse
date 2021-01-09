package tools.mysql.ppy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnect {
	public static Connection conect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} // ×°ÔØmysqlÇý¶¯
		
		try {
			return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/qq_user?serverTimezone=GMT%2B8","root","zy2001.11.11");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
