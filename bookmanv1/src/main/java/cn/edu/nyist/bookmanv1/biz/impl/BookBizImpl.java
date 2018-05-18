package cn.edu.nyist.bookmanv1.biz.impl;

import java.util.Date;

import cn.edu.nyist.bookmanv1.biz.BookBiz;
import cn.edu.nyist.bookmanv1.dao.BookDao;
import cn.edu.nyist.bookmanv1.dao.impl.BookDaoJdbcImpl;
import cn.edu.nyist.bookmanv1.vo.BookVo;

public class BookBizImpl implements BookBiz {

	@Override
	public int saveBook(BookVo bookVo) {
		BookDao bookDao = new BookDaoJdbcImpl();
		return bookDao.save(bookVo);
	}

}
