package com.Emma.util;

import java.util.List;

public class Page<T> {
	  private List<T> list;      //���ݿ��ѯ�õ�
	  private Integer totalRecord;    //���ݿ��ѯ�õ�
	  
	  private Integer currentPage;//ǰ�˴�����
	  private Integer  pageSize;//ǰ�˴�����
	  
	//  private Integer  totalPage;   //����õ�
	//  private Integer  startIndex;  //����õ�
	  
	  
	  
	  public List<T> getList() {
	    return list;
	  }

	  public Page(Integer totalRecord, Integer currentPage, Integer pageSize) {
		super();
		this.totalRecord = totalRecord;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}

	public void setList(List<T> list) {
	    this.list = list;
	  }

	  public Integer getTotalRecord() {
	    return totalRecord;
	  }

	  public void setTotalRecord(Integer totleRecord) {
	    this.totalRecord = totleRecord;
	  }

	  public Integer getCurrentPage() {
	    if (currentPage <1) {
	    	currentPage=1;
		}
	    if (currentPage > getTotalPage()) {
	    	currentPage=getTotalPage();
		}
//	    System.out.println("currentPage:"+currentPage);
		  return currentPage;
	  }



	  public Integer getPageSize() {
	    return pageSize;
	  }

	  public void setPageSize(Integer pageSize) {
	    this.pageSize = pageSize;
	  }

	  public Integer getTotalPage() {
	    
	    //totalRecord          pageSize             totalPage
	    //     9                   3                    3
	    //     10                  3                    4
	    //     11                  3                    4
	    //     12                  3                    4
	    if(getTotalRecord()  %  getPageSize() == 0){    
	      return getTotalRecord()/getPageSize();
	    }else{
	      return getTotalRecord()/getPageSize()+1;
	    }
	  }

	//  public void setTotalPage(Integer totlePage) {
//	    this.totalPage = totlePage;
	//  }

	  public Integer getStartIndex() {
	    //select * from ����  limit startIndex pageSize
	    //currentPage        pageSize         startIndex
	    //      1               3                 0
	    //      2               3                 3
	    //      3               3                 6 
	    //       startIndex=(currentPage-1)*pageSize
	    
	    
	    return (getCurrentPage()-1)*pageSize;
	  }

	  public String toString() {
	    return "Page [totleRecord=" + totalRecord + ", currentPage=" + currentPage + ", pageSize=" + pageSize
	        + ", totlePage=" + getTotalPage() + ", startIndex=" + getStartIndex() + "]";
	  }
}
