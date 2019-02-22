<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>05_idCheck.jsp</title>
</head>
<body>
<p>[회원가입]</p>
<form name="memfrm" id="memfrm" onsubmit="return send()">
<table>
<tr>
  <th>아이디</th>
  <td>
      <input type="text" name="uid" id="uid">
      <button type="button" id="checkId">중복확인</button>
  </td>
  <td>
      <span id="panel_id"></span> <!-- ID 중복 관련 메세지 -->
  </td>
</tr>  
<tr>
  <th>비밀번호</th>
  <td><input type="password" name="upw" id="upw"></td>
  <td>&nbsp;</td>
</tr>  
<tr>
  <td colspan="3"><input type="submit" value="가입"></td>
</tr>  
</table>    
</form>
    
<script src="js/jquery.js"></script>
<script src="js/jquery.cookie.js"></script>
<script>
	
</script>
</body>
</html>



