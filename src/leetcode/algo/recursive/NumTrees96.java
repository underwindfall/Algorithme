package leetcode.algo.recursive;

// https://leetcode-cn.com/problems/unique-binary-search-trees/
public class NumTrees95 {

    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j <= i; j++) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }

    class Recursive {
        // 1,2,3 和 4，5，6 构建的是同一种数
        public int numTrees(int n) {
            if (n == 0 || n == 1) {
                return 1;
            }
            int num = 0;
            for (int i = 1; i <= n; i++) {
                num += numTrees(i - 1) * numTrees(n - i);
            }
            return num;
        }
    }

    // 1,2,3 和 4，5，6 构建的是同一种数
    // 可以改进
    class RecursiveMemorize {
        public int numTrees(int n) {
            int[] record = new int[n + 1];
            record[0] = 1;
            return helper(n, record);
        }

        public int helper(int n, int[] record) {
            if (n == 0 || n == 1) { // 递归出口
                return 1;
            }
            if (record[n] > 0) { // 如果计算过了
                return record[n]; // 提前返回结束递归求解，省时省空间
            }
            for (int i = 1; i <= n; i++) {
                record[n] += helper(i - 1, record) * helper(n - i, record);
            }
            return record[n];
        }
    }
}
