package cn.itcast.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.itcast.bos.dao.UserDao;
import cn.itcast.bos.entity.User;


@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public User getUser(String username,String password) {
		String hql="from User where username=? and password=?";
		List<User> list = (List<User>) getHibernateTemplate().find(hql, username,password);
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
	}

	@Override
	public User findByUsername(String username) {
		String hql="from User where username=?";
		List<User> list = (List<User>) getHibernateTemplate().find(hql, username);
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
	}


}
