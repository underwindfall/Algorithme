package leetcode.algo.greedy;

// https://leetcode-cn.com/problems/smallest-string-with-a-given-numeric-value/
public class GetSmallestString1663 {
    // time O(n)
    // space O(1)
    // 每次都假设右边的pos使用的char 是Z 是最大的，保证前面的char 最小
    public String getSmallestString(int n, int k) {
        // 1 .... 1
        // n- 1 k - (n - 1)
        char[] res = new char[n];
        for (int position = 0; position < n; position++) {
            int leftCharPos = (n - position - 1);
            if (k > leftCharPos * 26) {
                int add = k - (leftCharPos * 26);
                res[position] = (char) ('a' + add - 1);
                k -= add;
            } else {
                res[position] = 'a';
                k--;
            }
        }
        return new String(res);
    }
}
