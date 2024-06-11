package bean;

import java.util.ArrayList;

public class SugorokuUser {

	// 名前
	private String name;
	// 自分のいるマス
	private int space;
	// 自分のターンかどうか
	private boolean turn;
	// 勝敗
	private boolean result;
	// 特殊状態(1.サイコロ2つ、2.次の目が6、3.1回休み)
	private int special;
	// アイテム使ったかのフラグ
	private boolean used;
	// アイテムマスを1ターンのうちにすでに踏んでるかのフラグ
	private boolean got;
	// アイテム
	/*
	* 1. サイコロ2つ
	* 2. 6マス進む
	* 3. 位置交換
	* 4. 相手を6マス戻す
	* 5. 相手を5マス戻す
	* 6. どちらかが10マス戻る
	* 7. 相手が1回休み
	*/
	private ArrayList<SugorokuItem> itemList;

	// 引数なしコンストラクタ
	public SugorokuUser() {
		this.name = null;
		this.space = 0;
		this.turn = false;
		this.itemList = null;
		this.special = 0;
		this.used = false;
		this.got = false;
	}

	// アクセサメソッド
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSpace() {
		return space;
	}

	public void setSpace(int space) {
		this.space = space;
	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public int getSpecial() {
		return special;
	}

	public void setSpecial(int special) {
		this.special = special;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}
	
	public boolean isGot() {
		return got;
	}

	public void setGot(boolean got) {
		this.got = got;
	}

	public ArrayList<SugorokuItem> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<SugorokuItem> itemList) {
		this.itemList = itemList;
	}

	// 効果マスを踏んだ際のメソッド
	public SugorokuUser effectSpace(SugorokuUser user, int effect) {
		// 現在地取得
		int space = user.getSpace();

		if (effect == 1) {
			// 1マス戻る
			user.setSpace(space - 1);

		} else if (effect == 2) {
			// 2マス戻る
			user.setSpace(space - 2);

		} else if (effect == 3) {
			// 3マス戻る
			user.setSpace(space - 3);

		} else if (effect == 4) {
			// 4マス戻る
			user.setSpace(space - 4);

		} else if (effect == 5) {
			// 1マス進む
			user.setSpace(space + 1);

		} else if (effect == 6) {
			// 2マス進む
			user.setSpace(space + 2);

		} else if (effect == 7) {
			// 3マス進む
			user.setSpace(space + 3);

		} else if (effect == 8) {
			// 4マス進む
			user.setSpace(space + 4);

		} else if (effect == 10) {
			// スタートに戻る
			user.setSpace(0);

		} else if (effect == 11) {
			// 10マス戻る
			user.setSpace(space - 10);

		} else if (effect == 9) {
			// アイテムインスタンス作成
			SugorokuItem item = new SugorokuItem();
			// 獲得アイテムをランダムに決める
			int number = (int) (Math.random() * 7) + 1;
			item.setNumber(number);
			// アイテムインスタンスをArrayListへ
			ArrayList<SugorokuItem> itemList = user.getItemList();
			if (itemList == null) {
				// １つ目のアイテムならArrayList作成
				itemList = new ArrayList<SugorokuItem>();
			}
			user.setGot(true);
			itemList.add(item);
			user.setItemList(itemList);
		}

		return user;
	}

}
