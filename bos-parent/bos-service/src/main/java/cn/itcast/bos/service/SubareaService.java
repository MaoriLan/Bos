package cn.itcast.bos.service;

import java.util.List;

import cn.itcast.bos.entity.Region;
import cn.itcast.bos.entity.Subarea;
import cn.itcast.bos.utils.PageBean;

public interface SubareaService {

	void addSubarea(Subarea model);

	void queryPage(PageBean pb);

	List<Subarea> findAll();

	List<Subarea> findByCriteria();

	List<Subarea> findByDecidedzoneId(String id);

	List<Object> showHighcharts();

}
