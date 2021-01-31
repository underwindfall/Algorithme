package leetcode.algo.dp.subsquence;

// https://leetcode-cn.com/problems/race-car/
public class RaceCar818 {
    // 思路
    // 题目理解 车子可以正向走可以反向走
    // 车子的速度 要么是 1,2,4,-1/ -1, -2, -4,
    // 可以判断出任意一个点在公路上跑都会有几种情况
    // 要么当前点 正好等于目的地 那正好前进的指令就是要求的距离
    // 当前点是大于目的地 那就意味者之后要进行反方向后退 子问题就是
    // 当前点是小于目的地
    // 第一种是刚好走forword步后到达了目标位置i，则dp[i] = forword。
    // 第二种情况是向前走forword步后到达了位置i后面，这时需要再往回走，再加上回头的那一步，此时dp[i] = Math.min(dp[i],
    // forword + 1 + dp[j - i]);（注意这里的上限是走到2 * i处）
    // 第三种情况是向前走forword步后未到达位置i处就需要返回，此时在保证返回的步数back < forword的条件下遍历back，此时dp[i] =
    // Math.min(dp[i], forword + 1 + back + 1 + dp[i - j + k])。
    // 得到转移方程后递归i，最后dp[target]即为所求值。

    class DP {
        public int racecar(int target) {
            int[] dp = new int[target + 1];
            for (int i = 1; i <= target; i++) {
                dp[i] = Integer.MAX_VALUE;
                // 这里的限制条件很有趣 因为可以想像的是如果当前你一直前进，最远能到的是i，因为否则之后你要返回的时候是同样的路程
                for (int forward = 1; Math.pow(2, forward) - 1 < 2 * i; forward++) {
                    // 向前走了forwardDistance
                    int forwardDistance = (int) Math.pow(2, forward) - 1;
                    // 对应第一种情况，走了forward步直接到达i
                    if (forwardDistance == i) {
                        dp[i] = forward;
                    } else if (forwardDistance > i) {
                        // 对应第二种情况，越过了i
                        // +1 是因为回头需要一个R指令
                        dp[i] = Math.min(dp[i], forward + 1 + dp[forwardDistance - i]);
                    } else {
                        for (int back = 0; back < forward; back++) {
                            // 第一个+1是还没到达i，先回头，使用一个R
                            // 第二个+1是回头走了backwardDistance，再使用R回头走向i
                            int backDistance = (int) Math.pow(2, back) - 1;
                            dp[i] = Math.min(dp[i], forward + 1 + back + 1 + dp[i - (forwardDistance - backDistance)]);
                        }
                    }
                }
            }
            return dp[target];
        }
    }
}
