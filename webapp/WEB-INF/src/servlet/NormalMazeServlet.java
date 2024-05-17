package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Maze;

public class NormalMazeServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		try {
			int square = Integer.parseInt(request.getParameter("number"));

			ArrayList<Maze> mazeList = new ArrayList<>();

			// 開始地点を1にする
			for (int i = 0; i < square * square; i++) {
				Maze maze = new Maze();
				if (i == square * square - square) {
					maze.setNum(1);
				}
				mazeList.add(maze);
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
						int top = current - square;
						int right = current + 1;
						Maze currentMaze = mazeList.get(current);
						Maze upMaze = mazeList.get(top);
						Maze rightMaze = mazeList.get(right);

						// 初回時と両方0のやりなおし時のみ 0 or 1 の割り振り
						if (upMaze.getNum() == 2 || upMaze.getNum() == 0 && rightMaze.getNum() == 0) {
							upMaze.setNum((int) (Math.random() * 2));
							rightMaze.setNum((int) (Math.random() * 2));
						}

						if (upMaze.getNum() == 1 && upMaze.isPassing() == false) {
							upMaze.setPrev(current);
							upMaze.setPassing(true);
							current = top;
							break;
						} else if (rightMaze.getNum() == 1 && rightMaze.isPassing() == false) {
							rightMaze.setPrev(current);
							rightMaze.setPassing(true);
							current = right;
							break;
						} else {
							// 初期化
							for(int i = 0; i < mazeList.size(); i++) {
								mazeList.get(i).setNum(2);
								mazeList.get(i).setPassing(false);
								mazeList.get(square * square - square).setNum(1);
							}
						}
						
					}
				} else if (current == 0) {
					// 0の位置(最上段左列)まで来るには2通りしかないから来てない方の値を決める
					int right = current + 1;

					Maze rightMaze = mazeList.get(right);
					Maze currentMaze = mazeList.get(current);

					// 初回時と2周目以降の抽選
					if (rightMaze.getNum() == 2) {
						rightMaze.setNum((int) (Math.random() * 2));
					}

					if (rightMaze.getNum() == 1 && rightMaze.isPassing() == false) {
						rightMaze.setPrev(current);
						rightMaze.setPassing(true);
						current = right;
					} else {
						current = currentMaze.getPrev();
					}

				} else if (current == square * square - 1) {
					// 最下段右列の場合
					int top = current - square;

					Maze topMaze = mazeList.get(top);
					Maze currentMaze = mazeList.get(current);

					if (topMaze.getNum() == 2) {
						topMaze.setNum((int) (Math.random() * 2));
					}

					if (topMaze.getNum() == 1 && topMaze.isPassing() == false) {
						topMaze.setPrev(current);
						topMaze.setPassing(true);
						current = top;
					} else {
						current = currentMaze.getPrev();
					}

				} else if (current % square == 0) {
					// 最左列の場合
					int top = current - square;
					int right = current + 1;

					Maze topMaze = mazeList.get(top);
					Maze rightMaze = mazeList.get(right);
					Maze currentMaze = mazeList.get(current);

					// まだマスに 0 か 1 が入っていないなら抽選
					if (topMaze.getNum() == 2) {
						topMaze.setNum((int) (Math.random() * 2));
					}
					if (rightMaze.getNum() == 2) {
						rightMaze.setNum((int) (Math.random() * 2));
					}

					// 右から最左列に来た場合、下が1だと別のルートができる可能性がある？
					// と考えて下は0にして別ルートが生まれる可能性を封じる
					if (mazeList.get(current + square).getNum() == 2) {
						mazeList.get(current + square).setNum(0);
					}

					// 1かつ未開だったら進む
					if (topMaze.getNum() == 1 && topMaze.isPassing() == false) {
						topMaze.setPrev(current);
						topMaze.setPassing(true);
						current = top;
					} else if (rightMaze.getNum() == 1 && rightMaze.isPassing() == false) {
						rightMaze.setPrev(current);
						rightMaze.setPassing(true);
						current = right;
					} else {
						// どっちも進めない場合は戻る
						current = currentMaze.getPrev();
					}

				} else if (current % square == square - 1) {
					// 最右列の場合
					int top = current - square;
					int left = current - 1;
					
					Maze topMaze = mazeList.get(top);
					Maze leftMaze = mazeList.get(left);
					Maze currentMaze = mazeList.get(current);
					
					if (topMaze.getNum() == 2) {
						topMaze.setNum((int) (Math.random() * 2));
					}
					if (leftMaze.getNum() == 2) {
						leftMaze.setNum((int) (Math.random() * 2));
					}
					// 下以外のルートから来た場合は下を0にする
					if (mazeList.get(current + square).getNum() == 2) {
						mazeList.get(current + square).setNum(0);
					}
					
					if(topMaze.getNum() == 1 && topMaze.isPassing() == false) {
						topMaze.setPrev(current);
						topMaze.setPassing(true);
						current = top;
					} else if(leftMaze.getNum() == 1 && leftMaze.isPassing() == false) {
						leftMaze.setPrev(current);
						leftMaze.setPassing(true);
						current = left;
					} else {
						current = currentMaze.getPrev();
					}
					
				
				} else if (current / square == square - 1) {
					// 最下段
					int top = current - square;
					int right = current + 1;

					Maze topMaze = mazeList.get(top);
					Maze rightMaze = mazeList.get(right);
					Maze currentMaze = mazeList.get(current);
					
					if (topMaze.getNum() == 2) {
						topMaze.setNum((int) (Math.random() * 2));
					}
					if (rightMaze.getNum() == 2) {
						rightMaze.setNum((int) (Math.random() * 2));
					}
					if (mazeList.get(current - 1).getNum() == 2) {
						mazeList.get(current - 1).setNum(0);
					}

					if (topMaze.getNum() == 1 && topMaze.isPassing() == false) {
						topMaze.setPrev(current);
						topMaze.setPassing(true);
						current = top;
					} else if (rightMaze.getNum() == 1 && rightMaze.isPassing() == false) {
						rightMaze.setPrev(current);
						rightMaze.setPassing(true);
						current = right;
					} else {
						current = currentMaze.getPrev();
					}

				} else if (current / square == 0) {
					// 最上段の場合
					int right = current + 1;
					int bottom = current + square;

					Maze rightMaze = mazeList.get(right);
					Maze bottomMaze = mazeList.get(bottom);
					Maze currentMaze = mazeList.get(current);

					if (rightMaze.getNum() == 2) {
						rightMaze.setNum((int) (Math.random() * 2));
					}
					if (bottomMaze.getNum() == 2) {
						bottomMaze.setNum((int) (Math.random() * 2));
					}
					if (mazeList.get(current - 1).getNum() == 2) {
						mazeList.get(current - 1).setNum(0);
					}

					if (rightMaze.getNum() == 1 && rightMaze.isPassing() == false) {
						rightMaze.setPrev(current);
						rightMaze.setPassing(true);
						current = right;
					} else if (bottomMaze.getNum() == 1 && bottomMaze.isPassing() == false) {
						bottomMaze.setPrev(current);
						bottomMaze.setPassing(true);
						current = bottom;
					} else {
						current = currentMaze.getPrev();
					}

				} else {
					// 上記以外
					int top = current - square;
					int right = current + 1;
					int bottom = current + square;
					int left = current - 1;

					Maze topMaze = mazeList.get(top);
					Maze rightMaze = mazeList.get(right);
					Maze bottomMaze = mazeList.get(bottom);
					Maze leftMaze = mazeList.get(left);
					Maze currentMaze = mazeList.get(current);
					
					if (topMaze.getNum() == 2) {
						topMaze.setNum((int) (Math.random() * 2));
					}
					if (rightMaze.getNum() == 2) {
						rightMaze.setNum((int) (Math.random() * 2));
					}
					if (bottomMaze.getNum() == 2) {
						bottomMaze.setNum((int) (Math.random() * 2));
					}
					if (leftMaze.getNum() == 2) {
						leftMaze.setNum((int) (Math.random() * 2));
					}

					if (topMaze.getNum() == 1 && topMaze.isPassing() == false) {
						topMaze.setPrev(current);
						topMaze.setPassing(true);
						current = top;
					} else if (rightMaze.getNum() == 1 && rightMaze.isPassing() == false) {
						rightMaze.setPrev(current);
						rightMaze.setPassing(true);
						current = right;
					} else if (bottomMaze.getNum() == 1 && bottomMaze.isPassing() == false) {
						bottomMaze.setPrev(current);
						bottomMaze.setPassing(true);
						current = bottom;
					} else if (leftMaze.getNum() == 1 && leftMaze.isPassing() == false) {
						leftMaze.setPrev(current);
						leftMaze.setPassing(true);
						current = left;
					} else {
						current = currentMaze.getPrev();
					}
				}
			}
			for(int i = 0; i < mazeList.size(); i++) {
				if(i == square * square - 1) {
					if(mazeList.get(i).getNum() == 2 && mazeList.get(i - 1).getNum() != 1 && mazeList.get(i - square).getNum() != 1) {
						mazeList.get(i).setNum((int)(Math.random() * 2));
					}
				} else if(i == 0) {
					if(mazeList.get(i).getNum() == 2 && mazeList.get(i + 1).getNum() != 1 && mazeList.get(i + square).getNum() != 1) {
						mazeList.get(i).setNum((int)(Math.random() * 2));
					}
				} else if(i / square == square - 1) {
					// 最下段
					if(mazeList.get(i).getNum() == 2 && mazeList.get(i + 1).getNum() != 1 && mazeList.get(i - square).getNum() != 1 && mazeList.get(i - 1).getNum() != 1) {
						mazeList.get(i).setNum((int)(Math.random() * 2));
					}
				} else if(i / square == 0) {
					// 最上段
					if(mazeList.get(i).getNum() == 2 && mazeList.get(i + 1).getNum() != 1 && mazeList.get(i + square).getNum() != 1 && mazeList.get(i - 1).getNum() != 1) {
						mazeList.get(i).setNum((int)(Math.random() * 2));
					}
				} else if(i % square == 0) {
					// 最左列
					if(mazeList.get(i).getNum() == 2 && mazeList.get(i + 1).getNum() != 1 && mazeList.get(i - square).getNum() != 1 && mazeList.get(i + square).getNum() != 1) {
						mazeList.get(i).setNum((int)(Math.random() * 2));
					}
				} else if(i % square == square - 1) {
					// 最右列
					if(mazeList.get(i).getNum() == 2 && mazeList.get(i + square).getNum() != 1 && mazeList.get(i - square).getNum() != 1 && mazeList.get(i - 1).getNum() != 1) {
						mazeList.get(i).setNum((int)(Math.random() * 2));
					}
				} else {
					// それ以外
					if(mazeList.get(i).getNum() == 2 && mazeList.get(i + 1).getNum() != 1 &&  mazeList.get(i - 1).getNum() != 1 && mazeList.get(i + square).getNum() != 1 && mazeList.get(i - square).getNum() != 1) {
						mazeList.get(i).setNum((int)(Math.random() * 2));
					}
				}
					
				if(mazeList.get(i).getNum() == 2) {
					mazeList.get(i).setNum(0);
				}
			}

			request.setAttribute("square", square);
			request.setAttribute("mazeList", mazeList);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			request.getRequestDispatcher("/view/normalMaze.jsp").forward(request, response);
		}
	}
}
