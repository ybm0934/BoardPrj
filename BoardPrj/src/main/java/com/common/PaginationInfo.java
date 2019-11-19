package com.common;

public class PaginationInfo {

	private int currentPage;
	private int recordCountPerPage;
	private int blockSize;
	private int totalRecord;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	private int totalPage;
	private int firstPage;
	private int lastPage;
	private int firstRecordIndex;
	private int lastRecordIndex;

	public int getTotalPage() {
		totalPage = (int) Math.ceil((float) totalRecord / recordCountPerPage);

		return totalPage;
	}

	public int getFirstPage() {
		firstPage = currentPage - ((currentPage - 1) % blockSize);
		return firstPage;
	}

	public int getLastPage() {
		lastPage = firstPage + (blockSize - 1);
		if (lastPage > getTotalPage()) {
			lastPage = getTotalPage();
		}
		return lastPage;
	}

	public int getFirstRecordIndex() {
		firstRecordIndex = (getCurrentPage() - 1) * getRecordCountPerPage();
		return firstRecordIndex;
	}

	public int getLastRecordIndex() {
		lastRecordIndex = (getCurrentPage() * getRecordCountPerPage()) - 1;
		return lastRecordIndex;
	}

}
