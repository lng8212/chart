package com.example.chart

import android.content.AttributionSource
import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat

class ProgressBar(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    val progess = ContextCompat.getColor(context, R.color.target)
    val full = ContextCompat.getColor(context, R.color.full)
    val text = ContextCompat.getColor(context, R.color.textProgess)
    var h = 0f
    var w = 0f
    var time = 0f
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
        setProgress(50f,5000)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.STROKE
        paint.color = progess
        paint.strokeWidth = w/30
        paint.textAlign = Paint.Align.CENTER

        val backPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        backPaint.style = Paint.Style.STROKE
        backPaint.color = full
        backPaint.textAlign = Paint.Align.CENTER
        backPaint.strokeWidth = w/30

        val textPaintPer = Paint(Paint.ANTI_ALIAS_FLAG)
        textPaintPer.textAlign = Paint.Align.CENTER
        textPaintPer.textSize = w/15
        textPaintPer.color = text
        textPaintPer.setTypeface(Typeface.DEFAULT_BOLD)

        val textPaintCen = Paint(Paint.ANTI_ALIAS_FLAG)
        textPaintCen.textAlign = Paint.Align.CENTER
        textPaintCen.color = Color.BLACK
        textPaintCen.textSize = w/10
        textPaintCen.setTypeface(Typeface.DEFAULT_BOLD)


        val textStep = Paint(Paint.ANTI_ALIAS_FLAG)
        textStep.textAlign = Paint.Align.CENTER
        textStep.color = Color.GRAY
        textStep.textSize = w/15
        textStep.setTypeface(Typeface.DEFAULT_BOLD)



        val circle = RectF(w / 2 - (h) / 2 + w/30, -h + w/30, w / 2 + (h) / 2 - w/30, -w/30)



        val d = ContextCompat.getDrawable(context, R.drawable.activity)
        d!!.setBounds(
            ((circle.right + circle.left) / 2).toInt() - (circle.left / 10).toInt(),
            circle.top.toInt() + (-circle.top/ 10).toInt(),

            ((circle.right + circle.left) / 2).toInt() + (circle.left / 10).toInt(),
            circle.top.toInt() + (-circle.top / 4).toInt()
        )
        d.draw(canvas)

        canvas.drawText("bước", (circle.right + circle.left) / 2,circle.top/3, textStep)
        canvas.drawText(String.format("%,d",step),(circle.right + circle.left) / 2,circle.top/2, textPaintCen)
        canvas.drawText( time.toString() + "%", (circle.right + circle.left) / 2, circle.bottom, textPaintPer)
        canvas.drawArc(circle, 120f, 300f, false, backPaint)
        canvas.drawArc(circle, 120f, time * (300f) / 100, false, paint)
    }

    private fun setProgress(time: Float, step : Int) {
        this.time = time
        this.step = step
        postInvalidate()
    }

}