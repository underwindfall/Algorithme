package leetcode.algo.binarysearch;

//https://leetcode-cn.com/problems/h-index-ii/
public class HIndexII275 {

    // time O(logN)
    // espace O(1)
    class BinarySearchImprove {
        public int hIndex(int[] citations) {
            int n = citations.length;
            int l = 0, r = n - 1;
            while (l < r) {
                int mid = (r + l) / 2;
                if (citations[mid] >= n- mid) {
                    r = mid;
                } else {
                    l = mid  +1;
                }
            }
            return citations[r] >= n -r ? n - r : 0;
        }
    }

    // 没有利用 顺序排列
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
