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
		//����ϴ�
		Part part=request.getPart("photo");
		String fileName=part.getHeader("Content-Disposition").split(";")[2].split("=")[1].replace("\"", "");
		//���IE���ļ�ȫ·��������
		fileName=fileName.lastIndexOf("\\")==-1?fileName:fileName.substring(fileName.lastIndexOf("\\")+1);
		//�������123.123.txt�����ļ���
		String ext=fileName.substring(fileName.lastIndexOf('.')+1);
		//ʹ��UUID�������һ�����ļ���
		String newFileName=UUID.randomUUID().toString()+"."+ext;
		part.write(request.getServletContext().getRealPath("upload/"+newFileName));
		//��ȡ����
		String name = request.getParameter("name");
		String descri = request.getParameter("descri");
		String author = request.getParameter("author");
		String stringPrice = request.getParameter("price");
		Double price = Double.parseDouble(stringPrice);
		String stringTid = request.getParameter("tid");
		int tid = Integer.parseInt(stringTid);
		//����ҵ����ϴ�
		BookBiz bookBiz = new BookBizImpl();
		int ret = bookBiz.saveBook(name,descri,author,price,tid,newFileName);
		//���û�һ������
		response.setContentType("text/html;charest=utf-8");
		if (ret>0) {
			response.getWriter().write("��ӳɹ�");
		} else {
			request.setAttribute("msg", "���ʧ��");
			request.getRequestDispatcher("bookAdd.jsp").forward(request, response);

		}
	}

}
