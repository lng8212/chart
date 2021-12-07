package com.example.chart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat

class StressChart(context: Context, attributeSet: AttributeSet): View(context,attributeSet)  {

    val yellow = ContextCompat.getColor(context,R.color.yellow)
    val blue = ContextCompat.getColor(context, R.color.blue)
    val green = ContextCompat.getColor(context, R.color.green)
    val color_text = ContextCompat.getColor(context,R.color.text)
    val color_line = ContextCompat.getColor(context,R.color.line)
    var h = 0f
    var w = 0f
    lateinit var paint:Paint
    val value = listOf(20 to 20, 21 to 15,22 to 12, 25 to 15, 26 to 15,27 to 21, 29 to 18, 30 to 25, 31 to 15, 32 to 16, 33 to 30, 34 to 17, 50 to 50, 51 to 30, 52 to 20)

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
        paint.strokeWidth = w/220
        val final = w/16 *14
        val begin = w/16*2
        for (item in value){
            if(item.second%3 ==0) paint.color = blue
            else if (item.second %3 ==1) paint.color = yellow
            else paint.color = green
            canvas.drawLine((final - begin)/144 * item.first + begin,-w/20f,(final - begin)/144* item.first + begin,((-h+w/20)/4) + ((-h+ w/20)/80)*item.second - w/20,paint );
        }

    }


    private fun drawText(canvas: Canvas?){
        val h1 = h - w/20
        val xValue = listOf("00:00" to w/16 * 2, "04:00" to w/16 * 4, "08:00" to w/16* 6, "12:00" to w/16*8, "16:00" to w/16*10, "20:00" to w/16*12, "23:59" to w/16 * 14)
        val yValue = listOf("0" to (-h1)/4 - w/20, "20" to -(h1/4) * 2 - w/20, "40" to -(h1/4) * 3 - w/20)

        paint.textSize = w/30
        paint.color = color_text

        for (item in xValue){
            canvas!!.drawText(item.first,item.second -  w/25, 0f, paint)
        }
        for (item in yValue){
            canvas!!.drawText(item.first,w/16*2 - w/17, item.second,paint)
        }

    }
    private fun drawAxis(canvas: Canvas?) {
        val h1 = h - w/20
        paint.color = color_line
        paint.strokeWidth = w/200

        canvas!!.drawLine(w/16*2 ,-w/20, (w/16)*14,-w/20f,paint)
        canvas!!.drawLine(w/16*2,-w/20,w/16*2,-h,paint)

        paint.strokeWidth = w/500
        for (i in 2..14){
            canvas.drawLine((w/16)*i,-w/20f,(w/16)*i, -h ,paint)
        }
        for (i in 1..3){
            canvas.drawLine(w/16*2,(-h1 /4)*i - w/20,(w/16)*14, (-h1/4)*i  - w/20,paint)
        }

    }
}