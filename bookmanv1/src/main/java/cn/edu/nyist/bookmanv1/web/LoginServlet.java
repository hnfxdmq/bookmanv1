package cn.edu.nyist.bookmanv1.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.nyist.bookmanv1.biz.AdminBiz;
import cn.edu.nyist.bookmanv1.biz.impl.AdminBizImpl;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1、获取用户输入
				String name=request.getParameter("name");
				String pwd=request.getParameter("pwd");
				String vcode=request.getParameter("vcode");
				//在查询数据库之前执行
				HttpSession session=request.getSession();
				String serverString=(String) session.getAttribute("validateCode");
				if(!serverString.equalsIgnoreCase(vcode)) {
					request.setAttribute("msg", "验证码错误");
					request.setAttribute("name", name);
					request.getRequestDispatcher("login.jsp").forward(request, response);
					return ;
				}
				//2、数据查询
				AdminBiz adminBiz=new AdminBizImpl();
				boolean ret=adminBiz.findAdminByNameAndPwd(name,pwd);
				//3、给用户一个相应
				if (ret) {
					response.sendRedirect("main.jsp");
				} else {
					request.setAttribute("msg", "用户名或密码错误");
					request.setAttribute("name", name);
					request.getRequestDispatcher("login.jsp").forward(request, response);

				}
			}
}
