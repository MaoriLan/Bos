package cn.itcast.bos.service;

import java.util.List;

import cn.itcast.bos.entity.Region;
import cn.itcast.bos.utils.PageBean;

public interface RegionService {

	void saveBatch(List<Region> list);

	void queryRegion(PageBean pb);

	List<Region> findByString(String q);

	List<Region> findAll();

	void saveRefion(Region model);

	void deleteBantch(String staff);

}
