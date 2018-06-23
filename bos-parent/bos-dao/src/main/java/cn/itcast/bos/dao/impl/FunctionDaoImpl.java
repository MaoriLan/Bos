package cn.itcast.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.itcast.bos.dao.FunctionDao;
import cn.itcast.bos.entity.Function;


@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements FunctionDao {
	public List<Function> findAll() {
		String hql="from Function where parentFunction is null";
		List<Function> list = (List<Function>) getHibernateTemplate().find(hql);
		return list;
	}

	public List<Function> findByUserId(String id) {
		String hql="select distinct f from Function f left outer join f.roles r left outer join r.users u where u.id=?";
		List<Function> list=(List<Function>) getHibernateTemplate().find(hql, id);
		return list;
	}

	public List<Function> findAllFunctions() {
		String hql = "FROM Function f WHERE f.generatemenu = '1' ORDER BY f.zindex asc";
		List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql);
		return list;
	} 
	public List<Function> findAllFunctionsByUserId(String id) {
		String hql = "SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles"
				+ " r LEFT OUTER JOIN r.users u WHERE u.id = ? AND f.generatemenu = '1' "
				+ "ORDER BY f.zindex asc";
		List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql, id);
		return list;
	}
}
