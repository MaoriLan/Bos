package cn.itcast.bos.service;

import java.util.List;

import cn.itcast.bos.entity.Decidedzone;
import cn.itcast.bos.utils.PageBean;

public interface DecidedZoneService {

	void save(Decidedzone model, String[] subareaId);

	void pqgeQuery(PageBean pb);

}
