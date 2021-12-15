package com.example.chart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        setUpProgessChart()
        setUpStressChart()
        setUpOxyChart()
        setUpSleepChart()
        setUpActivityWeek()
        setUpActivityDay()


    }
    private fun setUpActivityDay(){
        val value = mutableListOf(
            ActivityDay.Value(20, 20),
            ActivityDay.Value(21, 15),
            ActivityDay.Value(22, 12 ),
            ActivityDay.Value(25, 15),
            ActivityDay.Value(26, 100),
            ActivityDay.Value(27, 21),
            ActivityDay.Value(100,95),
            ActivityDay.Value(144,90)
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
            "50",
            "100"
        )
        activityDay.setValue(value, xAxisValue, yAxisValue)
    }
    private fun setUpActivityWeek() {
        val xAxisValue = listOf("28/10", "29/10", "30/10", "31/10", "01/11", "02/11", "03/11")
        val yAxisValue = listOf(
            ActivityWeek.Data(500f, 4000f),
            ActivityWeek.Data(600f, 1500f),
            ActivityWeek.Data(1200f, 5000f),
            ActivityWeek.Data(1500f, 4800f),
            ActivityWeek.Data(1200f, 1600f),
            ActivityWeek.Data(3000f, 5500f),
            ActivityWeek.Data(3000f, 4000f)
        )
        val activityWeek = ActivityWeek(activityWeek, this)
        activityWeek.setValue(xAxisValue, yAxisValue)
        activityWeek.setupActivityChart()

    }

    private fun setUpSleepChart() {
        var total = mutableListOf<SleepChart.Time>()
        for(i in 0..30){
            total.add(SleepChart.Time(1) )
        }
        sleepChart.setData(SleepChart.Data(100,total))
    }

    private fun setUpOxyChart() {
        val oxyChart = OxyChart(oxy, this)
        val listValue = listOf(
            OxyChart.Data(0, 30f),
            OxyChart.Data(1, 45f),
            OxyChart.Data(2, 65f),
            OxyChart.Data(3, 55f),
            OxyChart.Data(4, 70f),
            OxyChart.Data(5, 90f),
            OxyChart.Data(6,50f),
            OxyChart.Data(7,50f),
            OxyChart.Data(8, 75f),
            OxyChart.Data(9, 90f),
            OxyChart.Data(10, 100f),
            OxyChart.Data(11, 20f),
            OxyChart.Data(12, 20f),
            OxyChart.Data(13, 20f),
            OxyChart.Data(14, 20f),
            OxyChart.Data(15, 20f),
            OxyChart.Data(16, 20f),
            OxyChart.Data(17, 20f),
            OxyChart.Data(18, 20f),
            OxyChart.Data(19, 50f),
            OxyChart.Data(20, 20f),
            OxyChart.Data(21, 50f),
            OxyChart.Data(22, 50f),
            OxyChart.Data(23, 50f),
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