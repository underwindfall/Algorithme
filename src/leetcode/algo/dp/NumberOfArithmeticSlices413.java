package leetcode.algo.dp;

// https://leetcode-cn.com/problems/arithmetic-slices/
public class NumberOfArithmeticSlices413 {
    // the way to iteration
    // 第一层循环从0 - 数组倒数第二位
    // 第二层循环比较离第一层两个数字的距离 因为是等差数列最少两个
    // 通过比较 S - I 区间的数组 如果最后一个数字是后等差数字，那就++ 否则递减
    // time O(N^2)
    // espace O(1)
    class Iteration {
        public int numberOfArithmeticSlices(int[] A) {
            int count = 0;
            for (int i = 0; i < A.length - 1 - 1; i++) {
                int diff = A[i + 1] - A[i];
                for (int s = i + 2; s < A.length; s++) {
                    if (A[s] - A[s - 1] == diff) {
                        count++;
                    } else {
                        break;
                    }
                }
            }
            return count;
        }
    }

    
}
