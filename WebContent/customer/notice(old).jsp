<%@page import="java.util.Date"%>
<%@page import="java.util.Scanner"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";
	String sql = "SELECT * FROM NOTICE_VIEW WHERE TITLE LIKE?";
	String query = "";

	//쓰려는 서버를 지정해줘야 됨
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con = DriverManager.getConnection(url, "c##sist", "dclass");
	PreparedStatement st = con.prepareStatement(sql); //sql 쿼리문 넣어줌
	st.setString(1, "%" + query + "%");
	ResultSet rs = st.executeQuery();

	String code;
	String title;
	String writer;
	Date regdate;
	int hit;
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>
		<a href="">공지사항</a>
	</h1>
	<ul>
		<li><a href="../index.jsp">HOME</a></li>
		<li><a href="notice.jsp">공지사항</a></li>
	</ul>
	<table border=1>
		<thead>
			<tr>
				<td>번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>조회수</td>
			</tr>
		</thead>
		<tbody>
			<%
				while (rs.next()) {
					code = rs.getString("CODE");
					title = rs.getString("TITLE");
					writer = rs.getString("WRITER");
					regdate = rs.getDate("REGDATE");
					hit = rs.getInt("HIT");
			%>
			<tr>
				<td><%=code%></td>
				<td><a href="notice-detail.jsp?code=<%=code%>"><%=title%></a></td>
				<td><%=writer%></td>
				<td><%=regdate%></td>
				<td><%=hit%></td>
			</tr>
			<%
				}

				rs.close();
				st.close();
				con.close();
			%>
		</tbody>
	</table>

</body>
</html>