package models

class Exam(name: String, test: Test, testResult: Int = 0) : Lesson(name, test, testResult)