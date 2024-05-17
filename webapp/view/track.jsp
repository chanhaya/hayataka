<%@page contentType="text/html; charset=UTF-8" %>

<%
// 問1
int track1 = (int)(Math.random() * 10 + 1) * 1000;
int a1 = ((int)(Math.random() * 22)) * 10 + 80;
int b1 = ((int)(Math.random() * 22)) * 10 + 80;
//int track1 = 1200;
//int a1 = 60;
//int b1 = 120;
// 簡単になってしまうのでa1とb1が同じならやりなおし
// aの方が速いことにする
if(a1 == b1) {
	while(a1 == b1) {
		a1 = ((int)(Math.random() * 22)) * 10 + 80;
		b1 = ((int)(Math.random() * 22)) * 10 + 80;
	}
}
if(a1 > b1) {
	int tmp = a1;
	a1 = b1;
	b1 = tmp;
}
// 秒速
double sa1 = (double)(Math.round(a1 / 60.0 * 100)) / 100;
double sb1 = (double)(Math.round(b1 / 60.0 * 100)) / 100;
int ans1 = track1 / (a1 + b1); // 分
double dsans1 = (double)(Math.ceil(track1 / (sa1 + sb1)));// 秒
int sans1 = (int)dsans1 % 60;

// 問2
int track2 = (int)(Math.random() * 10 + 1) * 1000;
//int track2 = 1500;
int a2 = (int)(Math.random() * 15 + 1);
int b2 = (int)(Math.random() * 15 + 16);

//int a2 = 5;
//int b2 = 30;
int ansa = (track2 * (a2 + b2)) / (2 * a2 * b2);
int ansb = (b2 * ansa - track2) / b2;

// 問3
int track3 = (int)(Math.random() * 5 + 1) * 1000;
int a3 = ((int)(Math.random() * 24)) * 5 + 80;
int b3 = ((int)(Math.random() * 24)) * 5 + 80;

if(a3 == b3) {
	while(a3 == b3) {
		a3 = ((int)(Math.random() * 24)) * 5 + 80;
		b3 = ((int)(Math.random() * 24)) * 5 + 80;
	}
}

if(a3 < b3) {
	int tmp = a3;
	a3 = b3;
	b3 = tmp;
}
int time1 = track3 / (a3 + b3);
int time2 = track3 / (a3 - b3);

%>

<html>
	<head>
		<title>トラック</title>
		<style>
			.hidden {
			display: none;
			}
			.appear {
				display: block;
			}
		</style>
	</head>

	<body>
		<div>
			<p>問1.<br>
			1周<%= track1 %>mのトラックを同じ地点から同時にスタートして<br>
			Aさんは<%= a1 %>m/分、Bさんは<%= b1 %>m/分で2人が反対方向に走ったところ何分後に何m地点で出会うか</p>
			<p>Aさんは<%= sa1 %>m/秒</p>
			<p>Bさんは<%= sb1 %>m/秒</p>
			<div class="hidden" id="hidden1">
				<p><%= ans1 %>分<%= sans1 %>秒後</p>
				<p><%= (int)(a1 * ans1 + sa1 * sans1) %>m地点</p>
			</div>

			<p>問2.(時間を決めて問題を作る)<br>
			1周<%= track2 %>mのトラックを同じ地点から同時にスタートして<br>
			AさんとBさんはそれぞれ一定の速さで走ることにした。<br>
			2人が反対方向に走ったところ、<%= a2 %>分後に初めて出会った。<br>
			2人が同じ方向に走ったところ、<%= b2 %>分後にAさんがBさんに追いついた。<br>
			AさんとBさんの走る速さをそれぞれ求めよ</p>
			<div class="hidden" id="hidden2">
				<p>Aさんの速さ<%= ansa %>m/分</p>
				<p>Bさんの速さ<%= ansb %>m/分</p>
			</div>
			
			<p>問3.(速度を決めて問題を作る)<br>
			1周<%= track3 %>mのトラックを同じ地点から同時にスタートして<br>
			AさんとBさんはそれぞれ一定の速さで走ることにした。<br>
			2人が反対方向に走ったところ、<%= time1 %>分後に初めて出会った。<br>
			2人が同じ方向に走ったところ、<%= time2 %>分後にAさんがBさんに追いついた。<br>
			AさんとBさんの走る速さをそれぞれ求めよ</p>
			<div class="hidden" id="hidden3">
				<p>Aさんの速さ<%= a3 %>m/分</p>
				<p>Bさんの速さ<%= b3 %>m/分</p>
			</div>
			
			<button onClick="appear();">答え表示</button>
			
		</div>
		
		<script type="text/javascript">

			function appear() {
				var answer1 = document.getElementById('hidden1');
				var answer2 = document.getElementById('hidden2');
				var answer3 = document.getElementById('hidden3');
				answer1.classList.toggle('appear');
				answer2.classList.toggle('appear');
				answer3.classList.toggle('appear');
					
			}
		
		</script>
	
	</body>