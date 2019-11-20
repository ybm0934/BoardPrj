package com.board.model;

import java.sql.Timestamp;

public class BoardVO {

	private int no;
	private String memberid;
	private String password;
	private String title;
	private String email;
	private Timestamp regdate;
	private long cnt;
	private String content;
	private String delflag;
	private String ip;
	private String port;
	private String browser;
	private int newImgTerm;
	private String fileName;
	private String originalFileName;
	private double fileSize;
	private long downCount;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	public long getCnt() {
		return cnt;
	}

	public void setCnt(long cnt) {
		this.cnt = cnt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public int getNewImgTerm() {
		return newImgTerm;
	}

	public void setNewImgTerm(int newImgTerm) {
		this.newImgTerm = newImgTerm;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public double getFileSize() {
		return fileSize;
	}

	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}

	public long getDownCount() {
		return downCount;
	}

	public void setDownCount(long downCount) {
		this.downCount = downCount;
	}

	@Override
	public String toString() {
		return "BoardVO [no=" + no + ", memberid=" + memberid + ", password=" + password + ", title=" + title
				+ ", email=" + email + ", regdate=" + regdate + ", cnt=" + cnt + ", content=" + content + ", delflag="
				+ delflag + ", ip=" + ip + ", port=" + port + ", browser=" + browser + ", newImgTerm=" + newImgTerm
				+ ", fileName=" + fileName + ", originalFileName=" + originalFileName + ", fileSize=" + fileSize
				+ ", downCount=" + downCount + "]";
	}

}
