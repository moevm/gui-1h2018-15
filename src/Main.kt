import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import controllers.TestScreenController
import logic.TestAndQuestionsDB

class Main : Application() {

    override fun start(primaryStage: Stage?) {
        primaryStage?.title = "Googlr"
        val loader = FXMLLoader(javaClass.getResource("layouts/TestScreen.fxml"))
        val scene = Scene(loader.load())
        primaryStage?.scene = scene
        primaryStage?.show()
        loader.getController<TestScreenController>().bindTestAndStart(TestAndQuestionsDB.getTest())

    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }

}