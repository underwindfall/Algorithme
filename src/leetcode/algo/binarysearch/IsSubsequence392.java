package leetcode.algo.binarysearch;

import java.util.List;
import java.util.ArrayList;

// https://leetcode-cn.com/problems/is-subsequence/
public class IsSubsequence392 {

    class DP {
        public boolean isSubsequence(String s, String t) {
            int[][] dp = new int[s.length() + 1][t.length() + 1];

            for (int i = 0; i < dp[0].length; i++)
                dp[0][i] = 1;

            for (int i = 1; i <= s.length(); ++i) {
                for (int j = 1; j <= t.length(); ++j) {
                    if (s.charAt(i - 1) == t.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else
                        dp[i][j] = dp[i][j - 1];
                }
            }

            return dp[s.length()][t.length()] == 1;
        }
    }

    // 思路双指针比较两个字符串的，
    // 如果s字符串的index等于i遍历的值那就是说是子集
    class DoubleIndex {
        public boolean isSubsequence(String s, String t) {
            // 双指针解法
            int i = 0, j = 0;
            while (i < s.length() && j < t.length()) {
                char si = s.charAt(i);
                char tj = t.charAt(j);
                if (si == tj) {
                    i++;
                }
                j++;
            }
            return i == s.length();
        }
    }
    @SuppressWarnings("unchecked")
    class BinarySearch {
        // ndex保留t每个字符出现的index
        // 后遍历s
        public boolean isSubsequence(String s, String t) {
            List<Integer>[] index = new ArrayList[26];
            int m = s.length(), n = t.length();
            for (int i = 0; i < n; i++) {
                char c = t.charAt(i);
                if (index[c] == null) {
                    index[c] = new ArrayList<>();
                }
                index[c].add(i);
            }
            int j = 0;
            for (int i = 0; i < m; i++) {
                char c = s.charAt(i);
                // 找不到s上存在的这个字符，在t上 那就false
                if (index[c] == null)
                    return false;
                int pos = left_bound(index[c], j);
                // pos返回的是当前j，在list中所能找到的含有数量
                if (pos == index[c].size())
                    return false;
                j = index[c].get(pos) + 1;
            }
            return true;
        }

        // 返回的是剩余的大小
        int left_bound(List<Integer> arr, int tar) {
            int left = 0, right = arr.size() - 1;
            while (left <= right) {
                int mid = (right - left) / 2 + left;
                if (arr.get(mid) < tar) {
                    left = mid + 1;
                } else if (arr.get(mid) > tar) {
                    right = mid - 1;
                } else {
                    right = mid - 1;
                }
            }
            return left;
        }
    }
}
