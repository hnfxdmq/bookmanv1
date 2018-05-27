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
		// 1获取参数
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String vcode = request.getParameter("vcode");
		//会话范围变量
		HttpSession session = request.getSession();
		String serverString = (String) session.getAttribute("validateCode");
		if (!serverString.equalsIgnoreCase(vcode)) {
			request.setAttribute("msg", "验证码错误");
			request.setAttribute("name", name);
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		// 2调用业务层验证
		AdminBiz adminBiz = new AdminBizImpl();
		boolean ret = adminBiz.findAdminByNameAndPwd(name, pwd);
		// 3给用户反应
		if (ret) {
			// 记录下登录成功
			request.getSession().setAttribute("loginSuccess", "1");
			response.sendRedirect("main.jsp");
		} else {
			// 登录失败
			request.setAttribute("msg", "用户名或密码错误");
			request.setAttribute("name", name);
			request.getRequestDispatcher("login.jsp").forward(request, response);

		}
	}
}
