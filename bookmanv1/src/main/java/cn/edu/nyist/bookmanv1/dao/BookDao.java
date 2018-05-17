package cn.edu.nyist.bookmanv1.dao;

public interface BookDao {

	int save(String name, String descri, String author, Double price, int tid, String newFileName);

}
