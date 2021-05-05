package leetcode.algo.dp.subsquence;

// https://leetcode-cn.com/problems/shortest-way-to-form-string/
public class ShortestWay1055 {
    /**
     * 从头到尾比较 target中重复source的次数就是answer
     */
    class DoubleIndex {
        public int shortestWay(String source, String target) {
            int sourceIndex = 0;// source 指针 source的指针（指的是被重复使用的子串的指针，当循环到尾巴后会直接从头开始新的循环）
            int targetIndex = 0;// target指针
            int repeatTimes = 0;

            while (targetIndex < target.length() && sourceIndex < source.length()) {
                if (!source.contains(String.valueOf(target.charAt(targetIndex)))) {
                    return -1;
                }

                if (source.charAt(sourceIndex) == target.charAt(targetIndex)) {
                    sourceIndex++;
                    targetIndex++;
                } else {
                    sourceIndex++;// 当不匹配时，直接考察源串下一个子串
                }

                if (sourceIndex == source.length()) {
                    repeatTimes++;// 已经用完了一个子串，接着从一个新的子串开始
                    sourceIndex = 0;// 新的指针
                }
            }

            if (sourceIndex != 0) {
                repeatTimes++;
            }

            return repeatTimes;
        }
    }

    class DP {
        public int shortestWay(String source, String target) {
            int length = target.length();
            if (length == 0)
                return 0;

            int[] dp = new int[length];
            char[] char_source = source.toCharArray();
            char[] char_target = target.toCharArray();
            // 以 target[i] 结尾的最长的子序
            String[] sub = new String[length];
            if (charIsContain(char_source, char_target[0])) {
                dp[0] = 1;
                sub[0] = char_target[0] + "";
            } else {
                return -1;
            }
            //排除target中存在非source能组成的特殊字符
            for (int i = 0; i < length; i++) {
                if (!charIsContain(char_source, char_target[i]))
                    return -1;
            }
            for (int i = 1; i < length; i++) {
                String tailSubString = sub[i - 1] + char_target[i];
                //含有的话就是是dp(i) == dp(i - 1)
                if (isContain(char_source, tailSubString.toCharArray())) {
                    dp[i] = dp[i - 1];
                    sub[i] = tailSubString;
                } else {
                    //否则就是+1
                    //并且要把当前不匹配的值并入最后
                    dp[i] = dp[i - 1] + 1;
                    sub[i] = char_target[i] + "";
                }
            }

            return dp[length - 1];
        }

        public boolean isContain(char[] source, char[] sub) {

            if (sub.length > source.length)
                return false;
            int count = 0;

            int staSub = 0;
            for (int j = 0; j < source.length; j++) {
                if (sub[staSub] == source[j]) {
                    count++;
                    staSub++;
                    if (count == sub.length) {
                        return true;
                    }
                }
            }
            if (count == sub.length) {
                return true;
            } else {
                return false;
            }

        }

        public boolean charIsContain(char[] source, char sub) {
            for (int i = 0; i < source.length; i++) {
                if (source[i] == sub) {
                    return true;
                }
            }
            return false;
        }

    }
}
