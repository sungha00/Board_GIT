package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MessagesDTO;

@WebServlet("/MessageController")
public class MessageController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getRequestURI();
		MessagesDAO dao = new MessagesDAO();

		try {
			// 1. 메세지 입력
			if(cmd.equals("/insert.message")) {
				int id = Integer.parseInt(request.getParameter(""));
				String writer = request.getParameter("");
				String message = request.getParameter("");

				MessagesDTO dto = new MessagesDTO(id, writer, message);
				int result = dao.insertMessage(dto);
				
				// 등록완료 => 별도의 등록 결과창 제작 요망 
				response.sendRedirect("/index.jsp");
			}

			// 2. 메세지 출력
			if(cmd.equals("/select.message")){
				List<MessagesDTO> result = dao.selectMessage();
				
				// 출력 완료
				request.setAttribute("list", result);
				request.getRequestDispatcher("/list.jsp");
				
			// 3. 메세지 삭제
			}else if(cmd.equals("/delete.message")) {
				String deleteId = request.getParameter("deleteId");
				int result = dao.deleteMessage(deleteId);
				
				// 삭제 완료 => 별도의 삭제 결과창 제작 요망
				response.sendRedirect("/list.jsp");
				
				// 4. 메세지 수정
			}else if(cmd.equals("/update.message")) {
				String modifyId = request.getParameter("modifyId");
				String modifyWriter = request.getParameter("modifyWriter");
				String modifyMessage = request.getParameter("modifyMessage");
				
				dao.updateMessage(modifyId,modifyWriter,modifyMessage);
				
				// 수정 완료 => 별도의 수정 결과창 제작 요망
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
