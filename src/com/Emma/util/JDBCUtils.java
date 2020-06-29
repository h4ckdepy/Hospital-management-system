package com.Emma.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	
	public static ComboPooledDataSource dataSource = null;
	/**c3p0 加快连接数据源的速度 适合高并发省去每次获取数据库连接所需认证的时间
	 * 1.jar包
	 * 2.配置文件 src目录下 名字固定的 配置文件会从src目录下找 约定大于配置
	 * 
	 */
	static{
		dataSource = new ComboPooledDataSource("webDataSource");
		
	}
	
	
	public static Connection getConnection(){
		Connection conn = null;
		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitaldb?characterEncoding=utf8","root","root");
			System.out.println(dataSource.toString());
			conn = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return conn;
	
	}
	public static void releaseConnection (ResultSet rs , PreparedStatement ps, Connection connection){
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		把连接放回数据库连接池中
		if (connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
