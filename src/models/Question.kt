package models

data class Question(val textOfQuestion: String, val hasVariants: Boolean, val variants: List<String>? = null, val rightAnswer: String)