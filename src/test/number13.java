package test;

import java.util.Scanner;

public class number13 {

	public static void main(String[] args) {
		
		System.out.print("7桁以内の数字を入力してください＜");
		Scanner sin = new Scanner(System.in);
		
		String number = sin.next();
		int count = 0;
		
		// 値が変わらないよう参照型で取得
		int binary = Integer.parseInt(number);
		// 割れた回数を数える
		while(binary >= 1) {
			binary /= 2;
			count++;
		}
		// 割れた回数が配列の要素数
		int[] numList = new int[count];
		
		// 数値を復元し配列に後ろから2で割った場合の余りを代入
		binary = Integer.parseInt(number);
		for(int i = count-1; i >= 0; i--) {
			numList[i] = binary % 2;
			binary /= 2;
		}
		// 配列の中身を出力
		System.out.print(number + "の2進数は");
		for(int num: numList) {
			System.out.print(num);
		}
		System.out.println("です。");	
	}
}
