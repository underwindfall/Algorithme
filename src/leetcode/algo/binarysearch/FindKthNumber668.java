package leetcode.algo.binarysearch;

// https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/
public class FindKthNumber668 {
    // time O(logN * n)
    // space O(1)
    public int findKthNumber(int m, int n, int k) {
        int left = 1, right = m * n;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (count(mid, m, n, k)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    boolean count(int x, int m, int n, int k) {
        int count = 0;
        for (int i = 1; i <= m; i++) {
            count += Math.min(x / i, n);
        }
        return count >= k;
    }
}
