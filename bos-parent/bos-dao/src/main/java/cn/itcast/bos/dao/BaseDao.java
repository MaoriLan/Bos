package cn.itcast.bos.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.bos.utils.PageBean;

public interface BaseDao<T>{
	//增
    public void save(T entity);
    //删
	public void delete(T entity);
	public void deleteByID(Serializable id);
	//改
	public void update(T entity);
	//查
	public T findById(Serializable id);
	//更新
	public void saveOrUpdate(T entity);
	public List<T> findAll();
	public void executeUpdate(String queryName,Object...objects);
	public void executeFind(String queryName,Object...objects);
	public void  pageQuery(PageBean pageBean);
	public List<T> findByCriteria(DetachedCriteria dc);
}   
