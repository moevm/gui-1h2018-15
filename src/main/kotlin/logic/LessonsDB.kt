package logic

import models.Exam
import models.Lecture
import models.Lesson

object LessonsDB {

    private val lessons = listOf(
            Lecture(name = "Первый урок", test = TestAndQuestionsDB.getTest1(), wayToTheory = "http://telegra.ph/Pervyj-urok-04-12"),
            Lecture(name = "Второй урок", test = TestAndQuestionsDB.getTest2(), wayToTheory = "http://telegra.ph/Vtoroj-urok-04-12"),
            Lecture(name = "Третий урок", test = TestAndQuestionsDB.getTest3(), wayToTheory = "http://telegra.ph/Tretij-urok-04-12"),
            Exam("Первый экзамен", test = TestAndQuestionsDB.getTest())
            )

    fun getAllLessons(): List<Lesson> = lessons

    fun setResultToLesson(lessonName: String, result: Int) {
        lessons.find { it.name == lessonName }?.testResult = result
    }
}