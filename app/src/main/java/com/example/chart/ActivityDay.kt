package com.example.chart

import android.content.Context
import android.graphics.Color
import android.graphics.DashPathEffect
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

class ActivityDay (var lineChart: LineChart, val context: Context) {

    fun setupActivityDay(){
        val xAxisValues =  arrayListOf("00","04","08","12","16","20","00")
        val color_line = ContextCompat.getColor(context,R.color.line_day)

        val xAxis: XAxis = lineChart.xAxis
        xAxis.axisMaximum = 0f
        xAxis.axisMaximum = 6f
        xAxis.setLabelCount(20,true)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.valueFormatter =  IndexAxisValueFormatter(xAxisValues)

        val leftAxis: YAxis = lineChart.axisLeft
        leftAxis.axisMaximum = 4000f
        leftAxis.axisMinimum = 0f
        leftAxis.setDrawGridLines(true)
        leftAxis.setLabelCount(5,true)

        lineChart.setDrawGridBackground(false)
        lineChart.description.isEnabled = false
//        lineChart.legend.isEnabled = false
        lineChart.legend.form = Legend.LegendForm.LINE
        lineChart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER

        var legendEntrys = mutableListOf<LegendEntry>()
        val legendEntry = LegendEntry()
        legendEntry.formColor = Color.BLACK
        legendEntry.form =Legend.LegendForm.LINE
        legendEntry.label = "Goal"
        legendEntry.formLineWidth = 1f
        legendEntry.formSize = 40f
        legendEntry.formLineDashEffect = DashPathEffect(floatArrayOf(8f,8f),0f)
        legendEntrys.add(legendEntry)
        lineChart.legend.setCustom(legendEntrys)

        lineChart.axisRight.isEnabled= false
        lineChart.setTouchEnabled(false)
        lineChart.setPinchZoom(false)
        lineChart.isDoubleTapToZoomEnabled = false

        var lineDataSet1 = LineDataSet(dataValue(),null)
        lineDataSet1.lineWidth = 1f
        lineDataSet1.color = color_line
        lineDataSet1.setDrawCircles(false)

        var lineDataSet2 = LineDataSet(goal(), "Goal")
        lineDataSet2.enableDashedLine(10f,2f,0f)
        lineDataSet2.color= Color.BLACK
        lineDataSet2.setDrawCircles(false)

        var dataSets = ArrayList<ILineDataSet>()
        dataSets.add(lineDataSet1)
        dataSets.add(lineDataSet2)

        val data = LineData(dataSets)
        data.setDrawValues(false)
        lineChart.data = data
        lineChart.invalidate()
    }

    private fun dataValue(): ArrayList<Entry>{
        var dataVals = ArrayList<Entry>()
        dataVals.add(Entry(0f,0f))
        dataVals.add(Entry(1f,0f))
        dataVals.add(Entry(1.5f,0f))
        dataVals.add(Entry(2f,500f))
        dataVals.add(Entry(2.6f,500f))
        dataVals.add(Entry(3f,1200f))
        dataVals.add(Entry(3.5f,1200f))
        return dataVals
    }
    private fun goal():ArrayList<Entry>{
        var dataVals = ArrayList<Entry>()
        dataVals.add(Entry(0f,3500f))
        dataVals.add(Entry(6f,3500f))
        return dataVals
    }
}