<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="n" uri="http://www.suwon.com/jsp/tags/control"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%-- <!-- 애는 이제 컨트롤러를 분리시켜 관리할꺼임 -->
<%
	List<NoticeModel> list = new MyBatisNoticeDao().getList();
	pageContext.setAttribute("list", list);
%> --%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	.strong{
		color:red;
		text-decoration:none;
		font-size:1.2em;
	}
</style>
</head>
<body>
	<h1>
		<a href="">공지사항</a>
	</h1>
	<ul>
		<li><a href="../index.jsp">HOME</a></li>
		<li><a href="notice">공지사항</a></li>
		
	</ul>
	
	<form action="notice" method="get">		
		<fieldset>
		<select name="t">		
			<!-- select 가 보내주는 t(키값)은 옵션이 가지고 있는 밸류의 값이다 -->
			<!-- 검색한 애들이있으면 그것을 기본값으로 바꿔주기 -->
			<option value="NONE">분류선택</option>			
			<c:if test="${param.t =='TITLE'}">				
				<option value="TITLE" selected="selected">제목</option>
			</c:if>
			<c:if test="${param.t !='TITLE'}">				
				<option value="TITLE">제목</option>
			</c:if>
			
			<c:if test="${param.t =='CONTENT'}">				
				<option value="CONTENT" selected="selected">내용</option>
			</c:if>
			<c:if test="${param.t !='CONTENT'}">				
				<option value="CONTENT">내용</option>
			</c:if>
			
			<!-- 이게 좀더 좋음 추천하지만 편한대로 써도될듯 -->	
				<option value="WRITER" 
					<c:if test="${param.t =='WRITER'}">	
						selected="selected"
					</c:if>>작성자</option>
			
		</select>
			<label>검색어</label>
			<input name="q" value="${param.q}"/>
			<input type="submit" value="검색"/>
		</fieldset>
	</form>
	
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
			<!-- 반복문 태그 -->
			<c:forEach var="m" items="${list}">
				<tr>
					<td>${m.code}</td>
					<td><a href="notice-detail?code=${m.code}">${m.title}</a></td>
					<td>${m.writer}</td>
					<td>
						<!-- 날짜 나오는거 수정하기 (월은 대문자 M 분은 소문자m)-->
						<fmt:formatDate pattern="yy.MM.dd hh:mm" value="${m.regdate}"/>
					</td>
					<td>${m.hit}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- page기본값 설정 -->
	<c:if test="${empty param.p}">
		<c:set var="page" value="1" />
	</c:if>
	<c:if test="${!empty param.p}">
		<c:set var="page" value="${param.p}" />
	</c:if>
	<!-- page는 1~5 6~10 11~15 간격으로 나와야됨 -->
	<c:set var="start" value="${page-(page-1)%5}" />
	<c:set var="end" value="${fn:substringBefore((count%10 == 0 ? count/10 : count/10 + 1),'.')}" />

	<!-- 현재 상태-->
	<div>
		<h3 class="hidden">현재 페이지</h3>
		${page} / ${end} pages
	</div>

	<div>
		<a href="notice-reg">글쓰기</a>
	</div>
	
	<!-- 페이징 -->
	<div>		
		<%-- <!-- 카운트 맞는지 확인 -->
		<div>count : ${count}<br/>
			 end : ${end}</div> --%>
		<!-- 페이지가 1페이지일 때는 이전페이지 눌러도 전페이지 안가게 해주기 다음페이지가 없을 경우 다음페이지 하지 않게 설정 -->
		<div><a href="notice?p=${(start==1)?1:start-1}&q=${param.q}&t=${param.t}"><<</a></div>
		<div><a href="notice?p=${(page==1)?1:page-1}&q=${param.q}&t=${param.t}"><</a></div>
		<ul>
			<!-- 변수 n 을 1~5 5번 반복 시킨다 -->
			<c:forEach var="i" begin="0" end="4">
			<c:if test="${start+i <= end}">
				<li><a href="notice?p=${start+i}&q=${param.q}&t=${param.t}" <c:if test="${page == start+i}">class="strong"</c:if>>${start+i}</a></li>
			</c:if>
				<%-- <c:if test="${param.p == n }">
				<li><a href="notice?p=${n}" class="strong">${n}</a></li>
				</c:if>
				<c:if test="${param.p != n}">
				<li><a href="notice?p=${n}">${n}</a></li>
				</c:if> --%>
			</c:forEach>
		</ul>
		<div><a href="notice?p=${(page==end)?end:page+1}&q=${param.q}&t=${param.t}">></a></div>
		<div><a href="notice?p=${(start+5>end)?end:start+5}&q=${param.q}&t=${param.t}">>></a></div>
	</div>	
</body>
</html>










