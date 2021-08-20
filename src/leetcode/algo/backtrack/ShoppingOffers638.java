package leetcode.algo.backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode-cn.com/problems/shopping-offers/
public class ShoppingOffers638 {

    class Backtrack {
        public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
            Map<String, Integer> memo = new HashMap<>();
            return recurse(price, special, needs, memo);
        }

        private int recurse(List<Integer> price, List<List<Integer>> special, List<Integer> needs,
                Map<String, Integer> memo) {
            // check in dp table by creating unique string
            String key = getKey(needs);
            if (memo.containsKey(key))
                return memo.get(key);

            // 1. fulfill the needs without any offers
            int res = dot(price, needs);

            // 2. fulfill the needs using the offers
            for (List<Integer> offer : special) {

                // copy the needs array
                List<Integer> clone = new ArrayList<>(needs);

                // appply the offer on clone
                int i = 0;
                for (i = 0; i < needs.size(); i++) {
                    clone.set(i, needs.get(i) - offer.get(i));
                    if (clone.get(i) < 0)
                        break;
                }

                // find answer for the second case
                if (i == needs.size())
                    res = Math.min(res, offer.get(offer.size() - 1) + recurse(price, special, clone, memo));
            }

            memo.put(key, res);
            return res;
        }

        private int dot(List<Integer> a, List<Integer> b) {
            // calculate dot product of two arrays
            int res = 0;
            for (int i = 0; i < a.size(); i++)
                res += (a.get(i) * b.get(i));

            return res;
        }

        private String getKey(List<Integer> arr) {
            // convert array to a unique string
            StringBuilder sb = new StringBuilder();
            for (int ele : arr)
                sb.append(ele);
            return sb.toString();
        }
    }

    class DFS {
        public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
            // specical (i, j) specical.get(i) === n + 1 (include j)
            int res = 0;
            for (int i = 0; i < needs.size(); i++) {
                res += needs.get(i) * price.get(i);
            }
            for (List<Integer> item : special) {
                List<Integer> clone = new ArrayList<>(needs);
                int j;
                for (j = 0; j < needs.size(); j++) {
                    int diff = clone.get(j) - item.get(j);
                    if (diff < 0) {
                        // need < special buy special is ok
                        break;
                    }
                    // the rest need
                    clone.set(j, diff);
                }

                if (j == needs.size()) {
                    res = Math.min(res, item.get(j) + shoppingOffers(price, special, clone));
                }
            }
            return res;
        }
    }
}
