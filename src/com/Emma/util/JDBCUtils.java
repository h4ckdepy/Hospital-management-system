package com.Emma.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	
	public static ComboPooledDataSource dataSource = null;
	/**c3p0 �ӿ���������Դ���ٶ� �ʺϸ߲���ʡȥÿ�λ�ȡ���ݿ�����������֤��ʱ��
	 * 1.jar��
	 * 2.�����ļ� srcĿ¼�� ���̶ֹ��� �����ļ����srcĿ¼���� Լ����������
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
//		�����ӷŻ����ݿ����ӳ���
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
