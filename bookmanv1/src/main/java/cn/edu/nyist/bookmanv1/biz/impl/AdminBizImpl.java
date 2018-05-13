package cn.edu.nyist.bookmanv1.biz.impl;

import cn.edu.nyist.bookmanv1.biz.AdminBiz;
import cn.edu.nyist.bookmanv1.dao.AdminDao;
import cn.edu.nyist.bookmanv1.dao.impl.AdminDaoJdbcImpl;

public class AdminBizImpl implements AdminBiz{

	@Override
	public boolean findAdminByNameAndPwd(String name, String pwd) {
		AdminDao adminDao=new AdminDaoJdbcImpl();
		return adminDao.get(name,pwd);
	}

}
