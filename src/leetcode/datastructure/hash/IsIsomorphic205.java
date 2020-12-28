package leetcode.datastructure.hash;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/isomorphic-strings/solution/
public class IsIsomorphic205 {

    class Hash {
        public boolean isIsomorphic(String s, String t) {
            Map<Character, Character> sMap = new HashMap<>();
            Map<Character, Character> tMap = new HashMap<>();
            int len = s.length();
            for (int i = 0; i < len; i++) {
                char sc = s.charAt(i), tc = t.charAt(i);
                if ((sMap.containsKey(sc) && sMap.get(sc) != tc) || (tMap.containsKey(tc) && tMap.get(tc) != sc)) {
                    return false;
                }

                sMap.put(sc, tc);
                tMap.put(tc, sc);
            }
            return true;
        }
    }

    class Arr {
        public boolean isIsomorphic(String s, String t) {
            int[] intS = new int[128];
            int[] intT = new int[128];
            char[] charS = s.toCharArray();
            char[] charT = t.toCharArray();
            for (int i = 0; i < s.length(); i++) {
                if (intS[charS[i]] != intT[charT[i]]) {
                    return false;
                }
                intS[charS[i]] = intT[charT[i]] = i + 1;
            }
            return true;
        }
    }
}
