package leetcode.datastructure.array;

import java.util.LinkedList;
import java.util.Queue;

// 数据结构 queue
// https://leetcode-cn.com/problems/reshape-the-matrix/description/
public class ReshapeMatrix566 {


    // time O(M*N)=> r*c
    // espace O(M*N)=> r*c
    class QueueSolution {
        public int[][] matrixReshape(int[][] nums, int r, int c) {
            int[][] reshapedNums = new int[r][c];
            int numsR = nums.length;
            int numsC = nums[0].length;
            // 如果两个行列不一样，不能转化 return
            if (numsC * numsR != r * c) {
                return nums;
            }
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < numsR; i++) {
                for (int j = 0; j < numsC; j++) {
                    queue.add(nums[i][j]);
                }
            }
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    reshapedNums[i][j] = queue.remove();
                }
            }
            return reshapedNums;
        }
    }
    
    // time O(M*N)=> r*c
    // espace O(M*N)=> r*c
    class ModuleSolution {
        public int[][] matrixReshape(int[][] nums, int r, int c) {
            int[][] reshapedNums = new int[r][c];
            int numsR = nums.length;
            int numsC = nums[0].length;
            // 如果两个行列不一样，不能转化 return
            if (numsC * numsR != r * c) {
                return nums;
            }
            int count = 0;
            for (int i = 0; i < numsR; i++) {
                for (int j = 0; j < numsC; j++) {
                    reshapedNums[count / c][count % c] = nums[i][j];
                    count++;
                }
            }
            return reshapedNums;
        }
    }
}
