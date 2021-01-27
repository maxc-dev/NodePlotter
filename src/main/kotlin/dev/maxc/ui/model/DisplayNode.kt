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
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin


/**
 * @author Max Carter
 * @since  26/01/2021
 */
class DisplayNode(val analytics: Analytics, val word: String, var frequency: Int = 1) :
    Circle(0.0, 0.0, MIN_RADIUS, Color.rgb(60, 170, 170)) {
    val displayLinks = arrayListOf<DisplayLink>()

    private var targetRatio = 1.0
    private var actualRatio = 1.0

    private var xOffset = 0.0
    private var yOffset = 0.0
    private var counter = 0.0

    init {
        counter = Utils.randomInt(0, 360).toDouble()
        calcOffsets()
        setPosition()
    }

    private fun calcOffsets() {
        /*
            with offsets
            0 means the node will be in the center
            1 means the node will be to the edge
         */

        xOffset = Utils.randomInt(60, 90).toDouble() / 100
        yOffset = Utils.randomInt(60, 90).toDouble() / 100
    }

    private fun setPosition() {
        val testRatio = analytics.getNodeTargetRatio(this)
        if (testRatio != targetRatio) {
            targetRatio = testRatio
        } else {
            if (abs(targetRatio - actualRatio) < RATIO_INCREASE) {
                actualRatio = targetRatio
            } else if (targetRatio > actualRatio) {
                actualRatio += RATIO_INCREASE
            } else {
                actualRatio -= RATIO_INCREASE
            }
        }
        layoutX = (xOffset * actualRatio * sin(Math.toRadians(counter)) * (Utils.WIDTH / 2)) + Utils.WIDTH / 2
        layoutY = (yOffset * actualRatio * cos(Math.toRadians(counter)) * (Utils.HEIGHT / 2)) + Utils.HEIGHT / 2
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
                    counter += 0.0001 + analytics.getNodeTargetRatio(this) / 10
                    if (counter >= 360) {
                        counter = 0.0
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
        if (radius < MAX_RADIUS) {
            var rate = frequency.toDouble()
            val timeline = Timeline(
                KeyFrame(
                    Duration.seconds(0.2),
                    EventHandler {
                        radius = min(MIN_RADIUS + (RADIUS_INCREASE_RATIO * rate), MAX_RADIUS)
                        rate += 0.2
                    })
            )
            timeline.cycleCount = 5
            timeline.play()
        }
    }

    companion object {
        private const val MIN_RADIUS = 1.0
        private const val MAX_RADIUS = 14.0
        private const val RADIUS_INCREASE_RATIO = 1.1
        private const val RATIO_INCREASE = 0.0005
    }
}