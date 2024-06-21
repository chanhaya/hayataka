package bean;

import java.util.ArrayList;

public class ConcentrationUser {

	// プレイヤー名
	private String name;
	// ペア数
	private int pair;
	// 自ターンかどうか
	private boolean turn;
	// カード記憶(CP用)
	private ArrayList<PlayingCards> memory;
	// 2枚目に選ぶカード
	private PlayingCards secondCard;
	// 手番の判断
	private int order;
	
	// コンストラクタ(引数なし)
	public ConcentrationUser() {
		this.name = null;
		this.pair = 0;
		this.turn = false;
		this.memory = new ArrayList<>();
		this.secondCard = null;
		this.order = 0;
	}

	// アクセサメソッド
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPair() {
		return pair;
	}

	public void setPair(int pair) {
		this.pair = pair;
	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	public ArrayList<PlayingCards> getMemory() {
		return memory;
	}

	public void setMemory(ArrayList<PlayingCards> memory) {
		this.memory = memory;
	}

	public PlayingCards getSecondCard() {
		return secondCard;
	}

	public void setSecondCard(PlayingCards secondCard) {
		this.secondCard = secondCard;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
}
