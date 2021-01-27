package dev.maxc.ui

import dev.maxc.ui.model.DisplayNode
import kotlin.math.min
import kotlin.math.pow


/**
 * @author Max Carter
 * @since  27/01/2021
 */
class Analytics {
    /*
        Calculates sizes and proportions based on data set
     */

    var nodeCount = 0
    var linkCount = 0
    var highestLinkage = 0

    /**
     * Returns the node position ratio that the node should target to be at
     */
    fun getNodeTargetRatio(node: DisplayNode): Double {
        val size = node.displayLinks.size
        if (size > highestLinkage) {
            highestLinkage = size
        }

        if (nodeCount <= 1) {
            return 1.0
        }
        /*
            with dataset with large connections, nodes quickly tend to the center
         */
        val ratio = min(node.displayLinks.size.toDouble() /highestLinkage.toDouble(), 0.95)
        return 1 - ratio
    }
}