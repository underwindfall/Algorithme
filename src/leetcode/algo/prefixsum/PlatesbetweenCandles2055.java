package leetcode.algo.prefixsum;

// https://leetcode-cn.com/problems/plates-between-candles
public class PlatesbetweenCandles2055 {
    /**
     * 时间复杂度：O(n + q)，其中 n 为数组长度，q 为询问数量。
     * 
     * 空间复杂度：O(n)，其中 n 为数组长度
     * 
     */
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();
        int[] prefixSum = new int[n];
        for (int i = 0, sum = 0; i < n; i++) {
            if (s.charAt(i) == '*') {
                sum++;
            }
            prefixSum[i] = sum;
        }

        int[] left = new int[n];
        for (int i = 0, l = -1; i < n; i++) {
            if (s.charAt(i) == '|') {
                l = i;
            }
            left[i] = l;
        }

        int[] right = new int[n];
        for (int i = n - 1, r = -1; i >= 0; i--) {
            if (s.charAt(i) == '|') {
                r = i;
            }
            right[i] = r;
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int x = right[query[0]], y = left[query[1]];
            ans[i] = x == -1 || y == -1 || x >= y ? 0 : prefixSum[y] - prefixSum[x];
        }
        return ans;
    }
}
