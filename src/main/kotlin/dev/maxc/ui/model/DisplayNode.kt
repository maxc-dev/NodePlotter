package dev.maxc.ui.model

import dev.maxc.ui.Analytics
import javafx.animation.Animation
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.animation.FadeTransition
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.event.EventHandler
import javafx.util.Duration
import dev.maxc.ui.Utils
import kotlin.math.cos
import kotlin.math.sin


/**
 * @author Max Carter
 * @since  26/01/2021
 */
class DisplayNode(val analytics: Analytics, val word: String, var frequency: Int = 1) : Circle(0.0, 0.0, MIN_RADIUS, Color.rgb(60, 170, 170)) {
    val displayLinks = arrayListOf<DisplayLink>()

    private val OFFSET_Y = 4
    private val OFFSET_X = 12

    private var xOffset = 0.0
    private var yOffset = 0.0
    private var counter = 0.0

    init {
        counter = Utils.randomInt(0, 360).toDouble()
        calcOffsets()
        setPosition()
    }

    fun calcOffsets() {
        xOffset = (0.5 / frequency) + Utils.randomInt(0, OFFSET_X).toDouble() / 10
        yOffset = (0.2 / frequency) + Utils.randomInt(0, OFFSET_Y).toDouble() / 10
    }

    fun setPosition() {
        setLayoutX(xOffset * sin(Math.toRadians(counter)) * (Utils.WIDTH / 3) + Utils.WIDTH / 2)
        setLayoutY(yOffset * cos(Math.toRadians(counter)) * (Utils.HEIGHT) + Utils.HEIGHT / 2)
    }

    fun show() {
        opacity = 0.0
        val ft = FadeTransition(Duration.millis(1000.0), this)
        ft.toValue = 0.9
        ft.play()

        //forever move about
        val timeline = Timeline(
            KeyFrame(
                Duration.seconds(0.01),
                EventHandler {
                    counter += 0.02
                    if (counter >= 360) {
                        counter = 0.0;
                    }
                    setPosition()
                    nodeMoved()
                })
        )
        timeline.cycleCount = Animation.INDEFINITE
        timeline.play()
    }

    fun addLink(dl: DisplayLink) {
        displayLinks.add(dl)
        grow()
    }

    private fun nodeMoved() {
        displayLinks.forEach {
            it.onNodeMove()
        }
    }

    private fun grow() {
        frequency++
        //calcOffsets()
        var rate = frequency.toDouble()
        val timeline = Timeline(
            KeyFrame(
                Duration.seconds(0.2),
                EventHandler {
                    radius = MIN_RADIUS + (RADIUS_INCREASE * rate)
                    rate += 0.2
                })
        )
        timeline.cycleCount = 5
        timeline.play()

    }

    companion object {
        private const val MIN_RADIUS = 2.0
        private const val RADIUS_INCREASE = 1.5
    }
}