
package leetcode.algo.prefixsum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode-cn.com/problems/can-make-palindrome-from-substring/
public class CanMakePaliQueries1177 {
    // time O(n*26)
    // space O(n*26)
    // prefix sum 计算(0, i) 中字符出现的次数从而迭代+1(0, i+1)字符出现的次数
    // 偶数的能凑成回文，奇数不能
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        int[][] prefixCount = new int[s.length() + 1][26];
        for (int i = 0; i < s.length(); i++) {
            prefixCount[i + 1] = Arrays.copyOf(prefixCount[i], 26);
            ++prefixCount[i + 1][s.charAt(i) - 'a'];
        }
        List<Boolean> res = new ArrayList<>();
        for (int[] q : queries) {
            int sum = 0;
            for (int i = 0; i < 26; i++) {
                sum += (prefixCount[q[1] + 1][i] - prefixCount[q[0]][i]) % 2;
            }
            res.add(sum / 2 <= q[2]);
        }

        return res;
    }
}
