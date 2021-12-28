package leetcode.datastructure.linkdlist;

import java.util.Arrays;
import java.util.Random;

public class InsersionNode {
    class Node {

        /**
         * 节点存的内容
         */
        private Integer value;

        /**
         * 链表的下一个节点
         */
        private Node next;

        public Integer getValue() {
            return value;
        }

        public Node getNext() {
            return next;
        }
    }

    // 头插法 插入的node新节点都会从头部插入 类似stack的模式 先入后出
    class HeadInsert {

        /**
         * 用于记录所有生成的值 比较头插法 和 尾插法对于数据的顺序影响
         */
        public Integer[] valueArray;

        /**
         * 生成指定数量的节点链表
         *
         * @param count 链表节点数
         * @return 链表节点
         */
        public Node generateLinkedNode(int count) {
            Node head = null;
            // 初始化记录数组
            valueArray = new Integer[count];

            for (int i = 0; i < count; i++) {
                // 新节点
                Node newNode = new Node();
                // 随机一个值
                Integer value = new Random().nextInt(100) + 1;
                // 新节点赋值
                newNode.value = value;
                // 记录节点存的值
                valueArray[i] = value;
                // ---------------------------重点---------------------------------
                if (head == null) {
                    // 头节点不存在时候自己就是头节点
                    head = newNode;
                } else {
                    // 存在头结点的话，让新节点的下一节点设置成头结点
                    // 也就是说头节点前插入新节点
                    newNode.next = head;
                    // 将新节点变成头结点
                    // 移动链表
                    head = newNode;
                }
            }
            return head;
        }
    }

    /**
     * 生成指定数量的节点链表-尾插法
     *
     * @param prefixCount 链表节点数
     * @return 链表节点
     */
    class TailInsert {
        /**
         * 用于记录所有生成的值 比较头插法 和 尾插法对于数据的顺序影响
         */
        public Integer[] valueArray;

        /**
         * 生成指定数量的节点链表-尾插法
         *
         * @param count 链表节点数
         * @return 链表节点
         */

        public Node generateTailLinkedNode(int count) {
            // 头结点不能移动，所以需要一个临时节点来进行操作
            Node head = null, temp = null;
            // 初始化记录数组
            valueArray = new Integer[count];

            for (int i = 0; i < count; i++) {
                // 新节点
                Node newNode = new Node();
                // 随机值
                Integer value = new Random().nextInt(100) + 1;
                // 新节点赋值
                newNode.value = value;
                // 记录节点存的值
                valueArray[i] = value;

                // ---------------------------重点---------------------------------
                if (temp == null) {
                    // 头结点不存在时，新节点赋值成头结点
                    // 这一步结合下面的temp = newNode，两者此时都指向同一节点，
                    // 所以后续对temp 节点的操作其实就是对与head 的这一节点操作。
                    head = newNode;
                } else {
                    // 存在头结点，将新节点设置为下一节点
                    // 也就是说在头结点尾部插入新节点
                    temp.next = newNode;
                }
                // 临时节点重新赋值 移动到下一节点
                temp = newNode;
            }
            // temp 节点的意义所在
            return head;
        }
    }

    public static void main(String[] args) {
        InsersionNode insersionNode = new InsersionNode();
        HeadInsert headInsert = insersionNode.new HeadInsert();
        Node linkedNode = headInsert.generateLinkedNode(5);
        System.out.println(Arrays.toString(headInsert.valueArray));
        // 头插法 倒序插入
        // input: [84, 77, 32, 97, 7]
        // output: 7, 97, 32, 77, 84
        do {
            if (linkedNode.getNext() == null) {
                System.out.print(linkedNode.getValue());
            } else {
                System.out.print(linkedNode.getValue() + ", ");
            }
        } while ((linkedNode = linkedNode.getNext()) != null);

        System.out.println("\n==================");
        TailInsert tailInsert = insersionNode.new TailInsert();
        Node tailLinkedNode = tailInsert.generateTailLinkedNode(5);
        System.out.println(Arrays.toString(tailInsert.valueArray));
        do {
            if (tailLinkedNode.getNext() == null) {
                System.out.print(tailLinkedNode.getValue());
            } else {
                System.out.print(tailLinkedNode.getValue() + ", ");
            }
        } while ((tailLinkedNode = tailLinkedNode.getNext()) != null);
    }
}
