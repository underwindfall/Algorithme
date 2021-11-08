package leetcode.datastructure.hash;

import java.util.ArrayList;
import java.util.List;

//https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/
//cf 442
public class FindDisappearedNumbers448 {
    // time O(n)
    // space O(1)
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int pos = Math.abs(nums[i]) - 1;
            nums[pos] = nums[pos] < 0 ? nums[pos] : -nums[pos];
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res.add(i + 1);
            }
        }
        return res;

    }
}
