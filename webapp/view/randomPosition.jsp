<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.ArrayList" %>
<%
ArrayList<Integer> x = new ArrayList<>();
while(x.size() < 6) {
	int num = (int)(Math.random() * 30 + 10);
	if(x.contains(num) == false) {
		x.add(num);
	}
}
ArrayList<Integer> y = new ArrayList<>();
while(y.size() < 6) {
	int num = (int)(Math.random() * 30 + 10);
	if(y.contains(num) == false) {
		y.add(num);
	}
}
%>
<html>
	<head>
		<title>位置ランダム</title>
	</head>	
	<body>
		<div>
			<p style="position: relative">開始位置</p>
			<% for(int i = 0; i < 3; i++) { %>
				<p style="position: absolute; top: <%= x.get(i) %>vw; left: <%= y.get(i) %>vw">テスト<%= i %></p>
			<% } %>
			
			<% for(int i = 3; i < 6; i++) { %>
				<p style="margin-top: <%= x.get(i) %>vw; margin-left: <%= y.get(i) %>vw">テスト<%= i %></p>
			<% } %>
		</div>
	</body>
</html>