package leetcode.algo.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KaratBrowserHistory {
    /**
     * We have some clickstream data that we gathered on our client's website. Using
     * cookies, we collected snippets of users' anonymized URL histories while they
     * browsed the site. The histories are in chronological order and no URL was
     * visited more than once per person.
     * Write a function that takes two user’s browsing histories as input and
     * returns the longest contiguous sequence of URLs that appears in both.
     * Sample input:
     * user0 = [ "/start.html", "/pink.php", "/register.asp", "/orange.html",
     * "/red.html" ]
     * user1 = [ "/red.html", "/green.html", "/blue.html", "/pink.php",
     * "/register.asp" ]
     * user2 = [ "/start.html", "/green.html", "/blue.html", "/pink.php",
     * "/register.asp",
     *           "/orange.html" ]
     * user3 = [ "/blue.html", "/logout.php" ]
     * Sample output:
     * f(user0, user2):
     *    /pink.php
     *    /register.asp
     *    /orange.html
     * f(user1, user2):
     *    /green.html
     *    /blue.html
     *    /pink.php
     *    /register.asp
     * f(user0, user3):
     *    (empty)
     * f(user1, user3):
     *    /blue.html
     */

    public static void main(String[] args) {
        String[] user0 = { "/start", "/green", "/blue", "/pink", "/register", "/orange", "/one/two" };
        String[] user1 = { "/start", "/pink", "/register", "/orange", "/red", "a" };
        String[] user2 = { "a", "/one", "/two" };
        String[] user3 = { "/pink", "/orange", "/yellow", "/plum", "/blue", "/tan", "/red", "/amber", "/HotRodPink",
                "/CornflowerBlue", "/LightGoldenRodYellow", "/BritishRacingGreen" };
        String[] user4 = { "/pink", "/orange", "/amber", "/BritishRacingGreen", "/plum", "/blue", "/tan", "/red",
                "/lavender", "/HotRodPink", "/CornflowerBlue", "/LightGoldenRodYellow" };
        String[] user5 = { "a" };
        String[] user6 = { "/pink", "/orange", "/six", "/plum", "/seven", "/tan", "/red", "/amber" };

        System.out.println(findContiguousHistory(user0, user1));
        System.out.println(findContiguousHistory(user0, user2));
        System.out.println(findContiguousHistory(user2, user1));
        System.out.println(findContiguousHistory(user5, user2));
        System.out.println(findContiguousHistory(user3, user4));
        System.out.println(findContiguousHistory(user4, user3));
        System.out.println(findContiguousHistory(user3, user6));

        System.out.println("================================");
        System.out.println(findContiguousHistoryDp(user0, user1));
        System.out.println(findContiguousHistoryDp(user0, user2));
        System.out.println(findContiguousHistoryDp(user2, user1));
        System.out.println(findContiguousHistoryDp(user5, user2));
        System.out.println(findContiguousHistoryDp(user3, user4));
        System.out.println(findContiguousHistoryDp(user4, user3));
        System.out.println(findContiguousHistoryDp(user3, user6));
    }

    // time O(n)
    // space O(n)
    static List<String> findContiguousHistoryDp(String[] user1, String[] user2) {
        List<String> result = new ArrayList<>();

        if (user1.length == 0 || user1.length == 0) {

            return result;
        }

        int dp[][] = new int[user1.length + 1][user2.length + 1];

        int max = Integer.MIN_VALUE;
        int endIndex = -1;

        for (int i = user1.length - 1; i >= 0; i--) {
            for (int j = user2.length - 1; j >= 0; j--) {

                if (user1[i] == user2[j]) {

                    // System.out.println(Arrays.deepToString(dp) +"before");
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                    // System.out.println(Arrays.deepToString(dp));

                    if (max < dp[i][j]) {
                        max = dp[i][j];
                        endIndex = j;
                    }
                    break;
                }
            }
        }

        if (max == Integer.MIN_VALUE) {
            return result;
        }

        for (int i = endIndex; i < endIndex + max; i++) {

            result.add(user2[i]);
        }

        return result;

    }

    // time O(n^2)
    // space O(n)
    static List<String> findContiguousHistory(String[] array1, String[] array2) {
        List<String> res = new ArrayList<>();

        String[] longArr = array1;
        String[] shortArr = array2;

        if (array1.length < array2.length) {
            longArr = array2;
            shortArr = array1;
        } else {
            longArr = array1;
            shortArr = array2;
        }

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < longArr.length; i++) {
            map.put(longArr[i], i);
        }

        for (int i = 0; i < shortArr.length; i++) {
            List<String> curr = new ArrayList<>();
            String str = shortArr[i];
            if (map.containsKey(str)) {
                int index = map.get(str);
                int k = i;
                while (index < longArr.length && k < shortArr.length) {
                    if (longArr[index].equalsIgnoreCase(shortArr[k])) {
                        curr.add(shortArr[k]);
                    } else {
                        break;
                    }
                    index++;
                    k++;
                }
                if (res.size() < curr.size()) {
                    res = curr;
                }
            }
        }
        return res;
    }
}
