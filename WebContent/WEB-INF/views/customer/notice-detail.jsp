<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%-- <!-- 헤더 -->
	<jsp:include page="../inc/header.jsp"/> --%>
	<main>
	<h1>
		<a href="">(${n.code})디테일 페이지</a>
	</h1>
	<table border=1>
		<tbody>
			<tr>
				<td>제목</td>
				<td colspan="3">
					<%-- <!-- 아이피가져오기 -->
					${pageContext.request.remoteAddr} /
					<!-- header꺼 가져와보기 --> 
					${header["User-Agent"]} /  --%> <%-- 같은 EL표기 : ${n["title"]} --%>
					${n.title}
				</td>
			</tr>
			<tr>
				<td>작성일</td>
				<td colspan="3">
				<fmt:formatDate pattern="yy.MM.dd hh:mm" value="${n.regdate}"/>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${n.writer}</td>
				<td>조회수</td>
				<td>${n.hit}</td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td colspan="3">
				<!-- 모든 첨부파일 뽑아내기 -->
				<!-- varstatus="index"는 리스트의 인덱스 값을 가짐 -->
				<!-- varstatus="count"는 리스트의 인덱스+1 값을 가짐 -->
				<c:forEach var="f" items="${files}" varStatus="s">
				<%-- <!-- 하이퍼링크는 한계가 있음 -->
					<a href="upload/${f.src}">${f.src}</a> --%>
					<a href="../download?f=${f.src}">${f.src}</a>
					<!-- 맨 마지막이 아닐 때만 한줄 뛰기 -->
					<c:if test="${!s.last}"><br/></c:if>
				</c:forEach>
				</td>

			</tr>
			<tr>
				<td colspan="4">
					<!-- 사진 보여지기 --> 
					<c:forEach var="f" items="${files}" varStatus="s">
						<img src="upload/${f.src}" />
						<c:if test="${!s.last}">
							<br />
						</c:if>
					</c:forEach> ${n.content}
				</td>
			</tr>
		</tbody>
	</table>
	<div>		
		<c:if test="${empty prev}">이전글이 없습니다.</c:if>
		<c:if test="${!empty prev}"><a href="notice-detail?code=${prev.code}">이전글 : ${prev.title}</a></c:if>
	</div>
	<div>
		<c:if test="${empty next}">다음글이 없습니다.</c:if>
		<c:if test="${!empty next}"><a href="notice-detail?code=${next.code}">다음글 : ${next.title}</a></c:if>
	</div>
	<div>	
	<ul>
		<li><a href="notice?p=${param.p}">목록</a></li>
		<li><a href="notice-edit?code=${n.code}">수정</a></li>
		<li><a href="notice-del?code=${n.code}">삭제</a></li>
	</ul>
	</div>
	</main>


















