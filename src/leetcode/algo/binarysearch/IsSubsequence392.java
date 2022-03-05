package leetcode.algo.binarysearch;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

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

    // O(k * s * logT)
    // follow up
    class BinarySearch {
        public boolean isSubsequence(String s, String t) {
            // t index
            Map<Character, List<Integer>> map = new HashMap<>();
            for (int i = 0; i < t.length(); i++) {
                char c = t.charAt(i);
                if (!map.containsKey(c)) {
                    map.put(c, new ArrayList<>());
                }
                map.get(c).add(i);
            }

            // O(k * s * logT)
            // aaabbcc
            // a 0, 1,2
            // b 3,4
            // c 5,6
            // s1, s2 //aabc
            // for s
            // index
            int prev = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (map.get(c) == null) {
                    return false;
                } else {
                    List<Integer> list = map.get(c); // a 0,1,2 // sort order
                    // binary search(a, b,c) // leftmost
                    // a,b,c
                    // a = list.get(index)
                    // prev = list.get(index) + 1;
                    int index = binarySearch(prev, list, 0, list.size() - 1);
                    if (list.get(index) < prev) {
                        return false;
                    } else {
                        prev = list.get(index) + 1;
                    }
                }
            }
            return true;
        }

        // O(logT)
        // leftmost
        int binarySearch(int index, List<Integer> list, int left, int right) {
            while (left < right) {
                // lower bound
                int mid = (right - left) / 2 + left;
                if (list.get(mid) < index) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            return left;
        }
    }

    /**
     * KMP思想
     * 类似于用伪链表（基于idx索引）把相同的字符给链接起来
     * 举例：abac
     * 1、算法实现过程如下：
     * 1.1 填充字符 " " => ' abac'
     * 1.2 对其中的字符a链表而言（a-z每个字符都执行一次下述操作,共26次）
     * dp[3]['a'-'a'] => dp[3][0] = -1
     * 记录a最近的一次位置为，nexPos = 3
     * dp[1]['a'-'a'] => dp[1][0] = 3
     * 记录a最近的一次位置为，nexPos = 1
     * dp[0][0] = 1 (预处理填充的空字符意义所在，否则初始位置的a就找不到了)
     *
     * 2、查找子串过程（）
     * 2.1 初始索引为0,遍历待查找子串
     * 2.2 查找 aa 的过程如下
     * idx = 0 （从idx+1以及之后的位置开始查找）
     * idx = dp[0][c-'a'] => idx = dp[0][0] => idx = 1
     * idx = dp[idx][c-'a'] => dp[1][0] = 3
     * 此时 aa 已经遍历完，返回true
     * 上述过程，只要idx = -1,表示找不到字符，则返回false
     */
    class FollowUp {
        public boolean isSubsequence(String s, String t) {
            t = " " + t; // 预处理，保证t[0] 也被正确表示，即dp[0][..]
            int[][] dp = new int[t.length()][26];

            for (int ch = 0; ch < 26; ++ch) {// 每一轮处理一个字符
                int nexPos = -1;
                for (int i = t.length() - 1; i >= 0; --i) {
                    dp[i][ch] = nexPos;
                    if (t.charAt(i) == ch + 'a')
                        nexPos = i;
                }
            }
            // 起始位置是空字符（idx = 0）
            // dp[0][p]表示从0（不包括）下一个位置开始查找p+'a'在t中的位置
            int idx = 0;
            for (char c : s.toCharArray()) {
                idx = dp[idx][c - 'a'];
                if (idx == -1)
                    return false;
            }
            return true;
        }
    }
}
