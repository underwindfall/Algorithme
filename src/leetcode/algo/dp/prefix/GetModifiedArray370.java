package leetcode.algo.dp.prefix;

import java.util.Arrays;

// https://leetcode-cn.com/problems/range-addition/
public class GetModifiedArray370 {
    class PrefixSum {
        // B(n) = A(n) - A(n - 1)
        // 若是每个updates indice = array[2] 都会又一个indice去更新
        // B(n) = A(n) + indice - (A(n - 1)) <==> B(n) - indice = A(n) - A(n - 1)
        public int[] getModifiedArray(int length, int[][] updates) {
            // 差分数组 b(n) = a(n) - a(n-1) 类比理解a(n) = S(n) - S(n-1)
            int[] ans = new int[length];
            for (int[] array : updates) {
                int startIndex = array[0], endIndex = array[1], indice = array[2];
                ans[startIndex] += indice;
                // 最后一个元素没有后继了，需要特判一下
                if (endIndex < length - 1) {
                    ans[endIndex + 1] -= indice;
                }
            }

            // a(n)就是b(n)的前n项和
            // 利用差分数组求原数组
            for (int i = 1; i < ans.length; i++) {
                ans[i] = ans[i - 1] + ans[i];
            }
            return ans;
        }
    }

    class BruteForce {
        public int[] getModifiedArray(int length, int[][] updates) {
            int[] ans = new int[length];
            for (int[] array : updates) {
                int startIndex = array[0], endIndex = array[1], inc = array[2];
                for (int i = startIndex; i <= endIndex; i++) {
                    ans[i] += inc;
                }
            }
            return ans;
        }
    }

    class Explore {
        public int[] getModifiedArray(int length, int[] origin, int[][] updates) {
            // 差分数组 b(n) = a(n) - a(n-1) 类比理解a(n) = S(n) - S(n-1)
            int[] ans = new int[length];
            for (int[] array : updates) {
                int startIndex = array[0], endIndex = array[1], indice = array[2];
                ans[startIndex] += indice;
                // 最后一个元素没有后继了，需要特判一下
                if (endIndex < length - 1) {
                    ans[endIndex + 1] -= indice;
                }
               // System.out.println("===="+ Arrays.toString(ans));
            }

            // a(n)就是b(n)的前n项和
            // 利用差分数组求原数组
            int[] result = origin;
            for (int i = 1; i < ans.length; i++) {
                ans[i] = ans[i - 1] + ans[i];   
                result[i] = result[i - 1] + ans[i - 1];
            }
            return result;
        }
    }

    public static void main(String[] args) {
        int[][] updates = new int[][] {
                           //  1, 2, 3, 4, 5
              { 1, 3, 2 }, //  1, 4, 5, 6, 5       [0, 2, 0, 0, -2]  ====[-2, 0, 3, 2, -2]
              { 2, 4, 3 }, //  1, 4, 8, 9, 8       [0, 2, 3, 0, -2]  ====[-2, 0, 3, 2, -2]
              { 0, 2, -2 } // -1, 2, 6, 9, 8       [-2, 2, 3, 2, -2] ====[-2, 0, 3, 5,  3]
        };
        GetModifiedArray370 instance = new GetModifiedArray370();
        int[] result = instance.new Explore().getModifiedArray(5, new int[] { 1, 2, 3, 4, 5 }, updates);
        System.out.println(Arrays.toString(result));
    }
}
