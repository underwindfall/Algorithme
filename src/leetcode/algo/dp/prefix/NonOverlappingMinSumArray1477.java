package leetcode.algo.dp.prefix;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/
class NonOverlappingMinSumArray1477 {

    class SlidingWindoLikePrefix {
        public int minSumOfLengths(int[] arr, int target) {
            HashMap<Integer, Integer> hmap = new HashMap<>();
            int sum = 0, lsize = Integer.MAX_VALUE, result = Integer.MAX_VALUE;
            hmap.put(0, -1);
            for (int i = 0; i < arr.length; i++) {
                sum += arr[i];
                hmap.put(sum, i); // stores key as sum upto index i, and value as i.
            }
            sum = 0;
            for (int i = 0; i < arr.length; i++) {
                sum += arr[i];
                if (hmap.get(sum - target) != null) {
                    lsize = Math.min(lsize, i - hmap.get(sum - target)); // stores minimum length of sub-array ending
                                                                         // with index<= i with sum target. This ensures
                                                                         // non- overlapping property.
                }
                // hmap.get(sum+target) searches for any sub-array starting with index i+1 with
                // sum target.
                if (hmap.get(sum + target) != null && lsize < Integer.MAX_VALUE) {
                    result = Math.min(result, hmap.get(sum + target) - i + lsize); // updates the result only if both
                                                                                   // left and right sub-array exists.
                }
            }
            return result == Integer.MAX_VALUE ? -1 : result;
        }
    }

    // minLen[i] -> 代表以i作为index当前最短的子数组 的长度
    // subArray[s, e] -> 可以通过prefixSum找到s之前最短的数组的最小长度
    class SlidingWindwoPrefix {
        public int minSumOfLengths(int[] arr, int target) {
            int n = arr.length;
            int[] minLen = new int[n];
            Arrays.fill(minLen, Integer.MAX_VALUE);
            int ans = Integer.MAX_VALUE;

            // window 左边届
            int s = 0;
            int prefixSum = 0;
            int min_len = Integer.MAX_VALUE;

            for (int e = 0; e < n; e++) {
                prefixSum += arr[e];
                while (prefixSum > target)
                    prefixSum -= arr[s++];
                if (prefixSum == target) {
                    int curLen = e - s + 1;
                    // 左边之前找到了最小满足target的值 意味这已经存在一个数组满足sum == target
                    if (s > 0 && minLen[s - 1] != Integer.MAX_VALUE) {
                        ans = Math.min(ans, curLen + minLen[s - 1]);

                    }
                    // 第一个数组 or 最右边的满足数组的
                    min_len = Math.min(min_len, curLen);
                }
                minLen[e] = min_len;
            }
            return ans >= Integer.MAX_VALUE ? -1 : ans;
        }
    }

    // 数组的前缀和出发， 从左到右遍历一次，通过前缀和纪律下最小的长度 保存至left数组中
    // 从右到左遍历一次，通过前缀和纪律下最小的长度 保存至right数组中
    // 因为不能重复就需要算结果时候左右隔一个数
    class HashPrefix {
        public int minSumOfLengths(int[] arr, int target) {
            int[] left = new int[arr.length];
            int sum = 0, best = Integer.MAX_VALUE;
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, -1);
            for (int i = 0; i < arr.length; ++i) {
                sum += arr[i];
                if (map.containsKey(sum - target)) {
                    best = Math.min(best, i - map.get(sum - target));
                }
                left[i] = best;
                map.put(sum, i);
            }

            int[] right = new int[arr.length];
            sum = 0;
            best = Integer.MAX_VALUE;
            map = new HashMap<>();
            map.put(0, arr.length);
            for (int i = arr.length - 1; i >= 0; --i) {
                sum += arr[i];
                if (map.containsKey(sum - target)) {
                    best = Math.min(best, map.get(sum - target) - i);
                }
                right[i] = best;
                map.put(sum, i);
            }

            int result = Integer.MAX_VALUE;
            for (int i = 1; i < arr.length; ++i) {
                if (left[i - 1] != Integer.MAX_VALUE && right[i] != Integer.MAX_VALUE) {
                    result = Math.min(result, left[i - 1] + right[i]);
                }
            }
            return result == Integer.MAX_VALUE ? -1 : result;
        }
    }

}