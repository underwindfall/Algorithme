package leetcode.datastructure.array;

import java.util.ArrayList;
import java.util.List;

//https://leetcode-cn.com/problems/majority-element-ii/
public class MajorityElement229 {
    // 摩尔vote投票
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return res;
        }
        int cand1 = nums[0], count1 = 0;
        int cand2 = nums[0], count2 = 0;
        // 摩尔投票法，分为两个阶段：配对阶段和计数阶段
        // 配对阶段
        for (int num : nums) {
            if (num == cand1) {
                count1++;
            }
            else if (num == cand2) {
                count2++;
            }
            else if (count1 == 0) {
                cand1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                cand2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        // 计数阶段
        // 找到了两个候选人之后，需要确定票数是否满足大于 N/3
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (cand1 == num)
                count1++;
            else if (cand2 == num)
                count2++;
        }

        if (count1 > nums.length / 3)
            res.add(cand1);
        if (count2 > nums.length / 3)
            res.add(cand2);

        return res;
    }
}
