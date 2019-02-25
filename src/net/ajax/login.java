package net.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;


@WebServlet("/login.do")
public class login extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		try {

			resp.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = resp.getWriter();	// 출력 객체
			
			String id=req.getParameter("id").trim();
			String pw=req.getParameter("password").trim();

			MemberDTO dto=new MemberDTO();
			MemberDAO dao=new MemberDAO();
			
			dto.setId(id);
			dto.setPasswd(pw);

			// 로그인
			String mlevel=dao.login(dto);
			
			JSONObject obj=new JSONObject();
			obj.put("mlevel", mlevel);

			out.println(obj.toString());
			
			out.flush();	// buffer에 있는 데이터를 최종 출력장소로 밀어내는 메소드
			out.close();
			
			
		} catch (Exception e) {
			System.out.println("Request Failed! " + e);
		}
		
	}

}
