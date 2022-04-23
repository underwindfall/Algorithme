package leetcode.algo.prefixsum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode-cn.com/problems/intervals-between-identical-elements/solution/qian-zhui-he-ha-xi-by-gnomeshgh_plus-hsfd/
public class IntervalsBetweenElements2121 {
    //re1[i] = re1[pro] + (距离)*个数 前缀计算公式
    public long[] getDistances(int[] arr) {
        //前缀，<key,val>表示值为key的前面一个相同的下标为val[0]，相同的个数为val[1]
        Map<Integer, int[]> m1 = new HashMap<>();
        int n = arr.length;
        long[] re1 = new long[n];
        for (int i = 0; i < n; i++) {
            int[] orDefault = m1.getOrDefault(arr[i], new int[2]);
            //当其前面有与他下相同的时候。相同的下标为ordefaule[0],相同了几个为orderfault[1]
            if (orDefault[1] != 0) {
                re1[i] += re1[orDefault[0]] + (i - orDefault[0]) * orDefault[1];
            }
            orDefault[0] = i;
            orDefault[1]++;
            m1.put(arr[i], orDefault);
        }
        //后缀
        Map<Integer, int[]> m2 = new HashMap<>();
        long[] re2 = new long[n];
        for (int i = n - 1; i >= 0; i--) {
            int[] orDefault = m2.getOrDefault(arr[i], new int[2]);
            //当其后面有与他下相同的时候。相同的下标为ordefaule[0],相同了几个为orderfault[1]
            if (orDefault[1] != 0) {
                re2[i] += re2[orDefault[0]] + (orDefault[0] - i) * orDefault[1];
            }
            orDefault[0] = i;
            orDefault[1]++;
            m2.put(arr[i], orDefault);
        }
        long[] re = new long[n];
        for (int i = 0; i < n; i++) {
            re[i] = re1[i]+re2[i];
        }
        return re;
    }




    class Loop {
        public long[] getDistances(int[] arr) {
            Map<Integer, List<Integer>> m = new HashMap<>();
            int length = arr.length;
            long[] re = new long[length];

            for (int i = 0; i < length; i++) {
                List<Integer> orDefault = m.getOrDefault(arr[i], new ArrayList<>());
                int size = orDefault.size();
                for (int j = 0; j < size; j++) {
                    re[orDefault.get(j)] += i - orDefault.get(j);
                    re[i] += i - orDefault.get(j);
                }
                orDefault.add(i);
                m.put(arr[i], orDefault);
            }
            return re;
        }

    }
}
