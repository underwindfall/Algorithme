package leetcode.algo.dp.twodimension;

// https://leetcode-cn.com/problems/super-egg-drop
public class SuperEggDrop887 {

   

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
