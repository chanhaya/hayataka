package bean;

public class Maze {
	
	// マスの数字
	private int num;
	// 前のマス
	private int prev;
	// 通ったかのフラグ
	private boolean passing;
	
	// コンストラクタ(引数なし)
	public Maze() {
		num = 2;
		prev = -1;
		passing = false;
	}

	// アクセサメソッド
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getPrev() {
		return prev;
	}

	public void setPrev(int prev) {
		this.prev = prev;
	}

	public boolean isPassing() {
		return passing;
	}

	public void setPassing(boolean passing) {
		this.passing = passing;
	}
}
