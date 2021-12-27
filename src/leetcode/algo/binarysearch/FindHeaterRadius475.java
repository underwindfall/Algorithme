package leetcode.algo.binarysearch;

import java.util.Arrays;

//time O(n * logN)
//espace O(1)
public class FindHeaterRadius475 {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int l = 1, r = (int) (1e9);
        while (l < r) {
            int mid = (r - l) / 2 + l;
            if (check(houses, heaters, mid)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    boolean check(int[] houses, int[] heaters, int radius) {
        int n = houses.length, m = heaters.length;
        for (int i = 0, j = 0; i < n; i++) {
            while (j < m && houses[i] > heaters[j] + radius)
                j++;
            if (j < m && heaters[j] - radius <= houses[i] && houses[i] <= heaters[j] + radius)
                continue;
            return false;
        }
        return true;
    }
}
