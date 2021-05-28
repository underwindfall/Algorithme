package leetcode.datastructure.tree;

import java.util.Arrays;

//https://leetcode-cn.com/problems/construct-binary-search-tree-from-preorder-traversal/
public class BstFromPreorder1008 {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 想法就是把preorder 重新排序 + 要构建的是BST 所以 排序后的数组就是inorder
    // 题目可以转换成105题 preorder+inorder构建数组
    // time O(logN *N) 排序导致复杂度变高
    // espace O(N)
    class SortOrderAndUse105 {
        // preorder sort -> left < root < right -> inorder
        // preorder + inorder -> buildTree
        public TreeNode bstFromPreorder(int[] preorder) {
            int len = preorder.length;

            int[] inorder = new int[len];
            System.arraycopy(preorder, 0, inorder, 0, len);
            // O(N*logN)排列顺序
            Arrays.sort(inorder);

            return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        }

        TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
            if (preStart > preEnd) {
                return null;
            }
            // root 节点对应的值就是前序遍历数组的第一个元素
            int rootVal = preorder[preStart];
            // rootVal在中序遍历中的索引借以来找到左子树和右子树
            int index = 0;
            for (int i = 0; i < inorder.length; i++) {
                if (inorder[i] == rootVal) {
                    index = i;
                    break;
                }
            }
            TreeNode root = new TreeNode(rootVal);
            int leftSize = index - inStart;
            root.left = build(preorder, preStart + 1, leftSize + preStart, inorder, inStart, index - 1);
            root.right = build(preorder, leftSize + preStart + 1, preEnd, inorder, index + 1, inEnd);
            return root;
        }
    }

    // 同样的利用BST的性质 preorder顺序的first一定是root节点，其他的部分都是被递归求解
    // time O(N * logN)
    // espace O(N)
    class BST {
        public TreeNode bstFromPreorder(int[] preorder) {
            return bstFromPreorder(preorder, 0, preorder.length);
        }
    
        public TreeNode bstFromPreorder(int[] preorder, int start, int end) {
            if (start == end) {
                return null;
            }
            int root = preorder[start];
            int leftEnd = start + 1;
            while(leftEnd < end && preorder[leftEnd] < root) {
                leftEnd++;
            }
            TreeNode treeNode = new TreeNode(root);
            treeNode.left = bstFromPreorder(preorder, start + 1, leftEnd);
            treeNode.right = bstFromPreorder(preorder, leftEnd, end);
            return treeNode;
        }
    
        // public TreeNode bstFromPreorder(int[] preorder) {
        //     int len = preorder.length;
        //     if (len == 0) {
        //         return null;
        //     }
        //     return dfs(preorder, 0, len - 1);
        // }

        // // 根据 preorder 的子区间 [left..right] 构建二叉树
        // TreeNode dfs(int[] preorder, int left, int right) {
        //     if (left > right) {
        //         return null;
        //     }
        //     int rootVal = preorder[left];
        //     TreeNode root = new TreeNode(rootVal);
        //     if (left == right) {
        //         return root;
        //     }
        //     // 在区间 [left..right] 里找最后一个小于 preorder[left] 的下标
        //     // 注意这里设置区间的左边界为 left ，不能是 left + 1
        //     // 这是因为考虑到区间只有 2 个元素 [left, right] 的情况，第 1 个部分为空区间，第 2 部分只有一个元素 right
        //     int l = left, r = right;
        //     while (l < r) {
        //         int mid = l + (r - l) / 2;
        //         if (preorder[mid] < preorder[left]) {
        //             // 下一轮搜索区间是 [mid, r]
        //             l = mid;
        //         } else {
        //             r = mid - 1;
        //         }
        //     }

        //     TreeNode leftTree = dfs(preorder, left  + 1, l);
        //     TreeNode rightTree = dfs(preorder, l + 1, right);
        //     root.left  = leftTree;
        //     root.right = rightTree;
        //     return root;
        // }
    }
}
