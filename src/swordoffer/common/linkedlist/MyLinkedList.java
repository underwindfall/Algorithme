package swordoffer.common.linkedlist;

/**
 * 实现链表的功能，增删改查
 */
public class MyLinkedList<E> {
    Node head = null; // 头节点

    /**
     * 向链表中插入数据
     *
     * @param d
     */
    public void addNode(int d) {
        Node newNode = new Node(d);// 实例化一个节点
        if (head == null) {
            head = newNode;
            return;
        }
        Node tmp = head;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = newNode;
    }

    /**
     * @param index:删除第index个节点
     * @return
     */
    public boolean deleteNode(int index) {
        if (index < 1 || index > length()) {
            return false;
        }
        if (index == 1) {
            head = head.next;
            return true;
        }
        int i = 1;
        Node preNode = head;
        Node curNode = preNode.next;
        while (curNode != null) {
            if (i == index) {
                preNode.next = curNode.next;
                return true;
            }
            preNode = curNode;
            curNode = curNode.next;
            i++;
        }
        return false;
    }

    /**
     * @return 返回节点长度
     */
    public int length() {
        int length = 0;
        Node tmp = head;
        while (tmp != null) {
            length++;
            tmp = tmp.next;
        }
        return length;
    }

    /**
     * 在不知道头指针的情况下删除指定节点
     *
     * @param n
     * @return
     */
    public boolean deleteNode11(Node n) {
        if (n == null || n.next == null)
            return false;
        int tmp = n.data;
        n.data = n.next.data;
        n.next.data = tmp;
        n.next = n.next.next;
        System.out.println("删除成功！");
        return true;
    }

    /**
     * 链表中的节点，data代表节点的值，next是指向下一个节点的引用
     *
     * @author zjn
     */
    public class Node<E> {
        Node next = null;// 节点的引用，指向下一个节点
        int data;// 节点的对象，即内容

        public Node(int data) {
            this.data = data;
        }
    }
}
