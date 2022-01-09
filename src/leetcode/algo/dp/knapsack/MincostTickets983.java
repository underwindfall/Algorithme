package leetcode.algo.dp.knapsack;

// https://leetcode-cn.com/problems/minimum-cost-for-tickets/
public class MincostTickets983 {

    // time O(maxDay - minDay)
    // space O(maxDay)
    // https://leetcode-cn.com/problems/minimum-cost-for-tickets/solution/java-dong-tai-gui-hua-si-lu-bu-zou-cong-hou-xiang-/
    class BottomUp {
        public int mincostTickets(int[] days, int[] costs) {
            int len = days.length, maxDay = days[len - 1], minDay = days[0];
            int[] dp = new int[maxDay + 31]; // 多扩几天，省得判断 365 的限制
            // 只需看 maxDay -> minDay，此区间外都不需要出门，不会增加费用
            for (int d = maxDay, i = len - 1; d >= minDay; d--) {
                // i 表示 days 的索引
                // 也可提前将所有 days 放入 Set，再通过 set.contains() 判断
                if (d == days[i]) {
                    dp[d] = Math.min(dp[d + 1] + costs[0], dp[d + 7] + costs[1]);
                    dp[d] = Math.min(dp[d], dp[d + 30] + costs[2]);
                    i--; // 别忘了递减一天
                } else
                    dp[d] = dp[d + 1]; // 不需要出门
            }
            return dp[minDay]; // 从后向前遍历，返回最前的 minDay
        }

    }
    // 1.单独买票
    // 2.买一张以当前为结束时候的为期七天的票
    // 3.买一张以当前为结束时候的为期三十天的票
    // dp[i] =Math.min(Math.min(dp[i-1>=0?i-1:0]+costs[0],dp[i-7>=0?i-7:0]+costs[1]),dp[i-30>=0?i-30:0]+costs[2]);
    

    // time O(maxDay - minDay)
    // space O(maxDay)
    // https://leetcode-cn.com/problems/minimum-cost-for-tickets/solution/tong-su-yi-dong-by-shan-ding-dong-ren-e-437j/
    class TopDown {
        public int mincostTickets(int[] days, int[] costs) {
            int[] dp = new int[days[days.length - 1] + 1];
            dp[0] = 0;
            for (int i = 0; i < days.length; i++) {
                dp[days[i]] = -1;
            }
            for (int i = 1; i <= days[days.length - 1]; i++) {
                if (dp[i] == -1) {
                    dp[i] = Math.min(
                            Math.min(dp[i - 1 >= 0 ? i - 1 : 0] + costs[0], dp[i - 7 >= 0 ? i - 7 : 0] + costs[1]),
                            dp[i - 30 >= 0 ? i - 30 : 0] + costs[2]);
                } else {
                    dp[i] = dp[i - 1];
                }
            }
            return dp[dp.length - 1];
        }
    }

}
