package com.Emma.service;

import java.util.List;

import com.Emma.bean.Medicine;
import com.Emma.dao.MedicineDao;
import com.Emma.util.ConstantUtils;
import com.Emma.util.Page;

public class MedicineService {
	MedicineDao medicineDao = new MedicineDao();
	
	//药品列表
	public Page <Medicine> getMedicineList(String pageNum, String name, String type) {
		Page<Medicine> page = null;
	    int pageNo = 1;  //若传来的参数是空串 默认第一页 
	    try{
	      if(pageNum!=null){
	    	  pageNo = Integer.parseInt(pageNum);
	      }
	    }catch (Exception e) {
	      //e.printStackTrace();
	    }
	         
	    //1.查询中记录数  totalRecord
	    int  totalRecord = medicineDao.getTotalRecoad(name,type);
	    //2.创建一个page对象pageNum(pageNo当前页) totalRecord pageSize
	    page = new Page<Medicine>(totalRecord, pageNo, ConstantUtils.PAGE_SIZE);
	    
	    //3.查询当前页的列表数据，并封装到page对象中
	    List<Medicine> list = medicineDao.getMedicineList(page.getStartIndex(),page.getPageSize(),name,type);
	   
	    page.setList(list);
	    //4.返回page对象
	    return page;
//		return  medicineDao.getMedicineList();
	}

	public Medicine detail(String mid) {
		return medicineDao.detail(mid);
	}

	public void update(Medicine medicine) {
		medicineDao.update(medicine);
		
	}

	public void add(Medicine medicine) {
		medicineDao.add(medicine);
		
	}

	public void delAll(String ids) {
		medicineDao.delAll(ids);
		
	}


}
