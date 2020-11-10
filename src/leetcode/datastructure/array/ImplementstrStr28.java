package leetcode.datastructure.array;

// https://leetcode-cn.com/problems/implement-strstr/
public class ImplementstrStr28 {

    class OneByOne {

        // time: O((L-N)*N)
        // espace: O(1)
        public int strStr(String haystack, String needle) {
            int L = haystack.length() - 1;
            int N = needle.length() - 1;
            if (needle == null || needle.length() == 0) {
                return 0;
            }
            for (int i = 0; i < L - N + 1; i++) {
                if (haystack.substring(i, i + N + 1).equals(needle)) {
                    return i;
                }
            }
            return -1;
        }
    }

    //time O(N*(N-L)) O(N)
    //espace:O(1)
    class DoubleIndex {
        public int strStr(String haystack, String needle) {
            int L = haystack.length();
            int N = needle.length();
            if (N == 0) {
                return 0;
            }
            int pl = 0;
            while (pl < L - N + 1) {
                // 找出第一个匹配字符
                while (pl < L - N + 1 && haystack.charAt(pl) != needle.charAt(0)) {
                    pl++;
                }
                int currentLen = 0;
                int pn = 0;
                // 开始匹配needle找到能够找到的最大匹配值
                //pn 向后移动
                //pl 向后移动
                while (pn < N && pl < L && haystack.charAt(pl) == needle.charAt(pn)) {
                    pn++;
                    pl++;
                    currentLen++;
                }
                // 如果长度是N 意味着匹配结束
                if (currentLen == N) {
                    return pl - N;
                }
                pl = pl - currentLen + 1;
            }
            return -1;
        }
    }
}
