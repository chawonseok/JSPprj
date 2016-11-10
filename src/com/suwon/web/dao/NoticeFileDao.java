package com.suwon.web.dao;

import java.util.List;

import com.suwon.web.entities.NoticeFile;

public interface NoticeFileDao {
	List<NoticeFile> getList(String noticeCode);
	int insert(NoticeFile noticeFile);
	int update(NoticeFile noticeFile);
	int delete(String code);
}
