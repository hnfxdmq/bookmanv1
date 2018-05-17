package cn.edu.nyist.bookmanv1.biz;

import java.util.Date;

public interface BookBiz {

	int saveBook(String name,String descri,String author,Double price,int tid,String newFileName, Date pubDate);

}
