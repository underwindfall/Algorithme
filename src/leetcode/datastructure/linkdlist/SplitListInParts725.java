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
            int length = 0;
            ListNode cur = root;
            while (cur != null) {
                cur = cur.next;
                length++;
            }            
    
            int avg = length / k; //平均每组个数
    
            int res = length % k ;  // 剩余个数 -> 最后会前面几组分配几个
    
            ListNode[] ans = new ListNode[k];
            cur = root;
    
            for (int i = 0; i < k; i++){
                ListNode head = cur;
                for (int j = 0; j < avg + (i < res ? 1 :0) - 1; j++) {
                    if (cur != null) {
                        //求k 第(i)group的首个cur
                        cur = cur.next;
                    }
                }
                //prev 求出【i】 group下的尾节点
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
