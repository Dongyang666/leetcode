public class Hanoi {

  private static void func(int n, String from, String help, String to) {
    if (n == 1) {
      System.out.println("move from " + from + " to " + to);
    } else {
      func(n - 1, from, to, help);
      func(1, from, help, to);
      func(n - 1, help, from, to);
    }
  }

  public static void main(String[] args) {
    func(3, "left", "mid", "right");
  }
}
