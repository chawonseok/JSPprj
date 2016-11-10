package com.suwon.web.model;

import com.suwon.web.entities.Notice;

public class NoticeModel extends Notice{
	private int cmtCnt;

	public int getCmtCnt() {
		return cmtCnt;
	}

	public void setCmtCnt(int cmtCnt) {
		this.cmtCnt = cmtCnt;
	}
}
