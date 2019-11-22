package com.board.model;

import java.util.List;

import com.common.SearchVO;

public interface BoardDAO {
	
	public List<BoardVO> list(SearchVO searchVo);
	public int write(BoardVO boardVo);
	public int downCount(int no);
	public BoardVO selectByNo(int no);
	public int edit(BoardVO boardVo);
	public int delete(int no);
	public int getTotalRecord(SearchVO searchVo);

}
