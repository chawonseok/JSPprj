package com.suwon.web.controller.customer;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tiles.TilesContainer;
import org.apache.tiles.access.TilesAccess;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.suwon.web.dao.NoticeDao;
import com.suwon.web.dao.NoticeFileDao;
import com.suwon.web.dao.mybatis.MyBatisNoticeDao;
import com.suwon.web.dao.mybatis.MyBatisNoticeFileDao;
import com.suwon.web.entities.Notice;
import com.suwon.web.entities.NoticeFile;

@WebServlet("/customer/notice-reg")
public class NoticeRegController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session= request.getSession();
		
		String msg=URLEncoder.encode("권한없음","UTF-8");
		
		if(session.getAttribute("mid")==null)
			response.sendRedirect("../error?msg="+msg+"&returnUrl=customer/notice");
		
		// tiles를 통해호출하게 만들기
		TilesContainer container = TilesAccess.getContainer(request.getSession().getServletContext());
		container.render("customer.notice-reg", request, response);
		container.endContext(request, response);
//		request.getRequestDispatcher("/WEB-INF/views/customer/notice-reg.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * //한글설정하는 방법 -> JSP에서는 사용 불가능 request.setCharacterEncoding("UTF-8");
		 */

		/*
		 * String title = request.getParameter("title"); String content =
		 * request.getParameter("content");
		 */

		// con library를 쓰게 되면 인제부터 request도구를 사용할 수 없게 된다.
		// request에 넣어줘도 null값이 되버림 -> 다른 방법으로 사용해야됨

		// 업로드 시킬 경로는 무조건 절대경로여야 한다.
		// 이러한 절대 경로를 동적으로 받아오기 위해 하는 작업
		ServletContext ctx = request.getServletContext();
		String path = ctx.getRealPath("/customer/upload");
		// System.out.println(path);

		MultipartRequest req = new MultipartRequest(request, path,
				// 크기 :10mb
				1024 * 1024 * 10, "UTF-8", new DefaultFileRenamePolicy());

		String title = req.getParameter("title");
		String content = req.getParameter("content");

		/*
		 * //같은 title을 여러개 보낼 경우에 배열로 받아와진다 -> getParameter 가아니라
		 * getParameterValues사용해야됨 String[] titles =
		 * req.getParameterValues("title"); for(String title : titles)
		 * System.out.println(title);
		 */

		NoticeDao noticeDao = new MyBatisNoticeDao();

		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setContent(content);
		// ���� �α��� ����� ������ ��� ����
		notice.setWriter("me");

		noticeDao.insert(notice);

		//----------------------------------FileDao꺼-------------------------------------//

		String lastCode = noticeDao.getLastCode();

		Enumeration en = req.getFileNames();

		// String f1 = req.getParameter("file"); //null나옴

		// String f3 = req.getOriginalFileName("file");
		// System.out.println(f1 + f2 + f3);
		NoticeFileDao noticeFileDao = new MyBatisNoticeFileDao();

		while (en.hasMoreElements()){
			NoticeFile nf = new NoticeFile();

			// 파일에 대한 식별자를 반환해준다 object형이기 때문에 String으로 강제변환해준다.
			String name = (String) en.nextElement();
			String fname = req.getFilesystemName("file"); // 이거너야됨
			nf.setSrc(fname);
			nf.setNoticeCode(lastCode);
			noticeFileDao.insert(nf);
		}

		// 첫번째 파일명
//			req.getFilesystemName(name);
		response.sendRedirect("notice");
	}
}
