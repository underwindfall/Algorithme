package swordoffer;

//https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof/
public class LCOF68_I {
    // time O(n)
    // space O(n)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 终止条件：不需要，因为官方给了均存在与树中的条件
        // 即老子现在肯定是这两个乖孙子的公共祖先
        // 递推操作：
        // 第一种情况：
        if (root.val > p.val && root.val > q.val) {// 看看是不是都是左儿子的后代
            return lowestCommonAncestor(root.left, p, q);// 是的话就丢给左儿子去认后代（继续递归）
        }
        // 第二种情况：
        if (root.val < p.val && root.val < q.val) {// 不是左儿子的后代，看看是不是都是右儿子的后代
            return lowestCommonAncestor(root.right, p, q);// 是的话就丢给右儿子去认后代（继续递归）
        }
        // 第三种情况：
        // 左儿子和右儿子都说我只认识一个，唉呀妈呀，那就是老子是你们最近的祖先，因为老子本来就是你们的公共的祖先
        // 现在都只认一个，那就是老子是最近的。
        // 其实第三种才是题目需要找到的解，所以返回，拜托我的祖先们也传一下（递归回溯返回结果），我才是他们最近的公共曾爷爷
        return root;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
