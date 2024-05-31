<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.*"%>

<%
String action = request.getParameter("action");
String alert = (String)request.getAttribute("alert");
if(alert == null) {
	alert = "";
}
int line = 0;
List<String> amidakuji = null;
int[][] amida = null;
Map<Integer, Integer> map = null;
if(action.equals("vertical")) {
	line = ((Integer)(session.getAttribute("line"))).intValue();
	amidakuji = (List<String>)(session.getAttribute("amidakuji"));

} else if(action.equals("beside")) {
	line = ((Integer)(session.getAttribute("line"))).intValue();
	amidakuji = (List<String>)(session.getAttribute("amidakuji"));
	amida = (int[][])session.getAttribute("amida");
	map = (Map<Integer, Integer>)session.getAttribute("map");
}
%>

<html>
	<head>
		<title>あみだくじ</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/amidakuji.css">
		
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
							
				// time = hour + ':' + min + ':' + sec; // パターン1
				time = 10 - sec;
			
				// idが'time'のHTML要素を取得し内容を変数timeに変更する
				var element = document.getElementById('time');
				element.innerHTML = time;
				
				setTimeout("disp()", 1000);

				if(time == 0) {
					window.location.href='<%= request.getContextPath() %>/amidakuji?action=death';
				}
			}
		</script>
	</head>

	<body onload="disp()">
		<div>
			<% if(action.equals("new")) { %>
				<div class="center">
					<form method="get" action="<%= request.getContextPath() %>/amidakuji">
						<p>縦線はいくつにしますか？(2〜9)</p>
						<input type="text" name="line" pattern="[2-9]" required>
						<input type="hidden" name="action" value="vertical">
						<input type="submit" value="送信">
					</form>
					<p>参考</p>
					<span>1</span><span>2</span><span>3</span><span>4</span><span>5</span><span>6</span>
					<table class="table" border=0>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td style="border-top: 1px solid gray;"></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td style="border-top: 1px solid gray;"></td>
							<td></td>
						</tr>
						<tr>
							<td style="border-top: 1px solid gray;"></td>
							<td></td>
							<td></td>
							<td style="border-top: 1px solid gray;"></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td style="border-top: 1px solid gray;"></td>
							<td></td>
							<td></td>
							<td style="border-top: 1px solid gray;"></td>
							<td></td>
						</tr>
						<tr>
							<td style="border-top: 1px solid gray;"></td>
							<td></td>
							<td style="border-top: 1px solid gray;"></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</table>
					<span>a</span><span>b</span><span>c</span><span>d</span><span>e</span><span>f</span>
				</div>		
					
			<% } else if(action.equals("vertical")) { %>
				<!-- 縦線作成後 縦線作成後 縦線作成後 縦線作成後 縦線作成後 縦線作成後 -->
				
				<div class="container">
					
					<form class="form" method="get" action="<%= request.getContextPath() %>/amidakuji">
						<% if(alert != null) { %>
							<p class="mimic"><%= alert %></p>
						<% } %>
						
						<% if(line <= 4) { %>
							<p>横線を何本引きますか？(<%= line %> 以上 24 以下)</p>
						<% } else if(line <= 7) { %>
							<p>横線を何本引きますか？(<%= line %> 以上 48 以下)</p>
						<% } else { %>
							<p>横線を何本引きますか？(<%= line %> 以上 72 以下)</p>
						<% } %>
							
						<input type="text" name="besideLine" required>
						<input type="hidden" name="action" value="beside">
						<input type="submit" value="送信">
					</form>
					<% for(int i = 0; i < line; i++) { %>
						<span class="number"><%= i + 1 %></span>
					<% } %>
					<table class="amida" border=0>
						<% for(int i = 0; i < line; i++) { %>
							<tr>
								<% for(int j = 0; j < line; j++) { %>
									<td></td>
								<% } %>
							</tr>
						<% } %>
					</table>
					<% for(int i = 0; i < line; i++) { %>
						<img src="<%= request.getContextPath() %>/image/amida/<%= amidakuji.get(i) %>.png">
					<% } %>
				</div>
				
			<% } else if(action.equals("beside")) { %>
				<!-- 横線作成後 横線作成後 横線作成後 横線作成後 横線作成後 横線作成後  -->
				<div class="container">
					宝につながる道(数字)を選べ
					<p class="mimic">制限時間：<span id="time"></span></p>
					<% for(int i = 0; i < line; i++) { %>
						<% if(map.get(i) == 2) { %>
							<span class="number cursor" onclick="location.href='<%= request.getContextPath() %>/amidakuji?action=mufu'"><%= i + 1 %></span>
						<% } else { %>
							<span class="number cursor" onclick="location.href='<%= request.getContextPath() %>/amidakuji?action=death'"><%= i + 1 %></span>
						<% } %>
						
					<% } %>
					<table class="amida" border=0>
						<% for(int i = 0; i < 25; i++) { %>
							<tr>
								<% for(int j = 0; j < line; j++) { %>
									<% if(amida[i][j] == 1) { %>
										<td style="border-top: 1px solid gray;"></td>
									<% } else { %>
										<td></td>
									<% } %>
									
								<% } %>
							</tr>
						<% } %>
					</table>
					<% for(int i = 0; i < line; i++) { %>
						<img src="<%= request.getContextPath() %>/image/amida/<%= amidakuji.get(i) %>.png">
					<% } %>
				</div>
				
			<% } else if(action.equals("mufu")) { %>
				<!-- お宝発見時 お宝発見時 お宝発見時 お宝発見時 お宝発見時 お宝発見時 -->
				<div class="result">
					<h1 class="treasure blink">お宝発見！！</h1>
					<img src="<%= request.getContextPath() %>/image/amida/mufu.png">
				</div>
				
			<% } else { %>
				<!-- お宝失敗時 お宝失敗時 お宝失敗時 お宝失敗時 お宝失敗時 お宝失敗時 -->
				<div class="result">
					<h1 class="mimic blink">ミミックに襲われた</h1>
					<img src="<%= request.getContextPath() %>/image/amida/death.png">
				</div>
				
			<% } %>	
		</div>
		
	</body>
</html>