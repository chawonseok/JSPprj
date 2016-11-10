package com.suwon.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tiles.TilesContainer;
import org.apache.tiles.access.TilesAccess;

@WebServlet("/error")
public class ErrorController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String msg=request.getParameter("msg");
		String url=request.getParameter("returnUrl");
		
		
		
		TilesContainer container = TilesAccess.getContainer(request.getSession().getServletContext());
		container.render("root.error", request, response);
		container.endContext(request,response);
	}
	
}
