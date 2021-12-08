package com.example.chart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

class ProgressBar(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    val progess = ContextCompat.getColor(context, R.color.target)
    val full = ContextCompat.getColor(context, R.color.full)
    val text = ContextCompat.getColor(context, R.color.textProgess)
    var h = 0f
    var w = 0f
    var percent = 0f
    var step = 0


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        this.h = h.toFloat()
        this.w = w.toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas!!.translate(0f, h)
        drawProgess(canvas)
    }

    private fun drawProgess(canvas: Canvas) {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.STROKE
        paint.color = progess
        paint.strokeWidth = w / 30
        paint.textAlign = Paint.Align.CENTER

        val backPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        backPaint.style = Paint.Style.STROKE
        backPaint.color = full
        backPaint.textAlign = Paint.Align.CENTER
        backPaint.strokeWidth = w / 30


        val circle = RectF(w / 2 - (h) / 2 + w / 30, -h + w / 30, w / 2 + (h) / 2 - w / 30, -w / 30)



        canvas.drawArc(circle, 120f, 300f, false, backPaint)
        canvas.drawArc(circle, 120f, percent * (300f) / 100, false, paint)
    }

    fun setProgress(percent: Float, step: Int) {
        this.percent = percent
        this.step = step
        postInvalidate()
    }

}