package com.board.prac;

import java.util.List;

import com.common.SearchVO;

public interface AjaxDAO {
	
	public List<AjaxVO> list(SearchVO searchVo);

}
