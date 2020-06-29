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
		 * 1.����������
		 * 2.��ȡ���ݿ�����
		 * 3.׼��sql�༭��
		 * 4.ִ��sql
		 * 5.�ر���Դ
		 * 
		 * 
		 * c3p0 �ӿ���������Դ���ٶ� �ʺϸ߲���
		 * 1.jar��
		 * 2.�����ļ� srcĿ¼�� ����д���Ķ�
		 * 
		 */
		
//		//1.����������
//		Class.forName("com.mysql.jdbc.Driver");
//		//2.��ȡ���ݿ�����
//		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitaldb","root","root");
		
		Connection conn = JDBCUtils.getConnection();
		
		//3.׼��sql�༭��
		String sql = "insert into users(name,email,status) values(?,?,?)";
		PreparedStatement pStatement = conn.prepareStatement(sql);
		pStatement.setString(1, "��");
		pStatement.setString(2, "zhangsan@163.com");
		pStatement.setInt(3, 1);
		
		//4.ִ��sql
		int update = pStatement.executeUpdate();
		
		JDBCUtils.releaseConnection(null, pStatement, conn);
	}
}
