# 链表
> 链表是一种数据结构，和数组同级。比如，Java中我们使用的ArrayList，其实现原理是数组。而LinkedList的实现原理就是链表了。链表在进行循环遍历时效率不高，但是插入和删除时优势明显

## 单向链表
### 介绍
单向链表是一种线性表，实际上是由节点（Node）组成的，一个链表拥有不定数量的节点。
其数据在内存中存储是不连续的，它存储的数据分散在内存中，
每个结点只能也只有它能知道下一个结点的存储位置
由N各节点（Node）组成单向链表，每一个Node记录本Node的数据及下一个Node。
向外暴露的只有一个头节点（Head），我们对链表的所有操作，都是直接或者间接地通过其头节点来进行的
![1](https://raw.githubusercontent.com/underwindfall/blogAssets/master/algo/linkedlist/1.png)
上图中最左边的节点即为头结点（Head），但是添加节点的顺序是从右向左的，添加的新节点会被作为新节点。最先添加的节点对下一节点的引用可以为空。引用是引用下一个节点而非下一个节点的对象。因为有着不断的引用，所以头节点就可以操作所有节点了。 
下图描述了单向链表存储情况。存储是分散的，每一个节点只要记录下一节点，就把所有数据串了起来，形成了一个单向链表。
![2](https://raw.githubusercontent.com/underwindfall/blogAssets/master/algo/linkedList/2.png)
节点（Node）是由一个需要储存的对象及对下一个节点的引用组成的。也就是说，节点拥有两个成员：储存的对象、对下一个节点的引用。下面图是具体的说明
![3](https://raw.githubusercontent.com/underwindfall/blogAssets/master/algo/linkedList/3.png)

### 实现
- [LinkedList]()
## 双向链表
### 介绍
### 实现