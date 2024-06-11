package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.SugorokuItem;
import bean.SugorokuSpace;
import bean.SugorokuUser;

public class SugorokuServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		SugorokuUser user = null;
		SugorokuUser cp = null;
		SugorokuUser player = null;
		SugorokuUser opponent = null;

		String action = null;
		String text = null;

		try {
			action = request.getParameter("action");

			// ゲーム開始時
			if (action.equals("make")) {
				// 自身と対戦相手(CP)のユーザー作成
				user = new SugorokuUser();
				cp = new SugorokuUser();

				// 名前設定
				cp.setName("CP");
				user.setName(request.getParameter("userName"));

				// ユーザーのターンからスタート
				user.setTurn(true);

				// マップ作成
				ArrayList<SugorokuSpace> sugorokuSpace = new ArrayList<>();
				// スタート設定
				SugorokuSpace ss = new SugorokuSpace();
				ss.setEffect(0);
				ss.setText("START");
				sugorokuSpace.add(ss);
				// 中身の設定(64マス)
				for (int i = 0; i < 61; i++) {
					
					// staticメソッドでマスの内容を設定
					ss = SugorokuSpace.randomEffect();
					// 1マス目で4マス戻るみたいな場合
					if (i < 4 && ss.getEffect() != 0 && i < ss.getEffect() && ss.getEffect() <= 4) {
						ss.setText(i + 1 + "マス戻る");
						ss.setEffect(i + 1);
					}

					sugorokuSpace.add(ss);

				}
				// ゴールから3マス手前はすべて10マス戻るにする
				for (int i = 0; i < 3; i++) {
					ss = new SugorokuSpace();
					ss.setText("10マス戻る");
					ss.setEffect(11);

					sugorokuSpace.add(ss);
				}

				// ゴール設定
				ss = new SugorokuSpace();
				ss.setEffect(0);
				ss.setText("GOAL");
				sugorokuSpace.add(ss);

				// セッションに登録
				session.setAttribute("sugorokuSpace", sugorokuSpace);

			} else if (action.equals("game")) {
				// アイテムで2つサイコロ振っていた場合の初期化
				session.setAttribute("dice2", null);
				// ユーザーとCPの情報を受け取る
				user = (SugorokuUser) session.getAttribute("user");
				cp = (SugorokuUser) session.getAttribute("cp");
				ArrayList<SugorokuSpace> sugorokuSpace = (ArrayList<SugorokuSpace>) session.getAttribute("sugorokuSpace");

				// 1つのソースコードで済むようにプレイヤーとCPを変数に入れる
				if(user.isTurn() == true) {
					player = user;
					opponent = cp;
					
				} else {
					player = cp;
					opponent = user;
					
				}
				
				// サイコロをふる
				int dice = (int) (Math.random() * 6) + 1;
				// 次の目が6になるアイテムを使っていた場合
				if(player.getSpecial() == 2) {
					dice = 6;
				}
				// 効果マス時にも残すように表示するためにセッションへ
				session.setAttribute("dice", dice);
				
				// alertのtext準備
				text = player.getName() + "はサイコロを振り" + dice + "が出た";
				
				// サイコロ2つの場合
				int dice2 = 0;
				if(player.getSpecial() == 1) {
					dice2 = (int) (Math.random() * 6) + 1;
					session.setAttribute("dice2", dice2);
					text = player.getName() + "はサイコロを2つ振り" + (dice + dice2) + "が出た";
				}
				
				// マスの移動
				player.setSpace(player.getSpace() + dice + dice2);
				if(player.getSpace() >= 65) {
					player.setSpace(65);
					// 1番にゴールしていれば勝者
					if(opponent.isResult() == false) {
						player.setResult(true);
					}
				}
				// 効果マスなら相手のターンになる前に効果が起きるようにする
				if (sugorokuSpace.get(player.getSpace()).getEffect() != 0) {
					action = "effect";

				} else {
					// ターンの変更とアイテム使用数の初期化
					player.setUsed(false);
					player.setSpecial(0);
					player.setTurn(false);
					opponent.setTurn(true);
				}

			} else if (action.equals("effect")) {
				// ユーザーとCPの情報を受け取る
				user = (SugorokuUser) session.getAttribute("user");
				cp = (SugorokuUser) session.getAttribute("cp");
				ArrayList<SugorokuSpace> sugorokuSpace = (ArrayList<SugorokuSpace>) session.getAttribute("sugorokuSpace");
				
				// 1つのソースコードで対応できるように変数にいれる
				// どちらのターンかで識別
				if(user.isTurn() == true) {
					player = user;
					opponent = cp;
					
				} else {
					player = cp;
					opponent = user;
				}
				
				// 4マス進む→4マス戻るでループしないように2回までの制限を設ける
				Object obj = session.getAttribute("count");
				int count = 0;
				if(obj != null) {
					count = (Integer)session.getAttribute("count");
				}
				
				text = player.getName() + "は「" + sugorokuSpace.get(player.getSpace()).getText() + "」を踏んだ";
				
				// ユーザーに対する効果
				int effect = sugorokuSpace.get(player.getSpace()).getEffect();
				player.effectSpace(player, effect);
				count++;
				
				// アイテム取得時の場合はなにを取得したかもtextに追加
				if(effect == 9) {
					text += "、「" + player.getItemList().getLast().getText() + "」を手に入れた！";
				}
				
				// 移動先が効果マスかどうか確認
				effect = sugorokuSpace.get(player.getSpace()).getEffect();
				// 効果マスならreturnでもう1周
				if(effect == 9) {
					if(player.isGot() == false && count < 2) {
						player.setGot(true);
						return;
					}
					
				} else if(effect != 0 && count < 2) {
					session.setAttribute("count", count);
					return;
				}
				
				session.setAttribute("count", 0);
				// アイテム連続使用のリセット
				player.setUsed(false);
				// アイテムマス通過フラグのリセット
				player.setGot(false);
				// 状態異常のリセット
				player.setSpecial(0);
				// ターンの変更
				player.setTurn(false);
				opponent.setTurn(true);
				
				action = "game";
				
			} else if(action.equals("item")) {
			
				user = (SugorokuUser) session.getAttribute("user");
				cp = (SugorokuUser) session.getAttribute("cp");

				// userとcpでアイテムのif文2つに分けるよりユーザーを変数にしてしまう
				if(user.isTurn() == true) {
					player = user;
					opponent = cp;
					
				} else {
					player = cp;
					opponent = user;
				}
				
				// 使用アイテムの番号取得
				int number = Integer.parseInt(request.getParameter("playerItem"));
				int itemNumber = player.getItemList().get(number).getNumber();
				
				text = player.getName() + "は「" + player.getItemList().get(number).getText() + "」を使った";
				
				// アイテムの効果
				int random = 0;
				switch(itemNumber) {
					case 1:
						// サイコロ2つ
						player.setSpecial(1);
						break;
					case 2:
						// 次の目が6になる
						player.setSpecial(2);
						break;
					case 3: 
						// 位置交換
						int tmp = player.getSpace();
						player.setSpace(opponent.getSpace());
						opponent.setSpace(tmp);
						break;
					case 4:
						// 相手のアイテムをランダムに1つ破壊
						ArrayList<SugorokuItem> opponentItemList = opponent.getItemList();
						random = (int)(Math.random() * opponentItemList.size());
						text += "、「" + opponentItemList.get(random).getText() + "」が破壊された";
						opponentItemList.remove(random);
						opponent.setItemList(opponentItemList);
						break;
					case 5:
						// 相手を6マス戻す
						opponent.setSpace(opponent.getSpace() - 6);
						if(opponent.getSpace() < 0) {
							opponent.setSpace(0);
						}
						break;
					case 6:
						// どちらかが10マス戻る
						random = (int)(Math.random() * 2);
						if(random == 0) {
							player.setSpace(player.getSpace() - 10);
							if(player.getSpace() < 0) {
								player.setSpace(0);
							}
						
						} else {
							opponent.setSpace(opponent.getSpace() - 10);
							if(opponent.getSpace() < 0) {
								opponent.setSpace(0);
							}
						}
						break;
					case 7:
						// 相手が1回休み
						opponent.setSpecial(3);
						break;						
				}
				
				// 使ったアイテムの削除
				player.getItemList().remove(number);
				// 連続でアイテムを使えないようにフラグを変更
				player.setUsed(true);
				
								
			} else if(action.equals("skip")) {
				// スキップ時
				user = (SugorokuUser) session.getAttribute("user");
				cp = (SugorokuUser) session.getAttribute("cp");
				
				// userとcpでアイテムのif文2つに分けるよりユーザーを変数にしてしまう
				if(user.isTurn() == true) {
					player = user;
					opponent = cp;
					
				} else {
					player = cp;
					opponent = user;
					
				}
				
				text = player.getName() + "のターンはスキップされた";
				
				// スキップ状態をリセットしてターンを変更
				player.setSpecial(0);
				player.setTurn(false);
				opponent.setTurn(true);
			}
			

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (action.equals("make")) {
				// セッションに登録
				session.setAttribute("user", user);
				session.setAttribute("cp", cp);
				request.getRequestDispatcher("/view/sugoroku.jsp?action=make").forward(request, response);

			} else {
				// alert文の用意
				request.setAttribute("text", text);
				// セッションに登録
				if(player.getName().equals("CP")) {
					session.setAttribute("cp", player);
					session.setAttribute("user", opponent);
				} else {
					session.setAttribute("user", player);
					session.setAttribute("cp", opponent);
				}
				
				// 分岐先を決める	
				if(action.equals("effect")) {
					request.getRequestDispatcher("/view/sugoroku.jsp?action=effect").forward(request, response);
				
				} else {
					request.getRequestDispatcher("/view/sugoroku.jsp?action=game").forward(request, response);
	
				}
			}
		}
	}
}
