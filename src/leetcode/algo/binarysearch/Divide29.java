package leetcode.algo.binarysearch;

//https://leetcode-cn.com/problems/divide-two-integers/
public class Divide29 {
    /**
     * 两数相除 题目经典在于 两个数相处 X/Y 所得商=[0, X]之间 可以利用这个将数组分成两段
     * 
     * time O(logN)
     * 
     * 
     * espace O(1)
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @return 商（不包含小数）
     */
    public int divide(int dividend, int divisor) {
        long result = 0;
        long x = dividend;
        long y = divisor;
        boolean negative = (x < 0 && y > 0) || (x > 0 && y < 0);

        if (x < 0) {
            x = -x;
        }
        if (y < 0) {
            y = -y;
        }
        // 由于x/y的结果肯定在[0,x]范围内，所以对x使用二分法
        long left = 0;
        long right = x;
        while (left < right) {
            long mid = left + right + 1 >> 1;
            if (quickMulti(mid, y) <= x) {
                // 相乘结果不大于x，左指针右移
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        result = negative ? -left : left;

        // 判断是否溢出
        if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        return (int) result;
    }

    /**
     * 快速乘法
     *
     * @param a 乘数
     * @param b 被乘数
     * @return 积
     */
    long quickMulti(long a, long k) {
        long ans = 0;
        while (k > 0) {
            // 当前最低位为1，结果里加上a
            if ((k & 1) == 1) {
                ans += a;
            }
            // 被乘数右移1位，相当于除以2
            k >>= 1;
            // 乘数倍增，相当于乘以2
            a += a;
        }
        return ans;
    }

    public static void main(String[] args) {
        Divide29 divide29 = new Divide29();
        divide29.quickMulti(10, 2);
    }
}
