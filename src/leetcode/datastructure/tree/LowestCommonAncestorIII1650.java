package leetcode.datastructure.tree;

import java.util.HashSet;
import java.util.Set;

// https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/
public class LowestCommonAncestorIII1650 {
    /**
     * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/ consider
     * this as linkedlist question parent == linkedlist.next
     */
    public Node lowestCommonAncestor(Node p, Node q) {
        Node dummyP = p;
        Node dummyQ = q;
        while (dummyP != dummyQ) {
            dummyP = dummyP == null ? q : dummyP.parent;
            dummyQ = dummyQ == null ? p : dummyQ.parent;
        }
        return dummyP;
    }

    // time O(n)
    // space O(n)
    class SetSolution {

        public Node lowestCommonAncestor(Node p, Node q) {
            Set<Node> seen = new HashSet<>();
            while (p != null) {
                seen.add(p);
                p = p.parent;
            }
            while (q != null) {
                if (seen.contains(q)) {
                    return q;
                }
                q = q.parent;
            }
            return null;
        }
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }
}
