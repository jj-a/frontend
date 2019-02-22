package net.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/book.do")
public class BookTest extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			resp.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = resp.getWriter();	// 출력 객체
			
			int bookIndex=Integer.parseInt(req.getParameter("book"));
			String img[]= {"spring.jpg", "android.jpg", "jquery.jpg", "jsmasterbook.jpg"};
			
			out.println("<img src='book/"+img[bookIndex]+"'>");
			
			out.flush();	// buffer에 있는 데이터를 최종 출력장소로 밀어내는 메소드
			out.close();
			
		} catch (NumberFormatException e) {; } catch (Exception e) {
			System.out.println("Request Failed! " + e);
		}
		
	} // doPost() end

}
