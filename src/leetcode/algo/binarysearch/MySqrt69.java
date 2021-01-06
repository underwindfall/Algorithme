package leetcode.algo.binarysearch;

// https://leetcode-cn.com/problems/sqrtx/
public class MySqrt69 {
    public int mySqrt(int x) {
        int left = 0;
        int right = x;
        int ans = -1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if ((long) mid * mid <= x) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }
}
