package leetcode.datastructure.array;

// https://leetcode.cn/problems/rotate-array/
public class RotateArray189 {
    // time O(n)
    // space O(1)
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start += 1;
            end -= 1;
        }
    }

    // To rotate left by D elements
    void leftRotate(int arr[], int d, int n) {
        for (int i = 0; i < d; i++)
            leftRotatebyOne(arr, n);
    }

    // Method 2
    // To rotate left one by one
    void leftRotatebyOne(int arr[], int n) {
        int i, temp;
        temp = arr[0];
        for (i = 0; i < n - 1; i++)
            arr[i] = arr[i + 1];
        arr[i] = temp;
    }

    void rightRotate(int arr[], int d, int n) {
        // Iterating till we want
        for (int i = n; i > d; i--)
            // Recursively calling
            rightRotatebyOne(arr, n);
    }

    // Method 2
    // To rotate array by 1
    void rightRotatebyOne(int arr[], int n) {
        int i, temp;
        temp = arr[0];
        for (i = 0; i < n - 1; i++)
            arr[i] = arr[i + 1];
        arr[i] = temp;
    }

}
