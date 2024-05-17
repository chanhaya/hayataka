<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.ArrayList" %>

<%
ArrayList<Integer> firstNumberList = (ArrayList<Integer>)request.getAttribute("firstNumberList");
ArrayList<Integer> secondNumberList = (ArrayList<Integer>)request.getAttribute("secondNumberList");
%>

<html>
	<head>
		<title>数字探しゲーム</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/trickNumber.css">
		
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
			
				// idが'time'のHTML要素を取得し内容を変数timeに変更する
				var element = document.getElementById('time');
				element.innerHTML = time;
				
				setTimeout("disp()", 1000);		
			}
		</script>
		
	</head>

	<body onload="disp()">
		<h2>数字を探せ<span class ="correct" id="correct">31</span></h2>
		<% if(firstNumberList != null) { %>
			<div class = "container">
				<p class = "relative">この中の不動の数字を小さい順に並べたときに真ん中に来る数字を選べ</p>
				
				<p>経過時間：<span id="time"></span></p>
						
				<button type="button" onclick="location.href='<%= request.getContextPath() %>/trickNumber'">リトライ</button>
				<div class = "aa" onclick="uncorrect()"><%= firstNumberList.get(0) %></div>
				<div class = "ab" onclick="uncorrect()"><%= firstNumberList.get(1) %></div>
				<div class = "ac" onclick="uncorrect()"><%= firstNumberList.get(2) %></div>
				<div class = "ad" onclick="uncorrect()"><%= firstNumberList.get(3) %></div>
				<div class = "ae" onclick="uncorrect()"><%= firstNumberList.get(4) %></div>
				<div class = "af" onclick="uncorrect()"><%= firstNumberList.get(5) %></div>
				<div class = "ag" onclick="uncorrect()"><%= firstNumberList.get(6) %></div>
				<div class = "ah" onclick="uncorrect()"><%= firstNumberList.get(7) %></div>
				<div class = "ai" onclick="uncorrect()"><%= firstNumberList.get(8) %></div>
				<div class = "aj" onclick="uncorrect()"><%= firstNumberList.get(9) %></div>
				<div class = "ak" onclick="uncorrect()"><%= firstNumberList.get(10) %></div>
				<div class = "al" onclick="uncorrect()"><%= firstNumberList.get(11) %></div>
				<div class = "am" onclick="uncorrect()"><%= firstNumberList.get(12) %></div>
				<div class = "an" onclick="uncorrect()"><%= firstNumberList.get(13) %></div>
				<div class = "ao" onclick="uncorrect()"><%= firstNumberList.get(14) %></div>
				<div class = "ap" onclick="uncorrect()"><%= secondNumberList.get(0) %></div>
				<div class = "aq" onclick="uncorrect()"><%= secondNumberList.get(1) %></div>
				<div class = "ar" onclick="uncorrect()"><%= secondNumberList.get(2) %></div>
				<div class = "as" onclick="uncorrect()"><%= secondNumberList.get(3) %></div>
				<div class = "at" onclick="uncorrect()"><%= secondNumberList.get(4) %></div>
				<div class = "au" onclick="uncorrect()"><%= secondNumberList.get(5) %></div>
				<div class = "av" onclick="uncorrect()"><%= secondNumberList.get(6) %></div>
				<div class = "aw" onclick="uncorrect()"><%= secondNumberList.get(7) %></div>
				<div class = "ax" onclick="uncorrect()"><%= secondNumberList.get(8) %></div>
				<div class = "ay" onclick="uncorrect()"><%= secondNumberList.get(9) %></div>
				<div class = "az" onclick="uncorrect()"><%= secondNumberList.get(10) %></div>
				<div class = "ba" onclick="uncorrect()"><%= secondNumberList.get(11) %></div>
				<div class = "bb" onclick="uncorrect()"><%= secondNumberList.get(12) %></div>
				<div class = "bc" onclick="uncorrect()"><%= secondNumberList.get(13) %></div>
				<div class = "bd" onclick="uncorrect()"><%= secondNumberList.get(14) %></div>
			</div>
		
			<script type="text/javascript">
						
				var correct = document.getElementById('correct');
				
				correct.onclick = function() {
					alert("正解⭕️" + "\n" + "時間：" + time);
				}

				function uncorrect() {
					alert("不正解❌");
				}
					
			</script>
		<% } %>
	</body>
</html>