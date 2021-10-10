package leetcode.datastructure.hash;

import java.util.ArrayList;
import java.util.List;

//https://leetcode-cn.com/problems/find-all-duplicates-in-an-array/
public class FindDuplicates442 {
    //time O(n)
    //space O(1)
    // 利用重复同样的数组的index被置换成1，重复元素仅出现一次
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i : nums) {
            if (nums[Math.abs(i) - 1] < 0) {
                res.add(Math.abs(i));
            }
            nums[Math.abs(i) - 1] *= -1;
        }
        return res;
    }
}
