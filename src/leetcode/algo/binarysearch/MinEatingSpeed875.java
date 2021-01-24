package leetcode.algo.binarysearch;

// https://leetcode-cn.com/problems/koko-eating-bananas/
public class MinEatingSpeed875 {
    public int minEatingSpeed(int[] piles, int H) {
        int left = 1;
        // piles 数组的最大值
        int right = getMax(piles);
        while (left <= right) {
            // 防止溢出
            int mid = left + (right - left) / 2;
            if (canFinish(piles, mid, H)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    boolean canFinish(int[] piles, int speed, int H) {
        int time = 0;
        for (int n : piles) {
            time += timeOf(n, speed);
        }
        return time <= H;
    }

    int timeOf(int n, int speed) {
        return (n / speed) + ((n % speed > 0) ? 1 : 0);
    }

    int getMax(int[] piles) {
        int max = 0;
        for (int n : piles)
            max = Math.max(n, max);
        return max;
    }
}
