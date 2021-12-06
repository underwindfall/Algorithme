package leetcode.algo.backtrack;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://leetcode-cn.com/problems/valid-word-square/
public class ValidWordSquare422 {
    //time o(n^2)
    //space O(n)
    public boolean validWordSquare(List<String> words) {
        int n = words.size();
        Set<String> seen = new HashSet<>();
        for (int i = 0; i < n; i++) {
            String str = words.get(i);
            int len = str.length();
            seen.add(str);

            StringBuilder builder = new StringBuilder();
            for (int j = 0;  j < len; j++) {
                if (i < words.get(j).length()) {
                    builder.append(words.get(j).charAt(i));
                } else {
                    return false;
                }
            }
            if (!seen.contains(builder.toString())) {
                return false;
            }
        }

        return true;
    }

}
