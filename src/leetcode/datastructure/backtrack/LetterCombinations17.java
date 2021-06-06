package leetcode.datastructure.backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
public class LetterCombinations17 {
    // time (3^M * 4^N)
    // espace O(M+N)
    class Backtrack {
        public List<String> letterCombinations(String digits) {
            List<String> combinations = new ArrayList<String>();
            if (digits.length() == 0) {
                return combinations;
            }
            Map<Character, String> phoneMap = new HashMap<Character, String>() {
                {
                    put('2', "abc");
                    put('3', "def");
                    put('4', "ghi");
                    put('5', "jkl");
                    put('6', "mno");
                    put('7', "pqrs");
                    put('8', "tuv");
                    put('9', "wxyz");
                }
            };
            backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
            return combinations;
        }

        public void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index,
                StringBuffer combination) {
            if (index == digits.length()) {
                combinations.add(combination.toString());
            } else {
                char digit = digits.charAt(index);
                String letters = phoneMap.get(digit);
                int lettersCount = letters.length();
                for (int i = 0; i < lettersCount; i++) {
                    combination.append(letters.charAt(i));
                    backtrack(combinations, phoneMap, digits, index + 1, combination);
                    combination.deleteCharAt(index);
                }
            }
        }
    }

    class DFS {
        public List<String> letterCombinations(String digits) {
            List<String> result = new ArrayList<>();
            if (digits.length() == 0)
                return result;
            dfs(0, digits.length(), "", digits, result);
            return result;
        }

        void dfs(int index, int len, String tmp, String digits, List<String> result) {
            if (index == len) {
                result.add(tmp);
                return;
            }

            List<String> array = new ArrayList<>();
            switch (digits.charAt(index)) {
                case '2':
                    array.add("a");
                    array.add("b");
                    array.add("c");
                    break;
                case '3':
                    array.add("d");
                    array.add("e");
                    array.add("f");
                    break;
                case '4':
                    array.add("g");
                    array.add("h");
                    array.add("i");
                    break;
                case '5':
                    array.add("j");
                    array.add("k");
                    array.add("l");
                    break;
                case '6':
                    array.add("m");
                    array.add("n");
                    array.add("o");
                    break;
                case '7':
                    array.add("p");
                    array.add("q");
                    array.add("r");
                    array.add("s");
                    break;
                case '8':
                    array.add("t");
                    array.add("u");
                    array.add("v");
                    break;
                case '9':
                    array.add("w");
                    array.add("x");
                    array.add("y");
                    array.add("z");
                    break;
                default:
                    break;
            }

            for (String s : array) {
                dfs(index + 1, len, tmp + s, digits, result);
            }

        }
    }
}
