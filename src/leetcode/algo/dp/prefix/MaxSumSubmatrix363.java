package leetcode.algo.dp.prefix;

// https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k/
public class MaxSumSubmatrix363 {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int row = matrix.length, col = matrix[0].length;
        int max = Integer.MIN_VALUE;
        for (int l = 0; l < col; l++) {
            int[] rowSum = new int[row]; // 左边界改变才算区域的重新开始
            for (int r = l; r < col; r++) {
                for (int i = 0; i < row; i++) {
                    rowSum[i] += matrix[i][r];
                }

                max = Math.max(max, dpmax(rowSum, k));
                if (max == k)
                    return k; // 尽量提前
            }
        }
        return max;
    }

    private int dpmax(int[] arr, int k) {
        int rollSum = arr[0], rollMax = rollSum;

        // O(rows)
        for (int i = 1; i < arr.length; i++) {
            if (rollSum > 0)
                rollSum += arr[i];
            else
                rollSum = arr[i];
            if (rollSum > rollMax)
                rollMax = rollSum;
        }

        if (rollMax <= k)
            return rollMax;

        // O(rows ^ 2)
        int max = Integer.MIN_VALUE;
        for (int l = 0; l < arr.length; l++) {
            int sum = 0;
            for (int r = l; r < arr.length; r++) {
                sum += arr[r];
                if (sum > max && sum <= k)
                    max = sum;
                if (max == k)
                    return k; // 尽量提前
            }
        }
        return max;
    }
}
