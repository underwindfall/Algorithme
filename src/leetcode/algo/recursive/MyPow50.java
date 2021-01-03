package leetcode.algo.recursive;

// https://leetcode-cn.com/problems/powx-n/
public class MyPow50 {
    class Recursive {
        class Binary {
            public double myPow(double x, int n) {
                return n >= 0 ? quickMul(x, n) : 1.0 / quickMul(x, -n);
            }

            private double quickMul(double x, int n) {
                if (n == 0) {
                    return 1.0;
                }
                double y = quickMul(x, n / 2);
                return n % 2 == 0 ? y * y : y * y * x;
            }
        }

        class Best {
            public double myPow(double x, int n) {
                long N = n;
                if (N < 0) {
                    x = 1 / x;
                    N = -N;
                }
                double ans = 1.0f;
                double current = x;
                for (long i = N; i > 0; i /= 2) {
                    if (i % 2 == 0) {
                        ans = current * ans;
                    }
                    current = current * current;
                }
                return ans;
            }
        }

        class Solution {
            public double myPow(double x, int n) {
                if (n == 0) {
                    return 1.00;
                }
                if (n > 0) {
                    return pow(x, x, n);
                } else {
                    return 1 / pow(x, x, Math.abs(n));
                }
            }

            double pow(double sum, double x, int n) {
                if (n == 1) {
                    return sum;
                }
                sum = sum * x;
                n--;
                return pow(sum, x, n);
            }
        }
    }
}
