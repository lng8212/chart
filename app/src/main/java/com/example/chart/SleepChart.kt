package com.example.chart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

class SleepChart(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private fun getColor(@ColorRes idColor: Int) = ContextCompat.getColor(context, idColor)
    private var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var h = 0f
    var w = 0f
    private var value: Data? = null
    private var listColor: Map<Int, Int>

    data class Data(
        val timeSleep: Long,
        val total: List<Time>
    ) {
        var startTime: Long = 0
        var endTime: Long = 0
        var totalSleep: Long = 0
        var date: Long = 0
    }

    fun setData(value: Data) {
        this.value = value
        postInvalidate()
    }

    init {
        paint.style = Paint.Style.FILL
        listColor = mapOf(
            1 to getColor(R.color.ngu_sau),
           2 to getColor(R.color.ngu_nong),
            3 to getColor(R.color.REM),
            4 to getColor(R.color.thuc),
        )

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        this.h = h.toFloat()
        this.w = w.toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas!!.translate(0f, h)
        drawEntry(canvas)
    }


    private fun drawEntry(canvas: Canvas) {
        val listValue = value?.total
        if (listValue != null) {
            var previousPosition = 0f
            for (i in listValue.indices) {
                paint.color = listColor[listValue[i].type] ?: Color.TRANSPARENT
                val currentPosition = (w / listValue.size) * (i + 1)
                Log.e("ABC", previousPosition.toString()+ " " + currentPosition.toString() )
                canvas.drawRect(
                    previousPosition,
                    -h,
                    currentPosition,
                    0f,
                    paint
                )
                previousPosition = currentPosition
            }
        }
    }

    data class Time(val type: Int, val data: Any? = null)

}