package logic

import models.Exam
import models.Lecture
import models.Lesson

object LessonsDB {

    private val lessons = listOf(
            Lecture(name = "Основы гугления", test = TestAndQuestionsDB.getTest(), wayToTheory = "http://telegra.ph/Guglik-03-28"),
            Exam("Первый экзамен", test = TestAndQuestionsDB.getTest()))

    fun getAllLessons(): List<Lesson> = lessons

    fun setResultToLesson(lessonName: String, result: Int) {
        lessons.find { it.name == lessonName }?.testResult = result
    }
}