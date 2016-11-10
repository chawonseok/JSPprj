<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="n" uri="http://www.suwon.com/jsp/tags/control"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="y" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시험중입니다</title>
<!-- css도 레이아웃으로 빼기 위해 -->
<%-- <tiles:getAsString name="css" />
<link href="css/notice.css" type="text/css" rel="stylesheet" /> --%>
<link href="css/layout.css" type="text/css" rel="stylesheet" />
<link href="css/<tiles:getAsString name="css" />" type="text/css" rel="stylesheet" />

<script src="../js/modernizr-custom.js">
	window.onload = function() {
		document.createElement("main");
	};
</script>
</head>
<body>
	<!-- 이제 타일즈를 써서 만들어볼꺼임 -->
	<!-- 헤더가 있었던 부분 -->
 	<tiles:insertAttribute name="header"/>
	<%-- <jsp:include page="../inc/header.jsp"/> --%>
	<!-- --------------------------------------<visual>------------------------------------ -->
	<tiles:insertAttribute name="visual" />
	<%-- <jsp:include page="inc/visual.jsp"/> --%>
	
	<!-- aside ~ main까지 가로영역 -->
	<div id="body">
		<div class="content-container clearfix">
			<!-- --------------------------------------aside------------------------------------ -->
			<%-- <jsp:include page="inc/aside.jsp" /> --%>
			<tiles:insertAttribute name="aisde"/>
			<!-- --------------------------------------main------------------------------------ -->
			<!-- 컨텐트부분 -->
			<tiles:insertAttribute name="main"/>		
		</div>
	</div>

	<!-- --------------------------------------footer------------------------------------ -->
	<%-- <jsp:include page="../inc/footer.jsp"/> --%>
	<tiles:insertAttribute name="footer"/>

</body>
</html>