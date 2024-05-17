package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Bingo;

public class BingoServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		try {
			// ビンゴ作成か穴あけか分けるための値
			String motion = request.getParameter("motion");
			// ビンゴ作成時
			if (motion.equals("create")) {
				// ビンゴの縦横の数
				int square = Integer.parseInt(request.getParameter("number"));
				// ビンゴのマスの総数
				int totalSquare = square * square;
				// 3, 5, 7マスによって出る数値の最大値を変更
				int variable;
				if (square == 3) {
					variable = 25;
				} else if (square == 5) {
					variable = 50;
				} else {
					variable = 75;
				}
				// ビンゴ用配列
				ArrayList<Bingo> bingoList = new ArrayList<>();
				// 数字被り判定用配列
				ArrayList<Integer> numberList = new ArrayList<>();
				// ビンゴ作成
				while (bingoList.size() < totalSquare) {
					int num = (int) (Math.random() * variable + 1);
					if (numberList.contains(num) == false) {
						Bingo bingo = new Bingo();
						bingo.setNum(num);
						bingoList.add(bingo);
						numberList.add(num);
					}
				}

				// 真ん中は0にする
				int center = totalSquare / 2;
				bingoList.get(center).setNum(0);
				bingoList.get(center).setHole(true);

				session.setAttribute("square", square);
				session.setAttribute("bingoList", bingoList);
				session.setAttribute("randomNum", null);

			} else {
				int square = ((Integer) (session.getAttribute("square"))).intValue();
				ArrayList<Bingo> bingoList = (ArrayList<Bingo>) session.getAttribute("bingoList");

				ArrayList<Integer> randomNum = (ArrayList<Integer>)session.getAttribute("randomNum");
				if(randomNum == null) {
					randomNum = new ArrayList<>();
				}
				int random;
				// 抽選数字に被りでない数字が出るまでループ
				while (true) {
					if (square == 3) {
						random = (int) (Math.random() * 25 + 1);
					} else if (square == 5) {
						random = (int) (Math.random() * 50 + 1);
					} else {
						random = (int) (Math.random() * 75 + 1);
					}
					if (randomNum.contains(random) == false) {
						randomNum.add(random);
						break;
					}
				}

				// ビンゴマスに該当があったらholeをtrueに
				for (int i = 0; i < bingoList.size(); i++) {
					if (random == bingoList.get(i).getNum()) {
						bingoList.get(i).setHole(true);
						break;
					}
				}
				request.setAttribute("random", random);
				session.setAttribute("randomNum", randomNum);
				session.setAttribute("bingoList", bingoList);
				
				// ビンゴの数を数える
				int count = 0;
				int success = 0;

				// 左斜め上
				for(int i = 0; i < bingoList.size(); i = i + square + 1) {
					Bingo bin = bingoList.get(i);
					if(bin.isHole() == true) {
						count++;
						
						if(count == square) {
							success++;
							count = 0;
						}
						
						continue;
						
					} else {
						count = 0;
						break;
					}
				}
				
				// 右斜め上
				for(int i = square - 1; i <= bingoList.size() - square; i = i + square - 1) {
					Bingo bin = bingoList.get(i);
					if(bin.isHole() == true) {
						count++;
						
						if(count == square) {
							count = 0;
							success++;
						}
						
						continue;
					
					} else {
						count = 0;
						break;
					}
				}
				
				// 上段
				for(int i = 0; i < square; i++) {
					for(int j = i; j < bingoList.size(); j = j + square) {
						Bingo bin = bingoList.get(j);
						if(bin.isHole() == true) {
							count++;
							
							if(count == square) {
								success++;
								count = 0;
							}
							
							continue;
							
						} else {
							count = 0;
							break;
						}
					}
				}
				
				// 左段
				for(int i = 0; i < bingoList.size(); i = i + square) {
					for(int j = i; j < i + square; j++) {
						Bingo bin = bingoList.get(j);
						if(bin.isHole() == true) {
							count++;
							
							if(count == square) {
								success++;
								count = 0;
							}
							
							continue;
						
						} else {
							count = 0;
							break;
						}
					}
				}
				request.setAttribute("success", success);
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			request.getRequestDispatcher("/view/bingo.jsp?game=start").forward(request, response);
		}
	}
}
