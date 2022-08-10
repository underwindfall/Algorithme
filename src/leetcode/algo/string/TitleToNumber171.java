package leetcode.algo.string;

// https://leetcode.cn/problems/excel-sheet-column-number/
public class TitleToNumber171 {
    // time O(n)
    // space O(1)
    public int titleToNumber(String columnTitle) {
        int ans = 0;
        int mutiple = 1;
        for (int i = columnTitle.length() - 1; i >= 0; i--) {
            int number = columnTitle.charAt(i) - 'A' + 1;
            ans = ans + number * mutiple;
            mutiple = mutiple * 26;
        }
        return ans;
    }
}
