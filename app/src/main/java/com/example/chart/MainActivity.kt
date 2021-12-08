package com.example.chart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val oxyChart = OxyChart(oxy, this)
        val activityWeek = ActivityWeek(activityWeek, this)
        val activityDay = ActivityDay(activityDay, this)
//        val value1 = listOf(
//            20 to 20,
//            21 to 15,
//            22 to 12,
//            25 to 15,
//            26 to 15,
//            27 to 21,
//            29 to 18,
//            30 to 25,
//            31 to 15,
//            32 to 16,
//            33 to 30,
//            34 to 17,
//            50 to 50,
//            51 to 30,
//            52 to 20
//        )
        val value = mutableListOf(
            StressChart.Value(20, 20, "blue"),
            StressChart.Value(21, 15, "yellow"),
            StressChart.Value(22, 12, "green"),
            StressChart.Value(25, 15, "blue"),
            StressChart.Value(26, 15, "blue"),
            StressChart.Value(27, 21, "blue")
        )

        val percent = 40f
        val step = 5000
        step_progess.text = step.toString()
        "$percent%".also { percent_progess.text = it }
        progressChart.setProgress(percent, step)
        stressChart.setValue(value)
        oxyChart.setupOxyChart()
        activityWeek.setupActivityChart()
        activityDay.setupActivityDay()

    }

}