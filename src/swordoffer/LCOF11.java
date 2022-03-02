package swordoffer;

//https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
public class LCOF11 {
    // time O(logN)
    // space O(n)
    public int minArray(int[] numbers) {
        // left, right
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            } else if (numbers[mid] < numbers[right]) {
                right = mid;
            } else {
                right--;
            }
        }
        return numbers[left];
    }
}
