package cn.itcast.bos.dao;

import java.util.List;

import cn.itcast.bos.dao.BaseDao;
import cn.itcast.bos.entity.Function;

public interface FunctionDao extends BaseDao<Function>{

	List<Function> findByUserId(String id);

	List<Function> findAllFunctions();
	List<Function> findAllFunctionsByUserId(String id);

}
