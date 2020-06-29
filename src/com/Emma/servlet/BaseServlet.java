package com.Emma.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class BaseServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.�����������
		request.setCharacterEncoding("utf-8");
		//2.������Ӧ����
		response.setContentType("text/html;charset=utf-8");
		
		//ͨ������ķ�����ִ�ж�Ӧ�ķ���
		String methodName = request.getParameter("method");
		
		Class<? extends BaseServlet> clazz = this.getClass();
		
		//����
		try {
			Method method = clazz.getMethod(methodName, HttpServletRequest.class , HttpServletResponse.class );
			method.invoke(this, request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			System.out.println("1111");
//			Method method = clazz.getMethod(methodName, HttpServletRequest.class , HttpServletResponse.class );
//			System.out.println("222");
//			method.invoke(this, request,response);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
	}
}
