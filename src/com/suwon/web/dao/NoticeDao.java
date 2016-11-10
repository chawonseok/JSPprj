package com.suwon.web.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.suwon.web.entities.Notice;
import com.suwon.web.model.NoticeModel;

public interface NoticeDao {
	//�����ͺ��̽� ���̺�� ���� �̸�
	Notice get(String _code) ;
	List<NoticeModel> getList(int page, String field, String query);
	List<NoticeModel> getList(int page);
	List<NoticeModel> getList();
	
	Notice getPrev(String code);
	Notice getNext(String code);
	int hitUp(String code);
	
	int insert(Notice notice);
	int update(Notice notice);
	int delete(String code);
	int getCount(String field, String query);
	String getLastCode();
}
