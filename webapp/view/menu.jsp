<%@page contentType="text/html; charset=UTF-8" %>

<%
int sales = (int)(Math.random() * 6);

%>

<html>
	<head>
		<title>メニュー</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/main.css">
	</head>

	<body>
		<div class = "container">
			<p><a href = "<%= request.getContextPath() %>/view/diagnosisMatch.jsp">相性診断</a></p>
			<p><a href = "<%= request.getContextPath() %>/randomNumber">数字選択ゲーム</a></p>
			<p><a href = "<%= request.getContextPath() %>/trickNumber">いじわる数字選択ゲーム</a></p>
			<p><a href = "<%= request.getContextPath() %>/view/randomPosition.jsp">文字位置ランダム</a></p>
			<p><a href = "<%= request.getContextPath() %>/view/bingo.jsp?game=new">ビンゴ</a></p>
			<p><a href = "<%= request.getContextPath() %>/view/calcProfit.jsp">利益率計算</a></p>
			<p><a href = "<%= request.getContextPath() %>/view/maze.jsp">迷路(左と下に進まない)</a></p>
			<p><a href = "<%= request.getContextPath() %>/view/normalMaze.jsp">迷路</a></p>
			<p><a href = "<%= request.getContextPath() %>/view/track.jsp">トラック同時にスタートして合流計算</a></p>
		</div>
	</body>
</html>