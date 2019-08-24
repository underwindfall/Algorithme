package swordoffer.exercise4;

public class JFindTwoDimensionArray {
    public static void main(String[] arg0) {
        int[][] matrix = {
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}};
        if (findTwoDimensionArrayBinarySearch(matrix, 10)) {
            System.out.println("存在");
        } else {
            System.out.println("不存在");
        }
    }


    /**
     * 解法一：双指针
     * 时间复杂度：O(mn)，空间复杂度：O(1)
     *
     * @param matrix
     * @param target
     * @return
     */
    public static boolean findTwoDimensionArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int row = 0;
        int column = matrix[0].length - 1;
        while (row < matrix.length && column >= 0) {
            if (matrix[row][column] == target) {
                return true;
            } else if (matrix[row][column] > target) {
                column--;
            } else {
                row++;
            }
        }
        return false;
    }

    /**
     * 解法二：二分法
     * 时间复杂度：O(log mn)，空间复杂度：O(1)
     *
     * @param matrix
     * @param target
     * @return
     */

    public static boolean findTwoDimensionArrayBinarySearch(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        int low = 0;
        int high = matrix.length * (matrix[0].length - 1);
        int col = matrix[0].length;

        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int value = matrix[mid / col][mid % col];
            if (value == target) {
                return true;
            } else if (value < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }

}
