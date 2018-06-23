package cn.itcast.bos.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import cn.itcast.bos.dao.RoleDao;
import cn.itcast.bos.entity.Role;
import cn.itcast.bos.utils.PageBean;


@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {
}
