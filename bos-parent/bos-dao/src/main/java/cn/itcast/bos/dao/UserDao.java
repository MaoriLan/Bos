package cn.itcast.bos.dao;

import cn.itcast.bos.entity.User;

public interface UserDao extends BaseDao<User> {


	User getUser(String username, String password);

	User findByUsername(String username);

}
