package com.example.cutandstack_kotlin.domain

class Generator {

    private var rows : Int = 0
    private var cols : Int = 0
    private var groups : Int = 0

    private lateinit var odds : IntArray
    private lateinit var event : IntArray

    fun generate(cols: Int, rows: Int, isOneSide: Boolean) : String {
        this.cols = cols
        this.rows = rows
        this.groups = cols * rows

        var result : String = ""

        if (isOneSide) {
            fillArray()
            result = arrayToString(odds)
        } else {
            fillArrays()
            reverseEventArray()
            result = arrayToString(odds) + arrayToString(event)
        }

        return result.trim()
    }

    private fun fillArray() {
        odds = IntArray(groups)
        for (i : Int in 0..(groups - 1)) {
            odds[i] = i + 1
        }
    }

    private fun fillArrays() {
        odds = IntArray(groups)
        event = IntArray(groups)
        var index : Int = 0
        for (i : Int in 1..((groups * 2))) {
            if (i % 2 != 0) odds[index] = i
            else if (i % 2 == 0) event[index ++] = i
        }
    }

    private fun reverseEventArray() {
        val temp : IntArray = IntArray(cols)
        for (i : Int in 0..rows - 1) {
            System.arraycopy(event, i * cols, temp, 0, cols)
            temp.reverse()
            System.arraycopy(temp, 0, event, i * cols, cols)
        }
    }

    private fun arrayToString(array : IntArray) : String {
        var result : String = ""
        array.forEach { result += "$it " }
        return result
    }
}