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
import com.suwon.web.dao.NoticeFileDao;
import com.suwon.web.dao.mybatis.MyBatisNoticeDao;
import com.suwon.web.dao.mybatis.MyBatisNoticeFileDao;
import com.suwon.web.entities.Notice;
import com.suwon.web.entities.NoticeFile;

@WebServlet("/customer/notice-detail")
public class NoticeDetailController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");

		NoticeDao noticeDao = new MyBatisNoticeDao();
		NoticeFileDao noticeFileDao = new MyBatisNoticeFileDao();

		//조회수 올리기 -> 호출될 때마다 1씩 올라감
		int hitUp = noticeDao.hitUp(code);
		
		Notice notice;
		List<NoticeFile> noticeFiles;
		
		notice = noticeDao.get(code);
		noticeFiles = noticeFileDao.getList(code);
		
		request.setAttribute("n", notice);
		request.setAttribute("files", noticeFiles);
		request.setAttribute("prev", noticeDao.getPrev(code));
		request.setAttribute("next", noticeDao.getNext(code));
		

		//이렇게 되면 layout이 합쳐지지 않은 상태에서 호출됨
		/*request.getRequestDispatcher("/WEB-INF/views/customer/notice-detail.jsp").forward(request, response);*/
		
		//tiles를 통해호출하게 만들기
		TilesContainer container = TilesAccess.getContainer(request.getSession().getServletContext());
		container.render("customer.notice-detail", request, response);
		container.endContext(request,response);
	}
}