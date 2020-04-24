package dp;

/**
 * @author liudongyang
 * 换钱问题
 * 给定数组arr，arr中所有的值都为正数且不重复。每个值代表 一种面值的货币，每种面值的货币可以使用任意张，
 * 再给定一 个整数aim代表要找的钱数，求换钱有多少种方法。
 * for example :arr=[5,10,25,1]，aim=0。 组成0元的方法有1种，就是所有面值的货币都不用。所以返回1。
 */
public class CoinsWay {
    public static int coins1(int[] arr, int aim) {
        if (arr.length == 0 || aim < 0) {
            return 0;
        }
        return process1(arr, 0, aim);
    }

    /**
     * 此方法描述：index位置之前如果需要换取得钱为aim有多少种换取得方法
     *
     * @param arr
     * @param index
     * @param aim
     * @return
     */
    private static int process1(int[] arr, int index, int aim) {
        int res = 0;
        if (index == arr.length) {
            return aim == 0 ? 1 : 0;
        } else {
            //如果当前位置为0 - aim / arr[index]
            for (int i = 0; arr[index] * i <= aim; i++) {
                res += process1(arr, index + 1, aim - arr[index] * i);
            }
        }
        return res;
    }

    private static int process2(int[] arr, int aim) {
        if (aim == 0 || arr == null || arr.length == 0) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        //初始化最原始的值
        dp[arr.length][0] = 1;
        for (int j = arr.length - 1; j >= 0; j--) {
            for (int i = 0; i <= aim; i++) {
				/*int x = i;
				while (x - arr[j] >= 0) {
					res += dp[j + 1][x - arr[j]];
					x = x - arr[j];
				}*/
                dp[j][i] = dp[j + 1][i];
                //优化
                dp[j][i] += i - arr[j] >= 0 ? dp[j][i - arr[j]] : 0;
            }
        }
        return dp[0][aim];


    }

    private static int process3(int[] arr, int aim) {
        if (aim == 0 || arr == null || arr.length == 0) {
            return 0;
        }
        int[] dp = new int[aim + 1];
        dp[0] = 1;
        for (int con : arr) {
            for (int i = con; i <= aim; i++) {
                dp[i] = dp[i] + dp[i - con];
            }
        }
        return dp[aim];


    }


    public static void main(String[] args) {
        System.out.println(process2(new int[]{5, 10, 25, 1}, 15));
        System.out.println(process3(new int[]{5, 10, 25, 1}, 15));

    }
}
