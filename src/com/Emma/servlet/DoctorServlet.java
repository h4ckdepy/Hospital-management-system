package com.Emma.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Emma.bean.Doctor;
import com.Emma.service.DoctorService;
import com.Emma.util.DateConvertUtils;
import com.Emma.util.Page;
import com.google.gson.Gson;

/**
 * Servlet implementation class DoctorServlet
 */
@WebServlet("/doctor")
public class DoctorServlet extends BaseServlet {
	  private DoctorService doctorService = new DoctorService();
	  
	
	  
	  //�༭
	  public void edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		  String did = request.getParameter("did");
		  Doctor doctor=doctorService.detail(did);
		  request.setAttribute("doctor", doctor);
		  request.getRequestDispatcher("/doctor/edit.jsp").forward(request, response);;
	  }
	  //�޸�
	  public void update(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		  	 int did = Integer.parseInt(request.getParameter("did"));
		  	 String name = request.getParameter("name");
			 System.out.println(name);
			 String cardno = request.getParameter("cardno");
			 String phone = request.getParameter("phone");
			 String sex = request.getParameter("sex");
			 boolean gender =true;
			 if (sex.equals("0")) {
				 gender=false;
			 }			 
			 String birthday = request.getParameter("birthday");
			 Date date = DateConvertUtils.ConvertStringToDate(birthday);
			 int age = Integer.parseInt(request.getParameter("age"));
			 String email = request.getParameter("email"); 
			 int department = Integer.parseInt(request.getParameter("department"));
			 int education = Integer.parseInt(request.getParameter("education")); 
			 String remark = request.getParameter("remark");	 
			 Doctor doctor = new Doctor(did, name, cardno, phone, gender, age, date, email, department, education, remark);		 
			 doctorService.update(doctor);
			 response.sendRedirect(request.getServletContext().getContextPath()+"/doctor?method=getDoctorList");
		  
	  }
	  
	  
	  //����ɾ��
	  public void delAll(HttpServletRequest request,HttpServletResponse response) throws IOException {
		  String ids = request.getParameter("ids");
		  doctorService.delAll(ids);
		  response.sendRedirect(request.getServletContext().getContextPath()+"/doctor?method=getDoctorList");
		  
	  }
	  
	  //����
	  public void detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		  String did = request.getParameter("did");
		  Doctor doctor=doctorService.detail(did);
		  request.setAttribute("doctor", doctor);
		  request.getRequestDispatcher("/doctor/look.jsp").forward(request, response);;
	  }
	  
	  //���ҽ����Ϣ
	  public void add(HttpServletRequest request,HttpServletResponse response) throws IOException {
		 String name = request.getParameter("name");
		 System.out.println(name);
		 String cardno = request.getParameter("cardno");
		 String phone = request.getParameter("phone");
		 String sex = request.getParameter("sex");
		 boolean gender =true;
		 if (sex.equals("0")) {
			 gender=false;
		 }
		 String birthday = request.getParameter("birthday");
		 Date date = DateConvertUtils.ConvertStringToDate(birthday);		 
		 int age = Integer.parseInt(request.getParameter("age"));
		 String email = request.getParameter("email");		 
		 int department = Integer.parseInt(request.getParameter("department"));
		 int education = Integer.parseInt(request.getParameter("education"));		 
		 String remark = request.getParameter("remark");		 
		 Doctor doctor = new Doctor(null, name, cardno, phone, gender, age, date, email, department, education, remark);		 
		 doctorService.add(doctor);
		 response.sendRedirect(request.getServletContext().getContextPath()+"/doctor?method=getDoctorList");  	  
	  }
	  
	  
	  //ҽ���б�
	  public void getDoctorList(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
//	      List<Doctor> doctors = doctorService.getDoctorList();
	    String pageNum= request.getParameter("pageNum");  ///�����ǿմ�
	    String name = request.getParameter("name");  //��ѯ��������Ҳ�ǿյ�
	    String department = request.getParameter("department");
	    String queryStr = parseQueryString(name,department);
	    Page <Doctor> page = doctorService.getDoctorList(pageNum,name,department);  //ҽ�������Ϳ��Ҷ�����service
	    request.setAttribute("page", page);
	    request.setAttribute("queryStr", queryStr);//Я�����������
	    request.setAttribute("department", department);//Я�����������
//	    System.out.println(name);
	    request.setAttribute("name", name);//Я�����������
	    request.getRequestDispatcher("/doctor/index.jsp").forward(request, response);	      
	  }
	  private String parseQueryString(String name, String department) {
	    StringBuilder sb = new StringBuilder();  //�ɱ��ַ���
	    
	    if (name != null && name.trim() != ""){  //name��ֵ������� �Ѳ�������ǰ��
	        sb.append("&name=").append(name);
	        sb.append("&name=").append(name);
	      }
	      if (department != null && department.trim() != ""){
//	      if (Integer.parseInt(department) != 0){
	        sb.append("&department="+department);
	      }
	    return sb.toString();
	  }
	
	  
	//���֤Ψһ��
			public void checkCardno(HttpServletRequest request,HttpServletResponse response) throws IOException{
		        String cardno = request.getParameter("cardno");//��ȡemail
				
				boolean result = doctorService.checkCardno(cardno);
				Map<String,Object> map = new HashMap<String,Object>();//��map����ת����json�ַ���
				
				if(result){//�����û����Ƿ��Ѿ�����
					map.put("statusCode", 200);
					map.put("message", "�����֤�ſ��Ա�ע��");
				}else {
					map.put("statusCode", 5000);
					map.put("message", "�����֤���ѱ�ע��");
				}
				
				System.out.println(cardno);
				//����json���ݣ��ȸ��Gson�����ࣺ���Խ�����java�������͡�pojo��Map��List��Set��ת����json��������
				/**
				 * 1.����gson��Ӧ��jar��
				 * ����gson�����toJson��������java����ת����json��������
				 */
				Gson gson = new Gson();
				String json = gson.toJson(map);
				response.getWriter().write(json);
			}
			//�ֻ���Ψһ����֤
			public void checkPhone(HttpServletRequest request,HttpServletResponse response) throws IOException{
		        String phone = request.getParameter("phone");//��ȡphone
				System.out.println("�绰��"+phone);
				boolean result = doctorService.checkPhone(phone);
				Map<String,Object> map = new HashMap<String,Object>();//��map����ת����json�ַ���
				
				if(result){//�����ֻ����Ƿ��Ѿ�����
					map.put("statusCode", 200);
					map.put("message", "���ֻ��ſ��Ա�ע��");
				}else {
					map.put("statusCode", 5000);
					map.put("message", "���ֻ����ѱ�ע��");
				}
				
				System.out.println(phone);
				//����json���ݣ��ȸ��Gson�����ࣺ���Խ�����java�������͡�pojo��Map��List��Set��ת����json��������
				/**
				 * 1.����gson��Ӧ��jar��
				 * ����gson�����toJson��������java����ת����json��������
				 */
				Gson gson = new Gson();
				String json = gson.toJson(map);
				response.getWriter().write(json);
			}
			//����Ψһ��
			public void checkEmail(HttpServletRequest request,HttpServletResponse response) throws IOException{
		        String email = request.getParameter("email");//��ȡemail
				
				boolean result = doctorService.checkEmail(email);
				Map<String,Object> map = new HashMap<String,Object>();//��map����ת����json�ַ���
				
				if(result){//�����û����Ƿ��Ѿ�����
					map.put("statusCode", 200);
					map.put("message", "��������Ա�ע��");
				}else {
					map.put("statusCode", 5000);
					map.put("message", "�������ѱ�ע��");
				}
				
				System.out.println(email);
				//����json���ݣ��ȸ��Gson�����ࣺ���Խ�����java�������͡�pojo��Map��List��Set��ת����json��������
				/**
				 * 1.����gson��Ӧ��jar��
				 * ����gson�����toJson��������java����ת����json��������
				 */
				Gson gson = new Gson();
				String json = gson.toJson(map);
				response.getWriter().write(json);
			}
			
	 
}
