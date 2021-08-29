package leetcode.algo.divideandconquer;

import java.util.Arrays;

//https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters/
public class LongestSubstring395 {
    // 子字符串
    class Divideandconquer {
        public int longestSubstring(String s, int k) {
            return dfs(s, k);
        }

        private int dfs(String s, int k) {
            // 统计每个字符出现的次数
            int[] charCount = new int[26];
            for (int i = 0; i < s.length(); i++) {
                charCount[s.charAt(i) - 'a']++;
            }

            // 统计<k的字符
            String split = "";
            for (int i = 0; i < charCount.length; i++) {
                int count = charCount[i];
                if (count > 0 && count < k) {
                    split = String.valueOf((char) (i + 'a'));
                    break;
                }
            }

            if (split.equals("")) {
                // 全部都>k
                return s.length();
            }

            final String[] split1 = s.split(split);
            int max = 0;
            for (final String s1 : split1) {
                final int dfs = dfs(s1, k);
                max = Math.max(max, dfs);
            }

            return max;
        }
    }

    // time O(n)
    // space O(n)
    class DoubeIndex {
        public int longestSubstring(String s, int k) {
            int ans = 0;
            int n = s.length();
            char[] cs = s.toCharArray();
            int[] cnt = new int[26];
            for (int p = 1; p <= 26; p++) {
                Arrays.fill(cnt, 0);
                // tot 代表 [j, i] 区间所有的字符种类数量；sum 代表满足「出现次数不少于 k」的字符种类数量
                for (int i = 0, j = 0, tot = 0, sum = 0; i < n; i++) {
                    int u = cs[i] - 'a';
                    cnt[u]++;
                    // 如果添加到 cnt 之后为 1，说明字符总数 +1
                    if (cnt[u] == 1)
                        tot++;
                    // 如果添加到 cnt 之后等于 k，说明该字符从不达标变为达标，达标数量 + 1
                    if (cnt[u] == k)
                        sum++;
                    // 当区间所包含的字符种类数量 tot 超过了当前限定的数量 p，那么我们要删除掉一些字母，即「左指针」右移
                    while (tot > p) {
                        int t = cs[j++] - 'a';
                        cnt[t]--;
                        // 如果添加到 cnt 之后为 0，说明字符总数-1
                        if (cnt[t] == 0)
                            tot--;
                        // 如果添加到 cnt 之后等于 k - 1，说明该字符从达标变为不达标，达标数量 - 1
                        if (cnt[t] == k - 1)
                            sum--;
                    }
                    // 当所有字符都符合要求，更新答案
                    if (tot == sum)
                        ans = Math.max(ans, i - j + 1);
                }
            }
            return ans;
        }

    }
}
