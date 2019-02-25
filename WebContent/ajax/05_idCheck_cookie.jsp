<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="UTF-8">
	<title>05_idCheck.jsp</title>
	<script src="js/jquery.js"></script>
	<script src="js/jquery.cookie.js"></script>
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

	<script>
	
		$(function () {
			$.removeCookie("checkID");	// 쿠키변수 삭제
		});

		$("#uid").keypress(function () { 
			$(this).css("background-color", "transparent");
		});
		
		$("#checkId").click(function () { 
			var params="uid="+$("#uid").val();
			$.post("/frontend/idcheck2.do", params,
				responseProc,
				"json"	// json library 이용 시 json으로 지정 (default=text)
			);
		}); // checkId.click() end

		
		function responseProc (result) {
			
			//alert(result.count);
			var uid=$("#uid").val();
			var message="";
			
			if(uid.length<5||uid.length>10){
				message+="아이디는 5~10글자 이내로 입력해주세요.";
				$.removeCookie("checkID");
				$("#uid").focus();
				$("#uid").css("background-color", "#faffbd");
			}
			else {
				if(result.count==0) {
					message+="사용 가능한 아이디입니다.";
					message+="<input type='button' value='사용' onclick=apply('"+uid+"')>";
					$.cookie("checkID","pass");
				}
				else {
					message+="해당 아이디는 이미 사용중입니다.";
					$.removeCookie("checkID");
					$("#uid").focus();
					$("#uid").css("background-color", "#faffbd");
				}
			}
			
			$("#panel_id").empty();
			$("#panel_id").append(message);
			
		} // responseProc() end


		function send(f){
			var uid=$("#uid").val();
			if($.cookie("checkID")=="pass"){
				return true;
			}
			else {
				alert("아이디 중복확인을 눌러 사용 가능한 아이디를 설정해주세요.");
				$("#uid").focus();
				$("#uid").css("background-color", "#faffbd");
				return false;
			}
		}

	</script>
</body>

</html>