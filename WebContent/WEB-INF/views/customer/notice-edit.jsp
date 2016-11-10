<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>
		<a href="">(${n.code})수정 페이지</a>
	</h1>
	<!-- 데이터를 수정한 후 전달하기 위해 form으로 감싸야 된다. -->
	<form action="notice-edit" method="post"  style="float:left; width:50%;">
		<fieldset>
			<legend>공지사항 수정 필드</legend>
			<table border=1>
				<tbody>
					<tr>
						<td>제목</td>
						<td colspan="3"><input type="text" name="title" value="${n.title}" /></td>
					</tr>
					<tr>
						<td>작성일</td>
						<td colspan="3"><fmt:formatDate pattern="yy.MM.dd hh:mm"
								value="${n.regdate}" />
					</tr>
					<tr>
						<td>작성자</td>
						<td>${n.writer}</td>
						<td>조회수</td>
						<td>${n.hit}</td>
					</tr>
					<tr>
						<td>첨부파일</td>
						<td colspan="3">없음</td>
					</tr>
					<tr>
						<td colspan="4"><textarea rows="20" cols="60" name="content">${n.content}</textarea></td>
					</tr>
				</tbody>
			</table>
			<div>
				<input type="hidden" name="code" value="${n.code}"/>
				<input type="submit" value="저장"/>
				<a href="notice-detail?code=${n.code}">취소</a>
			</div>
		</fieldset>
	</form>
</body>
</html>



















