package com.pd.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.SearchVO;

@Service
public class PdServiceImpl implements PdService {

	@Autowired
	private PdDAO pdDao;

	@Override
	public int insert(PdVO pdVo) {
		return pdDao.insert(pdVo);
	}

	@Override
	public PdVO detail(int no) {
		return pdDao.detail(no);
	}

	@Override
	public List<PdVO> selectAll(SearchVO searchVo) {
		return pdDao.selectAll(searchVo);
	}

	@Override
	public int getTotalRecord(SearchVO searchVo) {
		return pdDao.getTotalRecord(searchVo);
	}

	@Override
	public int edit(PdVO pdVo) {
		return pdDao.edit(pdVo);
	}

}
