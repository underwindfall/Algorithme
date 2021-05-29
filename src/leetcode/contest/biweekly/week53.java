package leetcode.contest.biweekly;

import java.util.Arrays;
import java.util.TreeSet;

// https://leetcode-cn.com/contest/biweekly-contest-53
public class week53 {

    // time O(N)
    // espace O(N)
    // https://leetcode-cn.com/problems/substrings-of-size-three-with-distinct-characters/
    class LC_5754 {
        public int countGoodSubstrings(String s) {
            int res = 0;
            int n = s.length();
            for (int i = 0; i < n - 2; ++i) {
                if (s.charAt(i) != s.charAt(i + 1) && s.charAt(i) != s.charAt(i + 2)
                        && s.charAt(i + 1) != s.charAt(i + 2)) {
                    ++res;
                }
            }
            return res;
        }
    }

    // time O(N*logN)
    // espace O(1)
    // https://leetcode-cn.com/problems/minimize-maximum-pair-sum-in-array/
    class LC_5755 {
        public int minPairSum(int[] nums) {
            Arrays.sort(nums);
            int sum = Integer.MIN_VALUE;

            for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
                sum = Math.max(nums[i] + nums[j], sum);
            }
            return sum;
        }
    }

    // https://leetcode-cn.com/problems/get-biggest-three-rhombus-sums-in-a-grid/
    // 暴力解法 比赛的时候卡住了 一度怀疑
    // time O(MN*min(M*N)*min(M*N))
    // espace O(1)
    class LC_5757 {
        public int[] getBiggestThree(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            TreeSet<Integer> set = new TreeSet<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    set.add(grid[i][j]);// 先把中心点添加到set里面
                    int step = 1;
                    while (i - step >= 0 && j - step >= 0 && i + step < m && j + step < n) {
                        int sub = 0;
                        for (int x = 0; x < step; x++) {
                            sub += grid[i + x][j + step - x] + grid[i - x][j - step + x] + grid[i + step - x][j - x]
                                    + grid[i - step + x][j + x];
                        }
                        set.add(sub);
                        step++;
                    }
                }
            }
            int len = set.size();
            if (len <= 3) {
                int[] result = new int[len];
                int loc = len - 1;
                for (int each : set) {
                    result[loc--] = each;
                }
                return result;
            } else {
                int[] result = new int[3];
                Object[] mid = set.toArray();
                result[2] = (int) mid[mid.length - 3];
                result[1] = (int) mid[mid.length - 2];
                result[0] = (int) mid[mid.length - 1];
                return result;
            }
        }
    }

    // https://leetcode-cn.com/problems/minimum-xor-sum-of-two-arrays/
    // dp 思路
    // dp(i)当前nums2 的数组中元素使用状况对于给定的state，
    // 我们可以选择一个尚未使用的元素
    // （设其为nums2的第k个元素，则需要满足state & 2^k=0）与nums1的当前元素配对，从而进行
    // time O(2^N*2^N)
    // espace O(2^N)
    class LC_5756 {
        public int minimumXORSum(int[] nums1, int[] nums2) {
            int n = nums1.length, range = 1 << n;
            int[] dp = new int[range];// 若i的二进制表示中1的个数为num, 1的位置为k1,k2,...,knum,
                                      // dp[i]表示nums1的前num个数和nums2第k1,k2,...,knum个数的最小异或值之和
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;
            for (int i = 0; i < range; i++) {
                for (int j = 0; j < n; j++) {// 遍历i的各个二进制位
                    if (((i >> j) & 1) == 1)// i的第j位为1
                        dp[i] = Math.min(dp[i], dp[i ^ (1 << j)] + (nums1[Integer.bitCount(i) - 1] ^ nums2[j]));// 尝试选取nums1[num]和nums2[j]进行异或更新dp[i]
                }
            }
            return dp[range - 1];// 答案为dp[111...1](n个1)
        }
    }
}
