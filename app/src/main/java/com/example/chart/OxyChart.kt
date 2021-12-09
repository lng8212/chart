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
    private var listValue = mapOf<Int,Float>()

    fun setupOxyChart() {
        val xAxisValues = arrayListOf("00:00","","04:00","", "08:00", "","12:00","", "16:00","", "20:00","", "23:59")
        val color_line = ContextCompat.getColor(context, R.color.line_color)
        val color_fill = ContextCompat.getColor(context, R.color.fill_color)

        val xAxis: XAxis = lineChart.xAxis
        xAxis.axisMaximum = 0f
        xAxis.axisMaximum = 12f
        xAxis.setLabelCount(13, true)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.valueFormatter = IndexAxisValueFormatter(xAxisValues)

        val leftAxis: YAxis = lineChart.axisLeft
        leftAxis.axisMaximum = 120f
        leftAxis.axisMinimum = -30f
        leftAxis.setLabelCount(6, false)

        lineChart.setDrawGridBackground(false)
        lineChart.description.isEnabled = false
        lineChart.legend.isEnabled = false
        lineChart.axisRight.isEnabled = false
        lineChart.setTouchEnabled(false)
        lineChart.setPinchZoom(false)
        lineChart.isDoubleTapToZoomEnabled = false


        val lineDataSet1 = LineDataSet(dataValueOxy(), "Data Set 1")
        lineDataSet1.lineWidth = 5f
        lineDataSet1.color = color_line
        lineDataSet1.mode = LineDataSet.Mode.CUBIC_BEZIER
        lineDataSet1.cubicIntensity = 0.15f
        lineDataSet1.setDrawFilled(true)
        lineDataSet1.fillColor = color_fill
        lineDataSet1.setDrawCircles(false)


        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(lineDataSet1)

        val data = LineData(dataSets)
        data.setDrawValues(false)
        lineChart.data = data
        lineChart.invalidate()

    }

    fun setValue(listValue: Map<Int,Float>){
        this.listValue = listValue
    }

    private fun dataValueOxy(): ArrayList<Entry> {
        val dataVals = ArrayList<Entry>()
        for (i in listValue)
        dataVals.add(Entry(i.key.toFloat()/2, i.value ))
        return dataVals
    }
}