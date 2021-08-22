package leetcode.algo.binarysearch;

import java.util.Arrays;

//https://leetcode-cn.com/problems/h-index/
public class HIndex274 {
    // time O(n * logN)
    // space O(logN)
    class Sort {
        public int hIndex(int[] citations) {
            Arrays.sort(citations);
            int h = 0, i = citations.length - 1;
            while (i >= 0 && citations[i] > h) {
                h++;
                i--;
            }
            return h;
        }
    }

    // time O(N*logN)
    // espace O(1)
    // 那么在以最大值 xx 为分割点的正整数数轴上，满足二段性：
    // - 少于等于 xx 的数值必然满足条件；
    // - 大于 xx 的数值必然不满足。
    class BinarySearch {
        public int hIndex(int[] citations) {
            int n = citations.length;
            int l = 0, r = n;
            while (l < r) {
                int mid = (r + l + 1) / 2;
                if (check(citations, mid)) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            return r;
        }

        boolean check(int[] nums, int target) {
            int ans = 0;
            for (int i : nums) {
                if (i >= target)
                    ans++;
            }
            return ans >= target;
        }
    }
}
