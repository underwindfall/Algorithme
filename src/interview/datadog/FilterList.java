package interview.datadog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FilterList {
    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input.add("apple, facebook, google");
        input.add("banana, facebook");
        input.add("facebook, google, tesla");
        input.add("intuit, google, facebook");

        for (String s : input) {
            System.out.printf("input %s \n", s);
        }

        List<String> res1 = filterListLoop(input, "apple");
        for (String s : res1) {
            System.out.printf("filterListLoop output %s \n", s);
        }
        System.out.println("===========\n");
        List<String> res2 = filterListLoop(input, "facebook, google");
        for (String s : res2) {
            System.out.printf("filterListLoop output %s \n", s);
        }

        System.out.println("---------------------\n");

        List<String> res3 = filterReverseIndex(input, "apple");
        for (String s : res3) {
            System.out.printf("filterReverseIndex output %s \n", s);
        }

        System.out.println("===========\n");
        List<String> res4 = filterReverseIndex(input, "facebook, google");
        for (String s : res4) {
            System.out.printf("filterReverseIndex output %s \n", s);
        }
    }

    // time O(n^3)
    // space O(n)
    // https://zh.wikipedia.org/zh-hans/%E5%80%92%E6%8E%92%E7%B4%A2%E5%BC%95?oldformat=true
    static List<String> filterReverseIndex(List<String> input, String keyword) {
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < input.size(); i++) {
            String word = input.get(i);
            String[] strings = word.split(",");
            for (String s : strings) {
                s = s.trim();
                if (map.containsKey(s)) {
                    map.get(s).add(i);
                } else {
                    map.put(s, new ArrayList<>());
                    map.get(s).add(i);
                }
            }
        }

        String[] keywords = keyword.split(",");
        Set<String> keys = new HashSet<>();
        for (String k : keywords) {
            k = k.trim();
            keys.add(k);
        }

        Set<Integer> set = new HashSet<>();
        for (String key : keys) {
            set = inCommon(set, map.get(key));
        }
        Set<String> res = new HashSet<>();

        for (int index : set) {
            String common = input.get(index);
            for (String c : common.split(",")) {
                c = c.trim();
                if (!keys.contains(c)) {
                    res.add(c.trim());
                }
            }
        }

        return new ArrayList<>(res);
    }

    private static Set<Integer> inCommon(Set<Integer> set, List<Integer> list) {
        Set<Integer> res = new HashSet<>();
        if (set.isEmpty()) {
            res.addAll(list);
        } else {
            for (int tmp : set) {
                if (list.contains(tmp)) {
                    res.add(tmp);
                }
            }
        }
        return res;
    }

    // time O(n^3)
    // space O(n)
    static List<String> filterListLoop(List<String> input, String keyword) {
        String[] keys = keyword.split(",");
        Set<String> res = new HashSet<>();

        for (int i = 0; i < input.size(); i++) {
            String tmp = input.get(i);
            int count = 0;
            for (String key : keys) {
                if (tmp.contains(key.trim())) {
                    count++;
                }
            }
            if (count >= keys.length) {
                String[] tmpWord = tmp.split(",");
                Set<String> sBuilder = new HashSet<>();
                for (String t : tmpWord) {
                    for (String key : keys) {
                        if (t.trim().equalsIgnoreCase(key.trim())) {
                            continue;
                        }
                        sBuilder.add(t.trim());
                    }
                }
                res.addAll(sBuilder);
            }

        }

        for (String key : keys) {
            if (res.contains(key.trim())) {
                res.remove(key.trim());
            }
        }
        return new ArrayList<>(res);
    }
}
