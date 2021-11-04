package leetcode.algo.binarysearch;

// https://leetcode-cn.com/problems/valid-perfect-square/
public class IsPerfectSquare367 {

    // time O(logN)
    // space O(1)
    public boolean isPerfectSquare(int num) {
        long left = 1, right = num;
        while (left <= right) {
            long mid = (right - left) / 2 + left;
            if (mid * mid > num) {
                right = mid - 1;
            } else if (mid * mid < num) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

}
