<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>02_bookTest.jsp</title>
</head>
<body>
	<form>
		교재선택 :
		<select name="book" id="book">
			<option>--선택 --</option>
			<option value="0">spring</option>
			<option value="1">Android</option>
			<option value="2">jQuery</option>
			<option value="3">javaScript</option>
		</select>
	</form>
	<br>
	<div id="display"></div>

	<script src="js/jquery.js"></script>
	<script>
		$.ajaxSetup({dataType: "text"});
		$("#book").change(function () { 
			//alert($(this).val());
			$.post("/frontend/book.do", "book="+$(this).val(), responseProc, "text");
		});

		function responseProc(result) {
			//alert(result);
			$("#display").append(result);
		}
	</script>
</body>
</html>