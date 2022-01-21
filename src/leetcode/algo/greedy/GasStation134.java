package leetcode.algo.greedy;

//https://leetcode.com/problems/gas-station/
public class GasStation134 {
    // greedy
    // time O(1)
    // space O(1)
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int total_surplus = 0;
        int surplus = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            total_surplus += gas[i] - cost[i];
            surplus += gas[i] - cost[i];
            if (surplus < 0) {
                surplus = 0;
                start = i + 1;
            }
        }
        return (total_surplus < 0) ? -1 : start;
    }

    // time o(n ^ 2)
    // space O(1)
    class BruteForce {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int n = gas.length;
            for (int i = 0; i < n; i++) {
                int totalFuel = 0;
                int stopCount = 0, j = i;
                while (stopCount < n) {
                    totalFuel += gas[j % n] - cost[j % n];
                    if (totalFuel < 0)
                        break;// whenever we reach -ve
                    stopCount++;
                    j++;
                }
                if (stopCount == n && totalFuel >= 0)
                    return i;// cover all the stops & our fuel left is 0 or more than that
            }
            return -1;
        }
    }
}
