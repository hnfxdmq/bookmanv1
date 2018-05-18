package cn.edu.nyist.bookmanv1.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.edu.nyist.bookmanv1.dao.TypeDao;
import cn.edu.nyist.bookmanv1.util.DsUtil;
import cn.edu.nyist.bookmanv1.vo.TypeVo;

public class TypeDaoJdbcImpl implements TypeDao {

	@Override
	public List<TypeVo> findAll() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DsUtil.getConn();
			stmt = conn.createStatement();
			String sql = "select * from t_type";
			rs = stmt.executeQuery(sql);
			List<TypeVo> ls = new ArrayList<>();
			while(rs.next()) {
				TypeVo typeVo = new TypeVo();
				typeVo.setId(rs.getInt("id"));
				typeVo.setName(rs.getString("name"));
				ls.add(typeVo);
			}
			return ls;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DsUtil.free(stmt, conn, rs);
		}
		return null;
	}

}
