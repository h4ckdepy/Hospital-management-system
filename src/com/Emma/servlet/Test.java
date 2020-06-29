package com.Emma.servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
@MultipartConfig
public class Test  extends BaseServlet{
	 public void add(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		 //获取上传的多部件
		Part part = request.getPart("picture");
		//获取头信息
		String header = part.getHeader("Content-Disposition");//获取文件的名字  aa.jpg
		//真实文件明名
		String fileName = header.substring(header.indexOf("filename")+10,header.length()-1);
		//
		String  realPath = request.getServletContext().getRealPath("/Images");
		File directory = new File(realPath);
		//判断页面是否村存在
		if (!directory.exists()) {
			directory.mkdir();
		}
		
		part.write(realPath+"//"+fileName);
		
		request.setAttribute("img", fileName);
		request.getRequestDispatcher("success.jsp").forward(request, response);
	 }

}
