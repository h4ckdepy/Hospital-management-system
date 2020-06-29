package com.Emma.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Emma.bean.Doctor;
import com.Emma.bean.Medicine;
import com.Emma.service.MedicineService;
import com.Emma.util.MyFileUtil;
import com.Emma.util.Page;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

@WebServlet("/medicine")
public class MedicineServlet  extends BaseServlet{
	
	private MedicineService medicineService = new MedicineService();
	
	//批量删除
	  public void delAll(HttpServletRequest request,HttpServletResponse response) throws IOException {
	    String ids = request.getParameter("ids");
	    medicineService.delAll(ids);
	    response.sendRedirect(request.getServletContext().getContextPath()+"/medicine?method=getMedicineList");
	    
	  }
	
	//添加药品
	public void add(HttpServletRequest request,HttpServletResponse response) throws IOException{
		    Map map = MyFileUtil.getFilePath(request);

	        String mid = UUID.randomUUID().toString();
	        String pictureUrl = null;
	        Double inPrice = Double.valueOf(map.get("inPrice").toString());
	        Double salPrice = Double.valueOf(map.get("salPrice").toString());
	        String name = map.get("name").toString();
	        Integer type = Integer.valueOf(map.get("type").toString());
	        String descs = map.get("descs").toString();
	        Integer qualityDate = Integer.valueOf(map.get("qualityDate").toString());
	        String description = map.get("description").toString();
	        String produceFirm = map.get("produceFirm").toString();
	        String readme = map.get("readme").toString();
	        String remark = map.get("remark").toString();
	        pictureUrl = map.get("filePath").toString();

		Medicine medicine = new Medicine(mid, pictureUrl, inPrice, salPrice, name, type, descs, qualityDate, description, produceFirm, readme, remark);
		medicineService.add(medicine);
		response.sendRedirect(request.getServletContext().getContextPath()+"/medicine?method=getMedicineList");
	}
	//编辑
	public void edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String mid=  request.getParameter("mid");
		Medicine medicine = medicineService.detail(mid);
		request.setAttribute("medicine", medicine);
		request.getRequestDispatcher("/medicine/edit.jsp").forward(request, response);
	}
	
	//修改
	public void update(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map map = MyFileUtil.getFilePath(request);
		for( Object ket:map.keySet()){
			System.out.println(ket.toString()+":"+map.get(ket.toString()));
		}
			String mid=  map.get("mid").toString();
	        String pictureUrl = null;
	        Double inPrice = Double.valueOf(map.get("inPrice").toString());
	        Double salPrice = Double.valueOf(map.get("salPrice").toString());
	        String name = map.get("name").toString();
	        Integer type = Integer.valueOf(map.get("type").toString());
	        String descs = map.get("descs").toString();
	        Integer qualityDate = Integer.valueOf(map.get("qualityDate").toString());
	        String description = map.get("description").toString();
	        String produceFirm = map.get("produceFirm").toString();
	        String readme = map.get("readme").toString();
	        String remark = map.get("remark").toString();
	        pictureUrl = map.get("filePath").toString();
	        Medicine medicine = new Medicine(mid, pictureUrl, inPrice, salPrice, name, type, descs, qualityDate, description, produceFirm, readme, remark);	        
	        medicineService.update(medicine);	        
	        response.sendRedirect(request.getServletContext().getContextPath()+"/medicine?method=getMedicineList");
	}
	
	//详情
	public void detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		 String mid = request.getParameter("mid");
		 System.out.println(mid);
		 Medicine medicine=medicineService.detail(mid);
		  request.setAttribute("medicine", medicine);
		  request.getRequestDispatcher("/medicine/look.jsp").forward(request, response);;
	}
		
	
	//药品列表
	public void getMedicineList(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
//		List<Medicine> medicines = medicineService.getMedicineList();
		
		String pageNum = request.getParameter("pageNum");
		System.out.println(pageNum);
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		
		String queryStr = parseQueryString(name,type);
		
		Page <Medicine> page = medicineService.getMedicineList(pageNum,name,type);  
	    request.setAttribute("page", page);
	    request.setAttribute("queryStr", queryStr);//携带这参数传递
	    request.setAttribute("type", type);
	    request.setAttribute("name", name);
	    request.getRequestDispatcher("/medicine/index.jsp").forward(request, response);
	      
		
		
//		request.setAttribute("medicines", medicines);
//		request.getRequestDispatcher("/medicine/index.jsp").forward(request, response);
	}
	private String parseQueryString(String name, String type) {
		StringBuilder sb = new StringBuilder();  //可变字符串
		if (name != null && name.trim() != ""){  //name有值的情况下 把参数传回前端			
			sb.append("&name=").append(name);
		}
		if (type != null && type.trim() != ""){
			sb.append("&type="+type);
		}
		return sb.toString();
	}
}
