package cn.edu.nyist.bookmanv1.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.edu.nyist.bookmanv1.dao.BookDao;
import cn.edu.nyist.bookmanv1.util.DsUtil;
import cn.edu.nyist.bookmanv1.util.PageConstant;
import cn.edu.nyist.bookmanv1.vo.BookVo;

public class BookDaoJdbcImpl implements BookDao {

	@Override
	public int save(BookVo bookVo) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DsUtil.getConn();
			String str = "Insert into t_book(tid,name,descri,photo,price,author,pubDate) values(?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(str);
			stmt.setInt(1, bookVo.getTid());
			stmt.setString(2, bookVo.getName());
			stmt.setString(3, bookVo.getDescri());
			stmt.setString(4, bookVo.getPhoto());
			stmt.setDouble(5, bookVo.getPrice());
			stmt.setString(6, bookVo.getAuthor());
			//java.util.Date--->java.sql.Date
			stmt.setDate(7, new java.sql.Date(bookVo.getPubDate().getTime()));
			int result = stmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DsUtil.free(stmt, conn);
		}
		return 0;
	}

	@Override
	public List<BookVo> findAll(int pageNo,String name,int tid) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DsUtil.getConn();
			stmt = conn.createStatement();
			//把2^n-1次条件减少为n次
			String sql = "select * from t_book where 1=1";
			/*if((tid!=-1)&&((name==null)||(name.equals("")))) {
				sql+="and tid="+tid;
			}else if(!((name==null)||(name.equals("")))&&(tid==-1)) {
				sql+="and name like '%"+name+"%'";
			}else {
				sql+="and tid="+tid+"and name like '%"+name+"%'";
			}*/
			if(tid!=-1) {
				sql+=" and tid="+tid;
			}
			if(name!=null&&!name.equals("")) {
				sql+=" and name like '%"+name+"%'";
			}
			sql+=" limit "+((pageNo-1)*PageConstant.PAGE_SIZE)+","+PageConstant.PAGE_SIZE;
			rs = stmt.executeQuery(sql);
			List<BookVo> ls = new ArrayList<>();
			while(rs.next()) {
				BookVo bookVo = new BookVo();
				bookVo.setAuthor(rs.getString("author"));
				bookVo.setDescri(rs.getString("descri"));
				bookVo.setId(rs.getInt("id"));
				bookVo.setName(rs.getString("name"));
				bookVo.setPhoto(rs.getString("photo"));
				bookVo.setPrice(rs.getDouble("price"));
				bookVo.setPubDate(rs.getDate("pubDate"));
				bookVo.setTid(rs.getInt("tid"));
				ls.add(bookVo);
			}
			return ls;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DsUtil.free(stmt, conn, rs);
		}
		return null;
	}

	@Override
	public int getTotal(String name,int tid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn=DsUtil.getConn();
			String sql="select count(*) from t_book where 1=1";
			if(tid!=-1) {
				sql+=" and tid="+tid;
			}
			if(name!=null&&!name.equals("")) {
				sql+=" and name like '%"+name+"%'";
			}
			stmt=conn.prepareStatement(sql);
			rs=stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DsUtil.free(stmt, conn, rs);
		}
		return 0;
	}

	@Override
	public boolean del(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DsUtil.getConn();
			String str = "delete from t_book where id="+id;
			stmt = conn.prepareStatement(str);
			int result = stmt.executeUpdate();
			if(result>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DsUtil.free(stmt, conn);
		}
		return false;
	}

	@Override
	public BookVo get(int id) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DsUtil.getConn();
			stmt = conn.createStatement();
			String sql = "select * from t_book where id="+id;
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				BookVo bookVo = new BookVo();
				bookVo.setAuthor(rs.getString("author"));
				bookVo.setDescri(rs.getString("descri"));
				bookVo.setId(rs.getInt("id"));
				bookVo.setName(rs.getString("name"));
				bookVo.setPhoto(rs.getString("photo"));
				bookVo.setPrice(rs.getDouble("price"));
				bookVo.setPubDate(rs.getDate("pubDate"));
				bookVo.setTid(rs.getInt("tid"));
				return bookVo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DsUtil.free(stmt, conn, rs);
		}
		return null;
	}

	@Override
	public int edit(BookVo bookVo) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DsUtil.getConn();
			if(bookVo.getPhoto()==null||bookVo.getPhoto().equals("")) {
				String str = "update t_book set tid=?,name=?,descri=?,price=?,author=?,pubDate=? where id=?";
				stmt = conn.prepareStatement(str);
				stmt.setInt(1, bookVo.getTid());
				stmt.setString(2, bookVo.getName());
				stmt.setString(3, bookVo.getDescri());
				stmt.setDouble(4, bookVo.getPrice());
				stmt.setString(5, bookVo.getAuthor());
				//java.util.Date--->java.sql.Date
				stmt.setDate(6, new java.sql.Date(bookVo.getPubDate().getTime()));
				stmt.setInt(7, bookVo.getId());
				int result = stmt.executeUpdate();
				return result;
			}else {
				String str = "update t_book set tid=?,name=?,descri=?,photo=?,price=?,author=?,pubDate=? where id=?";
				stmt = conn.prepareStatement(str);
				stmt.setInt(1, bookVo.getTid());
				stmt.setString(2, bookVo.getName());
				stmt.setString(3, bookVo.getDescri());
				stmt.setString(4, bookVo.getPhoto());
				stmt.setDouble(5, bookVo.getPrice());
				stmt.setString(6, bookVo.getAuthor());
				//java.util.Date--->java.sql.Date
				stmt.setDate(7, new java.sql.Date(bookVo.getPubDate().getTime()));
				stmt.setInt(8, bookVo.getId());
				int result = stmt.executeUpdate();
				return result;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DsUtil.free(stmt, conn);
		}
		return 0;
	}

}
