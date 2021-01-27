package dev.maxc.ui

import dev.maxc.ui.model.DataLink
import dev.maxc.ui.model.DisplayLink
import dev.maxc.ui.model.DisplayNode
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.layout.AnchorPane
import java.net.URL
import java.util.*
import javafx.animation.Animation

import javafx.animation.KeyFrame

import javafx.animation.Timeline
import javafx.event.EventHandler
import javafx.util.Duration
import kotlin.collections.ArrayList


/**
 * @author Max Carter
 * @since 06/06/2020
 */
class MainController : Initializable {
    @FXML
    lateinit var anchor: AnchorPane

    private val displayNodes = ArrayList<DisplayNode>()
    private val displayLinks = ArrayList<DisplayLink>()

    private val analytics = Analytics()

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        anchor.minWidth = 1920.0
        anchor.minHeight = 1080.0
        anchor.toBack()

        val timeline = Timeline(
            KeyFrame(
                Duration.seconds(0.1),
                EventHandler {
                    generateRandomPoint()
                })
        )
        timeline.cycleCount = Animation.INDEFINITE
        timeline.play()
    }

    private fun generateRandomPoint() {
        val dp = DisplayNode(analytics, "test-${displayNodes.size}")
        displayNodes.add(dp)
        analytics.nodeCount++
        if (displayNodes.size > 1) {
            displayNodes.takeLast(5).forEach {
                if (Utils.randomInt(0, displayNodes.size) > displayNodes.size*0.75) {
                    val displayLink = DisplayLink(dp, it)
                    it.addLink(displayLink)
                    dp.addLink(displayLink)

                    displayLinks.add(displayLink)
                    analytics.linkCount++
                    anchor.children.add(displayLink)
                    displayLink.show()
                }
            }
        }
        anchor.children.add(dp)
        dp.show()
    }
}