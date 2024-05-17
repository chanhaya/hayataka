package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MazeServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		try {

			int square = Integer.parseInt(request.getParameter("number"));

			ArrayList<Integer> maze = new ArrayList<>();

			// 開始地点とゴール地点は1にして他を2にする
			for (int i = 0; i < square * square; i++) {
				if (i == square * square - square) {
					maze.add(1);

				} else {
					maze.add(2);
				}
			}

			// 開始地点からランダムに0か1に上書きする
			// 行き先に0しかない場合は1が出るようにしてゴールまで結ぶ
			// 関与しなかった2は0か1に上書きする
			// squareで割って0なら最上段、square-1なら最下段
			// 余りが0なら一番左、余りがsquare-1なら一番右
			int current = square * square - square;
			while (current != square - 1) {
				// スタート地点
				if (current == square * square - square) {
					// 00の場合はやり直し
					while (true) {
						int up = current - square;
						int right = current + 1;
						maze.set(up, (int) (Math.random() * 2));
						maze.set(right, (int) (Math.random() * 2));
						if (maze.get(up) == 1) {
							current = up;
							break;
						} else if (maze.get(right) == 1) {
							current = right;
							break;
						}
					}
				} else if (current == 0) {
					// 0の位置(最上段左列)まで来るには2通りしかないから来てない方の値を決める
					int right = current + 1;
					if (maze.get(right) == 2) {
						maze.set(right, 1);
						current = right;
					}
				} else if (current == square * square - 1) {
					// 最下段右列の場合
					int top = current - square;
					if (maze.get(top) == 2) {
						maze.set(top, 1);
						current = top;
					}

				} else if (current % square == 0) {
					// 最左列の場合
					int top = current - square;
					int right = current + 1;

					while (true) {
						maze.set(top, (int) (Math.random() * 2));
						maze.set(right, (int) (Math.random() * 2));

						if (maze.get(top) == 1) {
							current = top;
							break;
						} else if (maze.get(right) == 1) {
							current = right;
							break;
						}
					}
				} else if (current % square == square - 1) {
					// 最右列の場合
					int top = current - square;
					if (maze.get(top) == 2) {
						maze.set(top, 1);
						current = top;
					}
				} else if (current / square == square - 1) {
					// 最下段
					int top = current - square;
					int right = current + 1;
					while (true) {

						maze.set(top, (int) (Math.random() * 2));
						maze.set(right, (int) (Math.random() * 2));

						if (maze.get(top) == 1) {
							current = top;
							break;
						} else if (maze.get(right) == 1) {
							current = right;
							break;
						}
					}
				} else if (current / square == 0) {
					// 最上段の場合
					int right = current + 1;
					if (maze.get(right) == 2) {
						maze.set(right, 1);
						current = right;
					}
				} else {
					int top = current - square;
					int right = current + 1;

					while (true) {

						maze.set(top, (int) (Math.random() * 2));
						maze.set(right, (int) (Math.random() * 2));

						if (maze.get(top) == 1) {
							current = top;
							break;
						} else if (maze.get(right) == 1) {
							current = right;
							break;
						}
					}
				}
			}
			
			maze.set(square - 1, 1);
			for(int i = 0; i < maze.size(); i++) {
				if(maze.get(i) == 2) {
					maze.set(i, (int)(Math.random() * 2));
				}
			}

			request.setAttribute("square", square);
			request.setAttribute("maze", maze);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			request.getRequestDispatcher("/view/maze.jsp").forward(request, response);
		}
	}
}
