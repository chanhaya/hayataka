<%@page contentType="text/html; charset=UTF-8" %>

<%
// 売上高
int sales = (int)(Math.random() * 3);
if(sales == 0) {
	sales = 1000;
} else if(sales == 1) {
	sales = 2000;
} else if(sales == 2) {
	sales = 3000;
}
// 固定費
int fix = (int)(Math.random() * 5);
if(fix == 0) {
	fix = 100;
} else if(fix == 1) {
	fix = 200;
} else if(fix == 2) {
	fix = 300;
} else if(fix == 3) {
	fix = 400;
} else if(fix == 4) {
	fix = 500;
}

// 変動費率
int num = (int)(Math.random() * 8);
double variable = 0.0;
if(num == 0) {
	variable = 0.1;
} else if(num == 1) {
	variable = 0.2;
} else if(num == 2) {
	variable = 0.3;
} else if(num == 3) {
	variable = 0.4;
} else if(num == 4) {
	variable = 0.5;
} else if(num == 5) {
	variable = 0.6;
} else if(num == 6) {
	variable = 0.7;
} else if(num == 7) {
	variable = 0.8;
}

// 限界利益率
double marginalProfit = (sales - (sales * variable)) / (double)sales;
// 損益分岐点売上高
int breakEvenPoint = (int)(fix / (1 - variable));
// 損益分岐点比率
double breakEvenPointRate = (double)(Math.round(breakEvenPoint / (double)sales * 100)) / 100;
// 安全余裕率
double safetyMarginRatio = (double)(Math.round((sales - breakEvenPoint) / (double)sales * 100)) / 100; 

%>

<html>
	<head>
		<title>利益計算</title>
	</head>

	<body>
		<div>
			<table style="margin-left: 4vw; margin-top: 3vw; border-spacing: 1vw; text-align: left;">
				<tr>
					<th>単位</th>
					<td>億円</td>
				<tr>
					<th>売上高</th>
					<td><%= sales %></td>
				</tr>
				<tr>
					<th>変動費</th>
					<td><%= (int)(sales * variable) %></td>
				</tr>
				<tr>
					<th>固定費</th>
					<td><%= fix %></td>
				</tr>
				<tr>
					<th>営業利益</th>
					<td><%= (int)(sales - (sales * variable + fix)) %></td>
				<tr>
			</table>
			
			<form onsubmit="check(); return false">
				<table style="margin-left: 4vw; border-spacing: 1vw; text-align: left;">
					<tr>
						<th>変動費率</th>
						<td><input type="text" id="variableRate"></td>
					</tr>
					<tr>
						<th>限界利益率</th>
						<td><input type="text" id="marginalProfit"></td>
					</tr>
					<tr>
						<th>損益分岐点売上高</th>
						<td><input type="text" id="breakEvenPoint"></td>
					</tr>
					<tr>
						<th>損益分岐点比率</th>
						<td><input type="text" id="breakEvenPointRate"></td>
					</tr>
					<tr>
						<th>安全余裕率</th>
						<td><input type="text" id="safetyMarginRatio"></td>
					</tr>
				</table>
				<input type="submit" value="答え合わせ" style="margin-left: 13vw; margin-top: 2vw;">
			</form>
			<p style="margin-left: 3vw; margin-top: 4vw;">【損益分岐点比率】<br>
			実際の売上高に対する損益分岐点の割合を示し、この値が低いほど収益性が高く、かつ売上減少に耐える力が強い</p>
			<p style="margin-left: 3vw;">【安全余裕率】<br>
			実際の売上高と損益分岐点の差がどれくらいあるかを示し、この値が高いほど経営に余裕があることを意味する</p>
		</div>
		
		<script type="text/javascript">

		function check() {
			var myVariableRate = document.getElementById('variableRate').value;
			var myMarginalProfit = document.getElementById('marginalProfit').value;
			var myBreakEvenPoint = document.getElementById('breakEvenPoint').value;
			var myBreakEvenPointRate = document.getElementById('breakEvenPointRate').value;
			var mySafetyMarginRatio = document.getElementById('safetyMarginRatio').value;

			var ans1;
			var ans2;
			var ans3;
			var ans4;
			var ans5;

			// 変動費率答え合わせ
			if(myVariableRate == <%= variable %>) {
				ans1 = "正解⭕️( 変動費 ÷ 売上高 )" + '\n' + "答え：" + <%= variable %>;
			} else {
				ans1 = "不正解❌( 変動費 ÷ 売上高 )" + '\n' + "答え：" + <%= variable %>;
			}

			// 限界利益率答え合わせ
			if(myMarginalProfit == <%= marginalProfit %>) {
				ans2 = "正解⭕( (売上高 - 変動費) ÷ 売上高 )" + '\n' + "答え：" + <%= marginalProfit %>;
			} else {
				ans2 = "不正解❌( (売上高 - 変動費) ÷ 売上高 )" + '\n' + "答え：" + <%= marginalProfit %>;
			}

			// 損益分岐点答え合わせ
			if(myBreakEvenPoint == <%= breakEvenPoint %>) {
				ans3 = "正解⭕( 固定費 ÷ (1 - 変動費率) )" + '\n' + "答え：" + <%= breakEvenPoint %>;
			} else {
				ans3 = "不正解❌( 固定費 ÷ (1 - 変動費率) )" + '\n' + "答え：" + <%= breakEvenPoint %>;
			}

			// 損益分岐点比率答え合わせ
			if(myBreakEvenPointRate == <%= breakEvenPointRate %>) {
				ans4 = "正解⭕( 損益分岐点売上高 ÷ 売上高 )" + '\n' + "答え：" + <%= breakEvenPointRate %>;
			} else {
				ans4 = "不正解❌( 損益分岐点売上高 ÷ 売上高 )" + '\n' + "答え：" + <%= breakEvenPointRate %>;
			}

			// 安全余裕率答え合わせ
			if(mySafetyMarginRatio == <%= safetyMarginRatio %>) {
				ans5 = "正解⭕( (売上高 - 損益分岐点売上高) ÷ 売上高 )" + '\n' + "答え：" + <%= safetyMarginRatio %>;
			} else {
				ans5 = "不正解❌( (売上高 - 損益分岐点売上高) ÷ 売上高 )" + '\n' + "答え：" + <%= safetyMarginRatio %>;
			}

			alert(ans1 + '\n' + ans2 + '\n' + ans3 + '\n' + ans4 + '\n' + ans5);
		}

		</script>
	</body>
</html>