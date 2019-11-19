package com.pd.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.common.SearchVO;

@Repository
public class PdDAOMybatis implements PdDAO {

	private String namespace = "config.mybatis.mapper.oracle.pd.";

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int insert(PdVO pdVo) {
		int cnt = sqlSession.insert(namespace + "insert", pdVo);
		return cnt;
	}

	@Override
	public PdVO detail(int no) {
		PdVO pdVo = sqlSession.selectOne(namespace + "detail", no);
		return pdVo;
	}

	@Override
	public List<PdVO> selectAll(SearchVO searchVo) {
		List<PdVO> list = sqlSession.selectList(namespace + "selectAll", searchVo);
		return list;
	}

	@Override
	public int getTotalRecord(SearchVO searchVo) {
		int cnt = sqlSession.selectOne(namespace + "getTotalRecord", searchVo);
		return cnt;
	}

	@Override
	public int edit(PdVO pdVo) {
		int cnt = sqlSession.update(namespace + "edit", pdVo);
		return cnt;
	}

}
