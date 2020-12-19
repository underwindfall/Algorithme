package leetcode.datastructure.tree;

// https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/
public class ConvertSortedLinkedListToPerfectBalancedResearchTree109 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public class TreeNode {
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

    public TreeNode sortedListToBST(ListNode head) {
        return buildTree(head);
    }

    // time O(N/2)*O(N) = O(N^2)
    // espace O(logN)*O(1) = O(logN)
    TreeNode buildTree(ListNode node) {
        if (node == null) {
            return null;
        }
        if (node.next == null) {
            return new TreeNode(node.val);
        }
        ListNode mid = findMiddleNode(node);
        TreeNode root = new TreeNode(mid.val);
        root.left = buildTree(node);
        root.right = buildTree(mid.next);
        return root;
    }

    // time O(N/2) = O(N)
    // espace O(1)
    ListNode findMiddleNode(ListNode head) {
        ListNode fast = head, slow = head, pre = null;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        return slow;
    }
}