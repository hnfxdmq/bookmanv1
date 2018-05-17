package cn.edu.nyist.bookmanv1.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import cn.edu.nyist.bookmanv1.biz.BookBiz;
import cn.edu.nyist.bookmanv1.biz.impl.BookBizImpl;

@WebServlet("/bookAdd")
@MultipartConfig
public class BookAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookAddServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//解决上传
		Part part=request.getPart("photo");
		String fileName=part.getHeader("Content-Disposition").split(";")[2].split("=")[1].replace("\"", "");
		//解决IE下全路径文件名
		fileName=fileName.lastIndexOf("\\")==-1?fileName:fileName.substring(fileName.lastIndexOf("\\")+1);
		//存在类似123.123.txt这种文件名
		String ext=fileName.substring(fileName.lastIndexOf('.')+1);
		//ʹ使用UUID产生随机文件名
		String newFileName=UUID.randomUUID().toString()+"."+ext;
		part.write(request.getServletContext().getRealPath("upload/"+newFileName));
		//获取用户输入参数
		String name = request.getParameter("name");
		String descri = request.getParameter("descri");
		String author = request.getParameter("author");
		String stringPrice = request.getParameter("price");
		Double price = Double.parseDouble(stringPrice);
		String stringTid = request.getParameter("tid");
		int tid = Integer.parseInt(stringTid);
		String strPubDate = request.getParameter("pubDate");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date pubDate = null;
		try {
			pubDate = sdf.parse(strPubDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//调用业务层保存
		BookBiz bookBiz = new BookBizImpl();
		int ret = bookBiz.saveBook(name,descri,author,price,tid,newFileName,pubDate);
		//给用户反馈
		response.setContentType("text/html;charest=utf-8");
		if (ret>0) {
			response.getWriter().write("添加成功");
		} else {
			request.setAttribute("msg", "添加失败");
			request.getRequestDispatcher("bookAdd.jsp").forward(request, response);

		}
	}

}
