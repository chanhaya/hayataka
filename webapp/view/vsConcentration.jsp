<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.*, bean.PlayingCards, bean.ConcentrationUser" %>

<%
ArrayList<PlayingCards> playingCards = null;
ArrayList<PlayingCards> copyCards = null;
String game = request.getParameter("game");
ConcentrationUser user = null;
ConcentrationUser cp = null;
ConcentrationUser player = null;
// 横に何枚表示するか
int beside = 11;
// はみでた枚数
int surplus = 0;
// cpのターン時にめくる番号
int randomCard = -1;

if(game.equals("choice") || game.equals("name")) {
// 「cp or 対人」 のときと 対人の名前入力時にNullPointerを出さないため

} else {
	playingCards = (ArrayList<PlayingCards>)session.getAttribute("playingCards");
	copyCards = (ArrayList<PlayingCards>)session.getAttribute("copyCards");
	user = (ConcentrationUser)session.getAttribute("user");
	cp = (ConcentrationUser)session.getAttribute("cp");
	surplus = playingCards.size() % beside;
	
	if(user.isTurn() == true) {
		player = user;
	} else {
		player = cp;
		if(game.equals("new") && copyCards.size() != 0) {
			randomCard = ((Integer)(request.getAttribute("randomCard"))).intValue();
		}
	}
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
			.color {
				color: green;
			}
			.border {
				border: solid 2px pink;
			}
		</style>
		
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
				time = 3 - sec;
			
				// idが'time'のHTML要素を取得し内容を変数timeに変更する
				var element = document.getElementById('time');
				element.innerHTML = time;
				
				setTimeout("disp()", 1000);

				if(time == 0) {
					window.location.href='<%= request.getContextPath() %>/vsConcentration?game=select&num=<%= randomCard %>';
				}
			}
		</script>
	</head>

	<body onload="disp()">
		<div class="container">
			<% if(game.equals("choice")) { %>
				<!-- CP戦か対人戦か選ぶ -->
				<p><a href = "<%= request.getContextPath() %>/vsConcentration?game=new">CP戦</a></p>
				<p><a href = "<%= request.getContextPath() %>/view/vsConcentration.jsp?game=name">対人戦</a></p>
			
			<% } else if(game.equals("name")) { %>
				<form method="get" action="<%= request.getContextPath() %>/vsConcentration">
					<input type="hidden" name="game" value="versus">
					プレイヤー1：<input type="text" name="player1" required><br>
					プレイヤー2：<input type="text" name="player2" required><br><br>
					&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;
					<input type="submit" value="送信">
				</form>
				
			<% } else { %>
				<p>
					<%= user.getName() %>のペア数：<%= user.getPair() %> &ensp;<%= cp.getName() %>のペア数：<%= cp.getPair() %> &ensp;<span class="color"><%= player.getName() %>のターン</span>
					<% if(game.equals("new") && cp.isTurn() == true && copyCards.size() != 0) { %>
						CPがめくるまで：<span id="time"></span>秒
					<% } %>
					<% if(copyCards.size() == 0) { %>
						<span class="color">勝者：
							<% if(user.getPair() > cp.getPair()) { %>
								<%= user.getName() %>
							<% } else if(user.getPair() < cp.getPair()) { %>
								<%= cp.getName() %>
							<% } else { %>
								引き分け
							<% } %>
						</span>						
					<% } %>
				</p>
				<table>
					<% for(int i = 0; i < playingCards.size() / beside; i++) { %>
						<tr>
							<% for(int j = i * beside; j < i * beside + beside; j++) { %>
								<% PlayingCards card = playingCards.get(j); %>
								<td>
									<% if(card.getSelect() == 1 || card.isPair() == true) { %>
										<% if(card.getSelect() == 1) { %>
											<img class="border" src="<%= request.getContextPath() %>/image/playingCards/<%= card.getPattern() %><%= card.getNum() %>.png">
										<% } else { %>
											<img src="<%= request.getContextPath() %>/image/playingCards/<%= card.getPattern() %><%= card.getNum() %>.png">
										<% } %>	
									<% } else { %>
										<% if(game.equals("vs")) { %>
											<!-- 対人戦 -->
											<a href = "<%= request.getContextPath() %>/vsConcentration?game=vs&num=<%= j %>">
												<img src="<%= request.getContextPath() %>/image/playingCards/back.png">
											</a>
										<% } else if(cp.isTurn() == true) { %>
											<!-- CP戦のcpのターン時はカードをタッチできないようにする -->
											<img src="<%= request.getContextPath() %>/image/playingCards/back.png">
										<% } else { %>
											<!-- CP戦の自ターン時 -->
											<a href = "<%= request.getContextPath() %>/vsConcentration?game=select&num=<%= j %>">
												<img src="<%= request.getContextPath() %>/image/playingCards/back.png">
											</a>
										<% } %>
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
										<!-- 揃っているもしくはめくられている場合 -->
										<% if(card.getSelect() == 1) { %>
											<!-- 今回めくられた場合、枠に色をつける -->
											<img class="border" src="<%= request.getContextPath() %>/image/playingCards/<%= card.getPattern() %><%= card.getNum() %>.png">
										<% } else { %>
											<img src="<%= request.getContextPath() %>/image/playingCards/<%= card.getPattern() %><%= card.getNum() %>.png">
										<% } %>										
									<% } else { %>
										<% if(game.equals("vs")) { %>
											<!-- 対人戦 -->
											<a href = "<%= request.getContextPath() %>/vsConcentration?game=vs&num=<%= playingCards.size() - i %>">
												<img src="<%= request.getContextPath() %>/image/playingCards/back.png">
											</a>
										<% } else if(cp.isTurn() == true) { %>
											<!-- CP戦のcpのターン時はカードをタッチできないようにする -->
											<img src="<%= request.getContextPath() %>/image/playingCards/back.png">
										<% } else { %>
											<!-- CP戦の自ターン時 -->
											<a href = "<%= request.getContextPath() %>/vsConcentration?game=select&num=<%= playingCards.size() - i %>">
												<img src="<%= request.getContextPath() %>/image/playingCards/back.png">
											</a>
										<% } %>
									<% } %>
								</td>							
							<% } %>
						</tr>
					<% } %>
				</table>				
			<% } %>
		</div>
	</body>
</html>