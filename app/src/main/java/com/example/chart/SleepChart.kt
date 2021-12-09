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

    data class Data(val timeSleep: Int,val ngusau:Map<Int,Int>, val ngunong:Map<Int,Int>, val rem: Map<Int,Int>, val thuc:Map<Int,Int>)
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
        val thucc = value.thuc
        for (item in thucc) {
            canvas.drawRect(
                w / value.timeSleep * item.key,
                -h,
                w / value.timeSleep * item.value,
                0f,
                paint
            )
        }
    }

    private fun drawRem(canvas: Canvas) {
        paint.color = rem
        val remm = value.rem
        for (item in remm) {
            canvas.drawRect(
                w / value.timeSleep* item.key,
                -h,
                w / value.timeSleep * item.value,
                0f,
                paint
            )
        }

    }

    private fun drawNgunong(canvas: Canvas) {
        paint.color = ngu_nong
        val ngunong = value.ngunong
        for (item in ngunong) {
            canvas.drawRect(
                w / value.timeSleep * item.key,
                -h,
                w / value.timeSleep * item.value,
                0f,
                paint
            )
        }
    }

    private fun drawNgusau(canvas: Canvas?) {
        paint.color = ngu_sau
        val ngusau = value.ngusau
        for (item in ngusau) {
            canvas!!.drawRect(
                w / value.timeSleep * item.key,
                -h,
                w / value.timeSleep * item.value,
                0f,
                paint
            )
        }
    }

}