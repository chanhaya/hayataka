package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ConcentrationUser;
import bean.PlayingCards;

public class VsConcentrationServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		ConcentrationUser user = null;
		ConcentrationUser cp = null;
		ConcentrationUser player = null;
		ConcentrationUser opponent = null;
		
		ArrayList<PlayingCards> memory = null;

		String game = null;

		try {
			game = request.getParameter("game");

			// ゲーム生成時
			if (game.equals("new") || game.equals("versus")) {
				// セッションを破棄する
				session.setAttribute("selectCards", null);
				// 配置場所として0〜51の数字をリストを作る
				ArrayList<Integer> position = new ArrayList<>();
				for (int i = 0; i < 52; i++) {
					position.add(i);
				}
				// 数字のリストの順番をシャッフル
				Collections.shuffle(position);

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
						// シャッフルした位置をセット
						playingCard.setPosition(position.get((i * 13) + j - 1));
						playingCards.add(playingCard);
					}
				}
				// positionの値でソート
				playingCards.sort(Comparator.comparing(PlayingCards::getPosition, Comparator.naturalOrder()));
				ArrayList<PlayingCards> copyCards = (ArrayList<PlayingCards>) playingCards.clone();

				// プレイヤー生成
				user = new ConcentrationUser();
				cp = new ConcentrationUser();
				if (game.equals("versus")) {
					// 対人戦
					user.setName(request.getParameter("player1"));
					cp.setName(request.getParameter("player2"));
					// 順番は抽選
					int random = (int) (Math.random() * 2);
					if (random == 0) {
						user.setTurn(true);
					} else {
						cp.setTurn(true);
					}
				} else {
					// cp戦
					user.setName("ユーザー");
					cp.setName("CP");
					// ユーザーから
					user.setTurn(true);
				}
				// 手番を判断するためのフラグ
				user.setOrder(1);
				cp.setOrder(2);

				// セッションスコープに登録
				session.setAttribute("playingCards", playingCards);
				session.setAttribute("user", user);
				session.setAttribute("cp", cp);
				// CPが残りのカードから選べるように
				session.setAttribute("copyCards", copyCards);

			} else {
				// CP戦
				// プレイヤー情報を受け取り変数に入れる
				user = (ConcentrationUser) session.getAttribute("user");
				cp = (ConcentrationUser) session.getAttribute("cp");
				if (user.isTurn() == true) {
					player = user;
					opponent = cp;
				} else {
					player = cp;
					opponent = user;
				}

				// めくったカードの数字を受け取る
				int num = Integer.parseInt(request.getParameter("num"));
				// セッションのカードリストを受け取りめくったカードのselect値をめくったに変更する
				ArrayList<PlayingCards> playingCards = (ArrayList<PlayingCards>) session.getAttribute("playingCards");
				PlayingCards selectCard = playingCards.get(num);
				selectCard.setSelect(1);

				// CP戦時のみ
				// CPの記憶領域に何番目に何のカードがあったか記憶させる
				if(game.equals("select")) {
					memory = cp.getMemory();
					if (!memory.contains(selectCard)) {
						if (memory.size() >= 6) {
							// 6つ以上は記憶できない
							memory.removeFirst();
						}
						// 記憶させる
						memory.add(selectCard);
					}
				}
				
				// ペアだった場合残りから削除する
				ArrayList<PlayingCards> copyCards = (ArrayList<PlayingCards>) session.getAttribute("copyCards");
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
					// 今回のを()外で1枚目として追加する
					if (selectCards.size() == 2) {
						selectCards.get(0).setSelect(0);
						selectCards.get(1).setSelect(0);
						selectCards.clear();
					} else {
						// すでに1枚めくられている場合
						PlayingCards firstCard = selectCards.get(0);
						// めくったカードと同じ数字か判定する
						if (firstCard.getNum() == selectCard.getNum()) {
							firstCard.setPair(true);
							selectCard.setPair(true);
							player.setPair(player.getPair() + 1);
							// 残りカードから削除する
							copyCards.remove(firstCard);
							copyCards.remove(selectCard);
							// CP戦時のみ
							// ペアになったカードをcpが記憶に持っていた場合は削除する
							if(game.equals("select")) {
								if (memory.contains(firstCard)) {
									memory.remove(firstCard);
								}
								if (memory.contains(selectCard)) {
									memory.remove(selectCard);
								}							
								// 1枚目ランダム2枚目記憶の場合にsecondCardをリセットする
								if (cp.getSecondCard() != null) {
									cp.setSecondCard(null);
								}
							}
							
						} else {
							// ペアが揃わなかった場合はターンが移る
							player.setTurn(false);
							opponent.setTurn(true);
						}
					}
					selectCards.add(selectCard);
				}

				// CP戦時のみ
				// cpがランダムにめくる番号を決める
				if(game.equals("select")) {
					if (cp.isTurn() == true) {
						int randomCard = -1;
						// まだ裏面カードが残っている場合
						if (copyCards.size() != 0) {
							// CPの記憶内にめくられていない同じ数字があればそれにする
							cpLabel: for (int i = 0; i < memory.size() - 1; i++) {
								PlayingCards memoryCard = cp.getMemory().get(i);
								for (int j = i + 1; j < memory.size(); j++) {
									// CPターン開始時に記憶領域ないでペアが完成していた場合の2枚目
									if (cp.getSecondCard() != null) {
										randomCard = cp.getSecondCard().getPosition();
										cp.setSecondCard(null);
										break cpLabel;

									} else if (memoryCard.getSelect() == 0) {
										// めくられていない(自分が1枚目にめくっていない)なら処理
										if (memoryCard.getNum() == memory.get(j).getNum()) {
											randomCard = memoryCard.getPosition();
											// 2枚目にめくるカードをセット
											cp.setSecondCard(memory.get(j));
											break cpLabel;
										}
									}
								}
							}
							// 記憶内でペアができずランダムにめくる場合
							if (randomCard == -1) {
								while (true) {
									// position(残りカード)の配列内のランダムな順番を取得
									int positionNum = new Random().nextInt(copyCards.size());
									// その該当順番にあるカードの持つ位置を取得
									randomCard = copyCards.get(positionNum).getPosition();
									// この番号がすでにめくられているか
									PlayingCards random = playingCards.get(randomCard);
									if (random.getSelect() == 0) {
										break;
									}
								}
							}
							// リクエストスコープに登録
							request.setAttribute("randomCard", randomCard);
						}
					}
				}
				
				// セッションスコープに登録
				session.setAttribute("selectCards", selectCards);
				session.setAttribute("playingCards", playingCards);
				session.setAttribute("copyCards", copyCards);
				
				if (player.getOrder() == 1) {
					session.setAttribute("user", player);
					session.setAttribute("cp", opponent);
					
				} else {
					session.setAttribute("cp", player);
					session.setAttribute("user", opponent);
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (game.equals("vs") || game.equals("versus")) {
				request.getRequestDispatcher("/view/vsConcentration.jsp?game=vs").forward(request, response);
			} else {
				request.getRequestDispatcher("/view/vsConcentration.jsp?game=new").forward(request, response);
			}
		}
	}
}
