package models

data class Test(val questions: List<Question>, val minimum: Int) {

    init {
        if (minimum > questions.size) {
            throw IllegalStateException("minimum couldn't be more than quanity of questions")
        }
    }
}