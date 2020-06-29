package com.Emma.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import com.Emma.util.JDBCUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sun.beans.infos.ComponentBeanInfo;

public class JDBCTest {
	@Test
	public void testConnection() throws ClassNotFoundException, SQLException{
		/**
		 * 1.加载驱动类
		 * 2.获取数据库连接
		 * 3.准备sql编辑器
		 * 4.执行sql
		 * 5.关闭资源
		 * 
		 * 
		 * c3p0 加快连接数据源的速度 适合高并发
		 * 1.jar包
		 * 2.配置文件 src目录下 名字写死的额
		 * 
		 */
		
//		//1.加载驱动类
//		Class.forName("com.mysql.jdbc.Driver");
//		//2.获取数据库连接
//		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitaldb","root","root");
		
		Connection conn = JDBCUtils.getConnection();
		
		//3.准备sql编辑器
		String sql = "insert into users(name,email,status) values(?,?,?)";
		PreparedStatement pStatement = conn.prepareStatement(sql);
		pStatement.setString(1, "张");
		pStatement.setString(2, "zhangsan@163.com");
		pStatement.setInt(3, 1);
		
		//4.执行sql
		int update = pStatement.executeUpdate();
		
		JDBCUtils.releaseConnection(null, pStatement, conn);
	}
}
