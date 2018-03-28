package controllers

import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.collections.FXCollections
import javafx.collections.ListChangeListener
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.web.WebView
import javafx.util.Duration
import logic.LessonsDB
import models.Lesson

class TestScreenController : BaseController<Lesson>() {

    companion object {
        private const val TIME_FOR_QUESTION = 10
        private const val TIME_FOR_RELAX = 3
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
    private lateinit var boxWithAnswer: HBox
    @FXML
    private lateinit var answersButtons: VBox
    @FXML
    private lateinit var answerField: TextField
    @FXML
    private lateinit var answerApplyButton: Button
    @FXML
    private lateinit var answerBlock: VBox
    @FXML
    private lateinit var yourAnswerLabel: Label
    @FXML
    private lateinit var rightAnswerLabel: Label

    private var questionNumber = 0
    private var rightAnswers = 0
    private var currentTimeline: Timeline? = null
    private var observableList = FXCollections.observableArrayList<String>()

    @FXML
    private fun initialize() {
        answerApplyButton.onAction = EventHandler {
            stopTimer(true, answerField.text)
        }

        observableList.addListener(ListChangeListener {
            answersButtons.children.clear()
            answersButtons.children.setAll(observableList.map { answer ->
                Button(answer).apply {
                    onMouseClicked = EventHandler {
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
        playTimer(1, true, {
            bindNextQuestion()
        })
    }

    override fun getTitle(): String = "Тестирование"

    private fun updateWebView() {
        webBrowser.engine.load("https://www.google.ru/")
    }

    private fun playTimer(secs: Int, changeLabel: Boolean, onFinishedAction: () -> (Unit) = {}) {
        var counter = secs
        currentTimeline = Timeline(KeyFrame(Duration.seconds(1.0), EventHandler {
            if (changeLabel) {
                timer.text = "${--counter} секунд"
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

        answerBlock.isVisible = true
        answersButtons.isVisible = false
        boxWithAnswer.isVisible = false

        rightAnswerLabel.text = "${if (answerIsRight) "П" else "Неп"}равильно"
        yourAnswerLabel.text = "Ваш ответ: ${if (byUser) answer else "-"}"

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
        answerBlock.isVisible = false
    }

    private fun endScreen() {
        rightAnswerLabel.text = "Вы${if (rightAnswers < model.test.minimum) " не " else ""}прошли минимальный порог в ${model.test.minimum} баллов"
        yourAnswerLabel.text = "Итог: $rightAnswers/${model.test.questions.size}"
        LessonsDB.setResultToLesson(model.name, rightAnswers)
        answerBlock.children.add(Button().apply {
            text = "Перейти в меню"
            setOnMouseClicked {
                closeWindowAndOpenNew<List<Lesson>, MainMenuController>(model = LessonsDB.getAllLessons(), childNode = answerBlock)
            }
        })
        textOfQuestion.isVisible = false
    }

    private fun bindNextQuestion() {
        val question = model.test.questions[questionNumber]
        clearAnswerFields()
        textOfQuestion.text = question.textOfQuestion
        boxWithAnswer.isVisible = !question.hasVariants
        answersButtons.isVisible = question.hasVariants

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
        answersButtons.isVisible = false
        textOfQuestion.text = ""
        questionsCounter.text = ""
        answerBlock.isVisible = false
        boxWithAnswer.isVisible = false
    }

}