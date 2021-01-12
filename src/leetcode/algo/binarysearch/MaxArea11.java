package leetcode.algo.binarysearch;

// https://leetcode-cn.com/problems/container-with-most-water/
public class MaxArea11 {
    /**
     * 思路
     * 
     * 如果left > right 柱子， 那高度最高是right, 宽度是L，
     * 这种情况下left动没意义，因为最低高度被限制了，动了反而面积回小。right--
     * 
     * 反之，left < right 那高度最高是left, 宽度是L， 这种情况下right动没意义，因为最低高度被限制了，动了反而面积回小。left++
     */
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int area = 0;
        while (left <= right) {
            int length = right - left;
            area = Math.max(length * Math.min(height[left], height[right]), area);
            if (height[left] < height[right]) {
                // left < right 那高度最高是left, 宽度是L， 这种情况下right动没意义，因为最低高度被限制了，动了反而面积回小。left++
                left++;
            } else {
                right--;
            }
        }
        return area;
    }
}
