package leetcode.datastructure.hash;

import java.util.HashSet;
import java.util.Set;

// https://leetcode-cn.com/problems/jewels-and-stones/
public class NumJewelsInStones771 {
    
    public int numJewelsInStones(String jewels, String stones) {
        Set<Character> types = new HashSet<>();
        for (char c : jewels.toCharArray()) {
            types.add(c);
        }
        int count = 0;
        for (char s: stones.toCharArray()) {
            if (types.contains(s)) {
                count++;
            }
        }
        return count;
    }
}
