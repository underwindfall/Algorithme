package leetcode.algo.dp.doublesequence.pattern;

// https://leetcode-cn.com/problems/regular-expression-matching/
public class Regex10 {
    class Solution {
        public boolean isMatch(String s, String p) {
            int m = s.length();
            int n = p.length();

            boolean[][] f = new boolean[m + 1][n + 1];
            f[0][0] = true;
            for (int i = 0; i <= m; ++i) {
                for (int j = 1; j <= n; ++j) {
                    //当前字符串是*，可以将 字符+*全部忽略掉，取f[i][j-2]的值
                    //或者看模式串P的上一个字符串是否能跟字符串S匹配
                    //如果能匹配上，可以忽略掉模式串的 字符+*，也可以忽略掉字符串S中的当前字符
                    //这里其实跟递归一样，也有两条转移路径
                    if (p.charAt(j - 1) == '*') {
                        if (matches(s, p, i, j - 1)) {
                            f[i][j] = f[i][j - 2] || f[i - 1][j];
                        } else {
                            f[i][j] = f[i][j - 2];
                        }
                    } else {
                        // 单个字符匹配的情况
                        if (matches(s, p, i, j)) {
                            f[i][j] = f[i - 1][j - 1];
                        }
                    }
                }
            }
            return f[m][n];
        }

        public boolean matches(String s, String p, int i, int j) {
            if (i == 0) {
                return false;
            }
            if (p.charAt(j - 1) == '.') {
                return true;
            }
            return s.charAt(i - 1) == p.charAt(j - 1);
        }
    }

    /**
     * s = "aab"
     * p = "c*a*b"  
     *       | ---> 可以把c干掉
     * 
     * s = "aab"
     * p = "a*a*b"  
     *       | ---> 可以把a干掉
     * 
     * s= "aab"
     * p = ".*" ---- 可以匹配任何东西
     */
    // time O(n)
    // space O(n)
    class Recursive {
        public boolean isMatch(String s, String p) {
            if (p.isEmpty()) {
                return s.isEmpty();
            }
            boolean isFirstMatch = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
            // s = "aab"
            // p = "c*a*b"  
            //       | ---> 可以把c干掉
            if (p.length() >= 2 && p.charAt(1) == '*') {
                                        // * matches one or more     //* matches zero character
                return isFirstMatch && isMatch(s.substring(1), p) || isMatch(s, p.substring(2));
            } else {
                return isFirstMatch && isMatch(s.substring(1), p.substring(1));
            }

        }
    }
}
