package training.array;

import java.util.ArrayList;
import java.util.List;

//https://leetcode-cn.com/problems/pascals-triangle-ii/
/**
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 * 
 * 
 * 
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * 
 * 示例:
 * 
 * 输入: 3 输出: [1,3,3,1]
 * 
 */
public class PascalsTriangle2 {
    // time: O(N^2)
    // espace:O(N)
    static List<Integer> getRow(int rowIndex) {
        List<Integer> preRow = new ArrayList<>();
        List<Integer> currentRow = new ArrayList<>();
        if (rowIndex < 0) {
            return currentRow;
        }
        for (int row = 0; row <= rowIndex; row++) {
            currentRow = new ArrayList<>();
            // 每行的详情
            for (int col = 0; col <= row; col++) {
                if (col == 0 || col == row) {
                    currentRow.add(1);
                } else {
                    currentRow.add(preRow.get(col - 1) + preRow.get(col));
                }
            }
            preRow = currentRow;
        }
        return currentRow;
    }

    // 优化模式
    static List<Integer> getRow1(int rowIndex) {
        int pre = 1;
        List<Integer> cur = new ArrayList<>();
        cur.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = 1; j < i; j++) {
                int temp = cur.get(j);
                cur.set(j, pre + cur.get(j));
                pre = temp;
            }
            cur.add(1);
        }
        return cur;
    }

    // 公式法
    // C nk =n!/(k!(n−k)!)=(n∗(n−1)∗(n−2)∗...(n−k+1))/k!
    // C nk=C nk−1×(n−k+1)/k
    static List<Integer> getRow2(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        int N = rowIndex;
        long pre = 1;
        row.add(1);
        for (int k = 1; k <= N; k++) {
            long cur = pre * (N - k + 1) / k;
            row.add((int) cur);
            pre = cur;
        }
        return row;
    }

    public static void main(String[] args) {
        print(getRow(3));
        print(getRow1(3));
        print(getRow2(3));
    }

    static void print(List<Integer> resulst) {
        for (Integer integer : resulst) {
            System.out.println(integer + ",");
        }
    }
}
