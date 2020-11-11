package leetcode.datastructure.array;

import java.util.ArrayList;
import java.util.List;

/**
 * PascalsTriangle118
 */
// https://leetcode-cn.com/problems/pascals-triangle/
public class PascalsTriangle118 {
    // 思路
    // 找到下一行 和上一行之间的关系
    // 关系是 出去第一个和最后一个数据 preRow.get(j - 1) + preRow.get(j) = row(j)
    // time O(N*(N-1)/2) =O(N^2)
    // espace O(N*(N-1)/2) =O(N^2)
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();

            for (int j = 0; j < i + 1; j++) {
                if (j == 0 || j == i) {
                    list.add(1);
                } else {
                    int a = result.get(i - 1).get(j - 1);
                    int b = result.get(i - 1).get(j);
                    list.add(a + b);
                }
            }
            result.add(list);
        }
        return result;
    }
}