import java.util.Arrays;
import java.util.Scanner;

/**
 * @author liudongyang
 * 画匠问题
 * 【题目】 给定一个整型数组arr，数组中的每个值都为正数，表示完成一幅画作需要的时间，
 * 再给定一个整数num表示画匠的数量，每个画匠只能画连在一起的画作。
 * 所有的画家并行工作，请返回完成所有的画作需要的最少时间。
 * 【举例】
 * arr=[3,1,4]，num=2。 最好的分配方式为第一个画匠画3和1，所需时间为4。
 * 第二个画匠画4，所需 时间为4。因为并行工作，所以最少时间为4。如果分配方式为第一个画匠画3，
 * 所需时间为3。第二个画匠画1和4，所需的时间为5。那么最少时间为5，显然 没有第一种分配方式好。所以返回4。
 *
 * arr=[1,1,1,4,3]，num=3。 最好的分配方式为第一个画匠画前三个1，所需时间为3。
 * 第二个画匠画4，所需时间为4。第三个画匠画3，所需时间为3。返回4。
 */
public class ArtistProblem {
	public static int solution1(int[] arr,int num){
		int maxSum = 0;
		for (int i : arr) {
			maxSum+=i;

		}

		int minSum = 0;
		while(minSum != maxSum - 1){
			int mid = ((maxSum - minSum)/2) + minSum;
			if(getSplit(arr,mid)> num){
				minSum = mid;
			}else {
				maxSum = mid;
			}
		}
		return maxSum;
	}

	public static int getSplit(int[] arr, int sum){
		int count = 0;
		int tmpSum = 0;
		for (int i : arr) {
			//假如说
			if(i > sum){
				return Integer.MAX_VALUE;
			}
			if(tmpSum>=sum){
				count++;
				tmpSum = 0;
			}
			tmpSum += i;

		}
		return tmpSum == 0 ? count : count + 1;
	}

	public static int getNeedNum(int[] arr, int lim) {
		int res = 1;
		int stepSum = 0;
		for (int i = 0; i != arr.length; i++) {
			if (arr[i] > lim) {
				return Integer.MAX_VALUE;
			}
			stepSum += arr[i];
			if (stepSum > lim) {
				res++;
				stepSum = arr[i];
			}
		}
		return res;
	}

	public static void main(String[] args) {
		System.out.println(solution1(new int[]{3,1,4}, 2));
	}

}
