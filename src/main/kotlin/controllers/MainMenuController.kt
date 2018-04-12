package controllers

import com.jfoenix.controls.JFXButton
import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.scene.control.Button
import javafx.scene.control.ScrollPane
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import models.Lecture
import models.Lesson

class MainMenuController : BaseController<List<Lesson>>() {

    @FXML
    private lateinit var scrollBar: ScrollPane
    @FXML
    private lateinit var vBox: VBox

    @FXML
    fun initialize() {
        scrollBar.isFitToWidth = true
    }

    override fun bindModel(model: List<Lesson>) {
        super.bindModel(model)

        var lastLessonFound = false
        vBox.maxHeight = Double.MAX_VALUE
        vBox.padding = Insets(5.0)
        vBox.children.setAll(model.map { lesson ->
            JFXButton().apply {
                VBox.setMargin(this, Insets(5.0))
                maxWidth = Double.MAX_VALUE
                text = "${lesson.name} ${if (lastLessonFound) "" else "(${lesson.testResult}/${lesson.test.questions.size})"}"
                isDisable = lastLessonFound
                if (!lastLessonFound && lesson.testResult < lesson.test.minimum) {
                    lastLessonFound = true
                }

                styleClass.add("button")
                id = if (lesson is Lecture) {
                    "lesson"
                } else {
                    "exam"
                }
                setOnMouseClicked {
                    if (lesson is Lecture) {
                        closeWindowAndOpenNew<Lecture, LectureController>(model = lesson, childNode = vBox)
                    } else {
                        closeWindowAndOpenNew<Lesson, TestScreenController>(model = lesson, childNode = vBox)
                    }

                }
            }
        })
    }

    override fun getTitle(): String = "Googlr"

}