package com.suwon.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.suwon.web.dao.MemberDao;
import com.suwon.web.entities.Member;


public class MyBatisMemberDao implements MemberDao {

	private SqlSessionFactory ssf;
	public MyBatisMemberDao() {
		ssf = SuwonSessionFactoryBuilder.getSqlSessionFactory();
	}
	@Override
	public Member get(String mid) {
		SqlSession session = ssf.openSession();
		MemberDao memberDao = session.getMapper(MemberDao.class);

		Member member = memberDao.get(mid);
		session.close();
		
		return member;
	}

	@Override
	public List<Member> getList(int page, String field, String query) {
		
		SqlSession session = ssf.openSession();
		MemberDao memberDao = session.getMapper(MemberDao.class);

		 return memberDao.getList(page, field,query);
	}

	@Override
	public int insert(Member member) {
		SqlSession session = ssf.openSession();
		MemberDao memberDao = session.getMapper(MemberDao.class);
		
		int result = memberDao.insert(member);
		
		session.commit();
		session.close();
		
		
		return result;
	}

	@Override
	public int update(Member member) {
		SqlSession session = ssf.openSession();
		MemberDao memberDao = session.getMapper(MemberDao.class);
		
		int result = memberDao.update(member);
		
		session.commit();
		session.close();
		
		
		return result;
	}

	@Override
	public int delete(String mid) {
		SqlSession session = ssf.openSession();
		MemberDao memberDao = session.getMapper(MemberDao.class);
		
		int result = memberDao.delete(mid);
		
		session.commit();
		session.close();
		
		
		return result;
	}

}
