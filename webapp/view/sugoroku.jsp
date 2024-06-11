<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.*, bean.SugorokuSpace, bean.SugorokuUser, bean.SugorokuItem" %>

<%
String action = request.getParameter("action");
String text = null;
SugorokuUser user = null;
SugorokuUser cp = null;
SugorokuUser player = null;
SugorokuUser opponent = null;
ArrayList<SugorokuSpace> sugorokuSpace = null;
boolean cpItem = false;
int cpItemNumber = 0;
int dice = 0;
int dice2 = 0;
Object obj = session.getAttribute("dice2");
if(obj != null) {
	dice2 = ((Integer)(session.getAttribute("dice2"))).intValue();
}
if(action.equals("new")) {
	
} else {
	text = (String)request.getAttribute("text");

	user = (SugorokuUser)session.getAttribute("user");
	cp = (SugorokuUser)session.getAttribute("cp");
	sugorokuSpace = (ArrayList<SugorokuSpace>)session.getAttribute("sugorokuSpace");
	
	if(user.isTurn() == true) {
		player = user;
		opponent = cp;
		
	} else {
		player = cp;
		opponent = user;
	}
	
	// cpがアイテムを持っている場合は基本使う
	// ※位置入れ替えは自分が後ろにいる場合に使う
	if(cp.getItemList() != null) {
		for(int i = 0; i < cp.getItemList().size(); i++) {
			int tmpItem = cp.getItemList().get(i).getNumber();
			if(tmpItem == 3) {
				// 位置入れ替え
				if(cp.getSpace() < user.getSpace()) {
					cpItemNumber = i;
					cpItem = true;
				}
			} else if(tmpItem == 4) {
				// 壊すアイテムがない場合
				if(user.getItemList() != null) {
					cpItemNumber = i;
					cpItem = true;
				}
				
			} else {
				cpItemNumber = i;
				cpItem = true;
			}
		}
	}
	
	if(action.equals("game") || action.equals("effect")) {
		dice = ((Integer)(session.getAttribute("dice"))).intValue();
	}	
} 

%>

