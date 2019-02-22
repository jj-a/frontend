<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="UTF-8">
	<title>01_ajaxTest.jsp</title>
</head>

<body>
	<button>* 서버에서 응답받기 *</button>
	<div id="panel"></div>

	<script src="js/jquery.js"></script>
	<script>

		$("button").click(function () { 
			/*
			$.ajax({
				url: "/frontend/test.do",
				method: "get",
				dataType: "text",
				success: function (result, status, xhr) {
					alert(result);
					$("#panel").empty();
					$("#panel").text(result);
				},
				error: function (xhr, status, error) {
					alert("Error! "+error);
				}
			});
			*/
			$.ajaxSetup({dataType:"text"});
			$.get("/frontend/test.do", responseProc);
		}); // button.click() end
		
		function responseProc(result){
			$("#panel").append(result);
		} // responseProc() end

	</script>
</body>

</html>