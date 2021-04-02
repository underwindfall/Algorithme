package leetcode.algo.dp.prefix;

//https://leetcode-cn.com/problems/xor-queries-of-a-subarray/
public class XorQueries1310 {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = queries.length;
        int[] ans = new int[n];

        // 00 -> 0
        // 10 -> 1
        int[] prefix = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            prefix[i + 1] = prefix[i] ^ arr[i];
        }

        for (int i = 0; i < n; i++) {
            // 前面多异或的部分，再重复异或一次就可以抵消了
            // 假设 求 [1, 2]，那么对于 [0, 2] 来说就是多异或了 [0, 0] 这个结果
            // 根据 两个相同值异或结果为 0，那么我们可以再异或一次 [0, 0] 就将 [0, 0] 给抵消掉了
            int start = queries[i][0];
            int end = queries[i][1];
            ans[i] = prefix[end + 1] ^ prefix[start];
        }

        return ans;
    }
}
