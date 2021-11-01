package leetcode.algo.divideandconquer;

//https://leetcode-cn.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/
public class NumPairsDisivisbleBy60_1010 {
    // time O(n)
    // space O(1)
    public int numPairsDivisibleBy60(int[] time) {
        int count = 0;
        int[] times = new int[60];
        for (int t : time) {
            times[t % 60]++;
        }
        count += combination(times[30], 2);
        count += combination(times[0], 2);
        int i = 1, j = 59;
        while (i < j) {
            count += times[i++] * times[j--];
        }
        return count;
    }

    // 求组合数
    int combination(int n, int k) {
        long result = 1;
        for (int i = 1; i <= k; i++) {
            result = result * (n - i + 1) / i;
        }
        return (int) result;
    }
}
