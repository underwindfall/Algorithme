package leetcode.algo.backtrack;

import java.util.HashSet;
import java.util.List;

// https://leetcode-cn.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters
public class MaxLengthConcatenated1239 {
    // time O(2^N)
    // espace O(N)

    private int res = 0;

    public int maxLength(List<String> arr) {

        StringBuilder sb = new StringBuilder();

        backtrack(arr, 0, sb);
        return res;
    }

    public void backtrack(List<String> arr, int start, StringBuilder sb) {

        if (!isVaild(sb.toString()))
            return; // 剪枝

        res = Math.max(res, sb.length());
        for (int i = start; i < arr.size(); i++) {

            String tmp = arr.get(i);
            sb.append(tmp); // 做选择
            backtrack(arr, i + 1, sb);
            sb.delete(sb.length() - tmp.length(), sb.length()); // 撤销选择
        }
    }

    public boolean isVaild(String s) {

        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {

            if (set.contains(s.charAt(i)))
                return false;
            set.add(s.charAt(i));
        }
        return true;
    }
}
