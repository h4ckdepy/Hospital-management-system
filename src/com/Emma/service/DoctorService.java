package com.Emma.service;

import java.util.List;

import com.Emma.bean.Doctor;
import com.Emma.dao.DoctorDao;
import com.Emma.util.ConstantUtils;
import com.Emma.util.Page;

public class DoctorService {
	
	private DoctorDao doctorDao = new DoctorDao();
    
	  public Page<Doctor> getDoctorList(String pageNum, String name, String department) {  //��ǰ�˴����ĵ�ǰҳ
	    Page<Doctor> page = null;
	    int pageNo = 1;  //�������Ĳ����ǿմ� Ĭ�ϵ�һҳ 
	    try{
	      pageNo = Integer.parseInt(pageNum);
	    }catch (Exception e) {
	      //e.printStackTrace();
	    }
	     
	    
	    //1.��ѯ�м�¼��  totalRecord
	    int  totalRecord = doctorDao.getTotalRecoad(name,department);
	    //2.����һ��page����pageNum(��ǰҳ) totalRecord pageSize
	    page = new Page<Doctor>(totalRecord, pageNo, ConstantUtils.PAGE_SIZE);
	    
	    //3.��ѯ��ǰҳ���б����ݣ�����װ��page������
	    List<Doctor> list = doctorDao.getDoctorList(page.getStartIndex(),page.getPageSize(),name,department);
	    System.out.println(page.getStartIndex());
	    page.setList(list);
	    //4.����page����
	    return page;
//	    return doctorDao.getDoctorList();
	  }

	public void add(Doctor doctor) {
		doctorDao.add(doctor);
		
	}

	public Doctor detail(String did) {
		return  doctorDao.detail(did);
		
	}

	public void delAll(String ids) {
		doctorDao.delAll(ids);
		
	}

	public void update(Doctor doctor) {
		doctorDao.update(doctor);
		
	}

	public boolean checkCardno(String cardno) {
		Boolean result = doctorDao.checkCardno(cardno);
		return result;
	}

	public boolean checkPhone(String phone) {
		Boolean result = doctorDao.checkPhone(phone);
		return result;
	}

	public boolean checkEmail(String email) {
		Boolean result = doctorDao.checkEmail(email);
		return result;
	}


}
