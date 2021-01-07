package leetcode.algo.binarysearch;

import java.util.Random;

// https://leetcode-cn.com/problems/first-bad-version/
public class FirstBadVersion278 {
    class BinearySearch {
        public int firstBadVersion(int n) {
            int left = 1, right = n;
            while (left < right) {
                int mid = (right - left) / 2 + left;
                if (!isBadVersion(mid)) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return left;
        }

    }

    boolean isBadVersion(int n) {
        return new Random().nextBoolean();
    }

    class Recurisve {
        /**
         * 思路2：二分查找 + 递归
         * <p>
         * 多次执行耗时： <br>
         * 执行用时：16 ms, 在所有 Java 提交中击败了98.85%的用户 <br>
         * 内存消耗：35.2 MB, 在所有 Java 提交中击败了97.41%的用户
         * 
         * @param n
         * @return
         */
        public int firstBadVersion2(int n) {
            return helper(1, n);
        }

        private int helper(int left, int right) {
            if (left > right) {
                return left;
            }
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
            return helper(left, right);
        }

    }
}
