package com.example.chart

import android.content.AttributionSource
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

class SleepChart(context: Context,attributeSet: AttributeSet) : View(context, attributeSet) {

    val ngu_sau = ContextCompat.getColor(context,R.color.ngu_sau)
    val ngu_nong = ContextCompat.getColor(context, R.color.ngu_nong)
    val rem = ContextCompat.getColor(context, R.color.REM)
    val thuc = ContextCompat.getColor(context,R.color.thuc)

    lateinit var paint: Paint
    var h = 0f
    var w = 0f
    val timeSleep = 8*60
    val ngusau = listOf(0 to 30, 50 to 70)
    val ngunong = listOf(30 to 35, 70 to 200, 202 to 300, 330 to 480)
    val remm= listOf(35 to 40, 200 to 202, 300 to 330)
    val thucc = listOf(40 to 50)


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
        for (item in thucc){
            canvas!!.drawRect(w/timeSleep * item.first, -h,w/timeSleep*item.second,0f,paint)
        }
    }

    private fun drawRem(canvas: Canvas) {
        paint.color = rem
        for (item in remm){
            canvas!!.drawRect(w/timeSleep * item.first, -h,w/timeSleep*item.second,0f,paint)
        }

    }

    private fun drawNgunong(canvas: Canvas) {
        paint.color = ngu_nong
        for (item in ngunong){
            canvas!!.drawRect(w/timeSleep * item.first, -h,w/timeSleep*item.second,0f,paint)
        }
    }

    private fun drawNgusau(canvas: Canvas?) {
        paint.color = ngu_sau
        for (item in ngusau){
            canvas!!.drawRect(w/timeSleep * item.first, -h,w/timeSleep*item.second,0f,paint)
        }
    }

}