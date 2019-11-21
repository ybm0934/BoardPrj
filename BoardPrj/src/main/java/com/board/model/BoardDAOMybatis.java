package com.board.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.common.SearchVO;

@Repository
public class BoardDAOMybatis implements BoardDAO {

	private String namespace = "config.mybatis.mapper.oracle.board.";

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<BoardVO> list(SearchVO searchVo) {
		List<BoardVO> list = sqlSession.selectList(namespace + "selectAll", searchVo);
		return list;
	}

	@Override
	public int write(BoardVO boardVo) {
		int cnt = sqlSession.insert(namespace + "write", boardVo);
		return cnt;
	}

	@Override
	public int downCount(int no) {
		int cnt = sqlSession.update(namespace + "downCount", no);
		return cnt;
	}

	@Override
	public BoardVO selectByNo(int no) {
		BoardVO boardVo = sqlSession.selectOne(namespace + "selectByNo", no);
		return boardVo;
	}

}
