package test;

import java.util.Scanner;

public class test1 {

	public static void main(String[] args) {
		
		Scanner sin = new Scanner(System.in);
		
		// ユーザーからnと入力されるまで繰り返す
		while(true) {
			System.out.print("2から1億未満の数値を入力してください＜");
			int num = Integer.parseInt(sin.nextLine());
			int count = 0; // カウント変数
			
			// 2~99,999,999以内の数値を入力してもらう
			if(num < 2 || num > 100000000) {
				System.out.println("数値が正しくありません。");
				continue;
			}
			
			// 余りが0になったらカウント変数を増やすする
			for(int i = 2; i <= num; i++) {
				if(num % i == 0) {
					count++;
				}
			}
			
			// カウント変数が1であれば素数
			if(count == 1) {
				System.out.println(num + "は素数です。\n");
			} else {
				System.out.println(num + "は素数ではありません。\n");
				continue;
			}
			
			// 「n」が入力された場合、処理を終了する
			System.out.print("素数判定を続けますか?(y or n)＜");
			String select = sin.nextLine();
			if(select.equals("n")) {
				System.out.println("素数判定を終了します。");
				break;
			}
		}

	}

}
