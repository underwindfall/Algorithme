package training.array05;

//https://leetcode-cn.com/problems/move-zeroes
//https://leetcode-cn.com/problems/move-zeroes/solution/dong-hua-yan-shi-283yi-dong-ling-by-wang_ni_ma/思路好
/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 
 * 示例:
 * 
 * 输入: [0,1,0,3,12] 输出: [1,3,12,0,0] 说明:
 * 
 * 必须在原数组上操作，不能拷贝额外的数组。 尽量减少操作次数。
 * 
 */
public class MoveZeroes {
    // time O(n)
    // espace O(1)
    static void moveZeroes(int[] nums) {
        int slow = 0;
        // 快指针循环
        // 满指针去遍历非0的数组比记录下来
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                nums[slow++] = nums[fast];
            }
        }

        // 最后循环一遍赋0归位
        for (int i = slow; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    // 这里参考了快速排序的思想，快速排序首先要确定一个待分割的元素做中间点x，然后把所有小于等于x的元素放到x的左边，大于x的元素放到其右边。
    // 这里我们可以用0当做这个中间点，把不等于0(注意题目没说不能有负数)的放到中间点的左边，等于0的放到其右边。
    // 这的中间点就是0本身，所以实现起来比快速排序简单很多，我们使用两个指针i和j，只要nums[i]!=0，我们就交换nums[i]和nums[j]
    // 请对照动态图来理解
    //
    static void moveZeroes2(int[] nums) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                int tmp = nums[fast];
                nums[fast] = nums[slow];
                nums[slow++] = tmp;
            }
        }
    }

    //best practices
    static void moveZeroes3(int[] nums) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                if (fast > slow) {
                    nums[slow] = nums[fast];
                    nums[fast] = 0;
                }
                slow++;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 0, 1, 0, 3, 12 };
        moveZeroes(nums);
        for (int i : nums) {
            System.out.println(i);
        }
    }
}
