<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.*, bean.Maze" %>

<%
ArrayList<Maze> mazeList = (ArrayList<Maze>)request.getAttribute("mazeList");
int square = 0;
if(mazeList != null) {
	square = ((Integer)(request.getAttribute("square"))).intValue();
}
%>

<html>
	<head>
		<title>迷路</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/maze.css">
	</head>

	<body>
		<div class="container">
			<% if(mazeList == null) { %>
				<form method="get" action="<%= request.getContextPath() %>/normalMaze">
					<p>？ × ？ の迷路を作りますか(19以下)</p>
					<input type="text" pattern="[0-3][0-9]" name="number" required>
					<input type="submit" value="作成">				
				</form>
			<% } else { %>
				<table style="margin: 0 auto; padding-top: 5vw">
					<% for(int i = 0; i < square * square; i += square) { %>
						<tr>
							<% if(i == square * square - square) { %>
								<th>S</th>
							<% } else { %>
								<th></th>
							<% } %>
							
							<% for(int j = 0; j < square; j++) { %>
								<td><%= mazeList.get(i + j).getNum() %></td>
							<% } %>
							
							<% if(i == 0) { %>
								<th>G</th>
							<% } else { %>
								<th></th>
							<% } %>
						</tr>
					<% } %>
				</table>
			<% } %>
		</div>
	</body>
</html>