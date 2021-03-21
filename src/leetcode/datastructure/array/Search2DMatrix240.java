package leetcode.datastructure.array;

public class Search2DMatrix240 {
    // time O(M+N)
    // espace O(1)
    class BestSolution {
        public boolean searchMatrix(int[][] matrix, int target) {
            int row = matrix.length - 1;
            int col = 0;
            while (row > 0 && col < matrix[0].length - 1) {
                if (matrix[row][col] > target) {
                    row--;
                } else if (matrix[row][col] < target) {
                    col++;
                } else {
                    return true;
                }
            }
            return false;
        }
    }

    // 递归写法 想法一致
    class RecursiveWay {
        public boolean searchMatrix(int[][] matrix, int target) {
            return search(matrix, target, matrix.length - 1, 0);
        }

        boolean search(int[][] matrix, int target, int row, int col) {
            int r = matrix.length, c = matrix[0].length;
            if (row < 0 || row >= r)
                return false;
            if (col < 0 || col >= c)
                return false;
            if (matrix[row][col] == target)
                return true;
            if (matrix[row][col] < target) {
                return search(matrix, target, row, col + 1);
            } else {
                return search(matrix, target, row - 1, col);
            }
        }
    }

    class DoubleIndex {
        public boolean searchMatrix(int[][] matrix, int target) {
            // an empty matrix obviously does not contain `target`
            if (matrix == null || matrix.length == 0) {
                return false;
            }

            int row = matrix.length - 1;
            int col = matrix[0].length - 1;
            int left = 0;
            int right = col;
            while (left <= row && right >= 0) {
                if (matrix[left][right] > target) {
                    right--;
                } else if (matrix[left][right] < target) {
                    left++;
                } else {
                    return true;
                }
            }
            return false;
        }
    }

    // time O(lg(n!))
    // espace O(1)
    class BinarySearch {
        public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix == null || matrix.length == 0) {
                return false;
            }
            int minDimension = Math.min(matrix.length, matrix[0].length);
            for (int i = 0; i < minDimension; i++) {
                boolean verticalFound = binarySearch(matrix, target, i, true);
                boolean horizontalFound = binarySearch(matrix, target, i, false);
                if (verticalFound || horizontalFound) {
                    return true;
                }
            }
            return false;
        }

        private boolean binarySearch(int[][] matrix, int target, int start, boolean vertical) {
            int lo = start;
            int hi = vertical ? matrix[0].length - 1 : matrix.length - 1;
            while (hi >= lo) {
                int mid = (hi + lo) / 2;
                if (vertical) {
                    if (matrix[mid][start] < target) {
                        lo = mid + 1;
                    } else if (matrix[mid][start] > target) {
                        hi = mid - 1;
                    } else {
                        return true;
                    }
                } else {
                    if (matrix[start][mid] < target) {
                        lo = mid + 1;
                    } else if (matrix[start][mid] < target) {
                        hi = mid - 1;
                    } else {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    // time O(M*N)
    // espace O(1)
    class WorstSolution {
        public boolean searchMatrix(int[][] matrix, int target) {
            int col = matrix[0].length;
            int row = matrix.length;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (matrix[i][j] == target) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

}
