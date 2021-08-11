package leetcode.datastructure.hash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SuppressWarnings("unchecked")
//https://leetcode-cn.com/problems/sort-characters-by-frequency/
public class FrequencySort451 {
    // time O(n+klogk)
    // espace O(n+k)
    class MapSort {
        public String frequencySort(String s) {
            Map<Character, Integer> map = new HashMap<>();
            int length = s.length();
            for (int i = 0; i < length; i++){
                char c = s.charAt(i);
                int frequency = map.getOrDefault(c, 0) + 1;
                map.put(c, frequency);
            }
            List<Character> list = new ArrayList<>(map.keySet());
            Collections.sort(list, (a, b) -> map.get(b) - map.get(a));
            StringBuilder sBuilder = new StringBuilder();
            int size  = list.size();
            for (int i = 0; i < size; i++){
                char c = list.get(i);
                int frequency = map.get(c);
                for (int j = 0; j < frequency; j++){
                    sBuilder.append(c);
                }
            }
            return sBuilder.toString();
        }
    }

    // time O(n)
    // epsace O(n)
    class BucketSort {
        public String frequencySort(String s) {
            if (s.isEmpty() || s.length() == 1) {
                return s;
            }
            // 构造HashMap Key s重的每个元素 Value 元素出现的次数
            Map<Character, Integer> store = new HashMap<>();
            for (char c : s.toCharArray()) {
                // 填充 HashMap。如果当前 Key c 不存在，getOrDefault() 方法返回默认值 0；
                // 否则返回当前 Key c 对应的 Value。
                // 不管哪种情况最后都在 0 或者 Value 的基础上 +1。
                store.put(c, store.getOrDefault(c, 0) + 1);
            }
            // 构造一个桶的集合（一系列桶） 桶的个数s的长度 + 1，因为buckets【0】没有意义
            // 目的是将出现频率为 i 的字符放到第 i 个桶里（即 buckets[i]）
            List<Character> buckets[] = new List[s.length() + 1];
            for (char key : store.keySet()) {
                // 某个字符在hashmap中value是几就放到第几个桶利
                int value = store.get(key);
                if (buckets[value] == null) {
                    // 如果某个桶还未放入过字符（即未初始化），则初始化其为一个数组
                    buckets[value] = new ArrayList<Character>();
                }
                buckets[value].add(key);
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = buckets.length - 1; i > 0; i--) {
                // 遍历每个桶
                if (buckets[i] != null) {
                    // 如果桶中又字符
                    for (char j : buckets[i]) {
                        // 遍历桶里面的每个字符
                        for (int k = i; k > 0; k--) {
                            // 字符出现了几次就向 res 中添加几次该字符
                            stringBuilder.append(j);
                        }
                    }
                }
            }
            return stringBuilder.toString();
        }
    }
}
