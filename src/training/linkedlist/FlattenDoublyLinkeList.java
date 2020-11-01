package training.linkedlist;

import java.util.Stack;

public class FlattenDoublyLinkeList {
    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node() {
        }

        public Node(int _val, Node _prev, Node _next, Node _child) {
            val = _val;
            prev = _prev;
            next = _next;
            child = _child;
        }
    }
    /*
     * 递归的深度优先搜索算法如下：
     * 
     * - 首先，我们定义递归函数 flatten_dfs(prev, curr)，它接收两个指针作为函数参数并返回扁平化列表中的尾部指针。curr
     * 指针指向我们要扁平化的子列表，prev 指针指向 curr 指向元素的前一个元素。
     * 
     * - 在函数 flatten_dfs(prev, curr)，我们首先在 prev 和 curr 节点之间建立双向连接。
     * 
     * 
     * - 然后在函数中调用 flatten_dfs(curr, curr.child)对左子树（curr.child
     * 即子列表）进行操作，它将返回扁平化子列表的尾部元素 tail，再调用 flatten_dfs(tail, curr.next) 对右子树进行操作。
     * 
     * - 为了得到正确的结果，我们还需要注意两个重要的细节： ------- 在调用 flatten_dfs(curr,curr.child) 之前我们应该复制
     * curr.next 指针，因为 curr.next 可能在函数中改变。 -------- 在扁平化 curr.child指针所指向的列表以后，我们应该删除
     * child 指针，因为我们最终不再需要该指针。
     */

    // 时间复杂度：\mathcal{O}(N)O(N)。NN 指的是列表的节点数，深度优先搜索遍历每个节点一次。
    // 空间复杂度：\mathcal{O}(N)O(N)，NN 指的是列表的节点数，二叉树很可能不是个平衡的二叉树，若节点仅通过 child
    // 指针相互链接，则在递归调用的过程中堆栈的深度会达到 NN

    public Node flatten(Node head) {
        if (head == null) {
            return head;
        }

        Node pseudoHead = new Node(0, null, head, null);
        flattenDFS(pseudoHead, head);
        // detach the pseudo head from the real head
        pseudoHead.next.prev = null;
        return pseudoHead.next;
    }

    private Node flattenDFS(Node prev, Node curr) {
        if (curr == null) {
            return prev;
        }
        curr.prev = prev;
        prev.next = curr;

        Node tempNext = curr.next;
        Node tail = flattenDFS(curr, curr.child);

        curr.child = null;

        return flattenDFS(tail, tempNext);
    }

    public Node flatten1(Node head) {
        if (head == null) {
            return head;
        }

        Node pseudoHead = new Node(0, null, head, null);
        Node curr, prev = pseudoHead;

        Stack<Node> stack = new Stack<>();

        stack.push(head);

        while (!stack.isEmpty()) {
            curr = stack.pop();
            prev.next = curr;
            curr.prev = prev;

            if (curr.next != null) {
                stack.push(curr.next);
            }

            if (curr.child != null) {
                stack.push(curr.child);
                curr.child = null;
            }
            prev = curr;
        }

        pseudoHead.next.prev = null;

        return pseudoHead.next;
    }
}