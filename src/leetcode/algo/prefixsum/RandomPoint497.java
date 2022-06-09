package leetcode.algo.prefixsum;

import java.util.Random;

// https://leetcode.cn/problems/random-point-in-non-overlapping-rectangles/
public class RandomPoint497 {
    // 思路是从 rects含有多个不同面积的矩形，每个矩形都是代表着含有相同面积的点数
    // 为了让每个点数的概率平均可选
    // 我们需要根据长方形内所包含的点数来选择，及如果分别有三个长方形其包含的点数为[9,12,16],
    // 那么其选中的概率分别应该对应为[9/37, 12/37, 16/37]，
    // 其中37为三个长方形所包含点数只和。因为总共有37个坐标点，所以我们希望每个坐标点被选中的概率为1/37。
    // 因此必须根据每个长方形内所包含的点数来分配概率。
    // 我们简单的看一下如果以9/37的概率选中第一个长方形会怎么样，第一个长方形中有9个点，因此每个点被选中的概率为1/9,
    // 乘以其正方行被选中的概率及1/9 * 9/37刚好为1/37。对于第二个与第三个长方形则是一样的道理。
    // https://leetcode.cn/problems/random-point-in-non-overlapping-rectangles/solution/shui-tang-chou-yang-by-xiaohu9527-7b3d/

    // time O(n)
    // space O(n)
    class Solution {
        // Ot(n)
        public Solution(int[][] rects) {
            assert rects.length > 0;
            this.rects = rects;

            // Build prefix sum array, with rectangle area as weight
            sums = new int[rects.length + 1];
            for (int i = 1; i <= rects.length; i++) {
                int[] rect = rects[i - 1];
                sums[i] = sums[i - 1] + (rect[2] - rect[0] + 1) * (rect[3] - rect[1] + 1);
            }
        }

        // Ot(log2(n))
        public int[] pick() {
            // Pick random index by weight in prefix sum array
            int index = randomIndexByWeight();

            // Pick random point in selected rectangle
            int x = rand.nextInt(rects[index][2] - rects[index][0] + 1) + rects[index][0],
                    y = rand.nextInt(rects[index][3] - rects[index][1] + 1) + rects[index][1];
            return new int[] { x, y };
        }

        // Ot(log2(n))
        private int randomIndexByWeight() {
            // Generate target in [0, max sum)
            int target = rand.nextInt(sums[sums.length - 1]);

            // Right-bound binary search to find last sum <= target
            int left = 0, right = sums.length - 1;
            while (left < right) {
                int mid = right - (right - left) / 2;
                if (sums[mid] <= target) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }

            return left;
        }

        private int[][] rects;
        private int[] sums;
        private Random rand = new Random();
    }
}
