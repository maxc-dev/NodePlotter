package dev.maxc

import dev.maxc.ui.MainController
import javafx.application.Application
import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.stage.StageStyle
import java.util.*

/**
 * @author Max Carter
 * @since 06/06/2020
 */
class App : Application() {
    override fun start(stage: Stage?) {
        if (stage != null) {
            val loader = FXMLLoader(javaClass.getResource("/root.fxml"))
            val scene = Scene(loader.load())
            stage.scene = scene

            stage.isAlwaysOnTop = true
            stage.x = 0.0
            stage.y = 0.0
            stage.initStyle(StageStyle.UNDECORATED)

            stage.show()
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(App::class.java)
        }
    }
}