package com.Emma.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.Emma.bean.Medicine;
import com.Emma.util.JDBCUtils;

public class MedicineDao {

	//分页获取药品列表
	public List<Medicine> getMedicineList(Integer startIndex, Integer pageSize, String name1, String type1) {
		 Connection conn = JDBCUtils.getConnection();
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 List<Medicine> medicines=new ArrayList<Medicine>();
		 String sql="select * from medicine where 1=1 ";
		 if (name1 != null && name1.trim() != ""){
			 sql = sql+ "and name like '%"+name1+"%'";	      
		 }
		 if (type1 != null && type1.trim() != "" && !type1.trim().equals("0")){	      
		     sql = sql+ " and type = "+type1;	      
		 }
		 sql = sql+" limit ?,?";		 
		 
		 try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, startIndex);
			ps.setInt(2, pageSize);
			System.out.println(ps.toString());
			rs=ps.executeQuery();
			while(rs.next()){				
				String mid = rs.getString("mid");
				String picture = rs.getString("picture");
				double inPrice = rs.getDouble("inPrice");
				double salPrice = rs.getDouble("salPrice");
				String name = rs.getString("name"); 
				int type = rs.getInt("type");
				String descs = rs.getString("descs");
				int qualityDate = rs.getInt("qualityDate");
				String description = rs.getString("description");
				String produceFirm = rs.getString("produceFirm");
				String readme = rs.getString("readme");
				String remark = rs.getString("remark");
				
				Medicine medicine = new Medicine(mid, picture, inPrice, salPrice, name, type, descs, qualityDate, description, produceFirm, readme, remark);
				
				medicines.add(medicine);		
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.releaseConnection(rs, ps, conn);
		}		 
		return medicines;
	}

	
	//获取查询药品的总数
	public int getTotalRecoad(String name, String type) {
		Connection conn = JDBCUtils.getConnection();
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    String sql = "select count(*) aa from medicine where 1=1 ";  //aa是别名
	    if (name != null && name.trim() != ""){	      
	      sql = sql+ " and name like '%"+name+"%'";	      
	    }
	    if (type != null && type.trim() != "" && !type.trim().equals("0")){	      
	      sql = sql+ " and type = "+type;	      
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

	//药品详情
	public Medicine detail(String mid1) {
		Connection conn = JDBCUtils.getConnection();
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    Medicine medicine =null;
	    String sql = "select * from medicine where mid='"+mid1+"'";  
	    try {
			ps=conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				String mid = rs.getString("mid");
				String picture = rs.getString("picture");
				double inPrice = rs.getDouble("inPrice");
				double salPrice = rs.getDouble("salPrice");
				String name = rs.getString("name"); 
				int type = rs.getInt("type");
				String descs = rs.getString("descs");
				int qualityDate = rs.getInt("qualityDate");
				String description = rs.getString("description");
				String produceFirm = rs.getString("produceFirm");
				String readme = rs.getString("readme");
				String remark = rs.getString("remark");				
				medicine = new Medicine(mid, picture, inPrice, salPrice, name, type, descs, qualityDate, description, produceFirm, readme, remark);				
				System.out.println(medicine.toString());
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.releaseConnection(rs, ps, conn);
		}
		return medicine;
	}

	//修改
	public void update(Medicine medicine) {
		Connection conn = JDBCUtils.getConnection();
	    PreparedStatement ps = null;
	    String sql = "update medicine set picture=?, inPrice=?, salPrice=?, name=?, "
	    		+ "type=?, descs=?, qualityDate=?, description=?, "
	    		+ "produceFirm=?, readme=? ,remark=? where mid = ?";
	    
	    try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, medicine.getPicture());
			ps.setDouble(2, medicine.getInPrice());
			ps.setDouble(3, medicine.getSalPrice());
			ps.setString(4, medicine.getName());
			ps.setInt(5, medicine.getType());
			ps.setString(6, medicine.getDescs());
			ps.setInt(7, medicine.getQualityDate());
			ps.setString(8, medicine.getDescription());
			ps.setString(9, medicine.getProduceFirm());
			ps.setString(10, medicine.getReadme());
			ps.setString(11, medicine.getRemark());
			ps.setString(12, medicine.getMid());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.releaseConnection(null, ps, conn);
		}
	}

	//添加药品
	  public void add(Medicine medicine) {
	    Connection conn = JDBCUtils.getConnection();
	      PreparedStatement ps = null;
	      String sql = "insert into medicine(mid,picture,inPrice,salPrice,name,"
	      		+ "type,descs,qualityDate,description,produceFirm,readme,remark) "
	      		+ "values(?,?,?,?,?,?,?,?,?,?,?,?)";      
	    try {
	      ps = conn.prepareStatement(sql);
	      ps.setString(1, medicine.getMid());
	      ps.setString(2, medicine.getPicture());
	      ps.setDouble(3, medicine.getInPrice());
	      ps.setDouble(4, medicine.getSalPrice());
	      ps.setString(5, medicine.getName());
	      ps.setInt(6, medicine.getType());
	      ps.setString(7, medicine.getDescs());
	      ps.setInt(8, medicine.getQualityDate());
	      ps.setString(9, medicine.getDescription());
	      ps.setString(10, medicine.getProduceFirm());
	      ps.setString(11, medicine.getReadme());
	      ps.setString(12, medicine.getRemark());
	      ps.executeUpdate();
	    } catch (SQLException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }finally {
	          JDBCUtils.releaseConnection(null, ps, conn);
	    }  
	  }


	//删除药品
	  public void delAll(String ids) {
	    Connection conn = JDBCUtils.getConnection();
	      PreparedStatement ps = null;
	      String sql = "delete from medicine where mid in ("+ids+")";
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

	

	

}
