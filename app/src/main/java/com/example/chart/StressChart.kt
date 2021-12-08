package com.example.chart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

class StressChart(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {


    val color_text = ContextCompat.getColor(context, R.color.text)
    val color_line = ContextCompat.getColor(context, R.color.line)
    var h = 0f
    var w = 0f
    lateinit var paint: Paint
    var value = mutableListOf<Value>()
    var typeColor = mapOf(
        "blue" to ContextCompat.getColor(context, R.color.blue),
        "yellow" to ContextCompat.getColor(context, R.color.yellow),
        "green" to ContextCompat.getColor(context, R.color.green)
    )


    @JvmName("setValue1")
    fun setValue(value: MutableList<Value>) {
        this.value = value
        postInvalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        this.h = h.toFloat()
        this.w = w.toFloat()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        canvas!!.translate(0f, h)
        drawAxis(canvas)
        drawText(canvas)
        drawValue(canvas)
    }

    private fun drawValue(canvas: Canvas) {
        paint.strokeWidth = w / 220
        val final = w / 16 * 14
        val begin = w / 16 * 2
        for (item in value) {
            paint.color = typeColor.get(item.color)!!
            canvas.drawLine(
                (final - begin) / 144 * item.x + begin,
                -w / 20f,
                (final - begin) / 144 * item.x + begin,
                ((-h + w / 20) / 4) + ((-h + w / 20) / 80) * item.y - w / 20,
                paint
            )
        }

    }


    private fun drawText(canvas: Canvas?) {
        val h1 = h - w / 20
        val xValue = listOf(
            "00:00" to w / 16 * 2,
            "04:00" to w / 16 * 4,
            "08:00" to w / 16 * 6,
            "12:00" to w / 16 * 8,
            "16:00" to w / 16 * 10,
            "20:00" to w / 16 * 12,
            "23:59" to w / 16 * 14
        )
        val yValue = listOf(
            "0" to (-h1) / 4 - w / 20,
            "20" to -(h1 / 4) * 2 - w / 20,
            "40" to -(h1 / 4) * 3 - w / 20
        )

        paint.textSize = w / 30
        paint.color = color_text

        for (item in xValue) {
            canvas!!.drawText(item.first, item.second - w / 25, 0f, paint)
        }
        for (item in yValue) {
            canvas!!.drawText(item.first, w / 16 * 2 - w / 17, item.second, paint)
        }

    }

    private fun drawAxis(canvas: Canvas?) {
        val h1 = h - w / 20
        paint.color = color_line
        paint.strokeWidth = w / 200

        canvas!!.drawLine(w / 16 * 2, -w / 20, (w / 16) * 14, -w / 20f, paint)
        canvas.drawLine(w / 16 * 2, -w / 20, w / 16 * 2, -h, paint)

        paint.strokeWidth = w / 500
        for (i in 2..14) {
            canvas.drawLine((w / 16) * i, -w / 20f, (w / 16) * i, -h, paint)
        }
        for (i in 1..3) {
            canvas.drawLine(
                w / 16 * 2,
                (-h1 / 4) * i - w / 20,
                (w / 16) * 14,
                (-h1 / 4) * i - w / 20,
                paint
            )
        }

    }

    data class Value(val x: Int, val y: Int, val color: String, val time: String? = null)

}