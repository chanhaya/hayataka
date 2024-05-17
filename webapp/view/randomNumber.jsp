<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.ArrayList" %>

<%
ArrayList<Integer> numberList = (ArrayList<Integer>)request.getAttribute("numberList");
ArrayList<Integer> sortNumberList = (ArrayList<Integer>)request.getAttribute("sortNumberList");
%>

<html>
	<head>
		<title>数字探しゲーム</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/randomNumber.css">
		
		<script type="text/javascript">

			var start = new Date();
			// 初期化
			var hour = 0;
			var min = 0;
			var sec = 0;
			var now = 0;
			var datet = 0;
			var time = 0;
			
			function disp(){
				now = new Date();
				datet = parseInt((now.getTime() - start.getTime()) / 1000);			
				hour = parseInt(datet / 3600);
				min = parseInt((datet / 60) % 60);
				sec = datet % 60;
							
				// 数値が1桁の場合、頭に0を付けて2桁で表示する指定
				if(hour < 10) { hour = "0" + hour; }
				if(min < 10) { min = "0" + min; }
				if(sec < 10) { sec = "0" + sec; }

				// フォーマットを指定（不要な行を削除する）
				time = hour + ':' + min + ':' + sec; // パターン1
			
				// テキストフィールドにデータを渡す処理（不要な行を削除する）
				document.form.field.value = time; // パターン1
			
				setTimeout("disp()", 1000);		
			}
		</script>
		
	</head>

	<body onload="disp()">
		<h2>数字を探せ31</h2>
		<% if(numberList != null) { %>
			<div class = "container">
				<p class = "relative">下の31個の数字から最小, 最大, 中間(16番目)の<br>
				数字を探して答えてください</p>
				<form onsubmit = "submitCheck(); return false" name = "form">
					<table>
						<tr>
							<th></th>
							<td><input type="text" name="field"></td>
						</tr>
						<tr>
							<th>最小</th>
							<td><input id="min" type="text" name="min" required></td>
						</tr>
						<tr>
							<th>最大</th>
							<td><input id="max" type="text" name="max" required></td>
						</tr>
						<tr>
							<th>中間</th>
							<td><input id="mid" type="text" name="mid" required></td> 
						</tr>
						<tr>
							<th></th>
							<td><input id="btn" type="submit" value="答える"></td>
						</tr>
					</table>
				</form>
				<button type="button" onclick="location.href='<%= request.getContextPath() %>/randomNumber'">もう一度</button>
				<div class = "aa"><%= numberList.get(0) %></div>
				<div class = "ab"><%= numberList.get(1) %></div>
				<div class = "ac"><%= numberList.get(2) %></div>
				<div class = "ad"><%= numberList.get(3) %></div>
				<div class = "ae"><%= numberList.get(4) %></div>
				<div class = "af"><%= numberList.get(5) %></div>
				<div class = "ag"><%= numberList.get(6) %></div>
				<div class = "ah"><%= numberList.get(7) %></div>
				<div class = "ai"><%= numberList.get(8) %></div>
				<div class = "aj"><%= numberList.get(9) %></div>
				<div class = "ak"><%= numberList.get(10) %></div>
				<div class = "al"><%= numberList.get(11) %></div>
				<div class = "am"><%= numberList.get(12) %></div>
				<div class = "an"><%= numberList.get(13) %></div>
				<div class = "ao"><%= numberList.get(14) %></div>
				<div class = "ap"><%= numberList.get(15) %></div>
				<div class = "aq"><%= numberList.get(16) %></div>
				<div class = "ar"><%= numberList.get(17) %></div>
				<div class = "as"><%= numberList.get(18) %></div>
				<div class = "at"><%= numberList.get(19) %></div>
				<div class = "au"><%= numberList.get(20) %></div>
				<div class = "av"><%= numberList.get(21) %></div>
				<div class = "aw"><%= numberList.get(22) %></div>
				<div class = "ax"><%= numberList.get(23) %></div>
				<div class = "ay"><%= numberList.get(24) %></div>
				<div class = "az"><%= numberList.get(25) %></div>
				<div class = "ba"><%= numberList.get(26) %></div>
				<div class = "bb"><%= numberList.get(27) %></div>
				<div class = "bc"><%= numberList.get(28) %></div>
				<div class = "bd"><%= numberList.get(29) %></div>
				<div class = "be"><%= numberList.get(30) %></div>
			</div>
		
			<script type="text/javascript">
				
				function submitCheck() {			
					var min = document.getElementById('min').value;
					var max = document.getElementById('max').value;
					var mid = document.getElementById('mid').value;
					var minAnswer;
					var maxAnswer;
					var midAnswer;
	
					if(min == <%= sortNumberList.get(0) %>) {
						minAnswer = "正解⭕️";
					} else {
						minAnswer = "不正解(正解：<%= sortNumberList.get(0) %>)";
					}
		
					if(max == <%= sortNumberList.get(30) %>) {
						maxAnswer = "正解⭕️";
					} else {
						maxAnswer = "不正解(正解：<%= sortNumberList.get(30) %>)";
					}
		
					if(mid == <%= sortNumberList.get(15) %>) {
						midAnswer = "正解⭕️";
					} else {
						midAnswer = "不正解(正解：<%= sortNumberList.get(15) %>)";
					}
	
					alert(minAnswer + '\n' + maxAnswer + '\n' + midAnswer + '\n' + '時間：' + time);
				}
			</script>
		<% } %>
	</body>
</html>