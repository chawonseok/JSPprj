<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div>
<!-- page기본값 설정 -->
<c:if test="${empty param.p}">
	<c:set var="page" value="1" />
</c:if>
<c:if test="${!empty param.p}">
	<c:set var="page" value="${param.p}" />
	..
</c:if>
<!-- page는 1~5 6~10 11~15 간격으로 나와야됨 -->
<c:set var="start" value="${page-(page-1)%5}" />
<c:set var="end" value="${fn:substringBefore((count%10 == 0 ? count/10 : count/10 + 1),'.')}" />
</div>
<!-- 페이징 -->
<div>
	<div>
		<a href="notice?p=${(page==1)?1:page-1}&q=${param.q}&t=${param.t}">
			이전
		</a>
	</div>
	<ul>
		<!-- 변수 n 을 1~5 5번 반복 시킨다 -->
		<c:forEach var="i" begin="0" end="4">
			<c:if test="${start+i <= end}">
				<li>
				<a href="notice?p=${start+i}&q=${param.q}&t=${param.t}"	
				<c:if test="${page == start+i}">class="strong"</c:if>>
					${start+i}
				</a>
				</li>
			</c:if>
		</c:forEach>
	</ul>
	<div>
		<a href="notice?p=${(page==end)?end:page+1}&q=${param.q}&t=${param.t}">
			다음
		</a>
	</div>
</div>
