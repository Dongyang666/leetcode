import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {

  public static void main(String[] args) {
    HashMap<Integer, Integer> map = new HashMap<>();
    map.entrySet().stream()
        .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
        .map(Map.Entry::getKey)
        .collect(Collectors.toList())
        .subList(0, 1);
  }

  private boolean backtrack(char[][] board, String word, int x, int y, int index) {
    if (index > word.length()) return true;
    if (x > board[0].length || y > board.length || x < 0 || y < 0) return false;
    if (word.charAt(index) == board[x][y]) {
      index++;
    }
    return backtrack(board, word, x + 1, y, index)
        || backtrack(board, word, x, y + 1, index)
        || backtrack(board, word, x - 1, y, index)
        || backtrack(board, word, x, y - 1, index);
  }
}
