package leetcode.datastructure.linkdlist;

//https://leetcode-cn.com/problems/split-linked-list-in-parts/
public class SplitListInParts725 {
    public class ListNode {
        int val;
        ListNode next;

        public ListNode(int x) {
            val = x;
        }
    }

    // time  O(N+k)
    // espace O(1)
    class Solution {
        public ListNode[] splitListToParts(ListNode root, int k) {
            ListNode cur = root;
            int N = 0;
            while (cur != null) {
                cur = cur.next;
                N++;
            }
    
            int width = N / k, rem = N % k;
    
            ListNode[] ans = new ListNode[k];
            cur = root;
            for (int i = 0; i < k; ++i) {
                ListNode head = cur;
                for (int j = 0; j < width + (i < rem ? 1 : 0) - 1; ++j) {
                    if (cur != null) cur = cur.next;
                }
                if (cur != null) {
                    ListNode prev = cur;
                    cur = cur.next;
                    prev.next = null;
                }
                ans[i] = head;
            }
            return ans;
        }
    
    }

}
