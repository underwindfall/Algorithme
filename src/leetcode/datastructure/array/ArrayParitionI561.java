package leetcode.datastructure.array;

import java.util.Arrays;

// https://leetcode-cn.com/problems/array-partition-i
public class ArrayParitionI561 {
    class Sort {
        // time O(logn*N)
        // espace O(1)
        public int arrayPairSum(int[] nums) {
            Arrays.sort(nums);
            int sum = 0;
            for (int i = 0; i < nums.length; i = i + 2) {
                sum += nums[i];
            }
            return sum;
        }
    }

    class Bucket {
        public int arrayPairSum(int[] nums) {

            int[] arrays = new int[20001];
            // 这里移位 把所有的负数变成正数
            int lim = 10000;

            for (int num : nums) {
                // 统计下 每个数字出现的次数
                arrays[num + lim]++;
            }
            int sum = 0;
            int redundancy = 0;
            for (int i = -10000; i <= 10000; i++) {
                // 根据上一轮是否剩下的数字和这个匹配 算出总值
                sum += (arrays[lim + i] + 1 - redundancy) / 2 * i;
                // 这里计算是否会剩下这轮的数字 2 只是为了调整数字的正负
                redundancy = (2 + arrays[lim + i] - redundancy) % 2;
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        System.out.print(-1 % 2);
    }
}
