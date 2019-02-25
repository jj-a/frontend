package net.ajax;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {


	// -- Object
	
	private DBOpen dbopen = null;
	private DBClose dbclose = null;

	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private StringBuilder sql = null;

	
	// -- Constructor
	
	public MemberDAO() {
		dbopen = new DBOpen();
		dbclose = new DBClose();
	}

	
	// -- Method
	//////////////////////////////////////////////

	public int duplicateCheck(String column, String value) {
		// 아이디,이메일 중복확인

		int cnt = 0;

		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append("SELECT COUNT("+column+") cnt FROM member ");
			sql.append("WHERE "+column+"=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, value);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				cnt = rs.getInt("cnt");
				//System.out.println("cnt="+cnt);
			} else {
				throw new Exception("rs.next()가 제대로 동작하지 않습니다. " + "Check: Query가 제대로 들어갔는지, next()가 중복 사용된건 아닌지 확인해주세요.");
			}

		} catch (Exception e) {
			System.out.println("*Error* 아이디 중복 조회를 실패했습니다. \n" + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}

		return cnt;
		
	} // duplicateCheck() end ////////////////////////////////////////////

	
	
	public String login(MemberDTO dto) {
		// 로그인

		String mlevel = null;

		try {

			con = dbopen.getConnection();

			sql = new StringBuilder();
			sql.append("SELECT mlevel FROM member ");
			sql.append("WHERE id=? AND passwd=? AND mlevel IN('A1','B1','C1','D1') ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPasswd());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				mlevel = rs.getString("mlevel");
			} else {
				throw new Exception("회원이 아니거나 회원등급이 적절하지 않거나 아이디/비밀번호 오류입니다.");
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}

		return mlevel;

	} // login() end ////////////////////////////////////////////
	
	
	
	
	
	
}
