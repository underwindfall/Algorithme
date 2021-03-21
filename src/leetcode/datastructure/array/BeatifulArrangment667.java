package leetcode.datastructure.array;

import java.util.ArrayList;

// 数学
// https://leetcode-cn.com/problems/beautiful-arrangement-ii/
public class BeatifulArrangment667 {
    public int[] constructArray(int n, int k) {
        int[] ret = new int[n];
        ret[0] = 1;
        for (int i = 1, interval = k; i <= k; i++, interval--) {
            if (i % 2 == 1)
                ret[i] = ret[i - 1] + interval;
            else
                ret[i] = ret[i - 1] - interval;
        }
        for (int i = k + 1; i < n; i++) {
            ret[i] = i + 1;
        }
        return ret;
    }

    // https://leetcode-cn.com/problems/beautiful-arrangement-ii/solution/fan-jiu-wan-shi-liao-he-he-by-ri-mu-tu-yuan-12/
    // time O(N)
    // espace O(N)
    class Pattern {
        public int[] constructArray(int n, int k) {
            if (k >= n)
                return null;
            int[] arr = new int[n];
            int i = 0, small = 1, large = n;
            while (i < k) {
                arr[i++] = small++;
                if (i < k) {
                    arr[i++] = large--;
                }
            }
            if (k % 2 == 0) {
                while (i < arr.length) {
                    arr[i++] = large--;
                }
            } else {
                while (i < arr.length) {
                    arr[i++] = small++;
                }
            }
            return arr;
        }
    }

    // time O(N^2)
    // espace O(N)
    class ReverseSolution {
        public int[] constructArray(int n, int k) {
            int[] res = new int[n];
            for (int i = 0; i < n; i++) {
                res[i] = i + 1;
            }

            if (k == 1) {
                return res;
            }

            for (int m = 1; m < k; m++) {
                reverse(res, m, n - 1);
            }

            return res;
        }

        void reverse(int[] res, int i, int j) {
            while (i < j) {
                int t = res[i];
                res[i] = res[j];
                res[j] = t;
                i++;
                j--;
            }
        }
    }

    class RecursiveSolution {
        private ArrayList<ArrayList<Integer>> permutations(int[] nums) {
            ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
            permute(ans, nums, 0);
            return ans;
        }

        private void permute(ArrayList<ArrayList<Integer>> ans, int[] nums, int start) {
            if (start >= nums.length) {
                ArrayList<Integer> cur = new ArrayList<Integer>();
                for (int x : nums)
                    cur.add(x);
                ans.add(cur);
            } else {
                for (int i = start; i < nums.length; i++) {
                    swap(nums, start, i);
                    permute(ans, nums, start + 1);
                    swap(nums, start, i);
                }
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        private int numUniqueDiffs(ArrayList<Integer> arr) {
            boolean[] seen = new boolean[arr.size()];
            int ans = 0;

            for (int i = 0; i < arr.size() - 1; i++) {
                int delta = Math.abs(arr.get(i) - arr.get(i + 1));
                if (!seen[delta]) {
                    ans++;
                    seen[delta] = true;
                }
            }
            return ans;
        }

        public int[] constructArray(int n, int k) {
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = i + 1;
            }
            for (ArrayList<Integer> cand : permutations(nums)) {
                if (numUniqueDiffs(cand) == k) {
                    int[] ans = new int[n];
                    int i = 0;
                    for (int x : cand)
                        ans[i++] = x;
                    return ans;
                }
            }
            return null;
        }
    }
}
