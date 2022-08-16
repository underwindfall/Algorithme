package leetcode.algo.doubleindex;

// https://leetcode.cn/problems/sort-colors/
public class SortColors75 {

    // 把0全部置换到前面
    // 把1全部置换到0之后的靠前位置
    // time O(n)
    // space O(1)
    class SingleIndex {
        public void sortColors(int[] nums) {
            int len = nums.length;
            int zero = 0;
            for (int i = 0; i < len; i++) {
                if (nums[i] == 0) {
                    swap(nums, i, zero);
                    zero++;
                }
            }

            for (int i = zero; i < len; i++) {
                if (nums[i] == 1) {
                    swap(nums, i, zero);
                    zero++;
                }
            }
        }

        void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }

    // time O(n)
    // space O(1)
    public void sortColors(int[] nums) {
        int len = nums.length;
        // 两个参数划分三个区间,
        // [0, zero) = 0,0所在的区间
        // [two, len) = 2,2所在的区间
        // i in [zero,two)，i所在的区间

        int zero = 0;// 初始时0所在的区间为空
        int two = len;// 初始时2所在的区间为空
        int i = zero;// 初始时1(i)所在的区间
        // 当 i == two 上面的三个子区间正好覆盖了全部数组
        // 因此，循环可以继续的条件是 i < two
        while (i < two) {
            if (nums[i] == 0) {
                swap(nums, i, zero);
                zero++;
                i++;
            } else if (nums[i] == 1) {
                i++;
            } else {
                two--;
                swap(nums, i, two);
            }
        }
    }

    void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
