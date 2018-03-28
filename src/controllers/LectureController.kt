package controllers

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.web.WebView
import models.Lecture
import models.Lesson

class LectureController: BaseController<Lecture>() {

    @FXML
    private lateinit var webView: WebView
    @FXML
    private lateinit var openTestButton: Button

    override fun bindModel(model: Lecture) {
        super.bindModel(model)
        webView.engine.load(model.wayToTheory)
        openTestButton.setOnMouseClicked {
            closeWindowAndOpenNew<Lesson, TestScreenController>(openTestButton, model = model)
        }
    }

    override fun getTitle(): String = "Лекция"

}