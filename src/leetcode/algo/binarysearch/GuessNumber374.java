package leetcode.algo.binarysearch;

// https://leetcode-cn.com/problems/guess-number-higher-or-lower/
public class GuessNumber374 {
    public class Solution {
        public int guessNumber(int n) {
            int left = 1;
            int right = n;
            while (left < right) {
                int mid = (right - left) / 2 + left;
                if (guess(mid) == 0) {
                    return mid;
                } else if (guess(mid) == -1) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return right;
        }

        private int guess(int num) {
            return -1;
        }
    }

}
