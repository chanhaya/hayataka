package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RandomNumberServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		try {
			// 数値を作成し配列に入れる
			ArrayList<Integer> numberList = new ArrayList<>();
			
			// 計31個の数値を配列に格納する
			while(numberList.size() < 31) {
				// 1〜100以内の数字をランダムに作成
				int num = (int)(Math.random() * 100 + 1);
				// 重複チェック(すでに配列ないに存在しないか確認)
				if(numberList.contains(num) == false) {
					numberList.add(num);
				}
			}
			
			// 並び替え用の配列を用意する
			ArrayList<Integer> sortNumberList = (ArrayList<Integer>)numberList.clone();
			// 並び替える(バブルソート)
			// 配列の要素数 - 1 回繰り返します(外側for文)
			// 隣合う数字を比較し小さい順にします(大きいものを右に持っていく)(内側for文)
			// つまり1周するごとに大きい数(右)から定まっていきます
			// 周を重ねるごとに右は決まっていくのでわざわざ右端まで計算する必要はなくなります
			// そのため j < sortNumberList.size() - i と記載する
			for(int i = 1; i <= sortNumberList.size() - 1; i++) {
				for(int j = 0; j < sortNumberList.size() - i; j++) {
					int tmp1 = sortNumberList.get(j);
					int tmp2 = sortNumberList.get(j+1);
					if(tmp1 > tmp2) {
						sortNumberList.set(j, tmp2);
						sortNumberList.set(j+1, tmp1);
					}
				}
			}
			
			request.setAttribute("numberList", numberList);
			request.setAttribute("sortNumberList", sortNumberList);
			
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			request.getRequestDispatcher("/view/randomNumber.jsp").forward(request, response);
		}
	}
}
