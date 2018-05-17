package cn.edu.nyist.bookmanv1.biz.impl;

import cn.edu.nyist.bookmanv1.biz.BookBiz;
import cn.edu.nyist.bookmanv1.dao.BookDao;
import cn.edu.nyist.bookmanv1.dao.impl.BookDaoJdbcImpl;

public class BookBizImpl implements BookBiz {

	@Override
	public int saveBook(String name, String descri, String author, Double price, int tid, String newFileName) {
		BookDao bookDao = new BookDaoJdbcImpl();
		return bookDao.save(name,descri,author,price,tid,newFileName);
	}

}
