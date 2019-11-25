package com.ajax.controller;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.common.SearchVO;

@Repository
public class PlayerDAOMybatis implements PlayerDAO {

	private String namespace = "config.mybatis.mapper.oracle.ajax.";

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<PlayerVO> list(SearchVO searchVo) {
		List<PlayerVO> list = sqlSession.selectList(namespace + "selectAll", searchVo);
		return list;
	}

	@Override
	public int write(PlayerVO playerVo) {
		int cnt = sqlSession.insert(namespace + "write", playerVo);
		return cnt;
	}

}
