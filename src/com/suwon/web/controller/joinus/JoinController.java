package com.suwon.web.controller.joinus;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tiles.TilesContainer;
import org.apache.tiles.access.TilesAccess;

import com.suwon.web.dao.mybatis.MyBatisMemberDao;
import com.suwon.web.entities.Member;

/**
 * Servlet implementation class JoinController
 */
@WebServlet("/joinus/join")
public class JoinController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// tiles를 통해호출하게 만들기
		TilesContainer container = TilesAccess.getContainer(request.getSession().getServletContext());
		container.render("joinus.join", request, response);
		container.endContext(request, response);
//		request.getRequestDispatcher("/WEB-INF/views/joinus/join.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String btn= request.getParameter("btn");
		
		MyBatisMemberDao memberDao= new MyBatisMemberDao();
		
		if(btn.equals("확인")){
////		page,request,session,application 4대 저장소
			boolean hasMidChecked=false;
			Cookie[] cookies= request.getCookies();
			if(cookies != null)
				for(Cookie cookie:cookies)
					if("mid-checked".equals(cookie.getName()))
						hasMidChecked=true;
					
					String mid=request.getParameter("mid");
					String name=request.getParameter("name");
					String pwd=request.getParameter("pwd");
					String ppwd=request.getParameter("ppwd");
					String email=request.getParameter("email");
					//---------------------------------------------
					//유효성 검사
					//---------------------------------------------
					boolean hasError=false;
					
					Pattern pattern=Pattern.compile("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$");
					
					StringBuilder errorMsg= new StringBuilder();
					errorMsg.append("<ul>");
					
					if(mid==null || mid.equals("")){
						errorMsg.append("<li>아이디가 입력되지 않았습니다.</li>");
						hasError= true;
					}
					if(!hasMidChecked){
						errorMsg.append("<li>아이디 중복을 확인하지 않았습니다.</li>");
						hasError= true;
					}
					if(name==null|| name.equals("")){
						errorMsg.append("<li>비밀번호가 입력되지 않았습니다.</li>");
						hasError= true;
					}
					if(!pwd.equals(ppwd)){
						errorMsg.append("<li>비밀번호가 일치하지 않습니다.</li>");
						hasError= true;
					}
					if(!pattern.matcher(email).matches()){
						errorMsg.append("<li>이메일 형식이 올바르지 않습니다.</li>");
						hasError= true;
					}
						
					errorMsg.append("</ul>");
					
					if(hasError){
						request.setAttribute("errorMsg", errorMsg);
						
						request.setAttribute("mid",mid);
						request.setAttribute("name",name);
						request.setAttribute("email",email);
						
						TilesContainer container = TilesAccess.getContainer(request.getSession().getServletContext());
						container.render("joinus.join", request, response);
						container.endContext(request,response);
					}
					else{
						Member member=new Member();
						member.setMid(mid);
						member.setName(name);
						member.setPwd(pwd);
						member.setEmail(email);
						member.setNicName("기냥고수");
						
						memberDao.insert(member);
						
						response.sendRedirect("confirm");
					}
		}
		else if(btn.equals("중복확인")){
			String mid=request.getParameter("mid");
			Member member=memberDao.get(mid);
			
			if(member !=null)
				request.setAttribute("duplicatedResult", "이미 사용 중인 아이디입니다.");
			else{
				request.setAttribute("duplicatedResult", "사용 가능한 아이디입니다.");
			//쿠키로 아이디 사용가능함을 검증한 결과를 저장
			Cookie cookie= new Cookie("mid-checked","ok");
			cookie.setMaxAge(24*60*60);
			response.addCookie(cookie);
			
			}
			
			TilesContainer container = TilesAccess.getContainer(request.getSession().getServletContext());
			container.render("joinus.join", request, response);
			container.endContext(request,response);
		}
		
	}

}
