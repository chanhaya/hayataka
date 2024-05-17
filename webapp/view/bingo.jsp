<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.ArrayList, bean.Bingo" %>

<%
String game = request.getParameter("game");
ArrayList<Bingo> bingoList = null;
ArrayList<Integer> randomNum = null;
int random = 0;
int square = 0;
int success = 0;
if(game.equals("new")) {
	session.invalidate();
} else {
	bingoList = (ArrayList<Bingo>)session.getAttribute("bingoList");
	square = ((Integer)(session.getAttribute("square"))).intValue();
	randomNum = (ArrayList<Integer>)session.getAttribute("randomNum");
	if(randomNum != null) {
		random = ((Integer)(request.getAttribute("random"))).intValue();
		success = ((Integer)(request.getAttribute("success"))).intValue();
	}
}
%>

<html>
	<head>
		<title>ビンゴ</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/bingo.css">
	</head>

	<body>
		<div class="container">
			<form method="post" action="<%= request.getContextPath() %>/bingo">
				<p>？ × ？ のビンゴを作りますか(3, 5, 7 の中から)</p>
				<input type="text" pattern="[3-7]" id="number" name="number" required>
				<input type="hidden" name="motion" value="create">
				<input type="submit" value="作成" onClick="return check()">				
			</form>
			
			
			<% if(bingoList != null) { %>
				<table>
					<% for(int i = 0; i < square; i++) { %>
						<tr>
							<% for(int j = 0; j < square; j++) { %>
								<% Bingo bingo = bingoList.get(i * square +j); %>
								<% if(bingo.isHole() == true) { %>
									<td class="red"><%= bingo.getNum() %></td>
								<% } else { %>
									<td><%= bingo.getNum() %></td>
								<% } %>
							<% } %>
						</tr>
					<% } %>
				</table>
				<form method="post" action="<%= request.getContextPath() %>/bingo">
					<input type="hidden" name="motion" value="push">
					<input type="submit" value="抽選する">
				</form>
			<% }%>		
			<% if(randomNum != null) { %>
				<p>今回の数字：<%= random %></p>
				<p>これまでの数字</p>
				<p>
					<% for(int i = 0; i < randomNum.size() - 1; i++) { %>
						<%= randomNum.get(i) %>,
					<% } %>
				</p>
				<p>ビンゴの数：<%= success %></p>
			<% } %>		
		</div>
		
		<script type="text/javascript">
			function check() {
				var number = document.getElementById('number').value;
				if(number % 2 == 0) {
					alert("偶数が入力されています");
					false;
				} 
			}
		</script>
	</body>
</html>