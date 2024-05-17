<%@page contentType="text/html; charset=UTF-8" %>
<%
String result = (String)request.getAttribute("result");
if(result == null) {
	result = "";
}
String name1 = (String)request.getAttribute("name1");
if(name1 == null) {
	name1 = "";
}
String name2 = (String)request.getAttribute("name2");
if(name2 == null) {
	name2 = "";
}
%>

<html>
	<head>
		<title>相性診断</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/main.css">
	</head>

	<body>
		<div class = "container">
			<% if(!result.equals("")) { %>
				<h2><%= name1 %>と<%= name2 %>の相性結果</h2>
				<h2><%= result %>点</h2>
			<% } %>
			<h2>相性診断</h2>
			<p>相性診断したい名前をアルファベットで入れてください<br>
			その二人の相性を診断します</p>
			<h3>診断方法</h3>
			<p>①アルファベットを数字に変換します<br>
			&emsp;(例：A or a → 1, Z or z → 26)</p>
			<p>②隣り合った数字を足します<br>
			&emsp;(例：abcさん & defさん → 1 2 3 & 4 5 6 → 3 5 7 9 11)</p>
			<p>③足した結果の要素数が1つになるまで繰り返します、<br>
			&emsp;また計算の過程で101以上になった場合は101を引きます<br>
			&emsp;(例： 60 70 → 130 → 29  相性29点)</p>
			<p>④結果の大きい方を表示します<br>
			&emsp;(例： abさん & cdさんの場合、<br>
			&emsp;&emsp;abcd or cdab の並びが考えられるため、結果が大きい方を表示します)
		
			<form method = "post" action = "<%= request.getContextPath() %>/diagnosisMatch">
				<table>
					<tr>
						<th>1人目の名前</th>
						<td><input type="text" pattern="[a-zA-Z]+" placeholder="sato" name="first" required></td>
					</tr>
					<tr>
						<th>2人目の名前</th>
						<td><input type="text" pattern="[a-zA-Z]+" placeholder="suzuki" name="second" required></td>
					</tr>
					<tr>
						<th></th>
						<td><input type="submit" value="送信"></td>
					</tr>
				</table>
				
			</form>
		</div>
	</body>
</html>