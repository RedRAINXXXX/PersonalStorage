package com.zzy.investeval.service;

import com.zzy.investeval.entity.Field;

import java.util.List;
import java.util.Map;

/**
 * 技术领域分类业务逻辑接口
 *
 * @author 赵正阳
 */
public interface FieldService {

	/** 根据级别查询技术领域 */
	List<Field> findFieldByLevel(Integer level);

	/** 根据级别查询子领域列表，返回该级别领域id到子领域列表的映射 */
	Map<Integer, List<Field>> findChildFieldListByLevel(Integer level);

}
