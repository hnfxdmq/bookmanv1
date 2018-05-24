package cn.edu.nyist.bookmanv1.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.nyist.bookmanv1.biz.TypeBiz;
import cn.edu.nyist.bookmanv1.biz.impl.TypeBizImpl;
import cn.edu.nyist.bookmanv1.vo.TypeVo;

@WebServlet("/findAllTypes")
public class FindAllTypesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FindAllTypesServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 权限拦截
		/*if (request.getSession().getAttribute("loginSuccess") == null|| !request.getSession().getAttribute("loginSuccess").equals("1")) {
			response.sendRedirect("login.jsp");
			return;
		}*/
		//调用业务层
		TypeBiz typeBiz = new TypeBizImpl();
		List<TypeVo> ls = typeBiz.findAllTypes();
		//给用户响应
		/*先从服务器拿到数据，然后发送到客户端
		 * request.setAttribute("ls", ls);
		request.getRequestDispatcher("bookAdd.jsp").forward(request, response);*/
		//使用JavaScript发送请求到服务器拿数据，应返回JavaScript类型内容
		response.setContentType("text/javascript;charset=utf-8");
		String js = "var types= [";
		for(int i=0;i<ls.size();i++) {
			js+="{id:"+ls.get(i).getId()+",name:'"+ls.get(i).getName()+"'}";
			if(i<ls.size()-1) {
				js+=",";
			}
		}
		js+="]";
		response.getWriter().write(js);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
