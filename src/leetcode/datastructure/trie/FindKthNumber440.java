package leetcode.datastructure.trie;

// https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order/
public class FindKthNumber440 {
    // time O(log^2*N)
    // space O(1)
    // 首先递归一定会超时
    // 所以就是去基本判断tree的位置问题
    //              root
    //    1                             2      3      4 ...9
    // 10       11      12... 19
    // 100...109 110...119 120...129
    public int findKthNumber(int n, int k) {
        int curr = 1;
        k--;
        while (k > 0) {
            int steps = getSteps(curr, n);
            if (steps <= k) {
                //向右移动
                k -= steps;
                curr++;
            } else {
                //向下移动
                curr = curr * 10;
                k--;
            }
        }
        return curr;
    }

    int getSteps(int curr, long n) {
        int steps = 0;
        long first = curr;
        long last = curr;
        while (first <= n) {
            steps += Math.min(last, n) - first + 1;
            first = first * 10;
            last = last * 10 + 9;
        }
        return steps;
    }
}
