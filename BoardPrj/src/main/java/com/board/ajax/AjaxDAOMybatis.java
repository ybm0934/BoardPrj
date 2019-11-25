package com.board.prac;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.common.SearchVO;

@Repository
public class AjaxDAOMybatis implements AjaxDAO {
	
	private String namespace = "config.mybatis.mapper.oracle.ajax.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<AjaxVO> list(SearchVO searchVo) {
		List<AjaxVO> list = sqlSession.selectList(namespace + "selectAll", searchVo);
		return list;
	}

}
