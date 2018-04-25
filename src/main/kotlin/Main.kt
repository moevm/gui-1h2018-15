import controllers.FxmlNamesOfController
import controllers.MainMenuController
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import logic.LessonsDB

fun main(args: Array<String>) {
    Application.launch(Main::class.java)
}

class Main : Application() {

    override fun start(primaryStage: Stage?) {
        primaryStage?.title = "Googlr"
        val loader = FXMLLoader(javaClass.getResource(FxmlNamesOfController.getFxmlNameForController(MainMenuController::class.java)))
        val scene = Scene(loader.load())
        primaryStage?.scene = scene
        primaryStage?.show()
        LessonsDB.readFromFile()
        loader.getController<MainMenuController>().bindModel(LessonsDB.getAllLessons())

    }

}