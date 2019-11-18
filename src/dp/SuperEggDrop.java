package dp;

import java.util.HashMap;

/**
 * @description: { }
 * @author: dyliu7@iflytek.com
 * @date: 2019/11/18 15:35
 */
public class SuperEggDrop {
    public static int way1(int floor, int eggCount) {
        return process(floor, eggCount);
    }

    public static HashMap<String, Integer> map = new HashMap<>();

    private static int process(int floor, int eggCount) {
        if (map.containsKey(floor + "_" + eggCount)) {
            return map.get(floor + "_" + eggCount);
        }
        if (eggCount == 1) {
            return floor;
        }
        if (eggCount == 0) {
            return 0;
        }
        int res = floor;
        for (int i = 1; i <= floor; i++) {
            res = Math.min(res, Math.max(process(floor - i, eggCount), process(i - 1, eggCount - 1)) + 1);
        }
        map.put(floor + "_" + eggCount, res);
        return res;
    }

    public static void main(String[] args) {

        System.out.println(way1(100, 2));
    }
}
