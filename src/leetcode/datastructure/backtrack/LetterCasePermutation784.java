package leetcode.datastructure.backtrack;

import java.util.ArrayList;
import java.util.List;

//https://leetcode-cn.com/problems/letter-case-permutation/
public class LetterCasePermutation784 {

    //time O(2^N *N)
    //espace O(2^N *N)
    class DFS {
        public List<String> letterCasePermutation(String s) {
            List<String> result = new ArrayList<>();
            if (s.length() == 0)
                return result;

            dfs(0, s, "", result);
            return result;
        }

        void dfs(int index, String origin, String curr, List<String> result) {
            if (index == origin.length()) {
                result.add(curr);
                return;
            }
            char c = origin.charAt(index);
            if ((c >= 'a' && c <= 'z') || c >= 'A' && c <= 'Z') {
                dfs(index + 1, origin, curr + String.valueOf(c).toUpperCase(), result);
                dfs(index + 1, origin, curr + String.valueOf(c).toLowerCase(), result);
            } else {
                dfs(index + 1, origin, curr + c, result);
            }
        }
    }
}
