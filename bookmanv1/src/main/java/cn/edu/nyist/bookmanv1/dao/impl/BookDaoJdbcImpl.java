package cn.edu.nyist.bookmanv1.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.edu.nyist.bookmanv1.dao.BookDao;
import cn.edu.nyist.bookmanv1.util.DsUtil;

public class BookDaoJdbcImpl implements BookDao {

	@Override
	public int save(String name, String descri, String author, Double price, int tid, String newFileName) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DsUtil.getConn();
			// 输入SQL,执行SQL
			String str = "Insert into t_book(tid,name,descri,photo,price,author) values(?,?,?,?,?,?)";
			stmt = conn.prepareStatement(str);
			stmt.setInt(1, tid);
			stmt.setString(2, name);
			stmt.setString(3, descri);
			stmt.setString(4, newFileName);
			stmt.setDouble(5, price);
			stmt.setString(6, author);
			int result = stmt.executeUpdate();
			// 查看结果
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DsUtil.free(stmt, conn);
		}
		return 0;
	}

}
