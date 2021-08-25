package leetcode.algo.backtrack;

//https://leetcode-cn.com/problems/count-numbers-with-unique-digits/
public class CountNumbersWithUniqueDigits357 {
    // 数位Dp
    // 数学问题
    // dp(0) = 1
    // dp(1) = 9
    // dp(2) = 9 * 9
    // dp(3) = 9 * 9 * 8 ----- 首位不能0 第二位 有9种方法 第三位有8种
    // 。。。。。
    // dp(n) = dp(n - 1) + 9 * 9 * 8 *.... (9-(n-2))
    // time O(1)
    // space O(1)
    class DP {
        public int countNumbersWithUniqueDigits(int n) {
            if (n == 0) {
                return 1;
            }
            if (n == 1) {
                return 10;
            }
            int pre = 10;
            for (int i = 2; i <= n; i++) {
                int tmp = 9 * 9;
                for (int j = i - 2; j > 0; j--) {
                    tmp *= (9 - j);
                }
                pre = pre + tmp;
            }
            return pre;
        }
    }

    // time O(10!)
    // space O(10!)
    class Backtrack {
        public int countNumbersWithUniqueDigits(int n) {
            int count = 1; // x== 0
            long max = (long) Math.pow(10, n);
            boolean[] used = new boolean[10];

            for (int i = 1; i < 10; i++) {
                used[i] = true;
                count += search(i, max, used);
                used[i] = false;
            }
            return count;
        }

        int search(long prev, long max, boolean[] used) {
            int count = 0;
            if (prev < max) {
                count += 1;
            } else {
                return count;
            }

            for (int i = 0; i < 10; i++) {
                if (!used[i]) {
                    used[i] = true;
                    long cur = 10 * prev + i;
                    count += search(cur, max, used);
                    used[i] = false;
                }
            }
            return count;
        }
    }
}
