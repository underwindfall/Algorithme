package interview.datadog;

import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/problems/interval-list-intersections/
public class IntervalInterSection986 {

    // time O(n + m)
    // space O(n + m)
    class MergeInterval {
        public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
            List<int[]> ans = new ArrayList<>();
            int i = 0, j = 0;
            while (i < firstList.length && j < secondList.length) {
                int lo = Math.max(firstList[i][0], secondList[j][0]);
                int ho = Math.min(firstList[i][1], secondList[j][1]);
                if (lo <= ho) {
                    ans.add(new int[] { lo, ho });
                }
                if (firstList[i][1] < secondList[j][1]) {
                    i++;
                } else {
                    j++;
                }
            }

            return ans.toArray(new int[ans.size()][]);
        }
    }

    // time O(n + m)
    // space O(n + m)
    class Simulation {
        public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
            List<int[]> ans = new ArrayList<>();
            int i = 0, j = 0;
            while (i < firstList.length && j < secondList.length) {
                int first_left = firstList[i][0], first_right = firstList[i][1];
                int second_left = secondList[j][0], second_right = secondList[j][1];

                if (first_left > second_right) {// 列表1区间在列表2区间右边，没有交集
                    j++;
                    continue;
                }
                if (second_left > first_right) {// 列表2区间在列表1区间右边，没有交集
                    i++;
                    continue;
                }

                // 1、列表1区间左边和列表2区间右边有交集
                if (first_left >= second_left && first_left <= second_right && first_right > second_right) {
                    ans.add(new int[] { first_left, second_right });
                    j++;
                    continue;
                }
                // //2、覆盖的两种情况
                // 1覆盖2
                if (first_left <= second_left && first_right >= second_right) {
                    ans.add(new int[] { second_left, second_right });
                    j++;
                    continue;
                } else if (second_left <= first_left && second_right >= first_right) {
                    ans.add(new int[] { first_left, first_right });
                    i++;
                    continue;
                }
                // 3、列表1区间右边和列表2区间左边有重合
                if (first_right >= second_left && first_right <= second_right) {
                    ans.add(new int[] { second_left, first_right });
                    i++;
                }

            }
            return ans.toArray(new int[ans.size()][]);
        }

    }

}
