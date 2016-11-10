package com.suwon.web.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.suwon.web.dao.NoticeDao;
import com.suwon.web.entities.Notice;

public class Program {
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		/*//상대 경로를 지정해 주는 것이 좋음(리소스 위치 설정)
		//내 위치를 중심으로 위치설정
		//String src = "config.xml";
		String src = "com/suwon/web/dao/mybatis/config.xml";
		InputStream is = Resources.getResourceAsStream(src);
				
		//돌리게 만듬(매퍼컨테이너 만들기 - 매퍼가 담김)
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);		
		SqlSession session = ssf.openSession();
		
		//매퍼 부르기
		NoticeDao noticeDao = session.getMapper(NoticeDao.class);
		
		Notice n = noticeDao.get("981");
		
		System.out.println(n.getTitle());*/
		
		
	}
}
