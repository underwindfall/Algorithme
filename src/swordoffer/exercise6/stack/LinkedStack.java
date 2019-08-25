package swordoffer.exercise6.stack;

import swordoffer.common.Node;

import java.util.EmptyStackException;

public class LinkedStack<T> implements Stack<T> {
    private static final long serialVersionUID = 1911829302658328353L;

    private Node<T> top;

    private int size;

    public LinkedStack() {
        this.top = new Node(10);
    }

    //测试
    public static void main(String[] args) {
        LinkedStack<String> sl = new LinkedStack<>();
        sl.push("A");
        sl.push("B");
        sl.push("C");
        int length = sl.size();
        for (int i = 0; i < length; i++) {
            System.out.println("sl.pop->" + sl.pop());
        }
    }

    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return top == null || top.getValue() == null;
    }

    @Override
    public void push(T data) {
        if (data == null) {
            throw new EmptyStackException();
        }
        if (this.top == null) {//调用pop()后top可能为null
            this.top = new Node<>(data);
        } else if (this.top.getValue() == null) {
            this.top.setValue(data);
        } else {
            Node<T> p = new Node<>(data);
            p.setPrevious(top);
            top = p;//更新栈顶
        }
        size++;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return top.getValue();
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        T data = top.getValue();
        top = top.getNext();
        size--;
        return data;
    }
}