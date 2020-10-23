package training.array05;

import java.util.ArrayList;
import java.util.List;

//https://leetcode-cn.com/problems/pascals-triangle/
/**
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * 
 * 
 * 
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * 
 * 示例:
 * 
 * 输入: 5 输出: [ [1], [1,1], [1,2,1], [1,3,3,1], [1,4,6,4,1] ]
 */

public class PascalsTriangle {
    // time: O(numRows^2)
    //espace: O(numRows^2)
    static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();

        // 特殊情况为0
        if (numRows == 0) {
            return triangle;
        }

        triangle.add(new ArrayList<>());
        // 第一行第一个
        triangle.get(0).add(1);
        for (int rowNum = 1; rowNum < numRows; rowNum++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> preRow = triangle.get(rowNum - 1);
            // 加入当前行第一个数字为1
            row.add(1);
            // 开始循环每行的第二个数开始一直到倒数第二个
            for (int j = 1; j < rowNum; j++) {
                row.add(preRow.get(j - 1) + preRow.get(j));
            }
            // 最后一个数
            row.add(1);
            triangle.add(row);
        }

        return triangle;
    }

    public static void main(String[] args) {
        println(generate(5));
    }

    static void println(List<List<Integer>> data) {
        for (List<Integer> list : data) {
            StringBuilder result = new StringBuilder();
            for (Integer num : list) {
                result.append(num + ",");
            }
            System.out.println(result + "\n");
        }
    }
}
