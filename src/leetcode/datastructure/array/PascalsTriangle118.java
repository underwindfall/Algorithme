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

    class Recursive {
        // public List<List<Integer>> generate(int numRows) {
        //     List<List<Integer>> result = new ArrayList<>();
        //     for (int i = 0; i < numRows; i++) {
        //         List<Integer> lineResult = new ArrayList<>();
        //         for (int j = 0; j < i + 1; j++) {
        //             lineResult.add(calculateNums(i, j));
        //         }
        //         result.add(lineResult);
        //     }
        //     return result;
        // }

        // int calculateNums(int row, int col) {
        //     if (row == 0 && col == 0) {
        //         return 1;
        //     }
        //     if (col == 0 && row >= 0) {
        //         return 1;
        //     }
        //     if (col >= 0 && row >= 0 && col == row) {
        //         return 1;
        //     }
        //     if (row < 0 || col < 0) {
        //         return 0;
        //     }
        //     int a = calculateNums(row - 1, col - 1);
        //     int b = calculateNums(row - 1, col);
        //     return a + b;
        // }

        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> triangle = new ArrayList<>();
            if (numRows < 1)
                return triangle;
            if (numRows == 1) {
                List<Integer> temp = new ArrayList<>();
                temp.add(1);
                triangle.add(temp);
                return triangle;
            }
            if (numRows == 2) {
                List<Integer> temp1 = new ArrayList<>();
                temp1.add(1);
                List<Integer> temp2 = new ArrayList<>();
                temp2.add(1);
                temp2.add(1);
                triangle.add(temp1);
                triangle.add(temp2);
                return triangle;
            }
            List<List<Integer>> allButLast = generate(numRows - 1);
            List<Integer> last = new ArrayList<>();
            last.add(1);
            for (int i = 0; i < allButLast.size() - 1; i++)
                last.add(allButLast.get(allButLast.size() - 1).get(i)
                        + allButLast.get(allButLast.size() - 1).get(i + 1));
            last.add(1);
            allButLast.add(last);
            return allButLast;
        }
    }
}