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

		// 1. 메세지 입력
		try {
			if(cmd.equals("/insert.message")) {
				int id = Integer.parseInt(request.getParameter(""));
				String writer = request.getParameter("");
				String message = request.getParameter("");

				int result = dao.insertMessage();
				
			}


			// 2. 메세지 출력
			if(cmd.equals("/select.message")){
				
				List<MessagesDTO> result = dao.selectMessage();
				request.setAttribute("list", result);
				request.getRequestDispatcher("/list.jsp");




				// 3. 메세지 삭제
			}else if(cmd.equals("/delete.message")) {




				dao.deleteMessage();
				// 4. 메세지 수정
			}else if(cmd.equals("/update.message")) {


				dao.updateMessage();
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
