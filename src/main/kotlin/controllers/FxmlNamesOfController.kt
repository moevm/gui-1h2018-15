package controllers

object FxmlNamesOfController {
    fun getFxmlNameForController(cls: Class<*>): String = when (cls) {
        LectureController::class.java -> "fxml/TheoryLecture.fxml"
        MainMenuController::class.java -> "fxml/MainMenu.fxml"
        TestScreenController::class.java -> "fxml/TestScreen.fxml"
        else -> throw IllegalArgumentException("No fxml name for your controller")
    }
}