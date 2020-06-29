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
	  
	
	  
	  //编辑
	  public void edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		  String did = request.getParameter("did");
		  Doctor doctor=doctorService.detail(did);
		  request.setAttribute("doctor", doctor);
		  request.getRequestDispatcher("/doctor/edit.jsp").forward(request, response);;
	  }
	  //修改
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
	  
	  
	  //批量删除
	  public void delAll(HttpServletRequest request,HttpServletResponse response) throws IOException {
		  String ids = request.getParameter("ids");
		  doctorService.delAll(ids);
		  response.sendRedirect(request.getServletContext().getContextPath()+"/doctor?method=getDoctorList");
		  
	  }
	  
	  //详情
	  public void detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		  String did = request.getParameter("did");
		  Doctor doctor=doctorService.detail(did);
		  request.setAttribute("doctor", doctor);
		  request.getRequestDispatcher("/doctor/look.jsp").forward(request, response);;
	  }
	  
	  //添加医生信息
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
	  
	  
	  //医生列表
	  public void getDoctorList(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
//	      List<Doctor> doctors = doctorService.getDoctorList();
	    String pageNum= request.getParameter("pageNum");  ///可能是空串
	    String name = request.getParameter("name");  //查询条件可能也是空的
	    String department = request.getParameter("department");
	    String queryStr = parseQueryString(name,department);
	    Page <Doctor> page = doctorService.getDoctorList(pageNum,name,department);  //医生姓名和科室都传到service
	    request.setAttribute("page", page);
	    request.setAttribute("queryStr", queryStr);//携带这参数传递
	    request.setAttribute("department", department);//携带这参数传递
//	    System.out.println(name);
	    request.setAttribute("name", name);//携带这参数传递
	    request.getRequestDispatcher("/doctor/index.jsp").forward(request, response);	      
	  }
	  private String parseQueryString(String name, String department) {
	    StringBuilder sb = new StringBuilder();  //可变字符串
	    
	    if (name != null && name.trim() != ""){  //name有值的情况下 把参数传回前端
	        sb.append("&name=").append(name);
	        sb.append("&name=").append(name);
	      }
	      if (department != null && department.trim() != ""){
//	      if (Integer.parseInt(department) != 0){
	        sb.append("&department="+department);
	      }
	    return sb.toString();
	  }
	
	  
	//身份证唯一性
			public void checkCardno(HttpServletRequest request,HttpServletResponse response) throws IOException{
		        String cardno = request.getParameter("cardno");//获取email
				
				boolean result = doctorService.checkCardno(cardno);
				Map<String,Object> map = new HashMap<String,Object>();//将map对象转换成json字符串
				
				if(result){//测试用户名是否已经存在
					map.put("statusCode", 200);
					map.put("message", "该身份证号可以被注册");
				}else {
					map.put("statusCode", 5000);
					map.put("message", "该身份证号已被注册");
				}
				
				System.out.println(cardno);
				//返回json数据：谷歌的Gson工具类：可以将任意java数据类型【pojo、Map、List、Set】转换成json对象类型
				/**
				 * 1.导入gson对应的jar包
				 * 调用gson对象的toJson方法：把java类型转换成json数据类型
				 */
				Gson gson = new Gson();
				String json = gson.toJson(map);
				response.getWriter().write(json);
			}
			//手机号唯一性验证
			public void checkPhone(HttpServletRequest request,HttpServletResponse response) throws IOException{
		        String phone = request.getParameter("phone");//获取phone
				System.out.println("电话："+phone);
				boolean result = doctorService.checkPhone(phone);
				Map<String,Object> map = new HashMap<String,Object>();//将map对象转换成json字符串
				
				if(result){//测试手机号是否已经存在
					map.put("statusCode", 200);
					map.put("message", "该手机号可以被注册");
				}else {
					map.put("statusCode", 5000);
					map.put("message", "该手机号已被注册");
				}
				
				System.out.println(phone);
				//返回json数据：谷歌的Gson工具类：可以将任意java数据类型【pojo、Map、List、Set】转换成json对象类型
				/**
				 * 1.导入gson对应的jar包
				 * 调用gson对象的toJson方法：把java类型转换成json数据类型
				 */
				Gson gson = new Gson();
				String json = gson.toJson(map);
				response.getWriter().write(json);
			}
			//邮箱唯一性
			public void checkEmail(HttpServletRequest request,HttpServletResponse response) throws IOException{
		        String email = request.getParameter("email");//获取email
				
				boolean result = doctorService.checkEmail(email);
				Map<String,Object> map = new HashMap<String,Object>();//将map对象转换成json字符串
				
				if(result){//测试用户名是否已经存在
					map.put("statusCode", 200);
					map.put("message", "该邮箱可以被注册");
				}else {
					map.put("statusCode", 5000);
					map.put("message", "该邮箱已被注册");
				}
				
				System.out.println(email);
				//返回json数据：谷歌的Gson工具类：可以将任意java数据类型【pojo、Map、List、Set】转换成json对象类型
				/**
				 * 1.导入gson对应的jar包
				 * 调用gson对象的toJson方法：把java类型转换成json数据类型
				 */
				Gson gson = new Gson();
				String json = gson.toJson(map);
				response.getWriter().write(json);
			}
			
	 
}
