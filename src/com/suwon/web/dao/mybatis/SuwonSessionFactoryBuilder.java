package com.suwon.web.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class SuwonSessionFactoryBuilder extends HttpServlet{
	
	static String src = "com/suwon/web/dao/mybatis/config.xml";
	static SqlSessionFactory ssf = null;
	
	//static으로 호출하는 것이아니라 init으로 호출을 하게 만듬
	@Override
	public void init(ServletConfig config) throws ServletException {
		InputStream is = null;
		try {
			is = Resources.getResourceAsStream(src);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ssf = new SqlSessionFactoryBuilder().build(is);
	}
	
	//프로그램이 실행될 때 팩토리빌더가(매퍼 컨테이너)가 만들어짐
	//지금 이렇게만 쓰면 매퍼컨테이너가 서블릿 컨테이너보다 먼저 생성되는 상황
	/*static{		
		InputStream is = null;
		try {
			is = Resources.getResourceAsStream(src);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ssf = new SqlSessionFactoryBuilder().build(is);
	}
*/
	public static SqlSessionFactory getSqlSessionFactory() {		
		return ssf;
	}
	
}
