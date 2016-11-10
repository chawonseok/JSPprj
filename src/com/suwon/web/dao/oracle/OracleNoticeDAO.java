/*package com.suwon.web.dao.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.suwon.web.dao.NoticeDao;
import com.suwon.web.entities.Notice;
import com.suwon.web.model.NoticeModel;

public class OracleNoticeDAO implements NoticeDao{
	@Override
	public Notice get(String code) throws SQLException, ClassNotFoundException{
		String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";
		String sql = "SELECT * FROM NOTICE_VIEW WHERE CODE=?";
		String query = "";

		//쓰려는 서버를 지정해줘야 됨
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "c##sist", "dclass");
		PreparedStatement st = con.prepareStatement(sql); //sql 쿼리문 넣어줌
		st.setString(1, code);
		ResultSet rs = st.executeQuery();

		
		Notice notice = new Notice();

		if (rs.next()) {
			notice.setCode(rs.getString("CODE"));
			notice.setTitle(rs.getString("TITLE"));
			notice.setWriter(rs.getString("WRITER"));
			notice.setRegdate(rs.getDate("REGDATE"));
			notice.setHit(rs.getInt("HIT"));
			notice.setContent(rs.getString("CONTENT"));
		}

		rs.close();
		st.close();
		con.close();

		return notice;
	}

	@Override
	public List<NoticeModel> getList(int page, String field, String query) throws SQLException, ClassNotFoundException {
		String sql  = 
	            "SELECT * " +
	            "FROM(" +
	            "  SELECT ROWNUM NUM, N.* " + 
	            "  FROM( " + 
	            "    SELECT * " +
	            "    FROM NOTICE_VIEW " +
	            "    WHERE " + field + " LIKE ? " +
	            "    ORDER BY REGDATE DESC " +
	            "  ) N " +
	              
	            ") " +
	            "WHERE NUM BETWEEN ? AND ? ";
		
		//1, 11, 21, 31, 41 ... 페이지를 이용한 규칙
		int startNum = 1 + 10 * (page-1);
		//10, 20, 30, 40 ... 
		int endNum = page*10;
		query = "%" + query + "%";
		
		
		String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";		
		// 쓰려는 서버를 지정해줘야 됨
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "c##sist", "dclass");
		PreparedStatement st = con.prepareStatement(sql); // sql 쿼리문 넣어줌
		st.setString(1, query);
		st.setInt(2, startNum);
		st.setInt(3, endNum);
		ResultSet rs = st.executeQuery();

		List<NoticeModel> list = new ArrayList<>();

		if (rs.next()) {
			
			NoticeModel notice = new NoticeModel();
			
			notice.setCode(rs.getString("CODE"));
			notice.setTitle(rs.getString("TITLE"));
			notice.setWriter(rs.getString("WRITER"));
			notice.setRegdate(rs.getDate("REGDATE"));
			notice.setHit(rs.getInt("HIT"));
			notice.setContent(rs.getString("CONTENT"));
			notice.setCmtCnt(rs.getInt("CMTCNT"));
			
			list.add(notice);
		}

		rs.close();
		st.close();
		con.close();
		
		return list;
	}

	@Override
	public List<NoticeModel> getList(int page) throws ClassNotFoundException, SQLException {
		return getList(page, "TITLE", "");
	}

	@Override
	public List<NoticeModel> getList() throws ClassNotFoundException, SQLException {
		return getList(1, "TITLE", "");
	}

	@Override
	public int insert(Notice notice) throws ClassNotFoundException, SQLException {
		//코드는 기존의 가장 높은 수의 +1을 더한 값을 내가 넣어줘야된다.
		String sqlCode = "SELECT NVL(MAX(TO_NUMBER(CODE)),0)+1 CODE FROM NOTICE";
		String sql = "INSERT INTO NOTICE("
				+"CODE, "
				+"TITLE, "
				+"WRITER, "
				+"CONTENT, "
				+"REGDATE, "
				+"HIT) "
				+"VALUES(?,?,?,?,sysdate,0)";
		
		String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";		
		// 쓰려는 서버를 지정해줘야 됨
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "c##sist", "dclass");
		
		Statement stCode = con.createStatement();
		ResultSet rs = stCode.executeQuery(sqlCode);
		rs.next();
		
		//code를 가져오는 작업이 필요
		String code = rs.getString("CODE");

		rs.close();
		stCode.close();
		
		PreparedStatement st = con.prepareStatement(sql); // sql 쿼리문 넣어줌
		
		st.setString(1, code);
		st.setString(2, notice.getTitle());
		st.setString(3, notice.getWriter());
		st.setString(4, notice.getContent());
	
		int result = st.executeUpdate();

		//close는 거꾸로해주기
		st.close();
		con.close();
				
		return result;
	}

	@Override
	public int update(Notice notice) throws ClassNotFoundException, SQLException {
			
		String sql = "UPDATE NOTICE "
				+ "SET TITLE = ? , "
				+ "WRITER = ? , "
				+ "CONTENT = ? "
				+ "WHERE CODE = ?" ;
		
		String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";		
		// 쓰려는 서버를 지정해줘야 됨
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "c##sist", "dclass");
		PreparedStatement st = con.prepareStatement(sql); // sql 쿼리문 넣어줌
		
		st.setString(1, notice.getTitle());
		st.setString(2, notice.getWriter());
		st.setString(3, notice.getContent());
		st.setString(4, notice.getCode());
		
		//우리는 실행한 적이 없다 -> 쿼리문을 실행하는 방법
		int result = st.executeUpdate();
		
		st.close();
		con.close();
		return result;
	}

	@Override
	public int delete(String code) throws ClassNotFoundException, SQLException {
		
		String sql = "DELETE NOTICE "
				+ "WHERE CODE =  ? ";
		
		String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";		
		// 쓰려는 서버를 지정해줘야 됨
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "c##sist", "dclass");
		PreparedStatement st = con.prepareStatement(sql); // sql 쿼리문 넣어줌
		
		st.setString(1, code);
		
		//가급적 쓰진 않지만 statement를 사용할 경우
		//executeUpdate에 사용할 파라미터 물어보기
		int result = st.executeUpdate();
		
		
		 * Statement는 이런식으로 하면됨
		 * Statement st = con.createStatement();
		 * int result = st.executeUpdate(sql); 
		 *
		 * 
		
		st.close();
		con.close();
		
		return result;
	}


	
}
*/