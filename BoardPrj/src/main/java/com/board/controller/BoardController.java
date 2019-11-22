package com.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
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

	@Resource(name = "fileUploadProperties")
	Properties fileuploadProperties;

	@RequestMapping("/list.do")
	public String list(@ModelAttribute SearchVO searchVo, Model model) {
		logger.info("글 목록 조회 파라미터 searchVo = {}", searchVo);

		List<BoardVO> list = boardService.list(searchVo);
		logger.info("글 목록 조회 결과 list.size = {}", list.size());

		model.addAttribute("list", list);

		return "board/list";
	}

	@RequestMapping(value = "/write.do", method = RequestMethod.GET)
	public String write_get(BoardVO boardVo, HttpServletRequest request, Model model) {
		logger.info("글쓰기 화면");

		boardVo = Utility.getIp2(boardVo, request);
		logger.info("ip 정보 조회 vo = {}", boardVo);

		model.addAttribute("ip", boardVo.getIp());
		model.addAttribute("port", boardVo.getPort());
		model.addAttribute("browser", boardVo.getBrowser());

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

	@RequestMapping("/confirm.do")
	public String confirm(@RequestParam(defaultValue = "0") int no, Model model) {
		logger.info("비밀번호 확인창 파라미터 no={}", no);

		return "board/confirm";
	}

	@RequestMapping("/edit1.do")
	public String edit1(@ModelAttribute BoardVO boardVo, Model model, HttpServletRequest request) {
		logger.info("수정 화면 파라미터 boardVo={}", boardVo);

		String view = "";
		if (boardService.checkPwd(boardVo.getNo(), boardVo.getPassword())) {
			boardVo = boardService.selectByNo(boardVo.getNo());
			logger.info("수정 화면 불러오기 결과, boardVo={}", boardVo);

			boardVo = Utility.getIp2(boardVo, request);
			logger.info("ip 정보 조회 vo = {}", boardVo);

			model.addAttribute("ip", boardVo.getIp());
			model.addAttribute("port", boardVo.getPort());
			model.addAttribute("browser", boardVo.getBrowser());

			model.addAttribute("vo", boardVo);
			view = "board/edit";
		} else {
			model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
			model.addAttribute("url", "/board/confirm.do?no=" + boardVo.getNo());
			view = "inc/message";
		}

		return view;
	}

	@RequestMapping("/edit2.do")
	public String edit2(@ModelAttribute BoardVO boardVo, Model model) {
		logger.info("수정 처리 파라미터 boardVo={}", boardVo);

		int cnt = boardService.edit(boardVo);
		logger.info("수정 처리 결과 cnt = {}", cnt);

		String msg = "", url = "/board/detail.do?no=" + boardVo.getNo();
		if (cnt > 0) {
			msg = "글 수정 성공";
		} else {
			msg = "글 수정 실패";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		return "inc/message";
	}

	@RequestMapping("/delete1.do")
	public String delete1(@RequestParam(defaultValue = "0") int no, Model model) {
		logger.info("비밀번호 확인창 파라미터 no={}", no);

		return "board/delete";
	}

	@RequestMapping("/delete2.do")
	public String delete2(@ModelAttribute BoardVO boardVo, Model model, HttpServletRequest request) {
		logger.info("글 삭제 파라미터 boardVo = {}", boardVo);

		String view = "";
		if (boardService.checkPwd(boardVo.getNo(), boardVo.getPassword())) {
			boardVo = boardService.selectByNo(boardVo.getNo());
			// 파일 삭제
			String uploadLastPath = fileuploadProperties.getProperty("file.upload.path");
			String savePath1 = request.getSession().getServletContext().getRealPath(uploadLastPath);
			String savePath = fileuploadProperties.getProperty("file.upload.path.test");
			logger.info("savePath1 ={}, savePath ={}", savePath1, savePath);
			// 업로드된 파일이 있는 경우에만 삭제
			if (boardVo.getFileName() != null && !boardVo.getFileName().isEmpty()) {
				File myfile = new File(savePath, boardVo.getFileName());
				if (myfile.exists()) {
					boolean flag = myfile.delete();
					logger.info("파일 삭제 여부 : " + flag);
				}
			}

			int cnt = boardService.delete(boardVo.getNo());
			logger.info("글 삭제 처리 결과 cnt={}", cnt);

			model.addAttribute("msg", "글 삭제 성공");
			model.addAttribute("url", "/board/list.do");

			view = "inc/message";

		} else {
			model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
			model.addAttribute("url", "/board/delete1.do?no=" + boardVo.getNo());

			view = "inc/message";
		}

		return view;
	}

}
