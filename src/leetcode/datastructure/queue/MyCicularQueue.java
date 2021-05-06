package leetcode.datastructure.queue;

/**
 * 循环策略在于学习 
 * 入栈的时候 -> head 不动 tail ++
 * 出站的时候 -> head++ tail不动
 */
public class MyCicularQueue {
    private int[] data;
    private int head;
    private int tail;
    private int size;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    MyCicularQueue(int k) {
        data = new int[k];
        head = -1;
        tail = -1;
        size = k;
    }

    /**
     * Insert an element into the circular queue. Return true if the operation is
     * successful.
     */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }

        if (!isEmpty()) {
            head = 0;
        }

        tail = (tail + 1) % size;
        data[tail] = value;
        return true;
    }


    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        if (head == tail) {
            head = -1;
            tail = -1;
            return true;
        }
        head = (head + 1) % size;
        return true;
    }

    public boolean isFull() {
        return ((tail + 1) % size) == head;
    }

    public boolean isEmpty() {
        return head == -1;
    }

    public int first() {
        if (isEmpty() == true) {
            return -1;
        }
        return data[head];
    }

    public int last() {
        if (isEmpty() == true) {
            return -1;
        }
        return data[tail];
    }

}
