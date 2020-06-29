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
	 * Gson工类的使用
	 * 1.导入gson对应jar包
	 * 2. 调用gson的tojson方法/;把java对于像转为json
	 */
	@Test   //注解
	public  void testGson(){
		
		Gson gson = new Gson();
		User user = new User(100, "张三", "11111@163.com", 1, "aaa", "123", new Date());
		
		String json = gson.toJson(user);
		System.out.println(json);
		
	}
	
}
