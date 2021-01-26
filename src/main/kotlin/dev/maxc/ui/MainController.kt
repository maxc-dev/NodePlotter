package dev.maxc.ui

import dev.maxc.ui.model.DisplayLink
import dev.maxc.ui.model.DisplayNode
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Pane
import java.net.URL
import java.util.*
import javafx.animation.Animation

import javafx.animation.KeyFrame

import javafx.animation.Timeline
import javafx.event.ActionEvent
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

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        anchor.minWidth = 1920.0
        anchor.minHeight = 1080.0
        anchor.toBack()

        val timeline = Timeline(
            KeyFrame(
                Duration.seconds(0.5),
                EventHandler { generatePoint() })
        )
        timeline.cycleCount = Animation.INDEFINITE
        timeline.play()
    }

    private fun updateWithDataPoint(node: DisplayNode) {
        node.layoutX = getRandX()
        node.layoutY = getRandY()
        anchor.children.add(node)
        node.show()

        node.links.forEach {
            val displayLink = DisplayLink(node, it.comparator)
            displayLinks.add(displayLink)
            anchor.children.add(displayLink)
            displayLink.show()
        }
    }

    private fun generatePoint() {
        val dp = DisplayNode("test-${displayNodes.size}")
        displayNodes.add(dp)
        if (displayNodes.size > 1) {
            displayNodes.forEach {
                if (getRandX() > 1800) {
                    dp.addLink(it)
                }
            }
        }
        updateWithDataPoint(dp)
    }

    private val random = Random()

    private fun getRandX() = random.nextInt(1920).toDouble()
    private fun getRandY() = random.nextInt(1080).toDouble()
}