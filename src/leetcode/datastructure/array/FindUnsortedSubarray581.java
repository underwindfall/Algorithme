package leetcode.datastructure.array;

import java.util.Arrays;

//https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/
public class FindUnsortedSubarray581 {
    // time O(N * logN)
    // espace O(N)
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int[] arr = nums.clone();
        Arrays.sort(arr);
        int i = 0, j = n - 1;
        while (i <= j && nums[i] == arr[i]) {
            i++;
        }
        while (i <= j && nums[j] == arr[j]) {
            j--;
        }
        return j - i + 1;
    }
}
