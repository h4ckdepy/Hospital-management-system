package com.Emma.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.Emma.bean.User;
import com.Emma.util.DateConvertUtils;
import com.Emma.util.JDBCUtils;

public class UserDao {
	
	//用户名的唯一性
	public boolean checkUserName(String username){
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from users where username = ?";
		try {
//			System.out.println("username");
			ps = conn.prepareStatement(sql);
//			System.out.println("aaaaaa");
			ps.setString(1, username);
			rs = ps.executeQuery();
			if(rs.next()){
				//注册过
				return false;
			}else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.releaseConnection(rs, ps, conn);
		}
		return false;
		
	}
		
	
	//邮箱的唯一性
	public boolean checkEmail(String username){
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from users where email = ?";
		try {
//			System.out.println("email");
			ps = conn.prepareStatement(sql);
//			System.out.println("bbbbbb");
			ps.setString(1, username);
			rs = ps.executeQuery();
			if(rs.next()){
				//注册过
				return false;
			}else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.releaseConnection(rs, ps, conn);
		}
		return false;
		
	}
	
	//注册
	public void regist(User user){
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = null;
		String sql = "insert into users(name,email,username,password,status,modifytime) values(?,?,?,?,?,?)";
		try {
//			System.out.println("regist");
			ps = conn.prepareStatement(sql);
//			System.out.println("cccccc");
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getUsername());
			ps.setString(4, user.getPassword());
			ps.setInt(5, user.getStatus());
			
			/***
			 * util.Date 父类    是由年月日十分秒的
			 * sql.Date 子类 只有时分秒
			 * 
			 */
//			ps.setDate(6, new Date(user.getModifytime().getTime()));
			Date Modifytime = user.getModifytime();
			SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
			String date = sdf.format(Modifytime);
			ps.setString(6, date);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.releaseConnection(null, ps, conn);
		}
		
	}


	public User login(User user) {
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs =null;
		String sql = "select * from users where username=? and password=?";
		try {
			ps=conn.prepareStatement(sql );
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			
			rs = ps.executeQuery();
			if (rs.next()) {
				int uid = rs.getInt("uid");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String username = rs.getString("username");
				int status = rs.getInt("status");
				String modifytime = rs.getString("modifytime");
				Date date = DateConvertUtils.ConvertStringToDate(modifytime);
				user = new User(uid, name, email, status, username, password, date);
				return user;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
