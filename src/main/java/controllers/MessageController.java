package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MessageDAO;
import dto.MessageDTO;

@WebServlet("*.message")
public class MessageController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getRequestURI();
		MessageDAO dao = MessageDAO.getInstance();

		try {
			// 1. 메세지 입력
			if(cmd.equals("/insert.message")) {
				String writer = request.getParameter("writer");
				String message = request.getParameter("message");

				int result = dao.insertMessage(writer,message);

				if(result>0) {
					response.sendRedirect("/index.jsp");
				}
				// 2. 메세지 출력
			}else if(cmd.equals("/select.message")) {
				List<MessageDTO> result = dao.selectMessage();

				// 출력 완료
				request.setAttribute("list", result);
				request.getRequestDispatcher("/list.jsp");

				// 3. 메세지 삭제
			}else if(cmd.equals("/delete.message")) {
				int deleteId = Integer.parseInt(request.getParameter("deleteId"));
				int result = dao.deleteMessage(deleteId);

				response.sendRedirect("/list.jsp");

				// 4. 메세지 수정
			}else if(cmd.equals("/update.message")) {
				int modifyId = Integer.parseInt(request.getParameter("modifyId"));
				String modifyWriter = request.getParameter("modifyWriter");
				String modifyMessage = request.getParameter("modifyMessage");

				// DAO의 메세지 수정 메소드 확인
				// 메소드 하나로 구현 어려울 시, 검색 메소드와 수정 메소드 구분 사용가능
				dao.updateMessage(modifyId,modifyWriter,modifyMessage);

				response.sendRedirect("/list.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("/error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
