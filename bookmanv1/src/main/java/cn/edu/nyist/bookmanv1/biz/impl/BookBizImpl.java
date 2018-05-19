package cn.edu.nyist.bookmanv1.biz.impl;

import java.util.List;

import cn.edu.nyist.bookmanv1.biz.BookBiz;
import cn.edu.nyist.bookmanv1.dao.BookDao;
import cn.edu.nyist.bookmanv1.dao.TypeDao;
import cn.edu.nyist.bookmanv1.dao.impl.BookDaoJdbcImpl;
import cn.edu.nyist.bookmanv1.dao.impl.TypeDaoJdbcImpl;
import cn.edu.nyist.bookmanv1.vo.BookVo;
import cn.edu.nyist.bookmanv1.vo.TypeVo;

public class BookBizImpl implements BookBiz {

	@Override
	public int saveBook(BookVo bookVo) {
		BookDao bookDao = new BookDaoJdbcImpl();
		return bookDao.save(bookVo);
	}

	@Override
	public List<BookVo> findAllBook(int pageNo,String name,int tid) {
		BookDao bookDao = new BookDaoJdbcImpl();
		return bookDao.findAll(pageNo,name,tid);
	}

	@Override
	public int findTotal(String name,int tid) {
		BookDao bookDao = new BookDaoJdbcImpl();
		return bookDao.getTotal(name,tid);
	}

	@Override
	public List<TypeVo> findAllTypes() {
		TypeDao typeDao = new TypeDaoJdbcImpl();
		return typeDao.findAll();
	}

}
