package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.PlayingCards;

public class ConcentrationServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		try {
			String game = request.getParameter("game");

			// ゲーム生成時
			if (game.equals("new")) {
				// セッションを破棄する
				session.setAttribute("selectCards", null);
				// トランプをArrayListに格納する
				ArrayList<PlayingCards> playingCards = new ArrayList<>();
				for (int i = 0; i <= 3; i++) {
					String pattern;
					if (i == 0) {
						pattern = "spade";
					} else if (i == 1) {
						pattern = "club";
					} else if (i == 2) {
						pattern = "heart";
					} else {
						pattern = "diamond";
					}
					for (int j = 1; j <= 13; j++) {
						PlayingCards playingCard = new PlayingCards();
						playingCard.setPattern(pattern);
						playingCard.setNum(j);
						playingCards.add(playingCard);
					}
				}
				// トランプの順番をシャッフル
				Collections.shuffle(playingCards);

				// ペアとターン数も記録
				int pair = 0;
				int turn = 0;
				// セッションスコープに登録
				session.setAttribute("pair", pair);
				session.setAttribute("turn", turn);
				session.setAttribute("playingCards", playingCards);

			} else if (game.equals("select")) {

				// 揃えたペアの数とターン数
				int pair = ((Integer)(session.getAttribute("pair"))).intValue();
				int turn = ((Integer)(session.getAttribute("turn"))).intValue();

				// めくったカードの数字を受け取る
				int num = Integer.parseInt(request.getParameter("num"));
				// セッションのカードリストを受け取りめくったカードのselect値をめくった(1)に変更する
				ArrayList<PlayingCards> playingCards = (ArrayList<PlayingCards>) session.getAttribute("playingCards");
				PlayingCards selectCard = playingCards.get(num);
				selectCard.setSelect(1);

				// すでにめくられたカード(最大2)を取得する
				// 2枚なら裏に戻す(ここでやらないと揃わなかったときに2枚目が見れずに進んでしまう)
				// 1枚なら今回のカードと数字が同じか判定する
				ArrayList<PlayingCards> selectCards = (ArrayList<PlayingCards>) session.getAttribute("selectCards");
				if (selectCards == null) {
					// まだめくられたカードがない場合
					// 今回めくったカードを追加する
					selectCards = new ArrayList<PlayingCards>();
					selectCards.add(selectCard);

				} else {
					// 2枚格納していたらめくり値を0にして
					// 今回のを1枚目として追加する
					if (selectCards.size() == 2) {
						selectCards.get(0).setSelect(0);
						selectCards.get(1).setSelect(0);
						selectCards.clear();
					} else {
						// すでに1枚めくられている場合
						PlayingCards firstCard = selectCards.get(0);
						turn++;
						// めくったカードと同じ数字か判定する
						if (firstCard.getNum() == selectCard.getNum()) {
							firstCard.setPair(true);
							selectCard.setPair(true);
							pair++;
						}					
					}
					selectCards.add(selectCard);
				}

				// セッションスコープに登録
				session.setAttribute("pair", pair);
				session.setAttribute("turn", turn);
				session.setAttribute("selectCards", selectCards);
				session.setAttribute("playingCards", playingCards);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			request.getRequestDispatcher("/view/concentration.jsp").forward(request, response);
		}
	}

}
