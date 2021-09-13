package leetcode.datastructure.hash;

import java.util.HashMap;
import java.util.Map;

//https://leetcode-cn.com/problems/number-of-boomerangs/
public class NumberOfBoomerangs447 {

    // time O(n^2)
    // space O(N)
    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if (i == j)
                    continue;
                int x = points[j][0] - points[i][0];
                int y = points[j][1] - points[i][1];
                int distance = x * x + y * y;
                map.put(distance, map.getOrDefault(distance, 0) + 1);
            }
            for (int key : map.keySet()) {
                int n = map.get(key);
                res += n * (n - 1);
            }
        }
        return res;
    }
}
