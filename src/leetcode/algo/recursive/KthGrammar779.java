package leetcode.algo.recursive;

public class KthGrammar779 {
    // 第 K 个数字是上一行第 (K+1) / 2 个数字生成的。如果上一行的数字为 0，被生成的数字为 1 - (K%2)，如果上一行的数字为
    // 1，被生成的数字为 K%2
    public int kthGrammar(int N, int K) {
        if (N == 1)
            return 0;
        int N_1 = kthGrammar(N - 1, (K + 1) / 2);
        if (N_1 == 0) {
            return 1 - K % 2;
        } else {
            return K % 2;
        }
    }

    // 可以看出，如果 K 小于等于 所在行的一半，也就是2^N-2，那么直接去上一行找，也就是下面的helper(N-1, K, flag);
    // 反之，要从后半部分找，而后半部分，正是01翻转后的前一行，也就是helper(N-1, K - half, 1-flag)。

    class anothersolution {
        int kthGrammar(int N, int K) {
            return helper(N, K, 0);
        }

        int helper(int N, int K, int flag) {
            if (N == 1)
                return flag;
            int half = (1 << (N - 2));
            if (K <= half) {
                return helper(N - 1, K, flag);
            } else {
                return helper(N - 1, K - half, 1 - flag);
            }
        }

    }
}
