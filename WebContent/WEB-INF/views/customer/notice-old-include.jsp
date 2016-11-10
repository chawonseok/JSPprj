<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="n" uri="http://www.suwon.com/jsp/tags/control"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="y" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시험중입니다</title>
<link href="css/notice.css" type="text/css" rel="stylesheet" />

<script src="../js/modernizr-custom.js">
	window.onload = function() {
		document.createElement("main");
	};
</script>

</head>
<body>
	<!-- 제목부터 헤더까지 감싸줄 친구ㅡ -->
	<!-- 헤더가 있었던 부분 -->
	<jsp:include page="../inc/header.jsp"/>
	<!-- --------------------------------------<visual>------------------------------------ -->
	<jsp:include page="inc/visual.jsp"/>
	
	<!-- aside ~ main까지 가로영역 -->
	<div id="body">
		<div class="content-container clearfix">
			<!-- --------------------------------------aside------------------------------------ -->
			<jsp:include page="inc/aside.jsp" />
			<!-- --------------------------------------main------------------------------------ -->

			<main>
			<h2>공지사항</h2>

			<div>
				<h3>공지사항 검색폼</h3>
				<form>
					<fieldset>
						<legend>공지사항 검색 필드</legend>
						<select>
							<label>검색분류</label>
							<option>제목</option>
							<option>작성자</option>
						</select> <label>검색어</label> <input type="text" /> <input type="submit"
							value="검색결과" />
					</fieldset>
				</form>
			</div>

			<div>
				<h3>공지사항 검색목록</h3>
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
									<!-- 날짜 나오는거 수정하기 (월은 대문자 M 분은 소문자m)--> <fmt:formatDate
										pattern="yy.MM.dd hh:mm" value="${m.regdate}" />
								</td>
								<td>${m.hit}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div>
					<h3>현재 페이지</h3>
					<!-- <div>1/3 pages</div> -->
					<div>${page} / ${end} pages</div>
				</div>

				<div>
					<h3>페이지</h3>
					<y:pager/>
				</div>
			</div>
			</main>
		</div>
	</div>

	<!-- --------------------------------------footer------------------------------------ -->
	<jsp:include page="../inc/footer.jsp"/>

</body>
</html>