package leetcode.algo.dp.subsquence;

import java.util.Arrays;

// https://leetcode-cn.com/problems/minimum-swaps-to-make-sequences-increasing/
public class MinSwap801 {
    // 思路
    // 首先应该判断是不能交换的情况
    // A [4, 1]
    // B [2, 3]
    // 这样的数组是怎么都不能交换成功的 其中发现的不能成功交换数组的规律是
    // A[i - 1] > B[i] || B[i - 1] > A[i] <=====> A[i]< B[i - 1] || B[i] < A[i - 1]
    // 从而得出了可成功交换的条件 A[i] > B[i - 1] && B[i] > A[i - 1]
    // 还有一种情况是本身A，B就是递增数列 A[i] > A[i - 1] && B[i] > B[i - 1]
    // ---------------------------------------------------------------------------------
    // 在A[i] > A[i - 1] && B[i] > B[i - 1] 情况下
    // 1.交换 i，那 i-1 交换
    // 2.不交换 i，那 i-1 不交换

    // 在A[i] > B[i - 1] && B[i] > A[i - 1] 情况下
    // 1.交换 i，那 i-1 不交换
    // 2.不交换 i，那 i-1 交换

    // 在A[i] > B[i - 1] && B[i] > A[i - 1] && A[i] > A[i - 1] && B[i] > B[i - 1]情况下
    // 1.交换 i，那 i-1 交换不交换都可
    // 2.不交换 i，那 i-1 交换不交换都可
    // ----------------------------------------------------------------------------------
    public int minSwap(int[] A, int[] B) {
        int len = A.length;
        // 不交换 i 的情况
        int[] keep = new int[len];
        // 交换 i 的情况
        int[] swap = new int[len];

        Arrays.fill(keep, Integer.MAX_VALUE);
        Arrays.fill(swap, Integer.MAX_VALUE);
        // 初始条件，第 0 个位置不交换，次数为 0，第 0 个位置交换，次数为 1
        keep[0] = 0;
        swap[0] = 1;
        for (int i = 1; i < len; i++) {
            /*
             * 如果满足两种情况 i 交换的情况下，可以有 i - 1 不交换 和 i - 1 交换，选择最优情况 i 不交换的情况下，可以有 i - 1 交换和 i -
             * 1 不交换，选择最优情况
             */

            if ((A[i] > A[i - 1] && B[i] > B[i - 1]) && (A[i] > B[i - 1] && B[i] > A[i - 1])) {
                // i 不交换
                keep[i] = Math.min(keep[i - 1], swap[i - 1]);
                // i 交换
                swap[i] = Math.min(swap[i - 1], keep[i - 1]) + 1;
                continue;
            }

            if(A[i] > A[i - 1] && B[i] > B[i - 1]){
                //i 不交换
                keep[i] = keep[i - 1];
                //i 交换，那么意味着 i - 1 也交换
                swap[i] = swap[i - 1] + 1;
            }
            
            if(A[i] > B[i - 1] && B[i] > A[i - 1]){
                //i 不交换，那么就是交换 i - 1
                keep[i] = swap[i - 1];
                //i 交换，那么就是 i - 1 不交换
                swap[i] = keep[i - 1] + 1;
            }

        }
        return Math.min(keep[len - 1], swap[len - 1]);
    }
}
