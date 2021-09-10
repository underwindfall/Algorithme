package leetcode.algo.binarysearch;

//https://leetcode-cn.com/problems/find-the-student-that-will-replace-the-chalk/
public class ChalkReplacer1894 {
    // time O(n)
    // space O(1)
    class BinearySearch {
        public int chalkReplacer(int[] chalk, int k) {
            int n = chalk.length;
            long[] sum = new long[n + 1];
            for (int i = 1; i <= n; i++)
                sum[i] = sum[i - 1] + chalk[i - 1];
            k = (int) (k % sum[n]);
            int l = 1, r = n;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (sum[mid] <= k)
                    l = mid;
                else
                    r = mid - 1;
            }
            return sum[r] <= k ? r : r - 1;

        }
    }

    // time O(n)
    // space O(1)
    class Simulation {
        public int chalkReplacer(int[] chalk, int k) {
            int n = chalk.length;
            long total = 0;
            for (int i : chalk) {
                total += i;
            }
            k = (int) (k % total);

            int res = -1;
            for (int i = 0; i < n; i++) {
                if (chalk[i] > k) {
                    res = i;
                    break;
                }
                k -= chalk[i];
            }

            return res;
        }
    }
}
