package bean;

public class SugorokuSpace {

	// 表示する文字
	private String text;
	// 特殊効果(フラグ)
	private int effect;
	
	// 引数なしコンストラクタ
	public SugorokuSpace() {
		this.text = null;
		this.effect = 0;
	}

	// アクセサメソッド
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getEffect() {
		return effect;
	}

	public void setEffect(int effect) {
		this.effect = effect;
	}
	
	// マスの効果決めスタティックメソッド
	public static SugorokuSpace randomEffect() {
		int random = (int)(Math.random() * 100) + 1;
		SugorokuSpace ss = new SugorokuSpace();
		
		if(random <= 40) {
			ss.setText("");
			ss.setEffect(0);
		
		} else if(random <= 45) {
			ss.setText("1マス戻る");
			ss.setEffect(1);
		
		} else if(random <= 50) {
			ss.setText("2マス戻る");
			ss.setEffect(2);
			
		} else if(random <= 55) {
			ss.setText("3マス戻る");
			ss.setEffect(3);
			
		} else if(random <= 60) {
			ss.setText("4マス戻る");
			ss.setEffect(4);
		
		} else if(random <= 65) {
			ss.setText("1マス進む");
			ss.setEffect(5);
			
		} else if(random <= 70) {
			ss.setText("2マス進む");
			ss.setEffect(6);
			
		} else if(random <= 75) {
			ss.setText("3マス進む");
			ss.setEffect(7);
			
		} else if(random <= 80) {
			ss.setText("4マス進む");
			ss.setEffect(8);
			
		} else if(random <= 99) {
			ss.setText("アイテム獲得");
			ss.setEffect(9);
			
		} else if(random == 100) {
			ss.setText("スタートに戻る");
			ss.setEffect(10);
		}
		
		return ss;
	}
	
	
}
