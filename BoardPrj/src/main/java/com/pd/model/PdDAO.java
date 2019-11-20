package com.pd.model;

import java.util.List;

import com.common.SearchVO;

public interface PdDAO {
	
	public int insert(PdVO pdVo);
	public PdVO detail(int no);
	public List<PdVO> selectAll(SearchVO searchVo);
	public int getTotalRecord(SearchVO searchVo);
	public int edit(PdVO pdVo);
	public int delete(int no);

}
