package dev.maxc.ui

import kotlin.random.Random

object Utils {
    const val WIDTH = 1920
    const val HEIGHT = 1080

    fun randomInt(min: Int, max: Int): Int {
        return Random.nextInt(max - min + 1) + min
    }
}