package leetcode.algo.backtrack;

import java.util.ArrayList;
import java.util.List;

//https://leetcode-cn.com/problems/binary-watch/
public class ReadBinaryWatch401 {
    private List<String> res = new ArrayList<>();

    public List<String> readBinaryWatch(int turnedOn) {

        if (turnedOn >= 9)
            return res;

        int[] nums = new int[6];
        getNums(nums);
        backtrack(nums, 0, 0, 0, turnedOn);

        return res;
    }

    private void backtrack(int[] nums, int start, Integer h, Integer m, int turnedOn) {

        if (h >= 12 || m >= 60)
            return;

        if (turnedOn == 0) {

            res.add(h + ":" + String.format("%02d", m));
            return;
        } else {

            for (int i = start; i < 10; i++) {

                if (i < 6) {

                    m += nums[i];
                    backtrack(nums, i + 1, h, m, turnedOn - 1);
                    m -= nums[i];
                } else {

                    h += nums[i - 6];
                    backtrack(nums, i + 1, h, m, turnedOn - 1);
                    h -= nums[i - 6];
                }
            }
        }
    }

    private void getNums(int[] nums1) {

        for (int i = 0; i < nums1.length; i++) {

            nums1[i] = 1 << i;
        }
    }
}
