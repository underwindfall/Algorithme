package leetcode.algo.dp.prefix;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/
public class FindLongestVowels1371 {

    // [i: j] -> vowels -> 偶数
    // [:j] - [:i - 1] = 偶数
    // 能获得偶数的方式 是 偶数-偶数 奇数-奇数 <=====> [:j] 奇偶性 = [:i - 1]奇偶性 -> [0, x] state奇偶太
    // a,e,i,o,u -> (10000、01000、00100、00010、00001)
    // dp[:i + 1] = dp[:i] ^ (10000、01000、00100、00010、00001)
    public int findTheLongestSubstring(String s) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>(); // map存放各个前缀区间的state key-->state状态 value-->最早发现的character index
        map.put(0, -1);
        int state = 00000;// 前缀区间的state状态
        Map<Character, Integer> vowel = new HashMap<>();// 对照表
        vowel.put('a', 1);
        vowel.put('e', 2);
        vowel.put('i', 4);
        vowel.put('o', 8);
        vowel.put('u', 16);
        for (int i = 0; i < s.length(); i++) { // 遍历str串
            Character c = s.charAt(i);// 获取当前遍历的字符
            if (vowel.containsKey(c)) {// 当前遍历的字符是元音
                state ^= vowel.get(c);
                if (!map.containsKey(state)) {// 当前state没存过
                    map.put(state, i);// 存入该state，和对应的位置
                }
            }
            int distance = i - map.get(state);
            res = Math.max(res, distance);
        }
        return res;
    }
}
