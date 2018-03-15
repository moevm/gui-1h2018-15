package logic

import models.Question
import models.Test

object TestAndQuestionsDB {
    public fun getTest(): Test = Test(listOf(
            Question(textOfQuestion = "Заклинание боли в Гарри Поттере?", hasVariants = true, variants = listOf("Вингардиум левиОсса", "Вингардиум левиоссА", "ТолькоСиплюсПлюсус"), rightAnswer = "Круциатус"),
            Question(textOfQuestion = "Как звали дворецкого Тони Старка?", hasVariants = true, variants = listOf("Альтрон", "Иван", "Стивен Роджерс"), rightAnswer = "Джарвис"),
            Question(textOfQuestion = "Как расшифровывается аббревиатура \"ЛЭТИ\"?", hasVariants = true, variants = listOf("Липецкий Экономический Техникум Инвестиций", "Леонард Эйлер Творец Интегралов", "Лень Это Так Интересно"), rightAnswer = "Ленинградский Электротехнический Институт"),
            Question(textOfQuestion = "Сколько часов в неделе?", hasVariants = false, rightAnswer = "8760"),
            Question(textOfQuestion = "Год основания ЛЭТИ?", hasVariants = false, rightAnswer = "1886"),
            Question(textOfQuestion = "Закончите фразеологизм: \"Делу время, а...\"", hasVariants = true, variants = listOf("...сериал сам себя не посмотрит.", "лабы за тебя кто делать будет?", "времени и так не хватает. "), rightAnswer = "...потехе час."),
            Question(textOfQuestion = "Сколько ночей без сна составляет мировой рекорд?", hasVariants = false, rightAnswer = "11"),
            Question(textOfQuestion = "Что такое полиморфизм?", hasVariants = true, variants = listOf("Свойство языка программирования, позволяющее объединить и защитить данные и код в объектe и скрыть реализацию объекта от пользователя"), rightAnswer = "Возможность объектов с одинаковой спецификацией иметь различную реализацию "),
            Question(textOfQuestion = "Сколько морей омывают Россию?", hasVariants = false, rightAnswer = "12"),
            Question(textOfQuestion = "Как переводится deadline?", hasVariants = false, rightAnswer = "Крайний срок")
    ), 6)

}