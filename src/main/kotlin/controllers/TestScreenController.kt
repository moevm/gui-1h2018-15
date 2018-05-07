package controllers

import com.jfoenix.controls.JFXButton
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.collections.FXCollections
import javafx.collections.ListChangeListener
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextArea
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.web.WebView
import javafx.util.Duration
import logic.LessonsDB
import models.Exam
import models.Lecture
import models.Lesson

class TestScreenController : BaseController<Lesson>() {

    companion object {
        private const val TIME_FOR_QUESTION = 12
        private const val TIME_FOR_RELAX = 3
        private const val TIME_FOR_BEGIN = 3
    }

    @FXML
    private lateinit var webBrowser: WebView
    @FXML
    private lateinit var textOfQuestion: Label
    @FXML
    private lateinit var timer: Label
    @FXML
    private lateinit var questionsCounter: Label
    @FXML
    private lateinit var textAnswerBlock: HBox
    @FXML
    private lateinit var chooseAnswerButtons: VBox
    @FXML
    private lateinit var answerField: TextArea
    @FXML
    private lateinit var answerApplyButton: Button
    @FXML
    private lateinit var goToMenuButton: Button
    @FXML
    private lateinit var goToNextLessonButton: Button
    @FXML
    private lateinit var wrapUpText: Label

    private var questionNumber = 0
    private var rightAnswers = 0
    private var currentTimeline: Timeline? = null
    private var observableList = FXCollections.observableArrayList<String>()
    private var nextLesson: Lesson? = null

    @FXML
    private fun initialize() {
        answerApplyButton.setOnMouseClicked {
            stopTimer(true, answerField.text)
        }

        goToMenuButton.setOnMouseClicked {
            closeWindowAndOpenNew<List<Lesson>, MainMenuController>(model = LessonsDB.getAllLessons(), childNode = goToMenuButton)
        }

        observableList.addListener(ListChangeListener {
            chooseAnswerButtons.children.clear()
            chooseAnswerButtons.children.setAll(observableList.map { answer ->
                JFXButton(answer).apply {
                    VBox.setMargin(this, Insets(5.0))
                    maxWidth = Double.MAX_VALUE
                    styleClass.add("button")
                    setOnMouseClicked {
                        stopTimer(true, answer)
                    }
                }
            })
        })
    }

    override fun bindModel(model: Lesson) {
        super.bindModel(model)
        questionNumber = 0
        rightAnswers = 0
        cleanAll()
        playTimer(TIME_FOR_BEGIN, true, {
            bindNextQuestion()
        })

        nextLesson = LessonsDB.getNextLesson(model)
        nextLesson?.let { lesson ->
            goToNextLessonButton.text = "Начать \"${lesson.name}\""
            goToNextLessonButton.setOnMouseClicked {
                when (lesson) {
                    is Exam -> closeWindowAndOpenNew<Lesson, TestScreenController>(goToNextLessonButton, lesson)
                    is Lecture -> closeWindowAndOpenNew<Lecture, LectureController>(goToNextLessonButton, lesson)
                }

            }
        }
    }

    override fun getTitle(): String = "Тестирование"

    private fun updateWebView() {
        webBrowser.engine.load("https://www.google.ru/")
    }

    private fun playTimer(secs: Int, changeLabel: Boolean, onFinishedAction: () -> (Unit) = {}) {
        var counter = secs
        currentTimeline = Timeline(KeyFrame(Duration.seconds(1.0), EventHandler {
            if (changeLabel) {
                timer.text = "${--counter} s"
            }
            if (counter == 0) {
                currentTimeline?.stop()
                onFinishedAction()
            }

        })).apply {
            cycleCount = secs
            play()
        }
    }

    private fun stopTimer(byUser: Boolean, answer: String? = null) {
        answerApplyButton.isDisable = true
        val answerIsRight = answer == model.test.questions[questionNumber].rightAnswer

        wrapUpText.isVisible = true
        chooseAnswerButtons.isVisible = false
        textAnswerBlock.isVisible = false

        wrapUpText.text = "${if (answerIsRight) "П" else "Неп"}равильный ответ"
        wrapUpText.id = if (answerIsRight) "right" else "wrong"

        if (byUser) {
            currentTimeline?.stop()
            rightAnswers += if (answerIsRight) 1 else 0
        }

        questionNumber++

        playTimer(TIME_FOR_RELAX, true, {
            if (questionNumber == model.test.questions.size) {
                endScreen()
            } else {
                bindNextQuestion()
            }
        })
    }

    private fun clearAnswerFields() {
        answerField.clear()
        answerApplyButton.isDisable = false
        wrapUpText.isVisible = false
    }

    private fun endScreen() {
        wrapUpText.id = if (rightAnswers >= model.test.minimum) "right" else "wrong"
        wrapUpText.text = "Вы${if (rightAnswers < model.test.minimum) " не " else " "}прошли минимальный порог в ${model.test.minimum} баллов\nИтог: $rightAnswers/${model.test.questions.size}"
        LessonsDB.setResultToLesson(model.name, rightAnswers)
        goToMenuButton.isVisible = true
        goToNextLessonButton.isVisible = rightAnswers >= model.test.minimum && nextLesson != null
        textOfQuestion.isVisible = false
    }

    private fun bindNextQuestion() {
        val question = model.test.questions[questionNumber]
        clearAnswerFields()
        textOfQuestion.text = question.textOfQuestion
        textAnswerBlock.isVisible = !question.hasVariants
        chooseAnswerButtons.isVisible = question.hasVariants

        if (question.hasVariants) {
            observableList.setAll(question.variants.orEmpty().plus(question.rightAnswer).shuffled())
        }
        updateWebView()
        questionsCounter.text = "${questionNumber + 1}/${model.test.questions.size}"
        playTimer(TIME_FOR_QUESTION, true, {
            stopTimer(false)
        })

    }

    private fun cleanAll() {
        chooseAnswerButtons.isVisible = false
        textOfQuestion.text = "Тестирование сейчас начнется!"
        questionsCounter.text = "0/0"
        wrapUpText.isVisible = false
        textAnswerBlock.isVisible = false
        goToMenuButton.isVisible = false
        goToNextLessonButton.isVisible = false
    }

}