package interview.datadog;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/path-sum-iii/
public class PathSumIII437 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 这道题引入了将递归发挥了一下
    // 搞了双重递归
    // 理由 1. 他可以从root开头也可以不开头
    // 理由 2. 每个节点作为根结点时候，都可以通过相加来获取目标值
    // time O(N^2)
    // espace O(N*logN)
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int num = pathSumStartWithRootNode(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
        return num;
    }

    int pathSumStartWithRootNode(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        if (root.val == sum) {
            count++;
        }
        count += pathSumStartWithRootNode(root.left, sum - root.val)
                + pathSumStartWithRootNode(root.right, sum - root.val);
        return count;
    }

    // https://leetcode-cn.com/problems/path-sum-iii/solution/qian-zhui-he-di-gui-hui-su-by-shi-huo-de-xia-tian/
    class PrefixSum {
        public int pathSum(TreeNode root, int sum) {
            // key是前缀和, value是大小为key的前缀和出现的次数
            Map<Integer, Integer> prefixSumCount = new HashMap<>();
            // 前缀和为0的一条路径
            prefixSumCount.put(0, 1);
            // 前缀和的递归回溯思路
            return recursionPathSum(root, prefixSumCount, sum, 0);
        }

        /**
         * 前缀和的递归回溯思路 从当前节点反推到根节点(反推比较好理解，正向其实也只有一条)，有且仅有一条路径，因为这是一棵树
         * 如果此前有和为currSum-target,而当前的和又为currSum,两者的差就肯定为target了
         * 所以前缀和对于当前路径来说是唯一的，当前记录的前缀和，在回溯结束，回到本层时去除，保证其不影响其他分支的结果
         * 
         * @param node           树节点
         * @param prefixSumCount 前缀和Map
         * @param target         目标值
         * @param currSum        当前路径和
         * @return 满足题意的解
         */
        private int recursionPathSum(TreeNode node, Map<Integer, Integer> prefixSumCount, int target, int currSum) {
            // 1.递归终止条件
            if (node == null) {
                return 0;
            }
            // 2.本层要做的事情
            int res = 0;
            // 当前路径上的和
            currSum += node.val;

            // ---核心代码
            // 看看root到当前节点这条路上是否存在节点前缀和加target为currSum的路径
            // 当前节点->root节点反推，有且仅有一条路径，如果此前有和为currSum-target,而当前的和又为currSum,两者的差就肯定为target了
            // currSum-target相当于找路径的起点，起点的sum+target=currSum，当前点到起点的距离就是target
            res += prefixSumCount.getOrDefault(currSum - target, 0);
            // 更新路径上当前节点前缀和的个数
            prefixSumCount.put(currSum, prefixSumCount.getOrDefault(currSum, 0) + 1);
            // ---核心代码

            // 3.进入下一层
            res += recursionPathSum(node.left, prefixSumCount, target, currSum);
            res += recursionPathSum(node.right, prefixSumCount, target, currSum);

            // 4.回到本层，恢复状态，去除当前节点的前缀和数量
            prefixSumCount.put(currSum, prefixSumCount.get(currSum) - 1);
            return res;
        }

    }
}
