package dev.maxc.ui.model

import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.animation.FadeTransition
import javafx.util.Duration


/**
 * @author Max Carter
 * @since  26/01/2021
 */
class DisplayNode(val word: String, var frequency: Int = 0) : Circle(0.0, 0.0, MIN_RADIUS, Color.rgb(60, 170, 170)) {
    val links = arrayListOf<DataLink>()

    fun show() {
        opacity = 0.0
        val ft = FadeTransition(Duration.millis(1000.0), this)
        ft.toValue = 0.9

        ft.play()
    }

    fun addLink(dp: DisplayNode) {
        links.add(DataLink(dp))
        frequency++
        updateSize()
    }

    fun updateSize() {
        radius += RADIUS_INCREASE //will become dynamic based on relativity
    }

    companion object {
        private const val MIN_RADIUS = 5.0;
        private const val RADIUS_INCREASE = 2.0;
    }
}