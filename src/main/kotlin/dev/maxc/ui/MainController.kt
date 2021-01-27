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


/**
 * @author Max Carter
 * @since 06/06/2020
 */
class MainController : Initializable {
    @FXML
    lateinit var anchor: AnchorPane

    private val displayNodes = arrayListOf<DisplayNode>()
    private val displayLinks = arrayListOf<DisplayLink>()

    private val analytics = Analytics(displayNodes, displayLinks)

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        anchor.minWidth = 1920.0
        anchor.minHeight = 1080.0
        anchor.toBack()

        val timeline = Timeline(
            KeyFrame(
                Duration.seconds(0.5),
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
        if (displayNodes.size > 1) {
            displayNodes.forEach {
                if (Utils.randomInt(0, displayNodes.size) > displayNodes.size*0.95) {
                    val displayLink = DisplayLink(dp, it)
                    it.addLink(displayLink)
                    dp.addLink(displayLink)

                    displayLinks.add(displayLink)
                    anchor.children.add(displayLink)
                    displayLink.show()
                }
            }
        }
        anchor.children.add(dp)
        dp.show()
    }
}