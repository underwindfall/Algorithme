package swordoffer;

import java.util.Stack;

//https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
public class LCOF33 {
    // time O(n)
    // space O(n)
    class DFS {
        public boolean verifyPostorder(int[] postorder) {
            return helper(postorder, 0, postorder.length - 1);
        }

        boolean helper(int[] postorder, int left, int right) {
            // 如果left==right，就一个节点不需要判断了，如果left>right说明没有节点，
            // 也不用再看了,否则就要继续往下判断
            if (left >= right)
                return true;
            // 因为数组中最后一个值postorder[right]是根节点，这里从左往右找出第一个比
            // 根节点大的值，他后面的都是根节点的右子节点（包含当前值，不包含最后一个值，
            // 因为最后一个是根节点），他前面的都是根节点的左子节点
            int mid = left;
            int root = postorder[right];
            while (postorder[mid] < root)
                mid++;
            int temp = mid;
            // 因为postorder[mid]前面的值都是比根节点root小的，
            // 我们还需要确定postorder[mid]后面的值都要比根节点root大，
            // 如果后面有比根节点小的直接返回false
            while (temp < right) {
                if (postorder[temp++] < root)
                    return false;
            }
            // 然后对左右子节点进行递归调用
            return helper(postorder, left, mid - 1) && helper(postorder, mid, right - 1);
        }
    }

    // https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/solution/di-gui-he-zhan-liang-chong-fang-shi-jie-jue-zui-ha/
    class StackSolution {
        // time O(n)
        // space O(n)
        public boolean verifyPostorder(int[] postorder) {
            Stack<Integer> stack = new Stack<>();
            int parent = Integer.MAX_VALUE;
            // 注意for循环是倒叙遍历的
            for (int i = postorder.length - 1; i >= 0; i--) {
                int cur = postorder[i];
                // 当如果前节点小于栈顶元素，说明栈顶元素和当前值构成了倒叙，
                // 说明当前节点是前面某个节点的左子节点，我们要找到他的父节点
                while (!stack.isEmpty() && stack.peek() > cur)
                    parent = stack.pop();
                // 只要遇到了某一个左子节点，才会执行上面的代码，才会更
                // 新parent的值，否则parent就是一个非常大的值，也就
                // 是说如果一直没有遇到左子节点，那么右子节点可以非常大
                if (cur > parent)
                    return false;
                // 入栈
                stack.add(cur);
            }
            return true;
        }

    }
}
