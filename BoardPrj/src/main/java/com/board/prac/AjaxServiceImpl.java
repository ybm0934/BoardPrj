package com.board.prac;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.SearchVO;

@Service
public class AjaxServiceImpl implements AjaxService {
	
	private Logger logger = LoggerFactory.getLogger(AjaxServiceImpl.class);
	
	@Autowired
	private AjaxDAO ajaxDao;

	@Override
	public List<AjaxVO> list(SearchVO searchVo) {
		return ajaxDao.list(searchVo);
	}

}
