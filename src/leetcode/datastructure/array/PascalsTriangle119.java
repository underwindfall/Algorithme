package leetcode.datastructure.array;

import java.util.ArrayList;
import java.util.List;

/**
 * PascalsTriangle119
 */
// https://leetcode-cn.com/problems/pascals-triangle-ii/
public class PascalsTriangle119 {

    // time
    // espace
    // dp -> dp[j] = dp[j]+dp[j-1]
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>(rowIndex + 1);
        for (int i = 0; i <= rowIndex; i++) {
            res.add(1);
            for (int j = i - 1; j > 0; j--) {
                res.set(j, res.get(j) + res.get(j - 1));
            }
        }
        return res;
    }
}