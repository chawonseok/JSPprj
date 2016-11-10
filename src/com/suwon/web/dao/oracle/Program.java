/*package com.suwon.web.dao.oracle;

import java.sql.SQLException;

import com.suwon.web.entities.Notice;

public class Program {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		OracleNoticeDAO dao = new OracleNoticeDAO();
		
		Notice notice = new Notice();
		
		//code는 우리가 세팅 안해주는거
		notice.setCode(?);
		notice.setTitle("시험용");
		notice.setWriter("작가미상");
		notice.setContent("내용없음");
		
		//들어갔는지 확인하기위해 
		int count = dao.insert(notice);
		
		System.out.println(count);
		
		
	}
}
*/