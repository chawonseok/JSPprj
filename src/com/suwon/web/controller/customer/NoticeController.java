package com.suwon.web.controller.customer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tiles.TilesContainer;
import org.apache.tiles.access.TilesAccess;

import com.suwon.web.dao.NoticeDao;
import com.suwon.web.dao.mybatis.MyBatisNoticeDao;
import com.suwon.web.model.NoticeModel;

@WebServlet("/customer/notice")
public class NoticeController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String p = request.getParameter("p");
		String t = request.getParameter("t");
		String q = request.getParameter("q");
	
		//������ ���� �ʾ��� ���� �⺻��
		int page = 1;
		String field = "TITLE";
		String query = "";
		
		//���޵Ȱ� ���� �� ����
		if(p != null && !p.equals(""))
			page = Integer.parseInt(p);		
		if(t != null && !t.equals(""))
			field = t;
		if(q != null)
			query = q;
		
		NoticeDao noticeDao = new MyBatisNoticeDao();
		List<NoticeModel> list = noticeDao.getList(page, field, query);
		//���ڵ� ���� ���ϱ�(����¡ �� �� ���� ��ư ���� �� ���ڵ尡 ���� ���� �����ϰ� ����� ���ؼ�)
		int count = noticeDao.getCount(field,query);
		
		request.setAttribute("list", list);
		request.setAttribute("count", count);
//		request.getRequestDispatcher("/WEB-INF/views/customer/notice.jsp").forward(request, response);
		
		// tiles를 통해호출하게 만들기
		TilesContainer container = TilesAccess.getContainer(request.getSession().getServletContext());
		container.render("customer.notice", request, response);
		container.endContext(request,response);
	}
}
