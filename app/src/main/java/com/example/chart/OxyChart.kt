package com.example.chart

import android.content.Context
import android.graphics.Color
import android.util.Log
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet


class OxyChart(var lineChart: LineChart, context: Context) {
    private var listValue = listOf<Data>()
    private val color_line = ContextCompat.getColor(context, R.color.line_color)
    private val color_fill = ContextCompat.getColor(context, R.color.fill_color)
    private val line = ContextCompat.getColor(context, R.color.grid_backgroud)
    fun setupOxyChart() {
        val xAxisValues = arrayListOf(
            "00:00",
            "",
            "04:00",
            "",
            "08:00",
            "",
            "12:00",
            "",
            "16:00",
            "",
            "20:00",
            "",
            "23:59"
        )


        val xAxis: XAxis = lineChart.xAxis
        xAxis.axisMaximum = 0f
        xAxis.axisMaximum = 12f
        xAxis.setLabelCount(13, true)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.valueFormatter = IndexAxisValueFormatter(xAxisValues)
        xAxis.gridColor = line
        val leftAxis: YAxis = lineChart.axisLeft
        leftAxis.axisMaximum = 150f
        leftAxis.axisMinimum = 0f
        leftAxis.setLabelCount(4, false)
        leftAxis.gridColor= Color.RED

        lineChart.setDrawGridBackground(false)
        lineChart.description.isEnabled = false
        lineChart.legend.isEnabled = false
        lineChart.axisRight.isEnabled = false
        lineChart.setTouchEnabled(false)
        lineChart.setPinchZoom(false)
        lineChart.isDoubleTapToZoomEnabled = false

        val dataValueOxy = dataValueOxy()
        val lineDataSet1 = LineDataSet(dataValueOxy, "Data Set 1")
        lineDataSet1.lineWidth = 5f
        lineDataSet1.color = color_line
        lineDataSet1.mode = LineDataSet.Mode.CUBIC_BEZIER
        lineDataSet1.cubicIntensity = 0.1f
        lineDataSet1.setDrawFilled(true)
        lineDataSet1.fillColor = color_fill
        lineDataSet1.setDrawCircles(false)

        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(lineDataSet1)

        val data = LineData(dataSets)
        data.setDrawValues(false)
        lineChart.data = data
        lineChart.postInvalidate()

    }

    fun setValue(listValue: List<Data>) {
        this.listValue = listValue
    }

    private fun dataValueOxy(): ArrayList<Entry> {
        val dataVals = ArrayList<Entry>()

        for (i in listValue.indices) {
            dataVals.add(Entry(listValue[i].time.toFloat() / 2, listValue[i].value))
        }
        return dataVals
    }

    data class Data(val time: Int, val value: Float)
}