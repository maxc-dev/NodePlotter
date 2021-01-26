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
        super.setStrokeWidth(1.0)
        stroke = Color.rgb(60, 170, 170)
        println(target.toString())
        println(comparator.toString())
    }

    fun show() {
        opacity = 0.0
        val ft = FadeTransition(Duration.millis(2000.0), this)
        ft.toValue = 0.7

        ft.play()
    }
}