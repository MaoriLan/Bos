package cn.itcast.bos.dao.impl;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.bos.dao.UserDao;
import cn.itcast.bos.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:applicationContext.xml")
public class UserDaoImplTest {
    
	@Resource(name="userDao")
	private UserDao ud;
	@Test
	public void test() {
		User u=ud.findById(1);
	}

}
