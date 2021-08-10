package leetcode.algo.dp;

// https://leetcode-cn.com/problems/arithmetic-slices/
public class NumberOfArithmeticSlices413 {
    // the way to iteration
    // 第一层循环从0 - 数组倒数第二位
    // 第二层循环比较离第一层两个数字的距离 因为是等差数列最少两个
    // 通过比较 S - I 区间的数组 如果最后一个数字是后等差数字，那就++ 否则递减
    // time O(N^2)
    // espace O(1)
    class Iteration {
        public int numberOfArithmeticSlices(int[] A) {
            int count = 0;
            for (int i = 0; i < A.length - 1 - 1; i++) {
                int diff = A[i + 1] - A[i];
                for (int s = i + 2; s < A.length; s++) {
                    if (A[s] - A[s - 1] == diff) {
                        count++;
                    } else {
                        break;
                    }
                }
            }
            return count;
        }
    }

    public class Recursive {
        int sum = 0;

        public int numberOfArithmeticSlices(int[] A) {
            slices(A, A.length - 1);
            return sum;
        }

        public int slices(int[] A, int i) {
            if (i < 2)
                return 0;
            int ap = 0;
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                ap = 1 + slices(A, i - 1);
                sum += ap;
            } else
                slices(A, i - 1);
            return ap;
        }
    }

    // 和递归深思
    // 递归的规律就是 dp[i] = dp[i - 1] + 1
    class DP {
        public int numberOfArithmeticSlices(int[] A) {
            int dp[] = new int[A.length];
            int count = 0;
            for (int i = 2; i < A.length; i++) {
                if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                    dp[i] = dp[i - 1] + 1;
                    count += dp[i];
                } else {
                    continue;
                }
            }
            return count;
        }
    }

    // 双指针
    // 右指针一直走
    // 左指针再遇到右指针的前个值不相等的是否 移动到右指针的位置 同时归零当前的计数
    class DoubleIndex {
        public int numberOfArithmeticSlices(int[] nums) {
            int len = nums.length;
            if (len < 3) {
                return 0;
            }
            // 初始化
            int preDiff = nums[1] - nums[0];
            // 当前得到的等差数列的长度，有「差」必有两个元素，因此初始化的时候 L = 2
            int L = 2;
            int res = 0;
            // 从下标 2 开始比较「当前的差」与「上一轮的差」是否相等
            for (int i = 2; i < len; i++) {
                int diff = nums[i] - nums[i - 1];
                if (diff == preDiff) {
                    L++;
                } else {
                    // 加入结果，然后重置 L 和 preDiff
                    res += (L - 1) * (L - 2) / 2;
                    L = 2;
                    preDiff = diff;
                }
            }

            // 最后还要再计算一下结果
            res += (L - 1) * (L - 2) / 2;
            return res;
        }
    }
}
