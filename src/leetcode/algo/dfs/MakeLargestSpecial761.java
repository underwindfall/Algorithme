package leetcode.algo.dfs;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.cn/problems/special-binary-string/
public class MakeLargestSpecial761 {
    // time O(n^2)
    // space O(n)
    public String makeLargestSpecial(String s) {
        return dfs(s, 0, s.length() - 1);
    }

    private String dfs(String s, int start, int end) {
        if (end < start) {
            return "";
        }

        List<String> list = new ArrayList<>();
        int count = 0, pre = start;
        for (int i = start; i <= end; i++) {
            if (s.charAt(i) == '1') {
                count++;
            } else {
                count--;
                if (count == 0) {
                    // 对子串做相同的操作
                    list.add("1" + dfs(s, pre + 1, i - 1) + "0");
                    pre = i + 1;
                }
            }
        }

        list.sort((a, b) -> b.compareTo(a));

        return String.join("", list);
    }

}
