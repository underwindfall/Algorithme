package leetcode.datastructure.array;

// https://leetcode-cn.com/problems/array-nesting/description/
public class ArrayNesting565 {

    // time O(N)
    // espace O(1)
    class MaxValueArraySolution {
        public int arrayNesting(int[] nums) {
            int maxSize = 0;
            for (int i = 0; i < nums.length; i++) {
                int start = nums[i], count = 0;
                while (nums[i] != Integer.MAX_VALUE) {
                    int temp = start;
                    start = nums[temp];
                    count++;
                    nums[temp] = Integer.MAX_VALUE;
                }
                maxSize = Math.max(maxSize, count);
            }
            return maxSize;
        }
    }

    // time O(N)
    // espace O(1)
    class BooleanArraySolution {
        public int arrayNesting(int[] nums) {
            boolean[] visited = new boolean[nums.length];
            int maxSize = 0;
            for (int i = 0; i < nums.length; i++) {
                if (!visited[i]) {
                    int start = nums[i], count = 0;
                    do {
                        start = nums[start];
                        count++;
                        visited[start] = true;
                    } while (start != nums[i]);
                    maxSize = Math.max(maxSize, count);
                }
            }
            return maxSize;
        }
    }

    // 超过时间限制不能pass
    // time O(M^2)
    // espace O(1)
    class SetSolution {
        public int arrayNesting(int[] nums) {
            int maxSize = 0;
            for (int i = 0; i < nums.length; i++) {
                int startIndex = nums[i];
                int count = 0;
                do {
                    count++;
                    startIndex = nums[startIndex];
                } while (startIndex != nums[i]);
                maxSize = Math.max(count, maxSize);
            }
            return maxSize;
        }
    }

    public static void main(String[] args) {
        ArrayNesting565 arrayNesting565 = new ArrayNesting565();
        SetSolution setSolution = arrayNesting565.new SetSolution();
        int[] nums = new int[] { 0, 2, 1 };
        setSolution.arrayNesting(nums);
    }
}
