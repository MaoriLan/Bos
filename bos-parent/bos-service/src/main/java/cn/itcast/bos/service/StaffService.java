package cn.itcast.bos.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.bos.entity.Staff;
import cn.itcast.bos.utils.PageBean;

public interface StaffService {

	void saveStaff(Staff model);

	void pageQuery(PageBean pb);

	void deleteBantch(String id);

	void editStaff(Staff model);

	List<Staff> fingByCriteria();

	void restore(String staff);


}
