package com.board.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.board.model.BoardService;
import com.board.model.BoardVO;
import com.common.SearchVO;
import com.common.Utility;

@Controller
@RequestMapping("/board")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardService boardService;

	@RequestMapping("/list.do")
	public String list(@ModelAttribute SearchVO searchVo, Model model) {
		logger.info("글 목록 조회 파라미터 searchVo = {}", searchVo);

		List<BoardVO> list = boardService.list(searchVo);
		logger.info("글 목록 조회 결과 list.size = {}", list.size());

		model.addAttribute("list", list);

		return "board/list";
	}

	@RequestMapping(value = "/write.do", method = RequestMethod.GET)
	public String write_get(HttpServletRequest request, Model model) {
		logger.info("글쓰기 화면");

		BoardVO vo = Utility.getIp2(request);
		logger.info("ip 정보 조회 vo = {}", vo);

		model.addAttribute("ip", vo.getIp());
		model.addAttribute("port", vo.getPort());
		model.addAttribute("browser", vo.getBrowser());

		return "board/write";
	}

	@RequestMapping(value = "/write.do", method = RequestMethod.POST)
	public String write_post(@ModelAttribute BoardVO boardVo, MultipartHttpServletRequest request) throws IOException {
		logger.info("글쓰기 처리 파라미터 boardVo = {}", boardVo);

		// 파일 업로드 처리
		String fileName = "";
		String oName = "";
		double fileSize = 0;

		List<Map<String, Object>> resultList = null;
		try {
			resultList = boardService.fileupload(request);
			for (int i = 0; i < resultList.size(); i++) {
				Map<String, Object> fileInfoMap = resultList.get(i);
				fileName = (String) fileInfoMap.get("fileName");
				fileSize = (double) fileInfoMap.get("fileSize");
				oName = (String) fileInfoMap.get("oName");
			}
			logger.info("파일 업로드 성공, fileName={}, fileSize={}", fileName, fileSize);
		} catch (Exception e) {
			logger.info("파일 업로드 실패");
			e.printStackTrace();
		}

		boardVo.setFileName(fileName);
		boardVo.setFileSize(fileSize);
		boardVo.setOriginalFileName(oName);

		int cnt = boardService.write(boardVo);
		logger.info("글쓰기 처리 결과 cnt={}", cnt);

		return "redirect:/board/detail.do?no=" + boardVo.getNo();
	}

	@RequestMapping("/detail.do")
	public String detail(@RequestParam(defaultValue = "0") int no, Model model) {
		logger.info("글 상세보기 파라미터 no = {}", no);

		BoardVO boardVo = boardService.selectByNo(no);
		logger.info("글 상세보기 결과 vo = {}", boardVo);

		// 파일 첨부된 경우 파일정보와 다운로드 수 보여주기
		String fileName = boardVo.getFileName();
		String fileInfo = "";
		String getFileName = boardVo.getFileName();
		double getFileSize = boardVo.getFileSize() / 1024 / 1024;
		if (fileName != null && !fileName.isEmpty()) {
			if (fileName.length() > 45) {
				fileInfo = getFileName.substring(0, 15) + "....."
						+ getFileName.substring(getFileName.length() - 15, getFileName.length());
			} else {
				fileInfo = getFileName;
			}

			if (getFileSize < 1) {
				fileInfo += " (" + (Math.round((boardVo.getFileSize() / 1024) * 100) / 100.0) + " KB)";
			} else if (getFileSize >= 1) {
				fileInfo += " (" + (Math.round(getFileSize * 100) / 100.0) + " MB)";
			}
		}

		logger.info("파일정보 결과 fileInfo={}", fileInfo);
		
		model.addAttribute("vo", boardVo);
		model.addAttribute("fileInfo", fileInfo);
		
		return "board/detail";

	}

}
