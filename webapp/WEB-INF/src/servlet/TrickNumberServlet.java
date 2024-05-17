package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TrickNumberServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		try {
			// 数値を作成し配列に入れる
			ArrayList<Integer> firstNumberList = new ArrayList<>();
			ArrayList<Integer> secondNumberList = new ArrayList<>(); 
			
			// 計15個の数値を配列に格納する
			while(firstNumberList.size() < 15) {
				// 1〜30以内の数字をランダムに作成
				int num = (int)(Math.random() * 30 + 1);
				// 重複チェック(すでに配列内に存在しないか確認)
				if(firstNumberList.contains(num) == false) {
					firstNumberList.add(num);
				}
			}
			
			while(secondNumberList.size() < 15) {
				int num = (int)(Math.random() * 30 + 32);
				if(secondNumberList.contains(num) == false) {
					secondNumberList.add(num);
				}
			}
			
			request.setAttribute("firstNumberList", firstNumberList);
			request.setAttribute("secondNumberList", secondNumberList);
			
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			request.getRequestDispatcher("/view/trickNumber.jsp").forward(request, response);
		}
	}
}
