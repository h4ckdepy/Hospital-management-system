package com.Emma.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Emma.bean.User;
import com.Emma.service.UserService;
import com.google.gson.Gson;

@WebServlet("/user")
public class UserServlet extends BaseServlet{
	
	private UserService userService = new UserService();
	
	public void checkUserName(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String username = request.getParameter("username");
		
		boolean result = userService.checkUserName(username);
		Map<String,Object> map = new HashMap<String, Object>();
		
		if (result) {
			map.put("statusCode", 200);
			map.put("message", "���û����Ա�ע��");
			System.out.println(map.get("statusCode"));
		}else{
			map.put("statusCode", 500);
			map.put("message", "���û��Ѵ���");
		}
		
		//����json���� ���ȸ�Gson������ �����⣨pojo map list set��Java��������תλjson��������
		//��˸�ǰ�� ����״̬��  ����ɹ�����ʧ��
			
		Gson gson = new Gson();
	
		
		String json =gson.toJson(map);
	    //System.out.println(json);
	    response.getWriter().write(json);
		
	}
	
	
	
	
	//����Ψһ����֤	
	public void checkEmail(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String email = request.getParameter("email");
		
		boolean result = userService.checkEmail(email);
		Map<String,Object> map = new HashMap<String, Object>();
		
		if (result) {
			map.put("statusCode", 200);
			map.put("message", "��������Ա�ע��");
		}else{
			map.put("statusCode", 500);
			map.put("message", "�������Ѵ���");
		}
		
		//����json���� ���ȸ�Gson������ �����⣨pojo map list set��Java��������תλjson��������
		//��˸�ǰ�� ����״̬��  ����ɹ�����ʧ��
			
		Gson gson = new Gson();		
		String json =gson.toJson(map);
	    //System.out.println(json);
	    response.getWriter().write(json);
	}
	
	
	
	//ע��
	public void regist(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		User user = new User(null, name, email, 1, username, password, new Date());
		System.out.println(user.toString());
		userService.regist(user);
		response.sendRedirect(request.getServletContext().getContextPath()+"/login.jsp");
		
	}
	
	
	//��֤��
	public void checkVarifyCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String requsetCode = request.getParameter("code");
		String sessionCode = (String) request.getSession().getAttribute("verCode");
		Map<String,Object> map = new HashMap<String, Object>();
		if (requsetCode !=null && requsetCode.equals(sessionCode) ) {
			map.put("statusCode", 200);
			map.put("message", "��֤����ȷ");
		}else{
			map.put("statusCode", 600);
			map.put("message", "��֤�벻��ȷ");
		}
		Gson gson = new Gson();		
		String json =gson.toJson(map);
	    response.getWriter().write(json);
		
	}
	//��½
	public void login(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User(null, null, null, null, username, password, null);
		
		User loginUser = userService.login(user);
		
		if(loginUser==null){   //�ض��� ���ܴ�����
			request.setAttribute("errorMsg", "�˺Ż���������");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else {
			request.getSession().setAttribute("loginUser", loginUser);
			response.sendRedirect(request.getServletContext().getContextPath()+"/index.jsp");
		}
		
	}
	
}
