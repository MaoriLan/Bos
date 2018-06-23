package cn.itcast.bos.realm;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import cn.itcast.bos.dao.FunctionDao;
import cn.itcast.bos.dao.UserDao;
import cn.itcast.bos.entity.Function;
import cn.itcast.bos.entity.User;
import cn.itcast.bos.service.UserService;

public class BosRealm extends AuthorizingRealm {

	@Autowired
	private UserDao ud;
	@Autowired
	private FunctionDao fd;

	// 授权方法
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO 根据用户从而授予各种权限
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 获取当前用户,法一
		User user = (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
		// 法二
		/*
		 * User user2=(User) principals.getPrimaryPrincipal();
		 * System.out.println(user==user2);
		 */

		List<Function> list = null;
		if (user.getUsername().equals("admin")) {
			DetachedCriteria dc = DetachedCriteria.forClass(Function.class);
			list = fd.findByCriteria(dc);
		} else {
			list = fd.findByUserId(user.getId());
		}
		// 为用户授权

		for (Function f : list)
			info.addStringPermission(f.getCode());
		return info;
	}
	// shiro的认证方法

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken token2 = (UsernamePasswordToken) token;
		System.out.println(token2);
		String username = token2.getUsername();
		User user = ud.findByUsername(username);
		if (user == null)
			return null;
		AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		return info;
	}

}
