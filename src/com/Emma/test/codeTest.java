package com.Emma.test;

import org.junit.Test;

import com.Emma.util.CodeUtils;

public class codeTest {
	@Test
	public  void test01() throws Exception {
		byte[] aa = CodeUtils.encryptMD5("123456".getBytes());
		String ss= new String(aa);
		System.out.println(ss);
	}
}
