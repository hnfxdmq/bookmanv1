package cn.edu.nyist.bookmanv1.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.nyist.bookmanv1.biz.BookBiz;
import cn.edu.nyist.bookmanv1.biz.impl.BookBizImpl;
import cn.edu.nyist.bookmanv1.util.PageConstant;
import cn.edu.nyist.bookmanv1.vo.BookVo;
import cn.edu.nyist.bookmanv1.vo.TypeVo;

@WebServlet("/bookList")
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BookListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 权限拦截
		/*if (request.getSession().getAttribute("loginSuccess") == null|| !request.getSession().getAttribute("loginSuccess").equals("1")) {
			response.sendRedirect("login.jsp");
			return;
		}*/
		//获取参数
		String strPageNo = request.getParameter("pageNo");
		int pageNo;
		try {
			pageNo = Integer.parseInt(strPageNo);
		} catch (NumberFormatException e) {
			pageNo=1;//默认看第一页
		}
		//获取搜索条件
		String name = request.getParameter("name");
		String strTid = request.getParameter("tid");
		int tid;
		try {
			tid = Integer.parseInt(strTid);
		} catch (NumberFormatException e) {
			tid=-1;
		}
		//调用业务层
		BookBiz bookBiz = new BookBizImpl();
		List<BookVo> ls = bookBiz.findAllBook(pageNo,name,tid);
		int totalPage = bookBiz.findTotal(name,tid);
		List<TypeVo> types = bookBiz.findAllTypes();
		//根据业务层返回结果
		if(totalPage%PageConstant.PAGE_SIZE==0) {
			request.setAttribute("totalPage", totalPage/PageConstant.PAGE_SIZE);
		}else {
			request.setAttribute("totalPage", totalPage/PageConstant.PAGE_SIZE+1);
		}
		request.setAttribute("name", name);
		request.setAttribute("tid", tid);
		request.setAttribute("types", types);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("ls", ls);
		request.getRequestDispatcher("bookList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
