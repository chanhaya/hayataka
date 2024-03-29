package test;

public class AngoKaidoku {

	class Ango {
	    public String moji = "";
	    public int value = 0;
	    public Ango(String moji, int value) {
	        this.moji = moji;
	        this.value = value;
	    }
	}

	public static void main(String[] args) {
		AngoKaidoku kaidoku = new AngoKaidoku();
		kaidoku.procedure();
	}

	public void sort(Ango[] ango) {
		Ango tempAd = null;
		
		for(int i = ango.length-1; i > 0; i--) {
			for(int j = 0; j < i; j++ ) {
				if(ango[j].value > ango[j+1].value) {
					tempAd = ango[j];
					ango[j] = ango[j+1];
					ango[j+1] = tempAd;
				}
			}
		}

		return;
	}

	public void procedure(){
		Ango[] ango = new Ango[62];
		ango[0] = new Ango("と",484);
		ango[1] = new Ango("と",187);
		ango[2] = new Ango("と",439);
		ango[3] = new Ango("と",70);
		ango[4] = new Ango("と",241);
		ango[5] = new Ango("と",115);
		ango[6] = new Ango("し",538);
		ango[7] = new Ango("し",214);
		ango[8] = new Ango("し",133);
		ango[9] = new Ango("し",259);
		ango[10] = new Ango("し",493);
		ango[11] = new Ango("ご",358);
		ango[12] = new Ango("ご",106);
		ango[13] = new Ango("ご",394);
		ango[14] = new Ango("ご",232);
		ango[15] = new Ango("ご",475);
		ango[16] = new Ango("こ",322);
		ango[17] = new Ango("こ",430);
		ango[18] = new Ango("こ",61);
		ango[19] = new Ango("こ",178);
		ango[20] = new Ango("の",466);
		ango[21] = new Ango("の",97);
		ango[22] = new Ango("の",223);
		ango[23] = new Ango("の",25);
		ango[24] = new Ango("の",349);
		ango[25] = new Ango("や",448);
		ango[26] = new Ango("や",205);
		ango[27] = new Ango("や",286);
		ango[28] = new Ango("や",43);
		ango[29] = new Ango("く",376);
		ango[30] = new Ango("く",295);
		ango[31] = new Ango("く",124);
		ango[32] = new Ango("く",250);
		ango[33] = new Ango("ん",565);
		ango[34] = new Ango("ん",547);
		ango[35] = new Ango("ん",268);
		ango[36] = new Ango("る",421);
		ango[37] = new Ango("る",313);
		ango[38] = new Ango("る",169);
		ango[39] = new Ango("か",403);
		ango[40] = new Ango("か",151);
		ango[41] = new Ango("か",79);
		ango[42] = new Ango("は",34);
		ango[43] = new Ango("は",196);
		ango[44] = new Ango("と",331);
		ango[45] = new Ango("と",367);
		ango[46] = new Ango("ま",457);
		ango[47] = new Ango("だ",529);
		ango[48] = new Ango("げ",556);
		ango[49] = new Ango("た",511);
		ango[50] = new Ango("ず",142);
		ango[51] = new Ango("き",52);
		ango[52] = new Ango("ぜ",88);
		ango[53] = new Ango("ひ",340);
		ango[54] = new Ango("そ",16);
		ango[55] = new Ango("り",277);
		ango[56] = new Ango("な",160);
		ango[57] = new Ango("す",304);
		ango[58] = new Ango("ざ",412);
		ango[59] = new Ango("う",385);
		ango[60] = new Ango("け",520);
		ango[61] = new Ango("☆",502);

		sort(ango);

		for(int i = 0; i < ango.length; i++) {
		    System.out.print(ango[i].moji);
		}
	}

}
