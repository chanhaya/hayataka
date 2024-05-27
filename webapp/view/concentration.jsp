<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.*, bean.PlayingCards" %>

<%
ArrayList<PlayingCards> playingCards = (ArrayList<PlayingCards>)session.getAttribute("playingCards");
String game = request.getParameter("game");
// 横に何枚表示するか
int beside = 11;
// はみでた枚数
int surplus = playingCards.size() % beside;
// ペア数
int pair = 0;
int turn = 0;
if(!game.equals("new")) {
	pair = ((Integer)(session.getAttribute("pair"))).intValue();
	turn = ((Integer)(session.getAttribute("turn"))).intValue();
}


%>

<html>
	<head>
		<title>神経衰弱</title>
		<style>
			img {
				width: 8vw;
				height: 9vw;
			}
		</style>
	</head>

	<body>
		<div class="container">
		<p>ペア数：<%= pair %>&emsp;&emsp;ターン数：<%= turn %></p>
			<table>
				<% for(int i = 0; i < playingCards.size() / beside; i++) { %>
					<tr>
						<% for(int j = i * beside; j < i * beside + beside; j++) { %>
							<% PlayingCards card = playingCards.get(j); %>
							<td>
								<% if(card.getSelect() == 1 || card.isPair() == true) { %>
									<img src="<%= request.getContextPath() %>/image/playingCards/<%= card.getPattern() %><%= card.getNum() %>.png">
								<% } else { %>
									<a href = "<%= request.getContextPath() %>/concentration?game=select&num=<%= j %>">
										<img src="<%= request.getContextPath() %>/image/playingCards/back.png">
									</a>
								<% } %>
							</td>
						<% } %>
					</tr>
				<% } %>
				<% if(surplus > 0) { %>
					<tr>
						<% for(int i = surplus; i > 0; i--) { %>
							<% PlayingCards card = playingCards.get(playingCards.size() - i); %>
							<td>
								<% if(card.getSelect() == 1 || card.isPair() == true) { %>
									<img src="<%= request.getContextPath() %>/image/playingCards/<%= card.getPattern() %><%= card.getNum() %>.png">
								<% } else { %>
									<a href = "<%= request.getContextPath() %>/concentration?game=select&num=<%= playingCards.size() - i %>">
										<img src="<%= request.getContextPath() %>/image/playingCards/back.png">
									</a>
								<% } %>
							</td>							
						<% } %>
					</tr>
				<% } %>
			</table>
			
		</div>
	</body>
</html>