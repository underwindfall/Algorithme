package swordoffer.exercise3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 第3题
 * 一个二维数组，每一行从左到右递增，每一列从上到下递增．
 * 输入一个二维数组和一个整数，判断数组中是否含有整数
 */
public class JDuplicationInArray3 {

    public static int findDuplicationArray(int[] matrix) {
        int temp;
        for (int i = 0; i < matrix.length; i++) {
            while (matrix[i] != i) {
                if (matrix[matrix[i]] == matrix[i]) {
                    return matrix[i];
                }
                temp = matrix[i];
                matrix[i] = matrix[matrix[i]];
                matrix[temp] = temp;
            }
        }
        return -1;
    }

    //espace: O(n)
    public List<Integer> findDuplicationArrayByHashMap(int[] inputArray) {
        List<Integer> output = new ArrayList<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < inputArray.length; i++) {
            if (!hashMap.containsValue(inputArray[i])) {
                hashMap.put(i, inputArray[i]);
            } else {
                output.add(inputArray[i]);
            }
        }
        return output;
    }

    //不能改变数组本身（使用辅助数组，增加空间占用但节约时间）
    public static int findDuplicationNoChangOrder(int[] matrix) {
        int[] a = new int[matrix.length + 1];
        for (int value : matrix) {
            if (a[value] == value) {
                return value;
            }
            a[value] = value;
        }
        return -1;
    }

    //不能改变数组本身(二分查找)
    public static int findDuplicationNoChangOrderWithOutHelpArray(int[] matrix) {
        int high = matrix.length - 1;
        int low = 1;
        int count;
        while (high >= low) {
            int mid = (high - low) / 2 + low;
            count = 0;
            for (int value : matrix) {
                if (value <= mid && value >= low) {
                    count++;
                }
            }
            if (count > mid - low + 1) {
                if (low == mid) {
                    return mid;
                }
                high = mid;
            } else {
                if (mid == high) {
                    return mid;
                }
                low = mid + 1;
            }
        }
        return -1;
    }
}

