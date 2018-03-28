package controllers

import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Scene
import javafx.stage.Modality
import javafx.stage.Stage
import javafx.stage.StageStyle

abstract class BaseController<M : Any> {

    internal lateinit var model: M

    open fun bindModel(model: M) {
        this.model = model
    }

    abstract fun getTitle(): String

    inline fun <Model : Any, reified C : BaseController<Model>> closeWindowAndOpenNew(childNode: Node, model: Model) {
        (childNode.scene.window as Stage).close()

        val loader = FXMLLoader(javaClass.classLoader.getResource("layouts/${FxmlNamesOfController.getFxmlNameForController(C::class.java)}"))
        val stage = Stage()
        stage.initModality(Modality.APPLICATION_MODAL)
        stage.initStyle(StageStyle.DECORATED)
        stage.title = getTitle()
        stage.scene = Scene(loader.load())
        stage.show()
        loader.getController<C>().bindModel(model)
    }
}