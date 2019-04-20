package com.example.cutandstack_kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.NumberPicker
import android.widget.TextView
import com.example.cutandstack_kotlin.domain.Generator

class MainActivity : AppCompatActivity() {

    private lateinit var colsPicker : NumberPicker
    private lateinit var rowsPicker : NumberPicker
    private lateinit var resultText : TextView
    private lateinit var oneSide : CheckBox
    private lateinit var generator: Generator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        colsPicker = this.findViewById(R.id.cols_picker)
        rowsPicker = this.findViewById(R.id.rows_picker)
        resultText = this.findViewById(R.id.result_text)
        oneSide = this.findViewById(R.id.oneSideCheckBox)

        oneSide.setOnCheckedChangeListener { buttonView, isChecked -> run {
            resultText.text = generator.generate(colsPicker.value, rowsPicker.value, isChecked)
        } }

        generator = Generator()

        colsPicker.minValue = 0
        colsPicker.maxValue = 100

        rowsPicker.minValue = 0
        rowsPicker.maxValue = 100

        colsPicker.setOnValueChangedListener { picker, oldVal, newVal -> run {
            regenerate()
        } }

        rowsPicker.setOnValueChangedListener { picker, oldVal, newVal -> run {
            regenerate()
        } }
    }

    private fun regenerate() {
        val cols : Int = colsPicker.value
        val rows : Int = rowsPicker.value

        var result = generator.generate(cols, rows, oneSide.isChecked)

        var substring : String
        if (result.length > 100)
            substring = result.substring(0, 100) + " ..."
        else substring = result;

        resultText.text = substring
    }
}
