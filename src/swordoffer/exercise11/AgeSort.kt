package swordoffer.exercise11

//公司员工年龄排序
fun ageSort(ages: IntArray, number: Int) {
    val oldEstAge = 99
    val timesOfAge = IntArray(oldEstAge + 1)
        .apply {
            this.forEachIndexed { index, _ -> this[index] = 0 }
        }
    ages.forEach { age ->
        ++timesOfAge[age]
    }

    var index = 0
    for (i in 0..oldEstAge) {
        for (j in 0 until timesOfAge[i]) {
            ages[index] = i
            index++
        }
    }
}