package com.Emma.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Emma.bean.Doctor;
import com.Emma.bean.Register;
import com.google.gson.Gson;
import com.Emma.service.RegisterService;
import com.Emma.util.DateConvertUtils;
import com.Emma.util.Page;


@WebServlet("/register")
public class RegisterServlet extends BaseServlet{
	private  RegisterService registerService = new RegisterService();
	public void getRegisterList(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String pageNum = request.getParameter("pageNum");
		String rid = request.getParameter("rid");//���˱��
		String name = request.getParameter("name");//��������
		String department = request.getParameter("department");//����
		String queryStr = parseQueryString(rid,name,department);
		Page<Register> page = registerService.getRegisterList(pageNum,rid,name,department);
		request.setAttribute("page", page);//���б���
		request.setAttribute("queryStr", queryStr);
		request.setAttribute("rid", rid);
		request.setAttribute("name", name);
		request.setAttribute("department", department);
		request.getRequestDispatcher("/register/index.jsp").forward(request, response);//ת����Я��������ת��
	}
	private String parseQueryString(String rid, String name, String department) {
		StringBuilder sb = new StringBuilder();
		if(rid != null && rid.trim() != ""){
			sb.append("&rid=").append(rid);
		}
		if(name != null && name.trim() != ""){
			sb.append("&name=").append(name);
		}
		if(department != null && department.trim() != ""){
			sb.append("&department=").append(department);
		}
		return sb.toString();
	}
	//���֤Ψһ��
		public void checkIdCard(HttpServletRequest request,HttpServletResponse response) throws IOException{
		    String idCard = request.getParameter("idCard");//��ȡemail
				
			boolean result = registerService.checkIdCard(idCard);
			Map<String,Object> map = new HashMap<String,Object>();//��map����ת����json�ַ���
				
			if(result){//�����û����Ƿ��Ѿ�����
				map.put("statusCode", 200);
				map.put("message", "�����֤�ſ��Ա�ע��");
			}else {
				map.put("statusCode", 5000);
				map.put("message", "�����֤���ѱ�ע��");
			}
				
			System.out.println(idCard);
				//����json���ݣ��ȸ��Gson�����ࣺ���Խ�����java�������͡�pojo��Map��List��Set��ת����json��������
				/**
				 * 1.����gson��Ӧ��jar��
				 * ����gson�����toJson��������java����ת����json��������
				 */
			Gson gson = new Gson();
			String json = gson.toJson(map);
			response.getWriter().write(json);
			}
		//������Ψһ����֤
		public void checkRid(HttpServletRequest request,HttpServletResponse response) throws IOException{
		    String rid = request.getParameter("rid");//��ȡemail
				
			boolean result = registerService.checkRid(rid);
			Map<String,Object> map = new HashMap<String,Object>();//��map����ת����json�ַ���
				
			if(result){//�����û����Ƿ��Ѿ�����
				map.put("statusCode", 200);
				map.put("message", "�����ſ��ԹҺ�");
			}else {
				map.put("statusCode", 5000);
				map.put("message", "�������ѱ��Һ�");
			}
				
			System.out.println(rid);
				//����json���ݣ��ȸ��Gson�����ࣺ���Խ�����java�������͡�pojo��Map��List��Set��ת����json��������
				/**
				 * 1.����gson��Ӧ��jar��
				 * ����gson�����toJson��������java����ת����json��������
				 */
			Gson gson = new Gson();
			String json = gson.toJson(map);
			response.getWriter().write(json);
			}
		//�籣��Ψһ����֤
				public void checkSiNumber(HttpServletRequest request,HttpServletResponse response) throws IOException{
				    String siNumber = request.getParameter("siNumber");//��ȡemail
						
					boolean result = registerService.checkSiNumber(siNumber);
					Map<String,Object> map = new HashMap<String,Object>();//��map����ת����json�ַ���
						
					if(result){//�����û����Ƿ��Ѿ�����
						map.put("statusCode", 200);
						map.put("message", "�籣�ſ�����");
					}else {
						map.put("statusCode", 5000);
						map.put("message", "�籣���ѱ��ù�");
					}
						
					System.out.println(siNumber);
						//����json���ݣ��ȸ��Gson�����ࣺ���Խ�����java�������͡�pojo��Map��List��Set��ת����json��������
						/**
						 * 1.����gson��Ӧ��jar��
						 * ����gson�����toJson��������java����ת����json��������
						 */
					Gson gson = new Gson();
					String json = gson.toJson(map);
					response.getWriter().write(json);
					}
				public void add(HttpServletRequest request,HttpServletResponse response) throws IOException{
					String rid = request.getParameter("rid");
					String name = request.getParameter("name");
					String idCard = request.getParameter("idCard");
					String siNumber = request.getParameter("siNumber");
					Double registerMoney = Double.valueOf(request.getParameter("registerMoney"));
					String phone = request.getParameter("phone");
					String ispay = request.getParameter("isPay");
					boolean pay = true;
					if(ispay.equals("1")){
						pay=false;
					}
					String sex = request.getParameter("sex");
					boolean gender = true;
					if(sex.equals("1")){
						gender=false;
					}
					int age = Integer.parseInt(request.getParameter("age"));
					String consultation=request.getParameter("consultation");
					boolean consultation1 = true;
					if(consultation.equals("1")){
						consultation1 = false;
					}
					int department = Integer.parseInt(request.getParameter("department"));
					
					int did = Integer.parseInt(request.getParameter("did"));
					
					int status = '1';
					
					Date registerDate = DateConvertUtils.ConvertStringToDate(request.getParameter("birthday"));
					String remark = request.getParameter("remark");
					Register register = new Register(rid, name, idCard, siNumber, registerMoney, phone, pay, gender, age, consultation1, department, did, status, registerDate, remark);
					registerService.add(register);
					response.sendRedirect(request.getServletContext().getContextPath()+"/register?method=getRegisterList");
				}
				public void detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
					String rid = request.getParameter("rid");
					Register register = registerService.detail(rid);
					request.setAttribute("register", register);
					request.getRequestDispatcher("/register/look.jsp").forward(request, response);
				}
				public void delAll(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
					String ids = request.getParameter("ids");
					registerService.delAll(ids);
					response.sendRedirect(request.getServletContext().getContextPath()+"/register?method=getRegisterList");
				}
				//�Һ�����
				public void edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
					String rid = request.getParameter("rid");
					Register register = registerService.detail(rid);
					request.setAttribute("register", register);
					request.getRequestDispatcher("/register/edit.jsp").forward(request, response);
				}
				//�޸�
				public void update(HttpServletRequest request,HttpServletResponse response) throws IOException{
					String rid = request.getParameter("rid");
					String name = request.getParameter("name");
					String idCard = request.getParameter("idCard");
					String siNumber = request.getParameter("siNumber");
					Double registerMoney = Double.valueOf(request.getParameter("registerMoney"));
					String phone = request.getParameter("phone");
					String ispay = request.getParameter("isPay");
					boolean pay = true;
					if(ispay.equals("1")){
						pay=false;
					}
					String sex = request.getParameter("sex");
					boolean gender = true;
					if(sex.equals("1")){
						gender=false;
					}
					int age = Integer.parseInt(request.getParameter("age"));
					
					String consultation=request.getParameter("consultation");
					
					boolean consultation1 = true;
					if(consultation.equals("1")){
						consultation1 = false;
					}
					
					int department = Integer.parseInt(request.getParameter("department"));
					int did = Integer.parseInt(request.getParameter("did"));
//					int status = Integer.parseInt(request.getParameter("status"));
					String remark = request.getParameter("remark");
					int status = 1;		
					Date registerDate = DateConvertUtils.ConvertStringToDate(request.getParameter("birthday"));
					System.out.println(remark);
					Register register = new Register(rid, name, idCard, siNumber, registerMoney, phone, pay, gender, age, consultation1, department, did, status, registerDate, remark);
					registerService.update(register);
					response.sendRedirect(request.getServletContext().getContextPath()+"/register?method=getRegisterList");
					
				}

}
