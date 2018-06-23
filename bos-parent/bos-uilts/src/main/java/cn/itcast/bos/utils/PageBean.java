package cn.itcast.bos.utils;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public class PageBean {
    private int currentPage;
    private int total;
    private int pageSize;
    private List rows;
    private DetachedCriteria criteria;
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public PageBean(int currentPage, int total, int pageSize, List rows, DetachedCriteria criteria) {
		super();
		this.currentPage = currentPage;
		this.total = total;
		this.pageSize = pageSize;
		this.rows = rows;
		this.criteria = criteria;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	public DetachedCriteria getCriteria() {
		return criteria;
	}
	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setCriteria(DetachedCriteria criteria) {
		this.criteria = criteria;
	}
    
    
    
}
