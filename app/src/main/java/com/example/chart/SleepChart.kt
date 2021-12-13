package com.example.chart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

class SleepChart(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    val ngu_sau = ContextCompat.getColor(context, R.color.ngu_sau)
    val ngu_nong = ContextCompat.getColor(context, R.color.ngu_nong)
    val rem = ContextCompat.getColor(context, R.color.REM)
    val thuc = ContextCompat.getColor(context, R.color.thuc)

    lateinit var paint: Paint
    var h = 0f
    var w = 0f
    lateinit var value: Data

    data class Data(val timeSleep: Int,val total:List<Time>)
    fun getValue (value :Data){
        this.value = value
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        this.h = h.toFloat()
        this.w = w.toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.FILL
        canvas!!.translate(0f, h)
        drawNgusau(canvas)
        drawNgunong(canvas)
        drawRem(canvas)
        drawThuc(canvas)

    }

    private fun drawThuc(canvas: Canvas) {
        paint.color = thuc
        for (item in value.total) {
            if(item.type == "Thuc"){
                canvas.drawRect(
                    w / value.timeSleep * item.start,
                    -h,
                    w / value.timeSleep * item.end,
                    0f,
                    paint
                )
            }

        }
    }

    private fun drawRem(canvas: Canvas) {
        paint.color = rem
        for (item in value.total) {
            if(item.type == "REM"){
                canvas.drawRect(
                    w / value.timeSleep* item.start,
                    -h,
                    w / value.timeSleep * item.end,
                    0f,
                    paint
                )
            }

        }

    }

    private fun drawNgunong(canvas: Canvas) {
        paint.color = ngu_nong
        for (item in value.total) {
            if(item.type == "Ngu nong"){
                canvas.drawRect(
                    w / value.timeSleep * item.start,
                    -h,
                    w / value.timeSleep * item.end,
                    0f,
                    paint
                )
            }

        }
    }

    private fun drawNgusau(canvas: Canvas?) {
        paint.color = ngu_sau
        for (item in value.total) {
            if(item.type == "Ngu sau"){
                canvas!!.drawRect(
                    w / value.timeSleep * item.start,
                    -h,
                    w / value.timeSleep * item.end,
                    0f,
                    paint
                )
            }
        }
    }

    data class Time (val start: Int, val end: Int, val type: String)
}