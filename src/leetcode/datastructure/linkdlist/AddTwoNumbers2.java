package leetcode.datastructure.linkdlist;

// https://leetcode-cn.com/problems/add-two-numbers/
public class AddTwoNumbers2 {

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

    class Simulation {

        // 对于链表问题，返回结果为头结点时，通常需要先初始化一个预先指针 pre，
        // 该指针的下一个节点指向真正的头结点head。使用预先指针的目的在于链表初始化时无可用节点值，
        // 而且链表构造过程需要指针移动，进而会导致头指针丢失，无法返回结果
        /**
         * 思路
         * 
         * 两个数字相加 不足的的为数补0 记得要进位的位置
         * 
         * time O(N1+N2) = O(N)
         * 
         * espace O(1)
         * 
         * @param l1
         * @param l2
         * @return
         */
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode();
            ListNode cur = dummy;
            int carry = 0;
            while (l1 != null || l2 != null) {
                int x = l1 == null ? 0 : l1.val;
                int y = l2 == null ? 0 : l2.val;
                int sum = x + y + carry;
                carry = sum / 10;
                int result = sum % 10;
                cur.next = new ListNode(result);
                cur = cur.next;
                if (l1 != null) {
                    l1 = l1.next;
                }
                if (l2 != null) {
                    l2 = l2.next;
                }
            }
            // 两个单位数字相加不会进位超过1 9+9+1 = 19
            if (carry == 1) {
                cur.next = new ListNode(carry);
            }
            return dummy.next;
        }
    }

    // 每个数字的sum 都是(l1+l2+carry)%10的值
    // carry (l1+l2+carry)/10 的结果
    // time O(N1+N2)
    // espace O(N1+N2)
    class Recursive {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            return recursive(l1, l2, 0);
        }

        public ListNode recursive(ListNode l1, ListNode l2, int carry) {
            if (l1 == null && l2 == null && carry == 0)
                return null;
            int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
            // 计算个数
            int result = sum % 10;
            // 计算进位
            int newCarry = sum / 10;
            ListNode node = new ListNode(result);
            node.next = recursive(l1 == null ? null : l1.next, l2 == null ? null : l2.next, newCarry);
            return node;
        }
    }
}
