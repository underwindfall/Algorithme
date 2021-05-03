package leetcode.algo.string;

//https://leetcode-cn.com/problems/repeated-substring-pattern/
public class RepeatedString459 {
    // 1 假设存在一个string 是 s的子串 并且重复
    // s.length % string.length == 0
    // s.charAt(i) = s.charAt(i - string.length)
    class Loop {
        public boolean repeatedSubstringPattern(String s) {
            int n = s.length();
            for (int i = 1; i * 2 <= n; i++) {
                if (n % i == 0) {
                    boolean match = true;
                    for (int j = i; j < n; j++) {
                        if (s.charAt(j) != s.charAt(j - i)) {
                            match = false;
                            break;
                        }
                    }
                    if (match) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    // string s 存在重复字符串的话 意味移位可以是的他移位后的字符串和现在的字符串相同 就意味着存在重复
    class Move {
        public boolean repeatedSubstringPattern(String s) {
            if (s == null || s.length() < 1)
                return false;
            int len = s.length();
            String str = s;
            while (len > 1) {
                str = str.charAt(s.length() - 1) + str.substring(0, s.length() - 1);
                if (str.equals(s))
                    return true;
                len--;
            }
            return false;
        }
    }

    class Solution {
        public boolean repeatedSubstringPattern(String s) {
            String str = s + s;
            return str.substring(1, str.length() - 1).contains(s);
        }
    }

    // 作者：13217319563
    // 链接：https://leetcode-cn.com/problems/repeated-substring-pattern/solution/jian-dan-ming-liao-guan-yu-javaliang-xing-dai-ma-s/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

}
