package logic

import models.Exam
import models.Lecture
import models.Lesson
import java.io.File

object LessonsDB {

    private val fileToResults = javaClass.classLoader.getResource("results.txt").file

    private val lessons = listOf(
            Lecture(name = "Первый урок", test = TestAndQuestionsDB.getTest1(), wayToTheory = "https://medium.com/@milkis97/%D1%83%D1%80%D0%BE%D0%BA-%D0%BF%D0%B5%D1%80%D0%B2%D1%8B%D0%B9-441fa45d44de"),
            Lecture(name = "Второй урок", test = TestAndQuestionsDB.getTest2(), wayToTheory = "https://medium.com/@milkis97/%D1%83%D1%80%D0%BE%D0%BA-%D0%B2%D1%82%D0%BE%D1%80%D0%BE%D0%B9-2f281eabdaa4"),
            Lecture(name = "Третий урок", test = TestAndQuestionsDB.getTest3(), wayToTheory = "https://medium.com/@milkis97/%D1%83%D1%80%D0%BE%D0%BA-%D1%82%D1%80%D0%B5%D1%82%D0%B8%D0%B9-8bb3e1d0c7e3"),
            Exam("Первый экзамен", test = TestAndQuestionsDB.getTest()),
            Lecture(name = "Четвертый урок", test = TestAndQuestionsDB.getTest1(), wayToTheory = "https://medium.com/@milkis97/%D1%83%D1%80%D0%BE%D0%BA-%D1%87%D0%B5%D1%82%D0%B2%D0%B5%D1%80%D1%82%D1%8B%D0%B9-61dcfd02477c"),
            Lecture(name = "Пятый урок", test = TestAndQuestionsDB.getTest2(), wayToTheory = "https://medium.com/@milkis97/%D1%83%D1%80%D0%BE%D0%BA-%D0%BF%D1%8F%D1%82%D1%8B%D0%B9-c24ea6549c"),
            Lecture(name = "Шестой урок", test = TestAndQuestionsDB.getTest3(), wayToTheory = "https://medium.com/@milkis97/%D1%83%D1%80%D0%BE%D0%BA-%D1%88%D0%B5%D1%81%D1%82%D0%BE%D0%B9-58e29abf54a2"),
            Exam("Второй экзамен", test = TestAndQuestionsDB.getTest())
    )

    fun getAllLessons(): List<Lesson> = lessons

    fun setResultToLesson(lessonName: String, result: Int) {
        lessons.find { it.name == lessonName }?.testResult = result
        rewriteResultsToFile()
    }

    fun readFromFile() {
        val lines = File(fileToResults).readLines()
        lines.forEach {
            with (it.split(" - ")) {
                lessons.firstOrNull { it.name == get(0) }?.apply {
                    testResult = get(1).toIntOrNull() ?: throw Throwable("Wrong file format")
                } ?: throw Throwable("Wrong file format")
            }
        }
    }

    fun rewriteResultsToFile() {
        File(fileToResults).writeText(lessons.map { "${it.name} - ${it.testResult}" }.reduce { acc, s -> "$acc\n$s" })
    }
}