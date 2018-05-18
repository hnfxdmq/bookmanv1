package cn.edu.nyist.bookmanv1.dao;

import cn.edu.nyist.bookmanv1.vo.BookVo;

public interface BookDao {

	//int save(String name, String descri, String author, Double price, int tid, String newFileName, Date pubDate);
	int save(BookVo bookVo);

}
