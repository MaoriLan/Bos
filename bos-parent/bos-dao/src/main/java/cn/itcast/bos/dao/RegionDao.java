package cn.itcast.bos.dao;

import java.util.List;

import cn.itcast.bos.entity.Region;

public interface RegionDao extends BaseDao<Region> {

	List<Region> findByString(String q);

}
