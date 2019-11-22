package com.board.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.common.SearchVO;
import com.common.Utility;

@Service
public class BoardServiceImpl implements BoardService {

	private Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);

	@Autowired
	private BoardDAO boardDao;

	@Resource(name = "fileUploadProperties")
	Properties fileUploadProperties;

	@Override
	public List<BoardVO> list(SearchVO searchVo) {
		return boardDao.list(searchVo);
	}

	@Override
	public int write(BoardVO boardVo) {
		return boardDao.write(boardVo);
	}

	// 파일 업로드 처리
	public List<Map<String, Object>> fileupload(HttpServletRequest request) throws Exception {
		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

		// extract files
		final Map<String, MultipartFile> filesMap = multiRequest.getFileMap();

		// process files
		String uploadLastPath = fileUploadProperties.getProperty("file.upload.path");
		String uploadPath1 = request.getSession().getServletContext().getRealPath(uploadLastPath);
		String uploadPath = fileUploadProperties.getProperty("file.upload.path.test");
		logger.info("uploadPath1 ={}, uploadPath={}", uploadPath1, uploadPath);

		File saveFolder = new File(uploadPath);
		String fileName = null;
		// 디렉토리 생성
		if (!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Iterator<Entry<String, MultipartFile>> itr = filesMap.entrySet().iterator();
		MultipartFile tempFile;
		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();
			tempFile = entry.getValue();
			if (!tempFile.isEmpty()) {
				double fileSize = tempFile.getSize(); // 파일 크기
				String oName = tempFile.getOriginalFilename();

				// 변경된 파일 이름
				fileName = Utility.getUniqueFileName(oName);
				logger.info("fileSize={}, fileName={}", fileSize, fileName);
				logger.info("oName={}", oName);
				// 파일 전송
				File myfile = new File(uploadPath, fileName);
				tempFile.transferTo(myfile);
				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("fileName", fileName);
				resultMap.put("fileSize", fileSize);
				resultMap.put("oName", oName);
				resultList.add(resultMap);
			} // if
		} // while
		return resultList;
	}

	public String getUploadPath(HttpServletRequest request) {
		String uploadPath = "";
		String upType = fileUploadProperties.getProperty("file.upload.type");
		if (upType.equals("test")) {
			uploadPath = fileUploadProperties.getProperty("file.upload.path.test");
		} else {
			String upPath = fileUploadProperties.getProperty("file.upload.path");
			uploadPath = request.getSession().getServletContext().getRealPath(upPath);
		}
		logger.info("upType={}, uploadPath={}", upType, uploadPath);

		return uploadPath;
	}

	@Override
	public int downCount(int no) {
		return boardDao.downCount(no);
	}

	@Override
	public BoardVO selectByNo(int no) {
		return boardDao.selectByNo(no);
	}

	@Override
	public boolean checkPwd(int no, String password) {
		BoardVO boardVo = boardDao.selectByNo(no);
		String dbPwd = boardVo.getPassword();
		boolean result = false;
		if (password.equals(dbPwd)) {
			result = true;
		}
		return result;
	}

	@Override
	public int edit(BoardVO boardVo) {
		return boardDao.edit(boardVo);
	}

	@Override
	public int delete(int no) {
		return boardDao.delete(no);
	}

	@Override
	public int getTotalRecord(SearchVO searchVo) {
		return boardDao.getTotalRecord(searchVo);
	}

}
