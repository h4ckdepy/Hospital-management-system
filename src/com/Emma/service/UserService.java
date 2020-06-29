package com.Emma.service;

import com.Emma.bean.User;
import com.Emma.dao.UserDao;
import com.Emma.util.CodeUtils;

public class UserService {
		
	private  UserDao useDao = new UserDao();

	public boolean checkUserName(String username) {
		boolean result = useDao.checkUserName(username);
		return result ;
	}
	

	public boolean checkEmail(String email) {
		return useDao.checkEmail(email);
	}
	
	//¼ÓÃÜ×¢²á
	public void regist(User user) {
		String password = user.getPassword();
		byte [] bytes =password.getBytes();
		
		try {
			byte [] encryptMD5 = CodeUtils.encryptMD5(bytes);
			password = new String(encryptMD5);
			user.setPassword(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		useDao.regist(user);
		
	}

	//µÇÂ½
	public User login(User user) {
		byte[] encryptMD5;
		try {
			encryptMD5 = CodeUtils.encryptMD5(user.getPassword().getBytes());
			user.setPassword(new String (encryptMD5));
			return useDao.login(user);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
