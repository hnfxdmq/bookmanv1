package cn.edu.nyist.bookmanv1.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DsUtil {
	private static ComboPooledDataSource cpds = new ComboPooledDataSource();
	public static Connection getConn() throws SQLException {
		return cpds.getConnection();
	}
	public static void free(Statement stmt, Connection conn, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
