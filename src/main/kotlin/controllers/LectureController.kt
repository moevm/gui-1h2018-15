package controllers

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.web.WebView
import models.Lecture
import models.Lesson
import java.net.URL

class LectureController : BaseController<Lecture>() {

    @FXML
    private lateinit var webView: WebView
    @FXML
    private lateinit var openTextButton: Button

    override fun bindModel(model: Lecture) {
        super.bindModel(model)
        try {
            val url = URL(model.wayToTheory)

            val connection = url.openConnection()
            connection.connect()

            webView.engine.load(model.wayToTheory)

        } catch (e: Exception) {

            webView.engine.loadContent("<h1>Что-то пошло не так. Проблемы с интернетом</h1>")

        }

        openTextButton.setOnMouseClicked {
            closeWindowAndOpenNew<Lesson, TestScreenController>(openTextButton, model = model)
        }
    }

    override fun getTitle(): String = "Лекция"

}