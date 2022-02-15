package swordoffer;

import java.util.ArrayList;
import java.util.List;

//https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/
public class LCOF29 {
    // 转圈模拟
    // time O(nm)
    // space O(nm)
    public int[] spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length == 0)
            return new int[] {};
        int left = 0, right = matrix[0].length - 1, top = 0, bottom = matrix.length - 1;
        int numEles = matrix[0].length * matrix.length;
        while (numEles >= 1) {
            for (int i = left; i <= right && numEles >= 1; i++) {
                list.add(matrix[top][i]);
                numEles--;
            }
            top++;
            for (int i = top; i <= bottom && numEles >= 1; i++) {
                list.add(matrix[i][right]);
                numEles--;
            }
            right--;
            for (int i = right; i >= left && numEles >= 1; i--) {
                list.add(matrix[bottom][i]);
                numEles--;
            }
            bottom--;
            for (int i = bottom; i >= top && numEles >= 1; i--) {
                list.add(matrix[i][left]);
                numEles--;
            }
            left++;
        }

        int index = 0;
        int[] ans = new int[list.size()];
        while (index < list.size()) {
            ans[index] = list.get(index);
            index++;
        }
        return ans;
    }
}
