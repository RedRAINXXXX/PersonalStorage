package com.zzy.investeval.service;

import com.zzy.investeval.entity.Field;
import com.zzy.investeval.repository.FieldRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 技术领域分类业务逻辑接口实现类
 *
 * @author 赵正阳
 */
@Service
public class FieldServiceImpl implements FieldService {
	private final FieldRepository fieldRepository;

	@Autowired
	public FieldServiceImpl(FieldRepository fieldRepository) {
		this.fieldRepository = fieldRepository;
	}

	@Override
	public List<Field> findFieldByLevel(Integer level) {
		return fieldRepository.findByLevel(level);
	}

	@Override
	public Map<Integer, List<Field>> findChildFieldListByLevel(Integer level) {
		List<Field> parentFieldList = fieldRepository.findByLevel(level);
		Map<Integer, List<Field>> childListMap = new HashMap<>();
		parentFieldList.forEach(parentField -> childListMap.put(
				parentField.getId(), fieldRepository.findByParentId(parentField.getId())));
		return childListMap;
	}

}
