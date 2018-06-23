package cn.itcast.bos.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.google.common.base.Objects;

import cn.itcast.bos.dao.BaseDao;
import cn.itcast.bos.utils.PageBean;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{
	
	private Class<T> clazz;// 用于接收运行期泛型类型

	public BaseDaoImpl() {
		// 获得当前类型的带有泛型类型的父类，this代表的是运行时的类   eg:UserDaoImpl
		ParameterizedType ptClass = (ParameterizedType) this.getClass().getGenericSuperclass();
		System.out.println(ptClass.toString());
		// 获得运行期的父类上的泛型类型
		Type[] types = ptClass.getActualTypeArguments();
		clazz=(Class<T>) types[0];
		
	}
    @Resource
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}

	public void save(T entity) {
	    getHibernateTemplate().save(entity);
		
	}

	
	public void delete(T entity) {
	
		getHibernateTemplate().delete(entity);
	}

	
	public void deleteByID(Serializable id) {
	    T t=findById(id);
	    getHibernateTemplate().delete(t);
		
	}

	
	public void update(T entity) {
	   getHibernateTemplate().update(entity);
		
	}

	
	public T findById(Serializable id) {
	
		return (T) getHibernateTemplate().get(clazz, id);
	}

	
	public List<T> find(DetachedCriteria dc) {
	
		return (List<T>) getHibernateTemplate().findByCriteria(dc);
	}

	
	public void saveOrUpdate(T entity) {
	     getHibernateTemplate().saveOrUpdate(entity);
		
	}


	@Override
	public List<T> findAll() {
		String sql="from " +clazz.getSimpleName();
		return (List<T>) getHibernateTemplate().find(sql);
		
	}
	@Override
	public void executeUpdate(String queryName, Object... objects) {
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.getNamedQuery(queryName);
		int i=0;
		for(Object o:objects){
			query.setParameter(i++,o);
		}
		query.executeUpdate();
		
	}
	@Override
	public void pageQuery(PageBean pageBean) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria=pageBean.getCriteria();
		int currentPage=pageBean.getCurrentPage();
		int pageSize=pageBean.getPageSize();
		criteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(criteria);
		pageBean.setTotal(list.get(0).intValue());
		
		criteria.setProjection(null);
		criteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
		List rows=getHibernateTemplate().findByCriteria(criteria,(currentPage-1)*pageSize, pageSize);
		pageBean.setRows(rows);
	}
	@Override
	public List<T> findByCriteria(DetachedCriteria dc) {
		return (List<T>) getHibernateTemplate().findByCriteria(dc);
	}
	@Override
	public void executeFind(String queryName, Object... objects) {
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.getNamedQuery(queryName);
		int i=0;
		for(Object o:objects){
			query.setParameter(i++,o);
		}
		query.executeUpdate();
		
	}
	

}
