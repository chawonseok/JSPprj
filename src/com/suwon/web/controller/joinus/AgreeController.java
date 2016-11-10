package com.suwon.web.controller.joinus;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tiles.TilesContainer;
import org.apache.tiles.access.TilesAccess;

/**
 * Servlet implementation class JoinUsController
 */
@WebServlet("/joinus/agree")
public class AgreeController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// tiles를 통해호출하게 만들기
		TilesContainer container = TilesAccess.getContainer(request.getSession().getServletContext());
		container.render("joinus.agree", request, response);
		container.endContext(request,response);
//		request.getRequestDispatcher("/WEB-INF/views/joinus/agree.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String agree = request.getParameter("agree");
		
		//라디오 버튼 클릭 시 true가 반환되게 해놨음 -> 약관에 동의했다면
		if(agree != null && agree.equals("true"))
			response.sendRedirect("join");
		else
			//오류가있었음을 표시하면서 url을 통해 전달해주기
			response.sendRedirect("agree?er=1");
	}

}
