package com.suwon.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download")
public class Download extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname = request.getParameter("f");
		
		//다운로드경로만들어주기
		String path = "/customer/upload/" + fname;
		response.setHeader("Content-Disposition"
				, String.format("attachment; filename=\"%s\"",fname));

		ServletContext ctx = request.getServletContext();
		path = ctx.getRealPath(path);
		/*System.out.println(path);
		PrintWriter out = response.getWriter();
		out.write(fname);*/
		
		//입력버퍼를 통해 읽은것을 출력해주기
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		Scanner fin = new Scanner(fis);
		
		//출력도구
		OutputStream wos = response.getOutputStream();
		
		int count = -1;
		
		//한바이트씩 읽게 만들기
		byte[] buf = new byte[1024];
		while((count = fis.read(buf)) != -1 )
			wos.write(buf,0,count);
		
		
		//끝내는 순서도 꼭 지키기\
		fis.close();
		wos.close();
		
	}
}