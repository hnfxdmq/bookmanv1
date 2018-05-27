package cn.edu.nyist.bookmanv1.web;

import java.io.IOException;
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
import cn.edu.nyist.bookmanv1.util.MyBeanUtils;
import cn.edu.nyist.bookmanv1.vo.BookVo;

@WebServlet("/doBookEdit")
@MultipartConfig
public class DoBookEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DoBookEditServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//解决上传
		Part part=request.getPart("photo");
		String fileName=part.getHeader("Content-Disposition").split(";")[2].split("=")[1].replace("\"", "");
		String newFileName="";
		if(!fileName.equals("")) {//根据文件名是否为空判断用户是否更改图片
			//解决IE下全路径文件名
			fileName=fileName.lastIndexOf("\\")==-1?fileName:fileName.substring(fileName.lastIndexOf("\\")+1);
			//存在类似123.123.txt这种文件名
			String ext=fileName.substring(fileName.lastIndexOf('.')+1);
			//ʹ使用UUID产生随机文件名
			newFileName=UUID.randomUUID().toString()+"."+ext;
			part.write(request.getServletContext().getRealPath("upload/"+newFileName));
		}
		//获取用户输入参数
		//一个个保存参数太复杂，因此使用一个类对其进行包装
		BookVo bookVo = new BookVo();
		//BeanUtils无法转换日期格式
		MyBeanUtils.populate(bookVo, request.getParameterMap(), "yyyy-MM-dd");
		if(!fileName.equals("")) {
			bookVo.setPhoto(newFileName);
		}
		//调用业务层保存
		BookBiz bookBiz = new BookBizImpl();
		int ret = bookBiz.editBook(bookVo);
		//给用户反馈
		response.setContentType("text/html;charest=utf-8");
		if (ret>0) {
			//response.getWriter().write("添加成功");
			response.sendRedirect("bookList");
		} else {
			request.setAttribute("msg", "添加失败");
			request.setAttribute("bookVo", bookVo);
			request.getRequestDispatcher("bookEdit.jsp").forward(request, response);

		}
	}

}
