/**
 * 使用并查集
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * <p>
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * <p>
 * 示例:
 * <p>
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 * <p>
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/surrounded-regions
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AroundDomain {
	public void solve(char[][] board) {
		if (board.length == 0) {
			return;
		}
		int row = board.length;
		int col = board[0].length;
		//留一个给根节点
		UnionFind uf = new UnionFind(row * col + 1);
		int root = col * row;
		//init
		for (int i = 0; i < col; i++) {
			if (board[0][i] == 'O') {
				uf.union(root, i);
			}
			if (board[row - 1][i] == 'O') {
				uf.union(root, (row - 1) * col + i);
			}
		}
		for (int i = 0; i < row; i++) {
			if (board[i][0] == 'O') {
				uf.union(root, col * i);
			}
			if (board[i][col - 1] == 'O') {
				uf.union(root, col * i + col - 1);
			}
		}
		//方向矩阵
		int[][] d = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
		for (int i = 1; i < col - 1; i++) {
			for (int j = 1; j < row - 1; j++) {
				if (board[i][j] == 'O') {
					for (int k = 0; k < 4; k++) {
						int x = i + d[k][0];
						int y = j + d[k][1];
						if (board[x][y] == 'O') {
							uf.union(i * col + j, x * col + y);
						}
					}
				}
			}
		}
		for (int i = 1; i < row - 1; i++) {
			for (int j = 1; j < col - 1; j++) {
				if (!uf.connected(root, i * col + j)) {
					board[i][j] = 'X';
				}
			}
		}
	}
}
