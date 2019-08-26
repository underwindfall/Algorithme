package swordoffer.common.sort

import kotlin.random.Random

fun main(args: Array<String>) {
    val array = intArrayOf(1, 4, 2, 3, 4, 6, 5)
//    print(bubbleSort(array).toList())
//    print(selectSort(array).toList())
//    print(insertSort(array).toList())
    print(quickSort(array).toList())
}


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

/**
 * 查找出中轴（默认是最低位low）的在numbers数组排序后所在位置
 *
 * @param numbers 带查找数组
 * @param low   开始位置
 * @param high  结束位置
 * @return  中轴所在位置
 */
fun quickSort(numbers: IntArray): IntArray {

    fun swap(inputArray: IntArray, i: Int, j: Int) {
        inputArray[j].also { inputArray[j] = inputArray[i] }
    }

    fun partition(inputArray: IntArray, start: Int, end: Int): Int {
        val pivot = start + Random.nextInt(end - start + 1)
        var smallIndex = start - 1
        for (i in start..end) {
            if (inputArray[i] <= inputArray[end]) {
                smallIndex++
                if (i > smallIndex) {
                    swap(inputArray, i, smallIndex)
                }
            }
        }
        return smallIndex
    }

    fun QuickSort(intArray: IntArray, start: Int, end: Int) {
        val smallIndex = partition(intArray, start, end)
        if (smallIndex > start) {
            QuickSort(intArray, start, smallIndex - 1)
        }
        if (smallIndex < end) {
            QuickSort(intArray, smallIndex + 1, end)
        }
    }


    QuickSort(numbers, 0, numbers.size - 1)

    return numbers
}