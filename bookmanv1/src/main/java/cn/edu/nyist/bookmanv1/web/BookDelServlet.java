package cn.edu.nyist.bookmanv1.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.nyist.bookmanv1.biz.BookBiz;
import cn.edu.nyist.bookmanv1.biz.impl.BookBizImpl;

@WebServlet("/bookDel")
public class BookDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BookDelServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 权限拦截
		/*if (request.getSession().getAttribute("loginSuccess") == null|| !request.getSession().getAttribute("loginSuccess").equals("1")) {
			response.sendRedirect("login.jsp");
			return;
		}*/
		//1、获取参数
		String strId = request.getParameter("id");
		int id = Integer.parseInt(strId);
		//2、调用业务层
		BookBiz bookBiz = new BookBizImpl();
		boolean ret = bookBiz.delById(id);
		//3、返回相应
		if(!ret) {
		request.setAttribute("msg", "删除失败");
		}
		//request.getRequestDispatcher("bookList").forward(request, response);
		//用户有可能会在删除之后再多次点击刷新多次删除，因此不能使用转发，而使用重定向
		response.sendRedirect("bookList");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
