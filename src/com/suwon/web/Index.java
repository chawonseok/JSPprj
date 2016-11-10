package com.suwon.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/greeting")
public class Index extends HttpServlet{		
	@Override	 							
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{								   		//요청		                //입력
		//보낼때의 방식
		response.setCharacterEncoding("UTF-8");
		//client가 읽어야할 방법
		response.setContentType("text/html; charset=UTF-8");
		/*OutputStream os = response.getOutputStream();
		PrintWriter out = new PrintWriter(os);*/

		PrintWriter out = response.getWriter();
		
		//입력받은 것을 저장시킬 변수
		String _cnt = request.getParameter("cnt");
		
		//문자형으로 들어오기 때문에 정수형 변수를 미리 선언(초기값)
		int cnt = 0;
		
		//만약 입력받은 값이 없으면 100번 돌게 하고 입력받은 값이 있으면 입력받은 값 만큼만 돌아가게 만든다.
		if(_cnt != null && !_cnt.equals(""))
			cnt = Integer.parseInt(_cnt);		
		
		out.write("<!DOCTYPE html>");
		out.write("<html>");
		out.write("<head>");
		out.write("<meta charset=\"UTF-8\">");
		out.write("<title>Insert title here</title>");
		out.write("</head>");
		out.write("<body>");
		out.write("	<form action=\"greeting\" method=\"get\">");
		out.write("		<fieldset>");
		out.write("			<legend>환영인사 입력</legend>");
		out.write("			<div>");
		out.write("				<label>반복회수 :</label>");
		out.write("				<input type=	\"text\" name=\"cnt\"/>");
		out.write("			</div>");
		out.write("			<div>");
		out.write("				<input type=\"submit\" value=\"say hello\"/>");
		out.write("			</div>");
		out.write("		</fieldset>");
		out.write("	</form>");
		for(int i=0; i<cnt; i++)
			out.println((i+1)+ ": 안녕?<br />");
		out.write("</body>");
		out.write("</html>");
		
	}	
}
