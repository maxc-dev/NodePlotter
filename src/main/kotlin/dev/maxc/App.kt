package dev.maxc

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage


/**
 * @author Max Carter
 * @since 06/06/2020
 */
class App : Application() {
    override fun start(stage: Stage?) {
        stage?.scene = Scene(FXMLLoader.load(javaClass.getResource("/root.fxml")))
        stage?.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(App::class.java)
        }
    }
}