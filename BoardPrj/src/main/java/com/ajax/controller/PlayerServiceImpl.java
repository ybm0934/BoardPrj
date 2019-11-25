package com.ajax.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.SearchVO;

@Service
public class PlayerServiceImpl implements PlayerService {
	
	@Autowired
	private PlayerDAO playerDao;

	@Override
	public List<PlayerVO> list(SearchVO searchVo) {
		return playerDao.list(searchVo);
	}

	@Override
	public int write(PlayerVO playerVo) {
		return playerDao.write(playerVo);
	}

}
