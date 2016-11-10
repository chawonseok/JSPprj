<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="n" uri="http://www.suwon.com/jsp/tags/control"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="y" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시험중입니다</title>
<link href="css/layout.css" type="text/css" rel="stylesheet" />
<link href="css/<tiles:getAsString name="css" />" type="text/css"
	rel="stylesheet" />

<script src="../js/modernizr-custom.js">
	window.onload = function() {
		document.createElement("main");
	};
</script>
</head>
<body>

	<tiles:insertAttribute name="header" />
	<div id="body">
		<div class="content-container clearfix">
			<tiles:insertAttribute name="main" />
		</div>
	</div>
	<tiles:insertAttribute name="footer" />

</body>
</html>