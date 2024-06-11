package bean;

public class SugorokuItem {

	// アイテムの説明文
	private String text;
	// アイテムの番号
	int number;
	
	// 引数なしコンストラクタ
	public SugorokuItem() {
		this.text = null;
		this.number = 0;
	}

	// アクセサメソッド
	public String getText() {
		return text;
	}

	public int getNumber() {
		return number;
	}
	// ナンバーが決まったらテキストも決まる
	public void setNumber(int number) {
		this.number = number;
		
		switch(number) {
			case 1:
				this.text = "サイコロ2つ";
				break;
			case 2:
				this.text = "次の目が6になる";
				break;
			case 3:
				this.text = "位置交換";
				break;
			case 4:
				this.text = "相手のアイテムをランダムに1つ破壊する";
				break;
			case 5:
				this.text = "相手を6マス戻す";
				break;
			case 6:
				this.text = "どちらかが10マス戻る";
				break;
			case 7:
				this.text = "相手が1回休み";
				break;
		}
	}
		
}
