package leetcode.algo.lrucache;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// https://leetcode-cn.com/problems/maximum-frequency-stack/
// https://mp.weixin.qq.com/s?__biz=MzU4NDE3MTEyMA==&mid=2247486856&idx=1&sn=59b2ed57e4a75eac0e63fc0cf08bed5d
public class FreqStack895 {
    // time O(1)
    // space O(N)
    Map<Integer, Integer> freq;
    Map<Integer, Stack<Integer>> group;
    int maxFreq;

    public FreqStack895() {
        freq = new HashMap<>();
        group = new HashMap<>();
        maxFreq = 0;
    }

    public void push(int val) {
        int f = freq.getOrDefault(val, 0) + 1;
        freq.put(val, f);
        if (f > maxFreq)
            maxFreq = f;
        if (!group.containsKey(f)) {
            group.put(f, new Stack<>());
        }
        group.get(f).push(val);
    }

    public int pop() {
        int x = group.get(maxFreq).pop();
        freq.put(x, freq.get(x) - 1);
        if (group.get(maxFreq).size() == 0)
            maxFreq--;
        return x;
    }
}
