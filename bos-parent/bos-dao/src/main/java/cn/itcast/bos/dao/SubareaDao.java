package cn.itcast.bos.dao;

import java.util.List;

import cn.itcast.bos.entity.Region;
import cn.itcast.bos.entity.Subarea;

public interface SubareaDao  extends BaseDao<Subarea>{

	List<Object> showHighcharts();
}
