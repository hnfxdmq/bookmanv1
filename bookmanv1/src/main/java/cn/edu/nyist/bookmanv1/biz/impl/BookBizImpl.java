package cn.edu.nyist.bookmanv1.biz.impl;

import java.util.List;

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

	@Override
	public List<BookVo> findAllBook(int pageNo) {
		BookDao bookDao = new BookDaoJdbcImpl();
		return bookDao.findAll(pageNo);
	}

	@Override
	public int findTotal() {
		BookDao bookDao = new BookDaoJdbcImpl();
		return bookDao.getTotal();
	}

}
