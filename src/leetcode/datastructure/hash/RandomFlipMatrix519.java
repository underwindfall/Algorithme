package leetcode.datastructure.hash;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

//https://leetcode-cn.com/problems/random-flip-matrix/
//time O(n*m)
//space O(n*m)
public class RandomFlipMatrix519 {
    int m, n;
    Set<Integer> set = new HashSet<>();
    Random random = new Random(300);

    public RandomFlipMatrix519(int _m, int _n) {
        m = _m;
        n = _n;
    }

    public int[] flip() {
        int a = random.nextInt(m * n), b = a;
        while (a >= 0 && set.contains(a))
            a--;
        while (b < m * n && set.contains(b))
            b++;
        int c = a >= 0 && !set.contains(a) ? a : b;
        set.add(c);
        return new int[] { c / n, c % n };
    }

    public void reset() {
        set.clear();
    }
}
