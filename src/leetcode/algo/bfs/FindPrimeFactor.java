package leetcode.algo.bfs;

import java.util.ArrayList;
import java.util.List;

public class FindPrimeFactor {
    List<Integer> res = new ArrayList<>();

    int prime = 2;

    void findPrimeFactor(int n) {
        if (n == 1)
            return;
        if (n % prime != 0) {
            while (n % prime != 0) {
                prime++;
            }
        }
        
        if (n % prime == 0) {
            res.add(prime);
            findPrimeFactor(n / prime);
        }
    }

    public static void main(String[] args) {
        FindPrimeFactor oPrimeFactor = new FindPrimeFactor();
        oPrimeFactor.findPrimeFactor(10);
        System.out.println(oPrimeFactor.res);
    }
}
