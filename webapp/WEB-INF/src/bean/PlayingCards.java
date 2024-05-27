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
	
	// コンストラクタ(引数なし)
	public PlayingCards() {
		num = -1;
		pattern = null;
		select = 0;
		pair = false;
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
	
	
}
