package dp;

import java.util.HashMap;

/**
 * @author: liudongyang
 * @date: 2019/11/18 15:35
 * <p>
 * 你面前有一栋从 1 到 N 共 N 层的楼，然后给你 K 个鸡蛋（K 至少为 1）。
 * 现在确定这栋楼存在楼层 0 <= F <= N，在这层楼将鸡蛋扔下去，鸡蛋恰好没摔碎（高于 F 的楼层都会碎，低于 F 的楼层都不会碎）。
 * 现在问你，最坏情况下，你至少要扔几次鸡蛋，才能确定这个楼层 F 呢？
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
            //每一层都进行尝试
            res = Math.min(res,
                    //在这一层扔鸡蛋有两种结果，第一种，鸡蛋碎了则鸡蛋-1，并且楼层变成i楼层以下的楼层  ---第二种，鸡蛋么有碎则鸡蛋数量不变，---因为是最坏情况则取两者的最大值
                    Math.max(process(floor - i, eggCount), process(i - 1, eggCount - 1)) + 1);
        }
        map.put(floor + "_" + eggCount, res);
        return res;
    }

    public static int superEggDrop(int eggs, int floors) {
        //dp[i][j] 的 含义是 有j个鸡蛋  面对 i层楼时最优的扔鸡蛋次数
        int[][] dp = new int[floors + 1][eggs + 1];
        //init 初始化
        for (int i = 0; i <= floors; i++) {
            dp[i][1] = i;
        }
        for (int i = 1; i <= eggs; i++) {
            dp[1][i] = 1;
        }
        for (int floor = 2; floor <= floors; floor++) {
            for (int egg = 2; egg <= eggs; egg++) {
/*                int res = floor;
                for (int drop = 1; drop <= floor; drop++) {
                    //如果没碎 面对的是向上的楼层也就是floor - drop层楼的选择
                    int unbroken = dp[floor - drop][egg];
                    //如果碎了 面对的是向下的楼层也就是 drop -1 层楼的选择
                    int broken = dp[drop - 1][egg - 1];
                    //每层时，由于是最坏的情况（针对某层，也就是说坏或者不坏那个扔的次数多选那个）所以选择两者中的最大值
                    res = Math.min(res, Math.max(unbroken, broken) + 1);
                }*/
                //搜索改为二分查询
                int left = 1, right = floor;
                int res = floor;
                while (left <= right) {
                    int mid = (right + left) / 2;
                    //不碎--随着楼层的增大,鸡蛋数量不发生变化则需要试的次数慢慢变少。递减函数
                    int unbroken = dp[floor - mid][egg];
                    //如果碎了 面对的是向下的楼层也就是 drop -1 层楼的选择 ----随着楼层的增加---需要试的次数变多-递增函数
                    int broken = dp[mid - 1][egg - 1];
                    if (broken > unbroken) {
                        right = mid - 1;
                        res = Math.min(broken + 1, res);
                    } else {
                        left = mid + 1;
                        res = Math.min(unbroken + 1, res);
                    }
                }
                dp[floor][egg] = res;
            }
        }
        return dp[floors][eggs];
    }


    public static int superDp(int eggs, int floors) {
        //dp[i][j]  有i个鸡蛋，可以扔j次最多能验证多少楼
        int[][] dp = new int[eggs + 1][floors + 1];
        //base case;
        int m = 0;
        while (dp[eggs][m] < floors) {
            m++;
            for (int k = 1; k <= eggs; k++) {
                //3个鸡蛋 扔5次    3个鸡蛋扔4次 ，，，+ 2个鸡蛋扔4次，，，+1
                dp[k][m] = dp[k][m - 1] + dp[k - 1][m - 1] + 1;
            }
        }
        return m;
    }

    public static void main(String[] args) {

        System.out.println(superDp(4, 5000));
    }
}
