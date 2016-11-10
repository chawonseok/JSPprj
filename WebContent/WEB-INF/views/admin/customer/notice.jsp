<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="n" uri="http://www.suwon.com/jsp/tags/control"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="y" tagdir="/WEB-INF/tags"%>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>  
<main class="main">
<h2 class="main-title">공지사항</h2>

	<div class="breadcrumb">
		<h3 class="hidden">breadlet</h3>
		<ul>
			<li>home</li>
			<li>고객센터</li>
			<li>공지사항</li>
		</ul>
	</div>

	<div>
		<h3 class="hidden">>공지사항 검색폼</h3>
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

				</select> <label>검색어</label> <input name="q" value="${param.q}" /> <input
					type="submit" value="검색" />
			</fieldset>
		</form>
	</div>

	<div>
		<h3>>공지사항 검색목록</h3>
		<table class="table table-striped">
			<thead>
				<tr class="tr">
					<td class="td">번호</td>
					<td class="td">제목</td>
					<td class="td">작성자</td>
					<td class="td">작성일</td>
					<td class="td">조회수</td>
				</tr>
			</thead>
			<tbody>
				<!-- 반복문 태그 -->
				<c:forEach var="m" items="${list}">
					<tr class="tr">
						<td class="td">${m.code}</td>
						<td class="td"><a href="notice-detail?code=${m.code}">${m.title}</a></td>
						<td class="td">${m.writer}</td>
						<td class="td">
							<!-- 날짜 나오는거 수정하기 (월은 대문자 M 분은 소문자m)--> <fmt:formatDate
								pattern="yy.MM.dd hh:mm" value="${m.regdate}" />
						</td>
						<td class="td">${m.hit}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<a href="notice-reg">글쓰기</a>
		</div>
		<div>
			<h3 class="hidden">>현재 페이지</h3>
			<!-- <div>1/3 pages</div> -->
			<div>${page}/ ${end} pages</div>
		</div>

		<div>
			<h3 class="hidden">페이지</h3>
			<y:pager />
		</div>
	</div>
</main>