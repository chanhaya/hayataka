package servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AmidakujiServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String action = null;
		
		try {
			// 2次元配列にして隣(右側に線が伸びるかを格納する)
			// 最初のinput で縦線の数を決める
			// 正解地点をランダムに決める
			// 横列の数を決める(選択肢でもいいかも)
			// どの縦線も1本は横線が入るようにしたい
			
			action = request.getParameter("action");
			
			// 縦線作るだけ
			if(action.equals("vertical")) {
				// 縦線のパラメータを受け取る
				int line = Integer.parseInt(request.getParameter("line"));
				// リクエストスコープに登録
				session.setAttribute("line", line);
				// 正解を作る
				String[] amida = new String[line];
				amida[0] = "treasure";
				for(int i = 1; i < amida.length; i++) {
					amida[i] = "mimic";
				}
				List<String> amidakuji = Arrays.asList(amida);
				Collections.shuffle(amidakuji);
				session.setAttribute("amidakuji", amidakuji);
				
			} else if(action.equals("beside")) {
				// 縦線をセッションから受け取る
				int line = ((Integer)(session.getAttribute("line"))).intValue();
				// 横線のパラメータを受け取る
				int besideLine = Integer.parseInt(request.getParameter("besideLine"));
				// 数字が制限を超えていたらやりなおしさせる
				// 制限の設定
				int limit = 0;
				if(line <= 4) {
					limit = 24;
				} else if(line <= 7) {
					limit = 48;
				} else {
					limit = 72;
				}
				// 制限との照らし合わせ
				if(besideLine > limit) {
					String alert = "制限内で数字を入力してください";
					action = "vertical";
					request.setAttribute("alert", alert);
					return;
				}
				// 横線を引く箇所を2次元配列として
				int[][] amida = new int[26][line];
				// すべて0にする(横線なし)
				for(int i = 0; i < 25; i++) {
					for(int j = 0; j < line; j++) {
						amida[i][j] = 0;
					}
				}
				
				// 26段目には宝(2)かミミック(3)かを入れる
				List<String> amidakuji = (List<String>)(session.getAttribute("amidakuji"));
				for(int i = 0; i < line; i++) {
					if(amidakuji.get(i).equals("treasure")) {
						// 宝なら「2」
						amida[25][i] = 2;
					
					} else {
						// ミミックなら「3」
						amida[25][i] = 3;
					}
				}
								
				// まずは縦列すべてに横線が1つは入るようにする
				// ※ amida[段][横]
				// besideLine数までのcount
				int count = 0;
				for(int i = 0; i < line - 1; i++) {
					// 1直線は避けたい(2本横線が連なる)
					while(true) {
						int random = (int)(Math.random() * 24) + 1;
						// 隣に横線がなければループを抜ける
						if(i == 0) {
							amida[random][i] = 1;
							count++;
							break;
							
						} else if(amida[random][i - 1] == 0) {
							amida[random][i] = 1;
							count++;
							break;
						}						
					}					
				}
				
				// 指定された数(besideLine分の横線を引く)
				while(count < besideLine) {
					int verticalRandom = (int)(Math.random() * 24) + 1;
					int besideRandom = (int)(Math.random() * (line - 1));
					
					if(besideRandom == 0) {
						// 下の条件を上のif文に&&でくっつけてしまうと
						// 前半がtrueでも後半がfalseだと次のif文に移り
						// besideRandom - 1 で例外になるとおもわれるため分離
						// 右隣と左隣に横線がすでにないか確認
						if(amida[verticalRandom][besideRandom] == 0 && amida[verticalRandom][besideRandom + 1] == 0) {
							amida[verticalRandom][besideRandom] = 1;
							count++;
						}
						
					} else if(besideRandom == line - 1) {
						// 右端列の条件
						// lineまでだと存在しない線に向かって横線が伸びてしまう
						if(amida[verticalRandom][besideRandom] == 0 && amida[verticalRandom][besideRandom - 1] == 0) {
							amida[verticalRandom][besideRandom] = 1;
							count++;
						}
						
					} else if(amida[verticalRandom][besideRandom] == 0 && amida[verticalRandom][besideRandom - 1] == 0 && amida[verticalRandom][besideRandom + 1] == 0) {
						amida[verticalRandom][besideRandom] = 1;
						count++;
					}
				}
				
				// スタート地点と終着点を紐づける
				Map<Integer, Integer> map = new HashMap<>();
				for(int i = 0; i < line; i++) {
					int current = i;
					for(int j = 1; j < 26; j++) {
						if(current == 0) {
							// 左端の場合は右に行くことだけ考える
							if(amida[j][current] == 1) {
								// 右の列に移動
								current++;
							}
							
						} else {
							// 右端には横線が代入されないように
							// 72〜121行目でしている
							// 右に横線がある場合と左に横線がある場合
							if(amida[j][current] == 1) {
								current++;
								
							} else if(amida[j][current - 1] == 1) {
								current--;
							}
						}
						if(j == 25) {
							// 終着点の答えを記録
							int result = amida[j][current];
							map.put(i, result);
						}
					}
				}
				
				// とりあえずセッションに
				session.setAttribute("map", map);
				session.setAttribute("amida", amida);
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			if(action.equals("vertical")) {
				// 縦線作成時
				request.getRequestDispatcher("/view/amidakuji.jsp?action=vertical").forward(request, response);
				
			} else if(action.equals("beside")) {
				// 横線作成時
				request.getRequestDispatcher("/view/amidakuji.jsp?action=beside").forward(request, response);
			
			} else if(action.equals("mufu")) {
				request.getRequestDispatcher("/view/amidakuji.jsp?action=mufu").forward(request, response);
				
			} else {
				request.getRequestDispatcher("/view/amidakuji.jsp?action=death").forward(request, response);
			}
			
		}
	}

}
