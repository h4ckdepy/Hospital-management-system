package com.Emma.test;

import java.util.Date;

import org.junit.Test;

import com.Emma.bean.User;
import com.google.gson.Gson;

/**
 * 
 * @author Administrator
 *
 */

public class GsonTest {
	/**
	 * Gson�����ʹ��
	 * 1.����gson��Ӧjar��
	 * 2. ����gson��tojson����/;��java������תΪjson
	 */
	@Test   //ע��
	public  void testGson(){
		
		Gson gson = new Gson();
		User user = new User(100, "����", "11111@163.com", 1, "aaa", "123", new Date());
		
		String json = gson.toJson(user);
		System.out.println(json);
		
	}
	
}
