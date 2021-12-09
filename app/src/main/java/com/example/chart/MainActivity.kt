package com.example.chart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val activityDay = ActivityDay(activityDay, this)
        setUpProgessChart()
        setUpStressChart()
        setUpOxyChart()
        setUpSleepChart()
        setUpActivityWeek()
        activityDay.setupActivityDay()

    }

    private fun setUpActivityWeek() {
        val xAxisValue = listOf("28/10", "29/10", "30/10", "31/10", "01/11", "02/11", "03/11")
        val yAxisValue = listOf(
            500f to 4000f,
            600f to 1500f,
            1200f to 5000f,
            1500f to 4800f,
            1200f to 1600f,
            3000f to 5500f,
            3000f to 4000f
        )
        val activityWeek = ActivityWeek(activityWeek, this)
        activityWeek.setValue(xAxisValue, yAxisValue)
        activityWeek.setupActivityChart()

    }

    private fun setUpSleepChart() {
        val timeSleep = 8 * 60
        val ngusau = mapOf(0 to 30, 50 to 70)
        val ngunong = mapOf(30 to 35, 70 to 200, 202 to 300, 330 to 480)
        val remm = mapOf(35 to 40, 200 to 202, 300 to 330)
        val thucc = mapOf(40 to 50)
        sleepChart.getValue(SleepChart.Data(timeSleep, ngusau, ngunong, remm, thucc))
    }

    private fun setUpOxyChart() {
        val oxyChart = OxyChart(oxy, this)
        val listValue = mapOf(
            0 to 30f,
            1 to 45f,
            2 to 65f,
            3 to 55f,
            4 to 70f,
            5 to 90f,
            7 to 75f,
            8 to 90f,
            10 to 100f,
            11 to 20f,
            19 to 50f,
            23 to 70f
        )
        oxyChart.setValue(listValue)
        oxyChart.setupOxyChart()


    }

    private fun setUpStressChart() {
        val value = mutableListOf(
            StressChart.Value(20, 20, "blue"),
            StressChart.Value(21, 15, "yellow"),
            StressChart.Value(22, 12, "green"),
            StressChart.Value(25, 15, "blue"),
            StressChart.Value(26, 15, "blue"),
            StressChart.Value(27, 21, "blue")
        )
        val xAxisValue = mutableListOf(
            "00:00",
            "04:00",
            "08:00",
            "12:00",
            "16:00",
            "20:00",
            "23:59"
        )
        val yAxisValue = mutableListOf(
            "0",
            "20",
            "40"
        )
        stressChart.setValue(value, xAxisValue, yAxisValue)
    }

    private fun setUpProgessChart() {
        val percent = 40f
        val step = 5000
        step_progess.text = step.toString()
        "$percent%".also { percent_progess.text = it }
        progressChart.setProgress(percent, step)
    }


}