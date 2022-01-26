package interview.datadog;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/path-sum-iv/
public class PathSumIV666 {

    // time O(n)
    // space O(n)
    // For node xy? its left child is (x+1)(y*2-1)? and right child is (x+1)(y*2)?
    class BuildTreeNode {
        // 1 -> Depth
        // 2 -> index
        // 3 -> value
        int ans = 0;

        public int pathSum(int[] nums) {
            TreeNode root = new TreeNode(nums[0] % 10);
            for (int num : nums) {
                if (num == nums[0])
                    continue;
                int depth = num / 100, pos = num / 10 % 10, val = num % 10;
                // System.out.println("====="+pos);
                pos--;
                // 每次都会从root开始， 所以才会把curr 进行判断left right
                TreeNode cur = root;
                for (int d = depth - 2; d >= 0; d--) {
                    if (pos < (int) (Math.pow(2, d))) {
                        if (cur.left == null)
                            cur.left = new TreeNode(val);
                        cur = cur.left;
                    } else {
                        if (cur.right == null)
                            cur.right = new TreeNode(val);
                        cur = cur.right;
                    }
                    // System.out.println(pos + "====d===" + d + "====curr==" + cur.val);
                    // 这里很难想 这里pos等于说是通过pos 来轮训到当前真正的depth的index值
                    pos %= (int) (Math.pow(2, d));
                }
            }
            dfs(root, 0);
            return ans;
        }

        void dfs(TreeNode node, int sum) {
            if (node == null) {
                return;
            }
            sum += node.val;
            if (node.left == null && node.right == null) {
                ans += sum;
                return;
            }
            dfs(node.left, sum);
            dfs(node.right, sum);
        }

        class TreeNode {
            int val;
            TreeNode left, right;

            TreeNode(int val) {
                this.val = val;
            }

            TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }
    }

    // time O(n)
    // space O(n)
    // https://leetcode.com/problems/path-sum-iv/discuss/106892/Java-solution-Represent-tree-using-HashMap
    class MapSolution {
        int sum = 0;
        Map<Integer, Integer> tree = new HashMap<>();

        // 1 -> Depth
        // 2 -> index
        // 3 -> value
        public int pathSum(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            for (int num : nums) {
                int key = num / 10;
                int value = num % 10;
                tree.put(key, value);
            }
            dfs(nums[0] / 10, 0);
            return sum;
        }

        void dfs(int root, int presum) {
            int depth = root / 10;
            int pos = root % 10;
            int left = (depth + 1) * 10 + pos * 2 - 1;
            int right = (depth + 1) * 10 + pos * 2;
            
            int currSum = presum + tree.get(root);
            if (!tree.containsKey(left) && !tree.containsKey(right)) {
                sum += currSum;
                return;
            }
            if (tree.containsKey(left)) dfs(left, currSum);
            if (tree.containsKey(right)) dfs(right, currSum);
        }
    }
}
