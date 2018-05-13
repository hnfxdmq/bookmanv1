package cn.edu.nyist.bookmanv1.biz.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.nyist.bookmanv1.dao.AdminDao;
import cn.edu.nyist.bookmanv1.util.DsUtil;

public class AdminDaoJdbcImpl implements AdminDao {

	@Override
	public boolean get(String name, String pwd) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean ret = false;
		try {
			conn=DsUtil.getConn();
			//2、不在使用字符串拼接，改用占位符?代替
			String sql="select * from t_admin where name=? and password=?";
			stmt=conn.prepareStatement(sql);
			//3、setString给占位符赋值
			stmt.setString(1, name);
			stmt.setString(2, pwd);
			rs=stmt.executeQuery();
			if(rs.next()) {
				ret=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DsUtil.free(stmt, conn, rs);
		}
		return ret;
	}

}
