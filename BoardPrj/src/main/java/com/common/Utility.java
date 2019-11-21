package com.common;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import com.board.controller.BoardController;
import com.board.model.BoardVO;

public class Utility {

	public static final int BLOCK_SIZE = 3;
	public static final int RECORD_COUNT_PER_PAGE = 5;

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	public static String getUserId(String userCode, HttpSession session) {
		String result = "";
		if ("1".equals(userCode)) {
			result = (String) session.getAttribute("memberId");
		} else if ("2".equals(userCode)) {
			result = (String) session.getAttribute("sMemberId");
		}

		return result;
	}

	// 아이피 가져오기
	public static String getIp(HttpServletRequest request) {

		String ip = request.getHeader("X-Forwarded-For");

		logger.info(">>>> X-FORWARDED-FOR : " + ip);

		if (ip == null) {
			ip = request.getHeader("Proxy-Client-IP");
			logger.info(">>>> Proxy-Client-IP : " + ip);
		}
		if (ip == null) {
			ip = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
			logger.info(">>>> WL-Proxy-Client-IP : " + ip);
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_CLIENT_IP");
			logger.info(">>>> HTTP_CLIENT_IP : " + ip);
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			logger.info(">>>> HTTP_X_FORWARDED_FOR : " + ip);
		}
		if (ip == null) {
			ip = request.getRemoteAddr();
		}

		logger.info(">>>> Result : IP Address : " + ip);

		return ip;

	}

	// 아이피 가져오기2
	public static BoardVO getIp2(HttpServletRequest request) {

		BoardVO vo = new BoardVO();

		InetAddress inet;

		try {
			inet = InetAddress.getLocalHost();
			String ip = inet.getHostAddress();
			String port = Integer.toString(request.getServerPort());
			String browser = request.getHeader("User-Agent");

			vo.setIp(ip);
			vo.setPort(port);
			vo.setBrowser(browser);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return vo;
	}

	// 중복 파일명 수정하기 방법2-1
	public static String getTimeStamp() {
		String result = "";

		// 문자열로 변환하기 위한 패턴(년-월-일 시:분:초:밀리초(자정이후 초))
		String pattern = "yyyyMMddhhmmssSSS";

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);

		Date today = new Date();
		result = sdf.format(today);
		System.out.println("getTimeStamp() : " + result);

		return result;
	}

	// 중복 파일명 수정하기 방법2-2
	public static String getUniqueFileName(String fileName) {
		// 파일명이 중복될 경우 파일이름 변경하기
		// 파일명에 현재시간을 붙여서 변경된 파일이름 구하기
		// a.txt => a_20150519123315235.txt

		// 순수파일명만 구하기
		int idx = fileName.lastIndexOf(".");
		String fileNm = fileName.substring(0, idx); // a

		// 확장자 구하기
		String ext = fileName.substring(idx); // .txt

		// 변경 된 파일 이름
		String result = fileNm + "_" + getTimeStamp() + ext;
		logger.info("변경 된 파일 이름 : {}", result);

		return result;
	}
}
