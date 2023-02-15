package org.sopt.zooczoocbbangbbang.util

import android.content.Context
import android.view.View

abstract class ViewAssociatedPositionCalculator(val myContext: Context, val source: View) {
    val sourceX: Int
    val sourceY: Int

    init {
        val sourcePos: IntArray = intArrayOf(0, 0)
        source.getLocationOnScreen(sourcePos)
        sourceX = sourcePos[0]
        sourceY = sourcePos[1]
    }

    abstract fun calculatePosX(): Int

    abstract fun calculatePosY(): Int
}
