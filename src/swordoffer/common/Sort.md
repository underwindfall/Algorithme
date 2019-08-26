# 常用排序
面试如果是在一个排序的数组中（部分排序的数组）查找一个数字或者统计某个数字出现的次数，
那么可以尝试使用二分法。

## 冒泡排序(Bubble Sort)
> 冒泡排序是一种简单的排序算法。它重复地走访过要排序的数列，一次比较两个元素，如果他们的顺序错误就把他们交换过来。走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端
![](https://images2017.cnblogs.com/blog/849589/201710/849589-20171015223238449-2146169197.gif)

```kotlin
/*
* 冒泡排序
* 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
* 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
* 针对所有的元素重复以上的步骤，除了最后一个。
* 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
* @param numbers 需要排序的整型数组
*/
fun bubbleSort(numbers: IntArray): IntArray {
    for (i in 0 until numbers.size) {
        for (j in 0 until numbers.size - 1 - i) {
            if (numbers[j] > numbers[j + 1]) {
                val temp = numbers[j + 1]
                numbers[j] = numbers[j + 1]
                numbers[j] = temp
            }
        }
    }
    return numbers
}
```

### 分析
最佳情况：`T(n) = O(n)`   最差情况：`T(n) = O(n2)`   平均情况：`T(n) = O(n^2)`

## 选择排序(Selection Sort)
> 表现最稳定的排序算法之一，因为无论什么数据进去都是O(n2)的时间复杂度，所以用到它的时候，数据规模越小越好。唯一的好处可能就是不占用额外的内存空间了吧。理论上讲，选择排序可能也是平时排序一般人想到的最多的排序方法了吧。选择排序(Selection-sort)是一种简单直观的排序算法。它的工作原理：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕
![](https://zh.wikipedia.org/wiki/File:Selection-Sort-Animation.gif)

```kotlin
/**
 * 选择排序算法
 * 在未排序序列中找到最小元素，存放到排序序列的起始位置
 * 再从剩余未排序元素中继续寻找最小元素，然后放到排序序列末尾。
 * 以此类推，直到所有元素均排序完毕。
 * @param numbers
 */
fun selectSort(numbers: IntArray): IntArray {
    for (i in 0 until numbers.size) {
        var minIndex = i
        for (j in i until numbers.size) {
            if (numbers[j] < numbers[minIndex]) {
                minIndex = j
            }
        }
        val temp = numbers[minIndex]
        numbers[minIndex] = numbers[i]
        numbers[i] = temp
    }
    return numbers
}
```
### 分析
最佳情况：T(n) = O(n2)  最差情况：T(n) = O(n^2)  平均情况：T(n) = O(n^2)

## 插入排序
> 插入排序（Insertion-Sort）的算法描述是一种简单直观的排序算法。它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。插入排序在实现上，通常采用in-place排序（即只需用到O(1)的额外空间的排序），因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。

![](https://images2017.cnblogs.com/blog/849589/201710/849589-20171015225645277-1151100000.gif)

```kotlin
/**
 * 插入排序
 *
 * 从第一个元素开始，该元素可以认为已经被排序
 * 取出下一个元素，在已经排序的元素序列中从后向前扫描
 * 如果该元素（已排序）大于新元素，将该元素移到下一位置
 * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
 * 将新元素插入到该位置中
 * 重复步骤2
 * @param numbers  待排序数组
 */
fun insertSort(numbers: IntArray): IntArray {
    for (i in 0 until numbers.size - 1) {
        val current = numbers[i + 1]
        var preIndex = i
        while (preIndex >= 0 && current < numbers[preIndex]) {
            numbers[preIndex + 1] = numbers[preIndex]
            preIndex--
        }
        numbers[preIndex + 1] = current
    }
    return numbers
}
```


### 分析
最佳情况：T(n) = O(n2)  最差情况：T(n) = O(n^2)  平均情况：T(n) = O(n^2)

## 快速排序
> 快速排序的基本思想：通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序。

![](https://images2017.cnblogs.com/blog/849589/201710/849589-20171015230936371-1413523412.gif)

### 解释

快速排序使用分治法来把一个串（list）分为两个子串（sub-lists）。具体算法描述如下：
- 从数列中挑出一个元素，称为 “基准”（pivot）；
- 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
- 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。


###  分析
最佳情况：T(n) = O(nlogn)   最差情况：T(n) = O(n2)   平均情况：T(n) = O(nlogn)　


## Ref
  - [十大算法](https://www.cnblogs.com/guoyaohua/p/8600214.html)
  - [必须知道的八大种排序算法](https://www.jianshu.com/p/8c915179fd02)