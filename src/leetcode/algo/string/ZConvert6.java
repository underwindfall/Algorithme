package leetcode.algo.string;

import java.util.ArrayList;
import java.util.List;

//https://leetcode-cn.com/problems/zigzag-conversion
public class ZConvert6 {

    // time O(n)
    // space O(n)
    public String convert(String s, int numsRows) {
        if (numsRows < 2) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numsRows; i++) {
            rows.add(new StringBuilder());
        }
        int i = 0, flag = -1;
        for (char c : s.toCharArray()) {
            rows.get(i).append(c);
            if (i == 0 || i == numsRows - 1) {
                flag = -flag;
            }
            i += flag;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows) {
            res.append(row);
        }
        return res.toString();
    }
}
