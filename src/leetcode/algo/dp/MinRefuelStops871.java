package leetcode.algo.dp;

// https://leetcode-cn.com/problems/minimum-number-of-refueling-stops/
public class MinRefuelStops871 {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        if (startFuel >= target)
            return 0;
        // station passe -> either add fuel/ not add fuel
        // last station without gasline target
        // func(min) = min (station[0], min(station[n - 1]),......) <==> func(min) =
        // min(station[i], func(min - total -i))
        // dp[i][j] represent we pass i station and add j times fuel the car will the
        // distance (dp[i][j - 1] >= stations[i][0])
        // 1. dp[i] [j - 1] <==> dp[i][j - 1] = dp[i][j]
        // 2. dp[i][j - 1] + stations[i][1] = dp[i][j]
        // number of stations
        int n = stations.length;
        int[][] dp = new int[n + 1][n + 1];
        // initial value
        for (int i = 0; i <= n; i++) {
            dp[i][0] = startFuel;
        }

        for (int i = 1; i <= n; i++) {
            // j add j times fuel it'll never pass away i
            for (int j = 1; j <= i; j++) {
                // i站不加油
                if (dp[i - 1][j] >= stations[i - 1][0]) {
                    dp[i][j] = dp[i - 1][j];
                } 
                if (dp[i - 1][j - 1] >= stations[i - 1][0]) {
                    dp[i][j] = Math.max( dp[i - 1][j - 1] + stations[i - 1][1], dp[i][j]);
                }
            }
        }

        // condition
        for (int i = 0; i <= n; i++) {
            if (dp[n][i] >= target)
                return i;
        }
        return -1;
    }
}
