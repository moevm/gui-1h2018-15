package controllers

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.ScrollPane
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
        vBox.children.setAll(model.map { lesson ->
            Button().apply {
                text = "${lesson.name} ${if (lastLessonFound) "" else "(${lesson.testResult}/${lesson.test.questions.size})"}"
                isDisable = lastLessonFound
                if (!lastLessonFound && lesson.testResult < lesson.test.minimum) {
                    lastLessonFound = true
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