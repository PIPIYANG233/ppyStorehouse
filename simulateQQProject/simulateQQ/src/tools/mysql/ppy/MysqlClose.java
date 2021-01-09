package tools.mysql.ppy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlClose {
	public static void close(ResultSet rs) {
		try {
			if(null != rs)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement ps) {
		try {
			if(null != ps)
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn) {
		try {
			if(null != conn)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs, PreparedStatement ps) {
		try {
			if(null != rs)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(null != ps)
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rs, Connection conn) {
		try {
			if(null != rs)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(null != conn)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(PreparedStatement ps, Connection conn) {
		try {
			if(null != ps)
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(null != conn)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
		try {
			if(null != rs)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(null != ps)
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(null != conn)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
