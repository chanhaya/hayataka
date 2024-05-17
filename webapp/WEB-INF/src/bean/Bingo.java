package bean;

public class Bingo {
	
	// ビンゴの数字
	private int num;
	// 穴が開いたかどうか
	private boolean hole;

	// コンストラクタ(引数なし)
	public Bingo() {
		num = 0;
		hole = false;
	}

	// アクセサメソッド
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public boolean isHole() {
		return hole;
	}

	public void setHole(boolean hole) {
		this.hole = hole;
	}
	
}
