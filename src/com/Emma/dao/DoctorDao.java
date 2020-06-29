package com.Emma.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.Emma.bean.Doctor;
import com.Emma.util.DateConvertUtils;
import com.Emma.util.JDBCUtils;

public class DoctorDao {
	//分页医生列表
	public List<Doctor> getDoctorList(Integer index, Integer pageSize, 
			String name1, String department1){  //page.getCurrentPage(),page.getPageSize()
		Connection conn = JDBCUtils.getConnection();
	      PreparedStatement ps = null;
	      ResultSet rs = null;
	      List<Doctor> doctors=new ArrayList<Doctor>();
	      //select * from doctor where 1=1 and name = ? and department = ? limit ?,?
	      String sql = "select * from doctor where 1=1 ";
	      if (name1 != null && name1.trim() != ""){ 
	        sql = sql+ "and name like '%"+name1+"%'";
	      }
	      if (department1 != null && department1.trim() != "" && !department1.trim().equals("0")){     
	        sql = sql+ " and department = "+department1;  
	      }
	      sql = sql+" limit ?,?";
	      try {
	        ps = conn.prepareStatement(sql);
	        ps.setInt(1, index);
	        ps.setInt(2, pageSize);
	        System.out.println(ps.toString());
	        rs = ps.executeQuery();
	        while(rs.next()){
	           int did = rs.getInt("did");
	                String name = rs.getString("name");
	                String cardno = rs.getString("cardno");
	                String phone = rs.getString("phone");
	                boolean sex = rs.getBoolean("sex");
	                int age = rs.getInt("age");
	                String birthday = rs.getString("birthday");
	                Date date = DateConvertUtils.ConvertStringToDate(birthday);
	                String email = rs.getString("email");
	                int department = rs.getInt("department");
	                int education = rs.getInt("education");
	                String remark = rs.getString("remark");
	                
	                Doctor doctor = new Doctor(did, name, cardno, phone, sex, age, date, email, department, education, remark);
	                doctors.add(doctor);
	      }
	    
	    } catch (SQLException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }finally {
	      JDBCUtils.releaseConnection(rs, ps, conn);
	    }
	    return doctors;
	  }
	  
	//查询总记录数
	public int getTotalRecoad(String name, String department) {
	    Connection conn = JDBCUtils.getConnection();
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    //select conut(*) from doctor where 1=1 and name = ? and  department = ?
	    String sql = "select count(*) aa from doctor where 1=1 ";  //aa是别名
	    if (name != null && name.trim() != ""){	      
	      sql = sql+ " and name like '%"+name+"%'";	      
	    }
	    if (department != null && department.trim() != "" && !department.trim().equals("0")){	      
	      sql = sql+ " and department = "+department;	      
	    }
	    try {
	      ps = conn.prepareStatement(sql);
	      rs = ps.executeQuery();
	      if(rs.next()){
	         int count = rs.getInt("aa");//总记录数
	         return count;
	      }
	    
	    } catch (SQLException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }finally {
	      JDBCUtils.releaseConnection(rs, ps, conn);
	    }
	    return 0;	    
	  }
	//添加医生
	public void add(Doctor doctor) {
		Connection conn = JDBCUtils.getConnection();
	    PreparedStatement ps = null;
	    String sql = "insert into doctor(name,cardno,phone,sex,"
	    		+ "age,birthday,email,department,education,remark)"
	    		+ " values(?,?,?,?,?,?,?,?,?,?)";	    
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, doctor.getName());
//			System.out.println(doctor.getName());
			ps.setString(2, doctor.getCardno());
			ps.setString(3, doctor.getPhone());
			ps.setBoolean(4, doctor.isSex());
			ps.setInt(5, doctor.getAge());
			ps.setString(6, DateConvertUtils.ConvertDateToString(doctor.getBirthday()));
			ps.setString(7, doctor.getEmail());
			ps.setInt(8, doctor.getdepartment());
			ps.setInt(9, doctor.getEducation());
			ps.setString(10, doctor.getRemark());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		      JDBCUtils.releaseConnection(null, ps, conn);
		}
		
	}
	//详情页
	public Doctor detail(String did) {
		Connection conn = JDBCUtils.getConnection();
	    PreparedStatement ps = null;
	    Doctor doctor =null;
	    ResultSet rs = null;
	    String sql = "select * from doctor where did="+did;      
	    try {
	      ps = conn.prepareStatement(sql);
	      rs = ps.executeQuery();
	      if(rs.next()){
	    	  int did2 = rs.getInt("did");
              String name = rs.getString("name");
              String cardno = rs.getString("cardno");
              String phone = rs.getString("phone");
              boolean sex = rs.getBoolean("sex");
              int age = rs.getInt("age");
              String birthday = rs.getString("birthday");
              Date date = DateConvertUtils.ConvertStringToDate(birthday);
              String email = rs.getString("email");
              int department = rs.getInt("department");
              int education = rs.getInt("education");
              String remark = rs.getString("remark");
              doctor = new Doctor(did2, name, cardno, phone, sex, age, 
            		  date, email, department, education, remark);             
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }finally {
	      JDBCUtils.releaseConnection(rs, ps, conn);
	    }
	    return doctor;
		
	}
	//批量删除
	public void delAll(String ids) {
		Connection conn = JDBCUtils.getConnection();
	    PreparedStatement ps = null;
	    String sql = "delete from doctor where did in ("+ids+")";
	    try {
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		    JDBCUtils.releaseConnection(null, ps, conn);
		}	    
	}
	//修改
	public void update(Doctor doctor) {
		Connection conn = JDBCUtils.getConnection();
	    PreparedStatement ps = null;
	    String sql = "update doctor set name=?, cardno=?, "
	    		+ "phone=?, sex=?, age=?, birthday=?, email=?, "
	    		+ "department=?, education=?, remark=? where did = ?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, doctor.getName());
			ps.setString(2, doctor.getCardno());
			ps.setString(3, doctor.getPhone());
			ps.setBoolean(4, doctor.isSex());
			ps.setInt(5, doctor.getAge());
			ps.setString(6,DateConvertUtils.ConvertDateToString(doctor.getBirthday()));//datetime和字符串的可以互相转换
			ps.setString(7, doctor.getEmail());
			ps.setInt(8, doctor.getdepartment());
			ps.setInt(9, doctor.getEducation());
			ps.setString(10, doctor.getRemark());
			ps.setInt(11, doctor.getDid());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		    JDBCUtils.releaseConnection(null, ps, conn);
		}		
	}


	
	
	
	public Boolean checkCardno(String cardno) {
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from doctor where cardno=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cardno);
			rs=ps.executeQuery();
			if(rs.next()){//有用户注册过了
				return false;
			}
			else {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.releaseConnection(rs, ps, conn);
		}
		return false;
	}
	public Boolean checkPhone(String phone) {
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from doctor where phone=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, phone);
//			System.out.println(ps.toString());
			rs=ps.executeQuery();
			if(rs.next()){//有用户注册过了
				return false;
			}
			else {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.releaseConnection(rs, ps, conn);
		}
		return false;
	}
	public Boolean checkEmail(String email) {
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from doctor where email=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs=ps.executeQuery();
			if(rs.next()){//有用户注册过了
				return false;
			}
			else {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.releaseConnection(rs, ps, conn);
		}
		return false;
	}
	
}






