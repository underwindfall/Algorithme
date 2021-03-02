package leetcode.algo.dp.twodimension;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/super-egg-drop
public class SuperEggDrop887 {
    // https://leetcode-cn.com/problems/super-egg-drop/solution/ji-dan-diao-luo-xiang-jie-by-shellbye/
    class BinaryDP {

        Map<Integer, Integer> cache = new HashMap<>();

        public int superEggDrop(int K, int N) {
            if (N == 0)
                return 0;
            else if (K == 1)
                return N;

            Integer key = N * 1000 + K; // K <= 100
            if (cache.containsKey(key))
                return cache.get(key);

            int low = 1, high = N;
            int res = Integer.MAX_VALUE;
            while (low <= high) {
                int middle = (low + high) / 2;
                int broken = superEggDrop(K - 1, middle - 1);// 碎了
                int unbroken = superEggDrop(K, N - middle);// 没碎
                //res => min(max(broken, unbroken) + 1, res) 
                if (broken > unbroken) {
                    high = middle - 1;
                    res = Math.min(res, broken + 1);
                } else {
                    low = middle + 1;
                    res = Math.min(res, unbroken + 1);
                }
            }
            // while (low + 1 < high) {
            // int middle = (low + high) / 2;
            // int lowVal = superEggDrop(K - 1, middle - 1);
            // int highVal = superEggDrop(K, N - middle);

            // if (lowVal < highVal)
            // low = middle;
            // else if (lowVal > highVal)
            // high = middle;
            // else
            // low = high = middle;
            // }
            // int minimum = 1 + Math.min(Math.max(superEggDrop(K - 1, low - 1),
            // superEggDrop(K, N - low)),
            // Math.max(superEggDrop(K - 1, high - 1), superEggDrop(K, N - high)));

            cache.put(key, res);

            return cache.get(key);
        }

    }

    // bottom up
    class DP {
        public int superEggDrop(int K, int N) {
            int[][] middleResults = new int[K + 1][N + 1];
            for (int i = 1; i <= N; i++) {
                middleResults[1][i] = i; // only one egg
                middleResults[0][i] = 0; // no egg
            }
            for (int i = 1; i <= K; i++) {
                middleResults[i][0] = 0; // zero floor
            }

            // start with 2
            for (int k = 2; k <= K; k++) {
                for (int i = 1; i <= N; i++) {
                    int min = Integer.MAX_VALUE;
                    for (int j = 1; j <= i; j++) {
                        min = Math.min(min, Math.max(middleResults[k - 1][j - 1], middleResults[k][i - j]) + 1);
                    }
                    middleResults[k][i] = min;
                }
            }
            return middleResults[K][N];
        }
    }

    class MemoRecursive {
        public int superEggDrop(int K, int N) {
            int[][] memo = new int[K + 1][N + 1];
            return recursive(K, N, memo);
        }

        // N 是层高不是起始楼层
        int recursive(int K, int N, int[][] memo) {
            // 只有一个鸡蛋的情况 不得不测试每层的楼次数 从1-N
            if (K == 1)
                return N;
            // 0层楼 不需要测试
            if (N == 0)
                return 0;
            // 1层楼 测试一次就好 要么鸡蛋随调 要么不碎
            if (N == 1)
                return 1;
            if (memo[K][N] != 0)
                return memo[K][N];
            int min = N;
            // 其他时候 在i层楼有两种可能性
            // i层楼 鸡蛋下去碎了 鸡蛋总数减一 k-1 楼层要应该减一 i-1
            // i层楼 鸡蛋下去没碎 鸡蛋总数不变 k 楼层要应该上一层 i+1 总楼层数 N - i
            for (int i = 1; i <= N; i++) {
                min = Math.min(min, Math.max(recursive(K - 1, i - 1, memo), recursive(K, N - i, memo)) + 1);
                memo[K][N] = min;
            }
            return min;
        }
    }

    // 思路是题目需要翻译简化成变成语言
    // K个鸡蛋 N 层楼
    // 子问题 就是 i个鸡蛋 j层楼 测试好与坏
    // dp[i][j]代表的就是最差情况下 测试鸡蛋的次数的问题
    class Recursive {
        public int superEggDrop(int K, int N) {
            return recursive(K, N);
        }

        // N 是层高不是起始楼层
        int recursive(int K, int N) {
            // 只有一个鸡蛋的情况 不得不测试每层的楼次数 从1-N
            if (K == 1)
                return N;
            // 0层楼 不需要测试
            if (N == 0)
                return 0;
            // 1层楼 测试一次就好 要么鸡蛋随调 要么不碎
            if (N == 1)
                return 1;
            int min = N;
            // 其他时候 在i层楼有两种可能性
            // i层楼 鸡蛋下去碎了 鸡蛋总数减一 k-1 楼层要应该减一 i-1
            // i层楼 鸡蛋下去没碎 鸡蛋总数不变 k 楼层要应该上一层 i+1 总楼层数 N - i
            for (int i = 1; i <= N; i++) {
                min = Math.min(min, Math.max(recursive(K - 1, i - 1), recursive(K, N - i)) + 1);
            }
            return min;
        }
    }
}
