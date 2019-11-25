package com.ajax.controller;

import java.util.List;

import com.common.SearchVO;

public interface PlayerDAO {
	
	public List<PlayerVO> list(SearchVO searchVo);
	public int write(PlayerVO playerVo);

}
