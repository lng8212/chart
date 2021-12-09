package com.example.chart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

class StressChart(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {


    private val color_text = ContextCompat.getColor(context, R.color.text)
    private val color_line = ContextCompat.getColor(context, R.color.line)
    private var h = 0f
    private var w = 0f
    private lateinit var paint: Paint
    private var value = mutableListOf<Value>()
    private var xAxisValue = mutableListOf<String>()
    private var yAxisValue = mutableListOf<String>()
    private var typeColor = mapOf(
        "blue" to ContextCompat.getColor(context, R.color.blue),
        "yellow" to ContextCompat.getColor(context, R.color.yellow),
        "green" to ContextCompat.getColor(context, R.color.green)
    )

    @JvmName("setValue1")
    fun setValue(value: MutableList<Value>,xAxisValue: MutableList<String>,yAxisValue: MutableList<String>) {
        this.value = value
        this.xAxisValue = xAxisValue
        this.yAxisValue = yAxisValue
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
        val final = w / 16 * 14
        val begin = w / 16 * 2
        paint.textSize = w / 30
        paint.color = color_text
        val positionxAxisValue = mutableMapOf<String,Float>()
        val positionYAxisValue = mutableMapOf<String,Float>()

        val xSize = xAxisValue.size
        val ySize = yAxisValue.size
        for (i in 0 until xSize){
            positionxAxisValue[xAxisValue[i]] = ((final-begin)*(i+1))/ (xSize-1)
        }
        for (i in 0 until ySize){
            positionYAxisValue[yAxisValue[i]] = (i+1) * (-h1)/(ySize+1) - w/20
        }
        for (item in positionxAxisValue) {
            canvas!!.drawText(item.key, item.value - w / 25, 0f, paint)
        }
        for (item in positionYAxisValue) {
            canvas!!.drawText(item.key, w / 16 * 2 - w / 17, item.value, paint)
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