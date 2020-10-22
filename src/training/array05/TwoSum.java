package training.array05;

//https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
/**
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * 
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 * 
 * 说明:
 * 
 * 返回的下标值（index1 和 index2）不是从零开始的。 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。 
 * 
 * 示例:
 * 
 * 输入: numbers = [2, 7, 11, 15], target = 9 输出: [1,2] 解释: 2 与 7 之和等于目标数 9 。因此
 * index1 = 1, index2 = 2 。
 * 
 */
public class TwoSum {
    // 二分法
    // time: O(N*logN)
    // espace: O(1)
    static int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int low = i + 1, high = numbers.length - 1;
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                if (numbers[mid] == target - numbers[i]) {
                    return new int[] { i + 1, mid + 1 };
                } else if (numbers[mid] > target - numbers[i]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return new int[] { -1, -1 };
    }

    // 双向指针法
    // time: O(N/2) === O(N)
    // espace: O(1)
    static int[] twoSum1(int[] numbers, int target) {
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum < target) {
                low++;
            } else if (sum > target) {
                high--;
            } else {
                return new int[] { low + 1, high + 1 };
            }
        }
        return new int[] { -1, -1 };
    }

    public static void main(String[] args) {
        int[] numbers = new int[] { 2, 7, 11, 15 };
        int target = 9;
        printResult(twoSum(numbers, target));
        printResult(twoSum1(numbers, target));
    }

    private static void printResult(int[] origin) {
        for (int i = 0; i < origin.length; i++) {
            StringBuilder stringBuilder = new StringBuilder("");
            stringBuilder.append(origin[i] + ",");
            System.out.println(stringBuilder + "\n");
        }
    }
}
