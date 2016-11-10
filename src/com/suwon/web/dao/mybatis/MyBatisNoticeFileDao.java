package com.suwon.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.suwon.web.dao.NoticeFileDao;
import com.suwon.web.entities.NoticeFile;

public class MyBatisNoticeFileDao implements NoticeFileDao {
	SqlSessionFactory ssf;
	public MyBatisNoticeFileDao() {
		ssf = SuwonSessionFactoryBuilder.getSqlSessionFactory();
	}
	@Override
	public List<NoticeFile> getList(String noticeCode) {
		
		SqlSession session = ssf.openSession();
		NoticeFileDao noticeFileDao = session.getMapper(NoticeFileDao.class);
		
		return noticeFileDao.getList(noticeCode);
	}

	@Override
	public int insert(NoticeFile noticeFile) {
		
		SqlSession session = ssf.openSession();
		NoticeFileDao noticeFileDao = session.getMapper(NoticeFileDao.class);
		
		int result = noticeFileDao.insert(noticeFile);
		session.commit();
		session.close();
		
		return result;
	}

	@Override
	public int update(NoticeFile noticeFile) {
		
		SqlSession session = ssf.openSession();
		NoticeFileDao noticeFileDao = session.getMapper(NoticeFileDao.class);
		
		int result = noticeFileDao.update(noticeFile);
		session.commit();
		session.close();
		
		return result;
	}

	@Override
	public int delete(String code) {

		SqlSession session = ssf.openSession();
		NoticeFileDao noticeFileDao = session.getMapper(NoticeFileDao.class);
		
		int result = noticeFileDao.delete(code);
		session.commit();
		session.close();
		
		return result;
	}

}
