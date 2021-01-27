package dev.maxc.ui

import dev.maxc.ui.model.DisplayLink
import dev.maxc.ui.model.DisplayNode


/**
 * @author Max Carter
 * @since  27/01/2021
 */
class Analytics(val dataPoints: ArrayList<DisplayNode>, val dataLinks: ArrayList<DisplayLink>) {
    /*
        Calculates sizes and proportions based on data set
     */

    /**
     * Returns the size the node should be based on it's outward links
     * in relation to the total amount of outward links
     */
    fun getNodeSize(node: DisplayNode): Double {
       // val ratio = (node.links.size/dataLinks.size).toDouble() //also need to bare in mind frequency?
        return 0.0;
    }

    fun getNodeOffsetX(node: DisplayNode): Double {
        return 0.0
    }
}