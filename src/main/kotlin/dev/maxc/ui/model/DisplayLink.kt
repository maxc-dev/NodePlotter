package dev.maxc.ui.model

import javafx.animation.FadeTransition
import javafx.scene.paint.Color
import javafx.scene.shape.Line
import javafx.util.Duration


/**
 * @author Max Carter
 * @since  26/01/2021
 */
class DisplayLink(val target: DisplayNode, val comparator: DisplayNode, val distance: Int = 1, val frequency: Int = 1) :
    Line(target.layoutX, target.layoutY, comparator.layoutX, comparator.layoutY) {
    init {
        strokeWidth = MIN_WIDTH
        stroke = Color.rgb(60, 170, 170)
    }

    fun onNodeMove() {
        startX = target.layoutX
        startY = target.layoutY
        endX = comparator.layoutX
        endY = comparator.layoutY
    }

    fun grow() {
        strokeWidth = MIN_WIDTH + (WIDTH_INCREASE * frequency)
    }

    fun show() {
        opacity = 0.0
        val ft = FadeTransition(Duration.millis(1000.0), this)
        ft.toValue = 0.7

        ft.play()
    }

    companion object {
        const val MIN_WIDTH = 0.5
        const val WIDTH_INCREASE = 1.2
    }
}