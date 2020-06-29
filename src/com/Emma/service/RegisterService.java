package com.Emma.service;

import java.util.List;

import com.Emma.bean.Doctor;
import com.Emma.bean.Register;
import com.Emma.dao.RegisterDao;
import com.Emma.util.ConstantUtils;
import com.Emma.util.Page;

public class RegisterService {
	private  RegisterDao registerDao = new RegisterDao();
	
	
	
	
	public Page<Register> getRegisterList(String pageNum, String rid, String name, String department) {
		Page<Register> page = null;
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNum);
		} catch (Exception e) {
			// TODO: handle exception
		}
		//1.查询总记录数
		int totalRecord = registerDao.getTotalRecord(rid,name,department);
		//2.创建一个对象，pageNum【当前页】,totalRecord【总记录数】,pageSize【每页记录数】
		page = new Page<>(totalRecord, pageNo, ConstantUtils.PAGE_SIZE);
		//3.查询当前页的列表数据，并封装到page对象里面
		List<Register> registers = registerDao.getRegisterList(page.getStartIndex(),page.getPageSize(),rid,name,department);
		page.setList(registers);
		return page;
	}

	public  boolean checkIdCard(String idCard) {
		Boolean result = registerDao.checkIdCard(idCard);
		return result;
	}

	public boolean checkRid(String rid) {
		Boolean result = registerDao.checkRid(rid);
		return result;
	}

	public boolean checkSiNumber(String siNumber) {
		Boolean result = registerDao.checkSiNumber(siNumber);
		return result;
	}

	public void add(Register register) {
		registerDao.add(register);
		
	}

	public Register detail(String rid) {
		return registerDao.detail(rid);
	}

	public void delAll(String ids) {
		registerDao.delAll(ids);
		
	}

	public void update(Register register) {
		registerDao.update(register);
		
	}

}
