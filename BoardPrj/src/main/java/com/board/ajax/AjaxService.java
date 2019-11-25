package com.board.prac;

import java.util.List;

import com.common.SearchVO;

public interface AjaxService {
	
	public List<AjaxVO> list(SearchVO searchVo);

}
