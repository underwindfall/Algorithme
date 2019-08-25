# Stack 栈 (后进先出 Last In First Out,LIFO)

## 介绍
栈是一种用于存储数据的简单数据结构，有点类似链表或者顺序表（统称线性表），栈与线性表的最大区别是数据的存取的操作，我们可以这样认为栈(Stack)是一种特殊的线性表，其插入和删除操作只允许在线性表的一端进行，一般而言，把允许操作的一端称为栈顶(Top)，不可操作的一端称为栈底(Bottom)，同时把插入元素的操作称为入栈(Push),删除元素的操作称为出栈(Pop)。若栈中没有任何元素，则称为空栈，栈的结构如下图：
![](https://raw.githubusercontent.com/underwindfall/blogAssets/master/alog/stack/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-08-25%2017.48.07.png)

```java
public interface Stack<T> {

   /**
    * 栈是否为空
    * @return
    */
   boolean isEmpty();

   /**
    * data元素入栈
    * @param data
    */
   void push(T data);

   /**
    * 返回栈顶元素,未出栈
    * @return
    */
   T peek();

   /**
    * 出栈,返回栈顶元素,同时从栈中移除该元素
    * @return
    */
   T pop();
}
```
## 顺序栈

### 介绍
顺序栈，顾名思义就是采用顺序表实现的的栈，顺序栈的内部以顺序表为基础，实现对元素的存取操作，当然我们还可以采用内部数组实现顺序栈，在这里我们使用内部数据组来实现栈

### 实现
[SeqStack]()
#### peek
![](https://raw.githubusercontent.com/underwindfall/blogAssets/master/algo/stack/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-08-25%2018.03.23.png)
#### push
![](https://raw.githubusercontent.com/underwindfall/blogAssets/master/algo/stack/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-08-25%2018.03.23.png)
#### pop
![](https://raw.githubusercontent.com/underwindfall/blogAssets/master/algo/stack/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-08-25%2018.05.47.png)

## 链式栈 
### 介绍

我们接着来看看链式栈，所谓的链式栈（Linked Stack），就是采用链式存储结构的栈，由于我们操作的是栈顶一端，因此这里采用单链表（不带头结点）作为基础，直接实现栈的添加，获取，删除等主要操作即可
![](https://raw.githubusercontent.com/underwindfall/blogAssets/master/alog/stack/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-08-25%2018.08.55.png)
![](https://raw.githubusercontent.com/underwindfall/blogAssets/master/alog/stack/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-08-25%2018.09.37.png)

### 实现
[LinkedStack]()

## Ref
- [Stack](https://blog.csdn.net/javazejian/article/details/53362993)