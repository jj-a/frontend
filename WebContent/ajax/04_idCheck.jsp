<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="UTF-8">
	<title>04_idCheck.jsp</title>
	<script src="js/jquery.js"></script>
</head>

<body>
	<p>[회원가입]</p>
	<form name="memfrm" id="memfrm">
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="uid" id="uid"></td>
				<td>
					<div id="demo" style="display:none"></div>
				</td>
			</tr>
		</table>
	</form>

	<script>

		$("#uid").keyup(function (e) {
			var params = "uid=" + $("#uid").val();
			$.post("/frontend/idcheck.do",
				params,
				responseProc,
				"text"
			);
		});

		function responseProc(result) {
			//alert(result);
			$("#demo").css("display", "block");
			$("#demo").empty();
			$("<span>").html(result).appendTo("#demo");
		}

	</script>
</body>

</html>