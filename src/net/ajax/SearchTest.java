package net.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/search.do")
public class SearchTest extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			resp.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = resp.getWriter();	// 출력 객체
			
			String keyword=req.getParameter("keyWord");
			
			ArrayList<String> list=new ArrayList<String>();
			if(keyword.length()>0) {
				list=search(keyword);
			}

			String message="";
			
			message+=list.size()+"|";
			
			for(int i=0;i<list.size();i++) {
				message+=list.get(i);
				if(i<list.size()-1) message+=",";
			}
			
			/*
			for(int i=0;i<list.size();i++) {
				out.println("<li>"+list.get(i)+"</li>");
			}
			*/
			out.println(message);
			
			
			out.flush();	// buffer에 있는 데이터를 최종 출력장소로 밀어내는 메소드
			out.close();
			
		} catch (Exception e) {
			System.out.println("Request Failed! " + e);
		}
		
	} // doPost() end
	
	
	public ArrayList<String> search(String keyword){
		String[] keywords= {"Ajax", "Ajax 실전 프로그래밍", "JAVA", "자바의 정석", "Python", "처음 배우는 파이썬", "머신러닝", "인공지능, 머신러닝, 딥러닝", "텐서플로우", ""};
		ArrayList<String> list = new ArrayList<>();
		
		// 키워드 검색
		for(String compareKeyword: keywords) {
			compareKeyword=compareKeyword.toUpperCase();
			if(compareKeyword.startsWith(keyword.toUpperCase())) list.add(compareKeyword);
		}	
		
		return list;
	} // search() end

}
