package models

class Lecture(name: String, testResult: Int = 0, test: Test, val wayToTheory: String): Lesson(name, test, testResult)