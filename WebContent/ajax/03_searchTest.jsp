<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="UTF-8">
	<title>03_searchTest.jsp</title>
	<style>
		#suggest {
			margin: 30px 0 30px 0;
			font-size: 20px;
		}
		a, a:hover, a:link {
			color: black;
			font-style: normal;
			text-decoration: none;
		}
		a:hover {
		color: pink;
		}
		li {
			list-style: none;
		}
		#suggest li:first-child {
		font-weight: bold;
		}
	</style>
</head>

<body>
	<h3> [ Suggest 기능 구현 ]</h3>
	<form name="search" id="search">
		<input type="text" name="keyWord" id="keyWord" />
		<input type="button" value="검색" />
	</form>

	<div id="suggest" style="display:none">
	<!-- 제시어 단어 출력부분 -->
	</div>
	

	<script src="js/jquery.js"></script>
	<script>
		$.ajaxSetup({ dataType: "text" });

		$("#keyWord").keyup(function () {
			//alert($("#keyWord").val());
			// id=keyWord 가져오기
			//var params=$("#keyWord").val();

			// form안의 모든 컨트롤 요소 가져오기
			var params = $("#search").serialize();

			if ($("#keyWord").val() == "") {
				// 검색어가 없다면 출력하지 않음
				$("#suggest").hide();
			}

			var params = $("#search").serialize();
			$.post("/frontend/search.do", params, searchProc, "text");

		}); // keyup() end

		function searchProc(result) {
			if(result.length>0){
				$("#suggest").show();
				$("#suggest").empty();
				//$("#suggest").append(result);
				
				var data=result.split("|");
				var suggests=data[1].split(",");
				
				$.each(suggests, function (i, key) { 
					var baseurl="https://www.google.com/search?q=";
					var element="<a href='"+baseurl+key+"' target='_new'>"+key+"</a>";
					$("<li>").html(element).appendTo("#suggest");
				});
			}
		} // searchProc() end

	</script>
</body>

</html>