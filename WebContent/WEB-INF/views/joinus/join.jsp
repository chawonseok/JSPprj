<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<main>
<h2>회원가입</h2>
<c:if test="${not empty erroeMsg}">
</c:if>
<div>
	${errorMsg}
</div>
<form action="join" method="post">
   <fieldset>
      <legend>회원정보</legend>
      <table>
         <tbody>
            <tr>
               <th><label>아이디</label></th>
               <td><input name="mid" value="${mid}"/>
               <input type="submit" name="btn" value="중복확인" />
               <span>${duplicatedResult}</span>
               </td>
            </tr>
               <th><label>비밀번호</label></th>
               <td><input name="pwd" /></td>
            </tr>
            <tr>
               <th><label>비밀번호 확인</label></th>
               <td><input name="ppwd" /></td>
            </tr>
            <tr>
               <th><label>이름</label></th>
               <td><input name="name" value="${name}"/></td>
            </tr>
            <tr>
               <th><label>닉네임</label></th>
               <td><input name="nicname" /></td>
            </tr>
            <tr>
               <th><label>성별</label></th>
               <td>
                  <select name="gender">
                     <option>선택</option>
                     <option>남자</option>
                     <option>여자</option>
                  </select>
               </td>
            </tr>
            <tr>
               <th><label>생년월일</label></th>
               <td>
                  <input name="y"/>년<input name="m"/>월<input name="d"/>일
                  <input type="radio" name="sun" />양력
                  <input type="radio" name="moon" />음력
               </td>
            </tr>
            <tr>
               <th><label>핸드폰번호</label></th>
               <td><input name="phone" /></td>
            </tr>
            <tr>
               <th><label>이메일</label></th>
               <td><input name="email" value="${email}"/></td>
            </tr>
			<tr>
				<td><input type="submit" name="btn"  value="확인" /></td>
			</tr>
			</tbody>
      </table>
   </fieldset>
</form>
</main>