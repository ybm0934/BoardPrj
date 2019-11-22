package com.board.prac;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.SearchVO;

@Controller
@RequestMapping("/ajax")
public class AjaxController {
	
	private Logger logger = LoggerFactory.getLogger(AjaxController.class);
	
	@Autowired
	private AjaxService ajaxService;
	
	@RequestMapping("/list.do")
	public String list(@ModelAttribute SearchVO searchVo, Model model) {
		logger.info("게시판 목록 조회 파라미터 searchVo={}", searchVo);
		
		return null;
	}
	

}
