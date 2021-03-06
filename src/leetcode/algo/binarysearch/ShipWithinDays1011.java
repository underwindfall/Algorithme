package leetcode.algo.binarysearch;

// https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days/
public class ShipWithinDays1011 {
    public int shipWithinDays(int[] weights, int D) {
        // 载重可能的最小值
        int left = getMax(weights);
        // 载重可能的最大值 + 1
        int right = getSum(weights) + 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canFinish(weights, D, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


    // 如果载重为 cap，是否能在 D 天内运完货物？
    boolean canFinish(int[] w, int D, int cap) {
        // int i = 0;
        // for (int day = 0; day < D; day++) {
        //     int maxCap = cap;
        //     while ((maxCap -= w[i]) >= 0) {
        //         i++;
        //         if (i == w.length)
        //             return true;
        //     }
        // }
        // return false;
        int i = 1;
        int curIntervalSum = 0;
        for (int num : w) {
            // 尝试加上当前遍历的这个数，如果加上去超过了「子数组各自的和的最大值」，就不加这个数，另起炉灶
            if (curIntervalSum + num > cap) {
                curIntervalSum = 0;
                i++;
            }
            curIntervalSum += num;
        }
        return i <= D;
    }

    int getMax(int[] weights) {
        int max = 0;
        for (int w : weights) {
            max = Math.max(max, w);
        }
        return max;
    }

    int getSum(int[] weights) {
        int sum = 0;
        for (int s : weights) {
            sum += s;
        }
        return sum;
    }
}
