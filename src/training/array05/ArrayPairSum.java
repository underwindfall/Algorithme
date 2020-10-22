package training.array05;

import java.util.Arrays;

//https://leetcode-cn.com/problems/array-partition-i/
/**
 * 给定长度为 2n 的数组, 你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从1 到 n
 * 的 min(ai, bi) 总和最大。
 * 
 * 示例 1:
 * 
 * 输入: [1,4,3,2]
 * 
 * 输出: 4 解释: n 等于 2, 最大总和为 4 = min(1, 2) + min(3, 4). 提示:
 * 
 * n 是正整数,范围在 [1, 10000]. 数组中的元素范围在 [-10000, 10000].
 */
public class ArrayPairSum {
    // olog(n)
    static int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum += nums[i];
        }
        return sum;
    }

    // o(n)
    static int arrayPairSum1(int[] nums) {
        int kMaxValue = 10000;
        int array[] = new int[2 * kMaxValue + 1];
        for (int num : nums) {
            ++array[num + kMaxValue];
        }
        int sum = 0;
        int n = -kMaxValue;
        boolean first = true;
        while (n <= kMaxValue) {
            if (array[n + kMaxValue] == 0) {
                ++n;
                continue;
            }
            if (first) {
                sum += n;
                first = false;
            } else {
                first = true;
            }
            --array[n + kMaxValue];
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(arrayPairSum(new int[] { 1, 4, 3, 2 }));
        System.out.println(arrayPairSum1(new int[] { 1, 4, 3, 2 }));
    }
}
