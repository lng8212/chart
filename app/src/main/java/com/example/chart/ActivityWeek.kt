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

    private lateinit var xAxisValues: List<String>
    private lateinit var yAxisValues: List<Pair<Any, Float>>
    fun setValue(xAxisValues: List<String>, yAxisValues: List<Pair<Any, Float>>) {
        this.xAxisValues = xAxisValues
        this.yAxisValues = yAxisValues
    }

    fun setupActivityChart() {

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
        val dataVals = ArrayList<BarEntry>()
        for (i in yAxisValues.indices) {
            dataVals.add(
                BarEntry(
                    i.toFloat(),
                    floatArrayOf(yAxisValues[i].first as Float, yAxisValues[i].second)
                )
            )
        }

        return dataVals
    }
}