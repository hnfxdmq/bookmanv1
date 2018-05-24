package cn.edu.nyist.bookmanv1.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//用注解表达出拦截哪些URL,value属性可以省略,/*表示拦截所有
@WebFilter("/*")
//使用过滤器实现权限拦截功能，消除重复代码
public class AuthFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		//放行login,login.jsp,css,js,验证码
		String url = req.getRequestURI();
		if(url.contains("/bower_components/")||url.contains("/login")||url.endsWith("vcode.png")) {
			chain.doFilter(request, response);
			return;
		}
		//放置重复代码
		// 权限拦截
		if (req.getSession().getAttribute("loginSuccess") == null|| !req.getSession().getAttribute("loginSuccess").equals("1")) {
			resp.sendRedirect("login.jsp");
			return;
		}else {//登录之后流程继续走
			chain.doFilter(request, response);
		}

	}

	@Override
	public void destroy() {
		

	}

}
