
package leetcode.algo.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode-cn.com/problems/partition-labels/
public class PartitionLabels763 {
    //time O(n)
    //space O(1)
    public List<Integer> partitionLabels(String s) {
        int[] map = new int[26];
        Arrays.fill(map, -1);
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a'] = i;
        }
        int start = 0, scannedCharMaxPos = 0;

        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            int currentCharMaxPos = map[s.charAt(i) - 'a'];
            scannedCharMaxPos = Math.max(scannedCharMaxPos, currentCharMaxPos);
            if (i == scannedCharMaxPos) {
                res.add(i - start + 1);
                start = i + 1;
            }
        }
        return res;
    }
}
