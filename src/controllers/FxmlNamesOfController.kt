package controllers

object FxmlNamesOfController {
    fun getFxmlNameForController(cls: Class<*>): String = when(cls) {
        LectureController::class.java -> "TheoryLecture.fxml"
        MainMenuController::class.java -> "MainMenu.fxml"
        TestScreenController::class.java -> "TestScreen.fxml"
        else -> throw IllegalArgumentException("No fxml name for your controller")
    }
}