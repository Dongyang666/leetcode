import java.util.Arrays;

/**
 * test class
 */
class Solution {

	public static void main(String[] args) {
		String s = "filcncncncn";
		String[] cns = s.split("cn");
		System.out.println(Arrays.toString(cns));

	}

	private boolean backtrack(char[][] board, String word, int x, int y, int index) {
		if (index > word.length()) {
			return true;
		}
		if (x > board[0].length || y > board.length || x < 0 || y < 0) {
			return false;
		}
		if (word.charAt(index) == board[x][y]) {
			index++;
		}
		return backtrack(board, word, x + 1, y, index)
				|| backtrack(board, word, x, y + 1, index)
				|| backtrack(board, word, x - 1, y, index)
				|| backtrack(board, word, x, y - 1, index);
	}
}
