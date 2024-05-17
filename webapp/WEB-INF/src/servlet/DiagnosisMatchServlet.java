package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DiagnosisMatchServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		try {
			// 名前を取得
			String name1 = request.getParameter("first");
			String name2 = request.getParameter("second");
			// 遷移時に表示する
			request.setAttribute("name1", name1);
			request.setAttribute("name2", name2);
			
			
			// 小文字に変換
			name1 = name1.toLowerCase();
			name2 = name2.toLowerCase();
			
			//1文字ずつ切り取り数値に変換してArrayListに格納
			String alphbet = "abcdefghijklmnopqrstuvwxyz";
			
			ArrayList<Integer> nameScore1 = new ArrayList<>();
			for(int i = 0; i < name1.length(); i++) {
				// 1文字ずつ抜き取る
				String str = name1.substring(i, i+1);
				// その文字がアルファベットの何番目かを比較する
				// aの場合0を返すので+1する
				nameScore1.add(alphbet.indexOf(str) + 1);
			}
			
			ArrayList<Integer> nameScore2 = new ArrayList<>();
			for(int i = 0; i < name2.length(); i++) {
				String str = name2.substring(i, i+1);
				nameScore2.add(alphbet.indexOf(str) + 1);
			}
			
			// nameScore1 と 2の要素合計数
			int elementTotal = nameScore1.size() + nameScore2.size();
			
			// name1 + name2 の順
			ArrayList<Integer> case1 = new ArrayList<>();
			for(int i = 0; i < nameScore1.size(); i++) {
				case1.add(nameScore1.get(i));
			}
			for(int i = 0; i < nameScore2.size(); i++) {
				case1.add(nameScore2.get(i));
			}
			
			for(int i = 0; i < elementTotal - 1; i++) {
				// 合計値を格納する配列
				ArrayList<Integer> tmpArray = new ArrayList<>();
				for(int j = 0; j < case1.size() - 1; j++) {
					int num = case1.get(j) + case1.get(j+1);
					if(num >= 101) {
						num -= 101;
					}
					tmpArray.add(num);
				}
				case1 = tmpArray;
			}
			
			// name2 + name1 の順
			ArrayList<Integer> case2 = new ArrayList<>();
			for(int i = 0; i < nameScore2.size(); i++) {
				case2.add(nameScore2.get(i));
			}
			for(int i = 0; i < nameScore1.size(); i++) {
				case2.add(nameScore1.get(i));
			}
			
			for(int i = 0; i < elementTotal - 1; i++) {
				// 合計値を格納する配列
				ArrayList<Integer> tmpArray = new ArrayList<>();
				for(int j = 0; j < case2.size() - 1; j++) {
					int num = case2.get(j) + case2.get(j+1);
					if(num >= 101) {
						num -= 101;
					}
					tmpArray.add(num);
				}
				case2 = tmpArray;
			}
			
			if(case1.get(0) > case2.get(0)) {
				request.setAttribute("result", String.valueOf(case1.get(0)));
			} else {
				request.setAttribute("result", String.valueOf(case2.get(0)));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			request.getRequestDispatcher("/view/diagnosisMatch.jsp").forward(request, response);
		}
	}
}
