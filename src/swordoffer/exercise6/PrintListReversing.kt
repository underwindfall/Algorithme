package swordoffer.exercise6

import swordoffer.common.LinkedList
import swordoffer.common.Node
import java.util.*


fun main(args: Array<String>) {
    val list = LinkedList<Int>()
    list.append(0)
    list.append(1)
    list.append(2)
    list.append(3)
    list.append(4)
    printListReversing1(list.first()).forEach {
        print(it)
    }
}
// 面试题6：从尾到头打印链表
// 题目：输入一个链表的头结点，从尾到头反过来打印出每个结点的值。


//思路：利用栈的先进后出的特性来进行反转
fun printListReversing1(headNode: Node<Int>?): MutableList<Int> {
    var node = headNode
    val list = mutableListOf<Int>()
    val stack = Stack<Int>()
    while (node != null) {
        stack.push(node.value)
        node = node.next
    }
    while (!stack.isEmpty()) {
        list.add(stack.pop())
    }
    return list
}

//使用递归的方法，不推荐，因为当链表过长时函数调用栈过深，有可能会导致调用栈溢出；
fun printListReversing2(headNode: Node<Int>?) {
    if (headNode != null) {
        if (headNode.next != null) {
            printListReversing2(headNode.next)
        }
        print(headNode.value)
    }
}