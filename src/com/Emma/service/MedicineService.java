package com.Emma.service;

import java.util.List;

import com.Emma.bean.Medicine;
import com.Emma.dao.MedicineDao;
import com.Emma.util.ConstantUtils;
import com.Emma.util.Page;

public class MedicineService {
	MedicineDao medicineDao = new MedicineDao();
	
	//ҩƷ�б�
	public Page <Medicine> getMedicineList(String pageNum, String name, String type) {
		Page<Medicine> page = null;
	    int pageNo = 1;  //�������Ĳ����ǿմ� Ĭ�ϵ�һҳ 
	    try{
	      if(pageNum!=null){
	    	  pageNo = Integer.parseInt(pageNum);
	      }
	    }catch (Exception e) {
	      //e.printStackTrace();
	    }
	         
	    //1.��ѯ�м�¼��  totalRecord
	    int  totalRecord = medicineDao.getTotalRecoad(name,type);
	    //2.����һ��page����pageNum(pageNo��ǰҳ) totalRecord pageSize
	    page = new Page<Medicine>(totalRecord, pageNo, ConstantUtils.PAGE_SIZE);
	    
	    //3.��ѯ��ǰҳ���б����ݣ�����װ��page������
	    List<Medicine> list = medicineDao.getMedicineList(page.getStartIndex(),page.getPageSize(),name,type);
	   
	    page.setList(list);
	    //4.����page����
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
