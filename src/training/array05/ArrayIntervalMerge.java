package training.array05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode-cn.com/leetbook/read/array-and-string/c5tv3/
// 给出一个区间的集合，请合并所有重叠的区间。

//  

// 示例 1:

// 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
// 输出: [[1,6],[8,10],[15,18]]
// 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 示例 2:

// 输入: intervals = [[1,4],[4,5]]
// 输出: [[1,5]]
// 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
// 注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。

//  

// 提示：

// intervals[i][0] <= intervals[i][1]


//TODO ： 复习+看其他的解决方案
public class ArrayIntervalMerge {
    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; i++) {
            int L = intervals[i][0], R = intervals[i][1];
            // 不merge状况，因为他的右边R比新的左边L要小
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[] { L, R });
            } else {
                // merged的右边R为新加入的R和merged大小比较
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        int[][] result = merge(new int[][] { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } });
        for (int i = 0; i < result.length; i++) {
            StringBuilder stringBuilder = new StringBuilder("");
            for (int j = 0; j < result[i].length; j++) {
                stringBuilder.append(result[i][j] + ",");
            }
            System.out.println(stringBuilder + "\n");
        }
    }

}
