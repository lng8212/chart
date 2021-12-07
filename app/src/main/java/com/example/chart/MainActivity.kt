package com.example.chart

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_main.*
import java.security.KeyStore
import com.github.mikephil.charting.components.YAxis

import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val oxyChart = OxyChart(oxy,this)
        val activityWeek = ActivityWeek(activityWeek,this)
        val activityDay = ActivityDay(activityDay,this)
        oxyChart.setupOxyChart()
        activityWeek.setupActivityChart()
        activityDay.setupActivityDay()
    }





}