<html>
	<head>
		<title>すごろく</title>
		
		<style>
			img {
				width: 3vw;
				height: 3vw;
			}
			.table {
				border-collapse: collapse;
				width: 100%;
				height: 100%;
			}
			.table td {
				padding: 1vw;
				width: 12%;
			}
			.table td p {
				font-size: 10px;
			}
			.table .border {
				border: 1px solid gray;
				background-color: rgba(255, 255, 30, 0.3);
			}
			.color {
				color: green;
			}
		</style>
		
	</head>

	<body>
		<% if(action.equals("new")) { %>
			<form method="post" action="<%= request.getContextPath() %>/sugoroku">
				<p>名前を入力してください</p>
				<input type="text" name="userName" required>
				<input type="hidden" name="action" value="make">
				<input type="submit" value="送信">				
			</form>
			<p>
				①1回効果マスを踏んで移動した場合、移動先の効果は発動する。<br>
				その効果も移動であった場合は、次の移動先の効果は発動しない。<br>
				※効果は連続しても2回までということ<br><br>
				
				②アイテムは1ターンに1回<br>
				※アイテム→自サイコロの順<br><br>
				
				③アイテムでマスが変動した際にそのマスの効果は発動しない
			</p>
		<% } else { %>
			<table class="table">
				<tr>
					<td class="border">
						<%= sugorokuSpace.getFirst().getText() %>
						<% if(user.getSpace() == 0) { %>
							<img src="<%= request.getContextPath() %>/image/sugoroku/cat.png">
						<% } %>
						<% if(cp.getSpace() == 0) { %>
							<img src="<%= request.getContextPath() %>/image/sugoroku/dog.png">
						<% } %>
					</td>
					<td>
						<form method="post" action="<%= request.getContextPath() %>/sugoroku">
							<% if(user.isResult() == true) { %>
								勝者：<%= user.getName() %><br>
								<a href = "<%= request.getContextPath() %>/view/sugoroku.jsp?action=new">もう1回</a
							<% } else if(cp.isResult() == true) { %>
								勝者：<%= cp.getName() %><br>
								<a href = "<%= request.getContextPath() %>/view/sugoroku.jsp?action=new">もう1回</a
							<% } else if(player.getSpecial() == 3) { %>
								<!-- スキップ時 -->
								<input type="hidden" name="action" value="skip">
								<input type="submit" value="次">
							
							<% } else if(action.equals("effect")) { %>
								<!-- 効果マスを踏んだ場合 -->
								<input type="hidden" name="action" value="effect">
								<input type="submit" value="次">
							
							<% } else if(cp.isTurn() == true) { %>
								<% if(cpItem == true) { %>
									<!-- cpがアイテム使う場合 -->
									<input type="hidden" name="action" value="item">
									<input type="hidden" name="playerItem" value="<%= cpItemNumber %>">
									<input type="submit" value="次">
									
								<% } else { %>
									<!-- cpがアイテム使わない場合 -->
									<input type="hidden" name="action" value="game">
									<input type="submit" value="サイコロを振る">
									
								<% } %>
								
							<% } else { %>
								<input type="hidden" name="action" value="game">
								<input type="submit" value="サイコロを振る">
							<% } %>
							
						</form> 
					</td>
					<% if(action.equals("game") || action.equals("effect")) { %>
						<td colspan="2">
							<%= player.getName() %>のターン<br>
							<span class="color"><%= text %></span>
						</td>
						<td>
							出目<br>
							<img src="<%= request.getContextPath() %>/image/sugoroku/saikoro<%= dice %>.png">
							<% if(dice2 != 0) { %>
								<img src="<%= request.getContextPath() %>/image/sugoroku/saikoro<%= dice2 %>.png">
							<% } %>
						</td>
						<td>
							アイテム(猫)<br>
							<% if(user.getItemList() == null || user.getItemList().size() == 0) { %>
								なし
							<% } else { %>
								<form method="post" value="<%= request.getContextPath() %>/sugoroku">
									<input type="hidden" name="action" value="item">
									<% ArrayList<SugorokuItem> userItemList = user.getItemList(); %>
									<select name="playerItem">
										<% for(int i = 0; i < userItemList.size(); i++) { %>
											<option value="<%= i %>"><%= userItemList.get(i).getText() %></option>
										<% } %>
									</select>
									<% if(user.isUsed() == false && user.isTurn() == true && action.equals("game")) { %>
										<input type="submit" value="使う">
									<% } else if(user.isUsed() == true) { %>
										<br>アイテムは1ターンに1回のみ
									<% } %>												
								</form>		
							<% } %>
						</td>
						<td>
							アイテム(犬)<br>
							<% if(cp.getItemList() == null || cp.getItemList().size() == 0) { %>
								なし
							<% } else { %>
								<% ArrayList<SugorokuItem> cpItemList = cp.getItemList(); %>
								<select name="cpItem">
									<% for(int i = 0; i < cpItemList.size(); i++) { %>
										<option value="<%= i %>"><%= cpItemList.get(i).getText() %></option>
									<% } %>
								</select>	
								<% if(cp.isUsed() == true) { %>
									<br>アイテムは1ターンに1回のみ
								<% } %>
							<% } %>
						</td>
						<td>
						猫：<%= user.getName() %><br>
						犬：CP
						</td>
					<% } %>
					<% if(action.equals("game") || action.equals("effect")) { %>
						
					<% } else { %>
						<% for(int i = 0; i < 5; i++) { %>
							<td></td>
						<% } %>
					<% } %>
					
				</tr>
				<% for(int i = 0; i < 64; i += 8) { %>
					<tr>
						<% if(i % 16 == 0) { %>
							<% for(int j = 1; j <= 8; j++) { %>
								<td class="border">
									<% if(j == 8) { %>
										<p><%= i + j %>↓</p>
									<% } else { %>
										<p><%= i + j %>→</p>
									<% } %>
									<p><%= sugorokuSpace.get(i + j).getText() %></p>
									<% if(user.getSpace() == (i + j)) { %>
										<img src="<%= request.getContextPath() %>/image/sugoroku/cat.png">
									<% } %>
									<% if(cp.getSpace() == (i + j)) { %>
										<img src="<%= request.getContextPath() %>/image/sugoroku/dog.png">
									<% } %>
								</td>
							<% } %>
						<% } else { %>
							<% for(int j = 8; j >= 1; j--) { %>
								<td class="border">
									<% if(j == 8) { %>
										<p><%= i + j %>↓</p>
									<% } else { %>
										<p><%= i + j %>←</p>
									<% } %>
									<p><%= sugorokuSpace.get(i + j).getText() %></p>
									<% if(user.getSpace() == (i + j)) { %>
										<img src="<%= request.getContextPath() %>/image/sugoroku/cat.png">
									<% } %>
									<% if(cp.getSpace() == (i + j)) { %>
										<img src="<%= request.getContextPath() %>/image/sugoroku/dog.png">
									<% } %>
								</td>
							<% } %>
						<% } %>
					</tr>
				<% } %>
				<tr>
					<td class="border">
						<%= sugorokuSpace.getLast().getText() %>
						<% if(user.getSpace() == 65) { %>
							<img src="<%= request.getContextPath() %>/image/sugoroku/cat.png">
						<% } %>
						<% if(cp.getSpace() == 65) { %>
							<img src="<%= request.getContextPath() %>/image/sugoroku/dog.png">
						<% } %>
					</td>
					<td>
						勝者：
						<% if(user.isResult() == true) { %>
							<%= user.getName() %>
						<% } else if(cp.isResult() == true) { %>
							<%= cp.getName() %>
						<% } %>
					</td>
					<% for(int i = 0; i < 6; i++) { %>
						<td></td>
					<% } %>
				</tr>
			</table>			
		<% } %>		
					
	</body>
</html>