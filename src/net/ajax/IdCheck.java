package net.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/idcheck.do")
public class IdCheck extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		try {

			resp.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = resp.getWriter();	// 출력 객체
			
			String uid=req.getParameter("uid").trim();
			String message="";
			
			if(uid.length()<5||uid.length()>10) message+="아이디는 5~10글자 이내로 입력해주세요.";
			else {
				// 아이디 중복 검사
				MemberDAO dao=new MemberDAO();
				int cnt=dao.duplicateCheck("id", uid);
				
				if(cnt==0) {
					message+="사용 가능한 아이디입니다.";
					message+="<input type='button' value='사용' onclick=apply('"+uid+"')>";
				}
				else {
					message+="해당 아이디는 이미 사용중입니다.";
				}

				message+="<input type='button' value='닫기' onclick='window.close()'>";
				
			}
			
			out.println(message);
			
			
			out.flush();	// buffer에 있는 데이터를 최종 출력장소로 밀어내는 메소드
			out.close();
			
		} catch (Exception e) {
			System.out.println("Request Failed! " + e);
		}
		
	}

}
