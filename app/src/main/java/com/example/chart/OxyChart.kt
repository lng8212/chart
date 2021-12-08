package com.example.chart

import android.content.Context
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

class OxyChart(var lineChart: LineChart, val context: Context) {
    fun setupOxyChart() {
        val xAxisValues = arrayListOf("00:00", "04:00", "08:00", "12:00", "16:00", "20:00", "23:59")
        val color_line = ContextCompat.getColor(context, R.color.line_color)
        val color_fill = ContextCompat.getColor(context, R.color.fill_color)

        val xAxis: XAxis = lineChart.xAxis
        xAxis.axisMaximum = 0f
        xAxis.axisMaximum = 6f
        xAxis.setLabelCount(7, true)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.valueFormatter = IndexAxisValueFormatter(xAxisValues)

        val leftAxis: YAxis = lineChart.axisLeft
        leftAxis.axisMaximum = 150f
        leftAxis.axisMinimum = -50f
        leftAxis.setLabelCount(4, false)

        lineChart.setDrawGridBackground(false)
        lineChart.description.isEnabled = false
        lineChart.legend.isEnabled = false
        lineChart.axisRight.isEnabled = false
        lineChart.setTouchEnabled(false)
        lineChart.setPinchZoom(false)
        lineChart.isDoubleTapToZoomEnabled = false


        var lineDataSet1 = LineDataSet(dataValueOxy(), "Data Set 1")
        lineDataSet1.lineWidth = 5f
        lineDataSet1.color = color_line
        lineDataSet1.mode = LineDataSet.Mode.CUBIC_BEZIER
        lineDataSet1.cubicIntensity = 0.15f
        lineDataSet1.setDrawFilled(true)
        lineDataSet1.fillColor = color_fill
        lineDataSet1.setDrawCircles(false)


        var dataSets = ArrayList<ILineDataSet>()
        dataSets.add(lineDataSet1)

        val data = LineData(dataSets)
        data.setDrawValues(false)
        lineChart.data = data
        lineChart.invalidate()

    }


    private fun dataValueOxy(): ArrayList<Entry> {
        var dataVals = ArrayList<Entry>()
        dataVals.add(Entry(0f, 45f))
        dataVals.add(Entry(0.7f, 65f))
        dataVals.add(Entry(1f, 55f))
        dataVals.add(Entry(2f, 0f))
        dataVals.add(Entry(3f, 51f))
        dataVals.add(Entry(3.4f, 70f))
        dataVals.add(Entry(4f, 50f))
        dataVals.add(Entry(4.6f, 30f))
        dataVals.add(Entry(5f, 45f))
        dataVals.add(Entry(5.4f, 51f))
        dataVals.add(Entry(6f, 47f))

        return dataVals
    }
}