package net.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;


@WebServlet("/idcheck2.do")
public class IdCheck2 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		try {

			resp.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = resp.getWriter();	// 출력 객체
			
			String uid=req.getParameter("uid").trim();

			// 아이디 중복 검사
			MemberDAO dao=new MemberDAO();
			int cnt=dao.duplicateCheck("id", uid);
			
			/*
			// 텍스트 방식
			out.println(cnt);
			*/
			
			JSONObject obj=new JSONObject();
			obj.put("count", cnt);

			out.println(obj.toString());
			
			out.flush();	// buffer에 있는 데이터를 최종 출력장소로 밀어내는 메소드
			out.close();
			
			
		} catch (Exception e) {
			System.out.println("Request Failed! " + e);
		}
		
	}

}
