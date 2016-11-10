<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${param.er== 1}">
	<div style="color:red;">
		약관에 동의하지 않았습니다. 
	</div>
</c:if>
	<form action="agree" method="post">
		<div>
			<!-- 동의했을 때 true를 반환하게 만든다 -->
			<input type="checkbox" name="agree" value="true"/>약관에 동의하시겠습니까
		</div>
		<div>
			<input type="submit" value="다음" />
		</div>
	</form>
