package swordoffer.common

class Node<T>(value: T) {
    var value: T = value // value可以是任意类型
    var next: Node<T>? = null // next可以为null
    var previous: Node<T>? = null // pre也可以为null
}

class LinkedList<T> {
    // 头结点，引导性作用
    var head: Node<T>? = null

    // 取决于head是否为null
    var isEmpty: Boolean = head == null

    // 获取first
    fun first(): Node<T>? = head

    // 获取last结点，需要一直next才能到达last结点
    fun last(): Node<T>? {
        var node = head
        if (node != null) {
            while (node?.next != null) {
                node = node.next
            }
            return node
        } else {
            return null
        }
    }


    // 获取count，同样通过next计算
    fun count(): Int {
        var node = head
        if (node != null) {
            var counter = 1
            while (node?.next != null) {
                node = node.next
                counter += 1
            }
            return counter
        } else {
            return 0
        }
    }

    // append操作,在last结点上append
    fun append(value: T) {
        var newNode = Node(value)
        // 获取当前节点的最后一个节点
        var lastNode = this.last()
        if (lastNode != null) {
            newNode.previous = lastNode
            lastNode.next = newNode
        } else {
            head = newNode
        }
    }

    // 删除操作
    fun removeNode(node: Node<T>): T {
        val prev = node.previous
        val next = node.next

        if (prev != null) {
            prev.next = next
        } else {
            head = next
        }
        next?.previous = prev

        node.previous = null // 将断开的节点前后置null
        node.next = null

        return node.value // 返回删除节点的value
    }
}
