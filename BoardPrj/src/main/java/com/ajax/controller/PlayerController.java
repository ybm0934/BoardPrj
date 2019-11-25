package com.ajax.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.common.SearchVO;

@Controller
@RequestMapping("/ajax")
public class PlayerController {

	private Logger logger = LoggerFactory.getLogger(PlayerController.class);

	@Autowired
	private PlayerService playerService;

	@RequestMapping("/list.do")
	public String list(@ModelAttribute SearchVO searchVo, Model model) {
		logger.info("글 목록 조회 파라미터 searchVo = {}", searchVo);
		
		List<PlayerVO> list = playerService.list(searchVo);
		logger.info("글 목록 조회 결과 list.size()={}", list.size());

		model.addAttribute("list", list);
		model.addAttribute("searchVo", searchVo);

		return "ajax/list";
	}

	@RequestMapping(value = "/write.do", method = RequestMethod.GET)
	public String write() {
		logger.info("글쓰기 화면 불러오기");
		
		return "ajax/list";
	}

	@RequestMapping(value = "/write.do", method = RequestMethod.POST)
	public String write(@ModelAttribute PlayerVO playerVo, Model model) {
		logger.info("글쓰기 처리 파라미터 playerVo = {}", playerVo);
		
		int cnt = playerService.write(playerVo);
		logger.info("글쓰기 처리 결과 cnt = {}", cnt);
		
		return "ajax/list";
		
	}
	
	@RequestMapping("/new.do")
	public String newWorld() {
		logger.info("새 화면 불러오기");
		
		return "ajax/new";
	}

}
