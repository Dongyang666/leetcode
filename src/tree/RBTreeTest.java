package tree;

import java.util.Scanner;

/**
 * @author liudongyang
 */
public class RBTreeTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RBTree<String, Object> rbTree = new RBTree<>();
        while (true) {
            String key = sc.nextLine();
            String value = null;
            rbTree.put(key, value);
            TreeOperation.show(rbTree.getRoot());
        }
    }
}
