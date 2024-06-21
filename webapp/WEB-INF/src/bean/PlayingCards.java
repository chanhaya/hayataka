package bean;

public class PlayingCards {
	// トランプの数字
	private int num;
	// トランプの柄：0(スペード)1(クローバー)2(ハート)3(ダイヤ)
	private String pattern;
	// めくられた状態かどうか(0:めくられてない、1:めくられた)
	private int select;
	// 揃っている状態かどうか(Trueならペア済み)
	private boolean pair;
	// 自分の配置
	private int position;
	
	// コンストラクタ(引数なし)
	public PlayingCards() {
		this.num = -1;
		this.pattern = null;
		this.select = 0;
		this.pair = false;
		this.position = -1;
	}
	
	// アクセサメソッド
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public int getSelect() {
		return select;
	}
	public void setSelect(int select) {
		this.select = select;
	}
	public boolean isPair() {
		return pair;
	}
	public void setPair(boolean pair) {
		this.pair = pair;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

}
