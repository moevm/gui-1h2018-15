package controllers

import javafx.fxml.FXML
import javafx.scene.control.Button

class AnswerCellController {

    @FXML
    private lateinit var answerCellButton: Button

    fun bind(answerText: String, onButtonClick: (String) -> (Unit)) {
        answerCellButton.text = answerText
        answerCellButton.setOnMouseClicked {
            onButtonClick(answerText)
        }
    }
}