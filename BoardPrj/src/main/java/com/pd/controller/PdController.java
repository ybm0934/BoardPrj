package com.pd.controller;

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

import com.common.PaginationInfo;
import com.common.SearchVO;
import com.common.Utility;
import com.pd.model.PdService;
import com.pd.model.PdVO;

@Controller
@RequestMapping("/pd")
public class PdController {

	private static final Logger logger = LoggerFactory.getLogger(PdController.class);

	@Autowired
	private PdService pdService;

	@RequestMapping(value = "/write.do", method = RequestMethod.GET)
	public String write_get() {
		logger.info("글쓰기 화면");

		return "pd/write";
	}

	@RequestMapping(value = "/write.do", method = RequestMethod.POST)
	public String write_post(@ModelAttribute PdVO pdVo) {
		logger.info("글쓰기 처리 파라미터 pdVo={}", pdVo);

		int cnt = pdService.insert(pdVo);
		logger.info("글쓰기 처리 결과 cnt={}", cnt);

		return "redirect:/pd/detail.do?no=" + pdVo.getNo();
	}

	@RequestMapping("/detail.do")
	public String detail(@RequestParam(defaultValue = "0") int no, Model model) {
		logger.info("상세보기 파라미터 no={}", no);

		PdVO pdVo = pdService.detail(no);
		logger.info("상세보기 결과 pdVo={}", pdVo);

		model.addAttribute("pdVo", pdVo);

		return "pd/detail";
	}

	@RequestMapping("/list.do")
	public String list(@ModelAttribute SearchVO searchVo, Model model) {
		logger.info("글 목록, 파라미터 searchVo={}", searchVo);

		PaginationInfo pagingInfo = new PaginationInfo();
		pagingInfo.setBlockSize(Utility.BLOCK_SIZE);
		pagingInfo.setRecordCountPerPage(Utility.RECORD_COUNT_PER_PAGE);
		pagingInfo.setCurrentPage(searchVo.getCurrentPage());

		searchVo.setRecordCountPerPage(Utility.RECORD_COUNT_PER_PAGE);
		searchVo.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
		logger.info("셋팅 후 searchVo={}", searchVo);

		List<PdVO> list = pdService.selectAll(searchVo);
		logger.info("글 목록 조회 결과 list.size={}", list.size());

		int totalRecord = pdService.getTotalRecord(searchVo);
		pagingInfo.setTotalRecord(totalRecord);
		logger.info("전체 레코드 개수={}", totalRecord);

		model.addAttribute("list", list);
		model.addAttribute("pageVo", pagingInfo);

		return "pd/list";
	}

	@RequestMapping("/edit1.do")
	public String edit1(@ModelAttribute PdVO pdVo, Model model) {
		logger.info("수정 화면 파라미터, pdVo={}", pdVo);

		pdVo = pdService.detail(pdVo.getNo());
		logger.info("수정화면 불러오기 결과, pdVo={}", pdVo);
		model.addAttribute("pdVo", pdVo);
		String view = "pd/edit";

		return view;
	}

	@RequestMapping("/edit2.do")
	public String edit2(@ModelAttribute PdVO pdVo, Model model) {
		logger.info("수정 처리 파라미터 pdVo={}", pdVo);

		int cnt = pdService.edit(pdVo);
		logger.info("수정 처리 결과 cnt={}", cnt);

		String msg = "", url = "/pd/detail.do?no=" + pdVo.getNo();
		if (cnt > 0) {
			msg = "글 수정 성공";
		} else {
			msg = "글 수정 실패";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		return "inc/message";
	}

	@RequestMapping("/delete.do")
	public String delete(@RequestParam(defaultValue = "0") int no, Model model) {
		logger.info("삭제 처리 파라미터 no={}", no);

		int cnt = pdService.delete(no);
		logger.info("삭제 처리 결과 cnt={}", cnt);

		String msg = "", url = "/pd/list.do";
		if (cnt > 0) {
			msg = "글 삭제 성공";
		} else {
			msg = "글 수정 실패";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		return "inc/message";
	}

}
