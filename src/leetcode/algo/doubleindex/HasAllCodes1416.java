package leetcode.algo.doubleindex;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.cn/problems/check-if-a-string-contains-all-binary-codes-of-size-k/
public class HasAllCodes1416 {
    // time O(s)
    // space O(2^k)
    public boolean hasAllCodes(String s, int k) {
        int left = 0, right = k;
        int len = s.length();
        Set<String> set = new HashSet<>();
        while (right <= len) {
            set.add(s.substring(left, right));
            left++;
            right++;
        }

        if (set.size() == Math.pow(2, k)) {
            return true;
        }
        return false;
    }
}
