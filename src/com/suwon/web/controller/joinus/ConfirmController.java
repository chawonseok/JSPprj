package com.suwon.web.controller.joinus;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tiles.TilesContainer;
import org.apache.tiles.access.TilesAccess;

@WebServlet("/joinus/confirm")
public class ConfirmController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		TilesContainer container = TilesAccess.getContainer(request.getSession().getServletContext());
		container.render("joinus.confirm", request, response);
		container.endContext(request,response);
	}

}
