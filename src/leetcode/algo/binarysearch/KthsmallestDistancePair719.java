package leetcode.algo.binarysearch;

import java.util.Arrays;

// https://leetcode-cn.com/problems/find-k-th-smallest-pair-distance/
public class KthsmallestDistancePair719 {
    // 桶排序
    // 计算数组里面的每个相同的差，用index代表差值，value代表frequency
    // 然后从头开始查找，如果不是k，那就想k递减这个桶的值
    class BucketSort {
        public int smallestDistancePair(int[] nums, int k) {
            int n = nums.length, N = 1_000_000;
            int[] bucket = new int[N]; // distance range is [0, 1_000_000)
            // for all pairs, put diff in corresponding bucket
            for (int i = 0; i < n; ++i) {
                for (int j = i + 1; j < n; ++j) {
                    bucket[Math.abs(nums[i] - nums[j])]++;
                }
            }
            // find 1st bucket with cnt >= k, return it's index (the dist)
            for (int i = 0; i < N; i++) {
                if (bucket[i] < k)
                    k -= bucket[i];
                else
                    return i;
            }
            return -1;
        }
    }

    // 二分法
    // low high 
    // 
    class Binarysearch {
        public int smallestDistancePair(int[] nums, int k) {
            Arrays.sort(nums);
            int len = nums.length;
            int low = 0;
            int high = nums[len - 1] - nums[0];

            while (low < high) {
                int mid = low + (high - low) / 2;
                int count = 0;
                int left = 0;
                for (int right = 0; right < len; right++) {
                    while (nums[right] - nums[left] > mid)
                        left++;
                    count += right - left;
                }

                if (count >= k) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            return low;
        }
    }
}
