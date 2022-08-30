package leetcode.algo.greedy;

import java.util.Arrays;

// https://leetcode.cn/problems/reorganize-string/
public class ReorganizeString729 {
    // 1. 将相同的字符放入不同的桶中以保证其彼此不会相邻，因此桶的数目应等于字符串中最多的元素的数目；
    // 2.
    // 按贪心策略，优先填充数目最多的元素，对于每一种元素，循环在不同桶中进行填充，由于桶的个数等于字符串中最多的元素的数目，因此每个桶中不会出现相同的元素，填充完毕后将桶依次相连即为答案；
    // 3.
    // 若填充完毕后长度为1的桶（只可能出现在最后的位置）的数目多于1，将桶依次相连会使得这些长度为1的桶中的相同元素相邻，说明不存在相应的排列，返回""（这一情况即官解中说明的如果存在一个字母的出现次数大于(n+1)/2，其中n为字符串长度，则无法重新排布字母使得相邻的字母都不相同）。
    // https://leetcode.cn/problems/reorganize-string/solution/by-zhug-4-p182/
    public String reorganizeString(String s) {

        int n = s.length();
        int[][] count = new int[26][2];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            count[c - 'a'][0] = c;
            count[c - 'a'][1]++;
        }
        Arrays.sort(count, (a, b) -> b[1] - a[1]);
        int max = count[0][1];
        // 合法性判断
        if (max > (n + 1) / 2)
            return "";

        StringBuilder[] buckets = new StringBuilder[max];
        for (int i = 0; i < max; i++) {
            buckets[i] = new StringBuilder();
        }

        // 获取所有桶
        int index = 0;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < count[i][1]; j++) {
                buckets[index].append((char) count[i][0]);
                index = (index + 1) % max;
            }
        }

        // 拼接
        StringBuilder ret = new StringBuilder();
        Arrays.stream(buckets).forEach(b -> {
            ret.append(b);
        });

        return ret.toString();
    }
}
