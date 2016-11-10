package com.suwon.web.controller.joinus;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tiles.TilesContainer;
import org.apache.tiles.access.TilesAccess;

import com.suwon.web.dao.MemberDao;
import com.suwon.web.dao.mybatis.MyBatisMemberDao;
import com.suwon.web.entities.Member;

/**
 * Servlet implementation class JoinController
 */
@WebServlet("/joinus/login")
public class LoginController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// tiles를 통해호출하게 만들기
		TilesContainer container = TilesAccess.getContainer(request.getSession().getServletContext());
		container.render("joinus.login", request, response);
		container.endContext(request, response);
//		request.getRequestDispatcher("/WEB-INF/views/joinus/join.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mid=request.getParameter("mid");
		String pwd=request.getParameter("pwd");
		
		// 인증
		MemberDao memberDao= new MyBatisMemberDao();
		Member member= memberDao.get(mid);
		//만약에 회원이 존재하지 않는다면 회원이 존재 하지 않습니다 문구 출력
		if(member == null){
			request.setAttribute("msg", "회원이 존재하지 않습니다.");
			TilesContainer container = TilesAccess.getContainer(request.getSession().getServletContext());
			container.render("joinus.login", request, response);
			container.endContext(request, response);
		}
		//비번이 틀리다면 비번이 잘못되엇다고 문구 출력
		else if(!member.getPwd().equals(pwd)){
			request.setAttribute("msg", "비밀번호 올치아나.");
			
			TilesContainer container = TilesAccess.getContainer(request.getSession().getServletContext());
			container.render("joinus.login", request, response);
			container.endContext(request, response);
		}

		//둘 다 아니라면 인증성공
		else{
			//인증 상태를 저장
			HttpSession session= request.getSession();
			session.setAttribute("mid", mid);
			response.sendRedirect("../index");
		}
		
		
		
	}

}
