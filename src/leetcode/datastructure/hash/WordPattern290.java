package leetcode.datastructure.hash;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/word-pattern
public class WordPattern290 {
    public boolean wordPattern(String pattern, String str) {
        Map<String, Character> strMap = new HashMap<>();
        Map<Character, String> patternMap = new HashMap<>();

        int m = str.length();
        int i = 0;
        for (int p = 0; p < pattern.length(); ++p) {
            char ch = pattern.charAt(p);
            if (i >= m) {
                return false;
            }
            int j = i;
            // 检索当前word的比较词汇
            while (j < m && str.charAt(j) != ' ') {
                j++;
            }
            String tmp = str.substring(i, j);
            if (strMap.containsKey(tmp) && strMap.get(tmp) != ch) {
                return false;
            }
            if (patternMap.containsKey(ch) && patternMap.get(ch) != tmp) {
                return false;
            }
            strMap.put(tmp, ch);
            patternMap.put(ch, tmp);
            i = j + 1;
        }
        return true;
    }

    public boolean wordPattern2(String pattern, String s) {
        int length = pattern.length();
        // 根据空格将字符串分割成数组
        String[] strings = s.split(" ");
        // 如果分割出来的数组与pattern长度不一致就返回false
        if (length != strings.length) {
            return false;
        }
        // 使用Map存储字母与对应的字符串
        HashMap<Character, String> map = new HashMap<>();
        // 遍历
        for (int i = 0; i < length; i++) {
            char c = pattern.charAt(i);
            // 如果Map中存在键pattern.charAt(i)就判断值是否和strings[i]相等
            if (map.containsKey(c)) {
                if (!map.get(c).equals(strings[i])) {
                    return false;
                }
            } else {
                // 判断strings[i]是否已经成为其他键的值，是就返回false
                if (map.containsValue(strings[i])) {
                    return false;
                }
                // 将Map中不存在的键值对存入
                map.put(c, strings[i]);
            }
        }
        return true;

    }
}
