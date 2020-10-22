package training.array05;

import java.util.Arrays;

public class ArrayPairSum {
    // olog(n)
    static int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum += nums[i];
        }
        return sum;
    }

    // o(n)
    static int arrayPairSum1(int[] nums) {
        int kMaxValue = 10000;
        int array[] = new int[2 * kMaxValue + 1];
        for (int num : nums) {
            ++array[num + kMaxValue];
        }
        int sum = 0;
        int n = -kMaxValue;
        boolean first = true;
        while (n <= kMaxValue) {
            if (array[n + kMaxValue] == 0) {
                ++n;
                continue;
            }
            if (first) {
                sum += n;
                first = false;
            } else {
                first = true;
            }
            --array[n + kMaxValue];
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(arrayPairSum(new int[] { 1, 4, 3, 2 }));
        System.out.println(arrayPairSum1(new int[] { 1, 4, 3, 2 }));
    }
}
