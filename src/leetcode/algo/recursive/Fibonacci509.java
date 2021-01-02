package leetcode.algo.recursive;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/fibonacci-number/
public class Fibonacci509 {
    class Recursive {
        public int fib(int N) {
            if (N <= 1) {
                return N;
            }
            return fib(N - 1) + fib(N - 2);
        }
    }

    // 因为会有重复的数字被计算进去 所以可以记录下之前的结果
    class Memorization {
        class Hash {
            Map<Integer, Integer> map = new HashMap<>();

            public int fib(int N) {
                if (map.containsKey(N)) {
                    return map.get(N);
                }

                int result;
                if (N < 2) {
                    return N;
                } else {
                    result = fib(N - 1) + fib(N - 2);
                }
                map.put(N, result);
                return result;
            }
        }

        class Interactive {
            public int fib(int N) {
                if (N <= 1) {
                    return N;
                }
                if (N == 2) {
                    return 1;
                }

                int current = 0;
                int prev1 = 1;
                int prev2 = 1;

                for (int i = 3; i <= N; i++) {
                    current = prev1 + prev2;
                    prev2 = prev1;
                    prev1 = current;
                }
                return current;
            }
        }

        class IterativeMemorizeArray {
            public int fib(int N) {
                if (N <= 1) {
                    return N;
                }
                return memoize(N);
            }

            private int memoize(int n) {
                int[] cache = new int[n + 1];
                cache[1] = 1;
                for (int i = 2; i <= n; i++) {
                    cache[i] = cache[i - 1] + cache[i - 2];
                }
                return cache[n];
            }
        }

        class RecursiveMemorize {
            private Integer[] array = new Integer[31];

            public int fib(int N) {
                if (N <= 1) {
                    return N;
                }
                array[0] = 0;
                array[1] = 1;
                return memorize(N);
            }

            int memorize(int n) {
                if (array[n] != null) {
                    return array[n];
                }
                array[n] = memorize(n - 1) + memorize(n - 2);
                return array[n];
            }
        }
    }
}
