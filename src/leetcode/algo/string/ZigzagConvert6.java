package leetcode.algo.string;

import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/problems/zigzag-conversion/
public class ZigzagConvert6 {
    // time O(n)
    // space O(n)
    public String convert(String s, int numRows) {
        if (numRows < 2)
            return s;
        List<StringBuilder> list = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            list.add(new StringBuilder());
        }

        int i = 0, flag = -1;
        for (char c : s.toCharArray()) {
            list.get(i).append(c);
            if (i == 0 || i == numRows - 1) {
                flag = -flag;
            }
            i += flag;
        }

        StringBuilder ans = new StringBuilder();
        for (StringBuilder sb : list) {
            ans.append(sb.toString());
        }
        return ans.toString();
    }
}
