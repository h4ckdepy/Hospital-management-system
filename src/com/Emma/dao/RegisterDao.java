package com.Emma.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import com.Emma.bean.Doctor;
import com.Emma.bean.Register;
import com.Emma.util.DateConvertUtils;
import com.Emma.util.JDBCUtils;

public class RegisterDao {

	public List<Register> getRegisterList(Integer index, Integer pageSize, String rid1, String name1, String department1) {
		Connection conn = JDBCUtils.getConnection();
		List<Register> registers = new ArrayList<Register>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from register where 1=1 ";
		if(rid1 != null && rid1.trim() != ""){
			sql = sql +" and rid = '"+rid1+"'";
			
		}
		if(name1 != null && name1.trim() != ""){
			sql = sql + "and name like '%"+name1+"%'";
		}
		if(department1 != null && department1.trim() != "" && !department1.trim().equals("0")){
			sql = sql + " and department = "+department1;
		}
		sql = sql +" limit ?,?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, index);
			ps.setInt(2, pageSize);
			System.out.println(ps.toString());
			rs = ps.executeQuery();
			while(rs.next()){
				 String rid = rs.getString("rid");
				 String name = rs.getString("name");
				 String idCard = rs.getString("idCard");
				 String siNumber = rs.getString("siNumber");
				 Double registerMoney = rs.getDouble("registerMoney");
				 String phone = rs.getString("phone");
				 boolean ispay = rs.getBoolean("ispay");
				 boolean sex = rs.getBoolean("sex");
				 Integer age = rs.getInt("age");
				 boolean consultation = rs.getBoolean("consultation");
				 Integer department = rs.getInt("department");
				 Integer did = rs.getInt("did");
				 Integer status = rs.getInt("status");
				 String registerDate = rs.getString("registerDate");
				 Date date = DateConvertUtils.ConvertStringToDate(registerDate);
				 String remark = rs.getString("remark");
				 Register register = new Register(rid, name, idCard, siNumber, registerMoney, phone, ispay, sex, age, consultation, department, did, status, date, remark);
				 registers.add(register);
				 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return registers;
	}

	public int getTotalRecord(String rid, String name, String department) {
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select count(*) aa from register where 1=1 ";
		if(rid != null && rid.trim() != ""){
			sql = sql +"and rid = '"+rid+"'";
		}
		if(name != null && name.trim() != ""){
			sql = sql + "and name like '%"+name+"%'";
		}
		if(department != null && department.trim() != "" && !department.trim().equals("0")){
			sql = sql + "and department = "+department;
		}
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				int count = rs.getInt("aa");
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

	public Boolean checkIdCard(String idCard) {
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from register where idCard=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, idCard);
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

	public Boolean checkRid(String rid) {
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from register where rid=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, rid);
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

	public Boolean checkSiNumber(String siNumber) {
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from register where siNumber=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, siNumber);
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

	//增加挂号信息
	public void add(Register register) {
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = null;
		String sql = "insert into register(rid,name,idCard,siNumber,registerMoney,phone,isPay,sex,age,consultation,department,did,status,registerDate,remark) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, register.getRid());
			ps.setString(2, register.getName());
			ps.setString(3, register.getIdCard());
			ps.setString(4, register.getSiNumber());
			ps.setDouble(5, register.getRegisterMoney());
			ps.setString(6, register.getPhone());
			ps.setBoolean(7, register.isIspay());
			ps.setBoolean(8, register.isSex());
			ps.setInt(9, register.getAge());
			ps.setBoolean(10, register.isConsultation());
			ps.setInt(11, register.getDepartment());
			ps.setInt(12, register.getDid());
			ps.setInt(13, register.getStatus());
			ps.setString(14, DateConvertUtils.ConvertDateToString(register.getRegisterDate()));
			ps.setString(15, register.getRemark());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtils.releaseConnection(null, ps, conn);
		}
	}

	public Register detail(String rid) {
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from register where rid=?";
		Register register = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, rid);
			rs = ps.executeQuery();
			if(rs.next()){
				String registerId = rs.getString("rid");
				 String name = rs.getString("name");
				 String idCard = rs.getString("idCard");
				 String siNumber = rs.getString("siNumber");
				 Double registerMoney = rs.getDouble("registerMoney");
				 String phone = rs.getString("phone");
				 boolean ispay = rs.getBoolean("ispay");
				 boolean sex = rs.getBoolean("sex");
				 Integer age = rs.getInt("age");
				 boolean consultation = rs.getBoolean("consultation");
				 Integer department = rs.getInt("department");
				 Integer did = rs.getInt("did");
				 Integer status = rs.getInt("status");
				 String registerDate = rs.getString("registerDate");
				 Date date = DateConvertUtils.ConvertStringToDate(registerDate);
				 String remark = rs.getString("remark");
				 register = new Register(registerId, name, idCard, siNumber, registerMoney, phone, ispay, sex, age, consultation, department, did, status, date, remark);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return register;
	}

	public void delAll(String ids) {
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = null;
		String sql ="delete from register where rid in ("+ids+")";
		try {
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtils.releaseConnection(null, ps, conn);
		}
		
	}

	public void update(Register register) {
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = null;
		String sql = "update register set name=?,idCard=?,siNumber=?,registerMoney=?,phone=?,isPay=?,sex=?,age=?,consultation=?,department=?,did=?,status=?,registerDate=?,remark=? where rid=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(15, register.getRid());
			ps.setString(1, register.getName());
			ps.setString(2, register.getIdCard());
			ps.setString(3, register.getSiNumber());
			ps.setDouble(4, register.getRegisterMoney());
			ps.setString(5, register.getPhone());
			ps.setBoolean(6, register.isIspay());
			ps.setBoolean(7, register.isSex());
			ps.setInt(8, register.getAge());
			ps.setBoolean(9, register.isConsultation());
			ps.setInt(10, register.getDepartment());
			ps.setInt(11, register.getDid());
			ps.setInt(12, register.getStatus());
			ps.setString(13, DateConvertUtils.ConvertDateToString(register.getRegisterDate()));
			ps.setString(14, register.getRemark());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("更新失败");
		}finally{
			JDBCUtils.releaseConnection(null, ps, conn);
		}
		
	}

	
}
