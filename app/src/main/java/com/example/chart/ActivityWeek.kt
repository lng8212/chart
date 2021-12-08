package com.example.chart

import android.content.Context
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet

class ActivityWeek(var barChart: BarChart, val context: Context) {

    fun setupActivityChart() {
        val xAxisValues = arrayListOf("28/10", "29/10", "30/10", "31/10", "01/11", "02/11", "03/11")
        val target = ContextCompat.getColor(context, R.color.target)
        val step = ContextCompat.getColor(context, R.color.step)
        val color = listOf(step, target)
        barChart.axisLeft.isEnabled = false
        barChart.axisLeft.axisMinimum = 0f
        barChart.axisRight.isEnabled = false

        val xAxis = barChart.xAxis
        xAxis.setDrawGridLines(false)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.textSize = 12f
        xAxis.valueFormatter = IndexAxisValueFormatter(xAxisValues)


        barChart.setDrawGridBackground(false)
        barChart.description.isEnabled = false
        barChart.legend.isEnabled = false
        barChart.setTouchEnabled(false)
        barChart.setPinchZoom(false)
        barChart.isDoubleTapToZoomEnabled = false

        val barDataSet = BarDataSet(dataValue(), "")
        barDataSet.colors = color

        val dataSet = ArrayList<IBarDataSet>()
        dataSet.add(barDataSet)

        val data = BarData(dataSet)
        data.setDrawValues(false)
        data.barWidth = 0.5f

        barChart.data = data
        barChart.invalidate()

    }

    private fun dataValue(): ArrayList<BarEntry> {
        var dataVals = ArrayList<BarEntry>()

        dataVals.add(BarEntry(0f, floatArrayOf(500f, 4000f)))
        dataVals.add(BarEntry(1f, floatArrayOf(600f, 1500f)))
        dataVals.add(BarEntry(2f, floatArrayOf(1200f, 5000f)))
        dataVals.add(BarEntry(3f, floatArrayOf(1500f, 4800f)))
        dataVals.add(BarEntry(4f, floatArrayOf(1200f, 1600f)))
        dataVals.add(BarEntry(5f, floatArrayOf(3000f, 5500f)))
        dataVals.add(BarEntry(6f, floatArrayOf(3000f, 4000f)))


        return dataVals
    }
}