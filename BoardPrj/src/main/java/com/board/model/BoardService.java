package com.board.model;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.common.SearchVO;

public interface BoardService {
	
	public List<BoardVO> list(SearchVO searchVo);
	public int write(BoardVO boardVo);
	public List<Map<String, Object>> fileupload(HttpServletRequest request) throws Exception;
	public int downCount(int no);
	public BoardVO selectByNo(int no);

}
