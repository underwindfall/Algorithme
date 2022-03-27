package leetcode.algo.greedy;

import java.util.Arrays;

// https://leetcode-cn.com/problems/two-city-scheduling/
public class TwoCitSchedCost1029 {
    // A,B只有两种选择机会
    // 所以选择A的论点在于 A-B 的diff这个值尽可能的大 也就以为此时选择A是最好的，不然就只能选择B了
    // [[10,20],[30,200],[400,50],[30,20]]
    // diff -10, -170, 350, 10
    // sorted diff -170, -10, 10, 350
    // ------------ A ----A ---B----B
    // 10 + 30 + 50 + 20 ==110
    public int twoCitySchedCost(int[][] costs) {
        int ans = 0;
        int n = costs.length / 2;
        // greedy?
        Arrays.sort(costs, (a, b) -> (a[0] - b[0]) - (a[1] - b[1]));

        for (int i = 0; i < n; i++) {
            ans += costs[i][0];
        }

        for (int i = n; i < costs.length; i++) {
            ans += costs[i][1];
        }

        return ans;
    }
}
