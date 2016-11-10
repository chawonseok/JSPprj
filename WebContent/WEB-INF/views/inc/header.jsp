<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 동적인 루트 설정해주기 -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- pageContext에 실제 루트(지금은 JSPPrj로 되어있지만 배포 후에는 다른 프로젝트가 된다) -->
<c:set var="ctx">${pageContext.request.contextPath}</c:set>
<div id="header">
	<div class="content-container">
		<!--------------------------------------<header>------------------------------------>
		<header>
			<!-- 제목 -->
			<h1 id="Logo">
				<img src="${ctx}/images/logo.png" alt="뉴렉처 온라인" />
			</h1>
			<section>
				<h1 class="hidden">헤더</h1>
				<nav id="main-menu">
					<h1 class="hidden">메인메뉴</h1>
					<ul>
						<li><a href="">뉴렉과정</a></li>
						<li><a href="">학습가이드</a></li>
						<li><a href="">강좌선택</a></li>
					</ul>
				</nav>

				<section id="search-form">
					<h1 class="hidden">강좌검색 폼</h1>
					<form>
						<fieldset>
							<legend>과정검색필드</legend>
							<label>과정검색</label> <input type="text" value="" /> <input
								type="submit" value="검색" />
						</fieldset>
					</form>
				</section>

				<nav id="member-menu">
					<h1 class="hidden">회원메뉴</h1>
					<ul>
						<li><a href="${ctx}/index">HOME</a></li>
						<c:if test="${empty sessionScope.mid}">
						<li><a href="${ctx}/joinus/login">로그인</a></li>
						</c:if>
						<c:if test="${not empty sessionScope.mid}">
						<li><a href="${ctx}/joinus/logout">로그아웃</a></li>
						</c:if>
						<li><a href="${ctx}/joinus/agree">회원가입</a></li>
					</ul>
				</nav>

				<nav id="customer-menu">
					<h1 class="hidden">고객메뉴</h1>
					<ul>
						<li><a href=""><img src="${ctx}/images/txt-mypage.png"
								alt="마이페이지" /></a></li>
						<li><a href="${ctx}/customer/notice"><img src="${ctx}/images/txt-customer.png"
								alt="고객센터" /></a></li>
					</ul>
				</nav>
			</section>
		</header>
	</div>
</div>
