package logic

import models.Question
import models.Test

object TestAndQuestionsDB {
    fun getTest(): Test = Test(listOf(
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

    fun getTest1(): Test = Test(listOf(
            Question(textOfQuestion = "Начнем тест с чего-то простенького. Год основания ЛЭТИ?", hasVariants = false, rightAnswer = "1886"),
            Question(textOfQuestion = "Какого рода слово \"кофе\"?", hasVariants = true, variants = listOf("Среднего", "Мужского", "И среднего, и мужского, и женсокго. Вкусная кофя"), rightAnswer = "И среднего, и мужского"),
            Question(textOfQuestion = "Бери тогда взамен” - в чьем литературном произведении была такая фраза? Напишите фамилию", hasVariants = false, rightAnswer = "Шекспир"),
            Question(textOfQuestion = "Нужно срочно найти лабораторную по методам оптимизации, в котором есть \"комбинированный метод наискорейшего спуска и Ньютона\". Именно в таком порядке, это важно! Скиньте первую ссылку в гугле.", hasVariants = false, rightAnswer = "https://studfiles.net/preview/1170541/"),
            Question(textOfQuestion = "Найдите песню, где есть слова \"сохраненки может\"... Кто ее исполняет?", hasVariants = false, rightAnswer = "COLDCLOUD"),
            Question(textOfQuestion = "Закончите фразеологизм: \"Делу время, а...\"", hasVariants = true, variants = listOf("...сериал сам себя не посмотрит.", "лабы за тебя кто делать будет?", "времени и так не хватает. "), rightAnswer = "...потехе час."),
            Question(textOfQuestion = "Сколько ночей без сна составляет мировой рекорд?", hasVariants = false, rightAnswer = "11"),
            Question(textOfQuestion = "Что такое полиморфизм?", hasVariants = true, variants = listOf("Свойство языка программирования, позволяющее объединить и защитить данные и код в объектe и скрыть реализацию объекта от пользователя"), rightAnswer = "Возможность объектов с одинаковой спецификацией иметь различную реализацию "),
            Question(textOfQuestion = " В произведении какого автора были слова \"славы не берет\"?  Напишите фамилию.", hasVariants = false, rightAnswer = "Лермонтов"),
            Question(textOfQuestion = "Как переводится deadline?", hasVariants = false, rightAnswer = "Крайний срок")
    ), 6)

    fun getTest2(): Test = Test(listOf(
            Question(textOfQuestion = "Допустим, на завтра задали выучить стихотворение Пушкина или Лермонтова. Прогуглите так, чтобы показывались стихи обоих авторов и вставьте первую ссылку:", hasVariants = false, rightAnswer = "http://pushkin.299.ru/"),
            Question(textOfQuestion = "А вообще, всегда хотел выучить для себя какое-нибудь известное стихотворение про любовь. Важное, чтобы оно было известное, но не хочу Пушкина. Помогите мне, напишите верный запрос, и вставьте первую ссылку:", hasVariants = false, rightAnswer = "http://www.stihi-rus.ru/love/007.html"),
            Question(textOfQuestion = "А помогите найти статью, там то ли про самого милого робота, то ли как-то по другому, но суть именно в милоте. Первую ссылку, пожалуйста:", hasVariants = false, rightAnswer = "https://www.popmech.ru/technologies/312642-kuri-vozmozhno-samyy-milyy-domashniy-robot-v-mire/"),
            Question(textOfQuestion = "А можно теперь статью про самого милого робота, но в pdf?", hasVariants = false, rightAnswer = "https://static.my-shop.ru/product/pdf/310/3099530.pdf"),
            Question(textOfQuestion = "Не могу решить, какие наушники взять. Хочу JBL или beats. Помогите написать верный запрос, и вставьте первую ссылку:", hasVariants = false, rightAnswer = "https://www.eldorado.ru/cat/3183214/JBL/ "),
            Question(textOfQuestion = "Хотя знаете, битсы еще радиоактивными будут, лучше посмотреть любые наушники, кроме beats. Вторую ссылку, пожалуйста:", hasVariants = false, rightAnswer = "https://ru.jbl.com/headphones-wireless"),
            Question(textOfQuestion = "Вообще скоро лето, хочу привести себя в форму. Найдите мне все статьи про “простой способ похудеть”, ну или можно легкий, а может даже и простейший, и вставьте первую ссылку:", hasVariants = false, rightAnswer = "http://mygrace.ru/zdorovoe-pitanie/samyj-legkij-sposob-poxudet.html"),
            Question(textOfQuestion = "Хочу шаблон отчета к лабораторной работе в формате doc. Помогите мне и копируйте первую ссылку :(", hasVariants = false, rightAnswer = "eltech.chemdm.ru/lp_report.doc"),
            Question(textOfQuestion = "Думаю начать выращивать лилии. Или розы. Они все такие красивые. Найдите информацию по лилиям. Или розам. А лучше бы сразу по всему, и вставьте первую ссылку:", hasVariants = false, rightAnswer = "https://ru.wikipedia.org/wiki/Лилия"),
            Question(textOfQuestion = "Найдите мне что-нибудь про самые популярные игры, но чтобы там не было ни слова про доту. И можно первую ссылку, пожалуйста:", hasVariants = false, rightAnswer = "https://kanobu.ru/games/popular/")
    ), 6)

    fun getTest3(): Test = Test(listOf(
            Question(textOfQuestion = "На днях смотрел одну статью, там в заголовке были слова \"Алиса, Google Assistant, Siri, Alexa\", помогите найти ссылочку.", hasVariants = false, rightAnswer = "https://habrahabr.ru/company/touchinstinct/blog/352982/"),
            Question(textOfQuestion = "Хочу найти песню, где сначала было слово \"Ты\" , а в конце \"мыслей\", а между ними слов пять.. Хорошая песня. Кто поет?", hasVariants = false, rightAnswer = "Бузова"),
            Question(textOfQuestion = "Чувствуете мощь? Нет, не от Бузовой. От того, что вы песню нашли по двум словам. Мощно, согласитесь. А теперь помогите найти стихотворение, там на месте троеточия слово какое-то... \"Ты меня не ... не жалеешь\". Кто автор? Фамилию бы..", hasVariants = false, rightAnswer = "Есенин"),
            Question(textOfQuestion = "Найдите первую ссылку, в заголовке которой будут слова \"10 фактов\"", hasVariants = false, rightAnswer = "https://www.youtube.com/watch?v=sX1r97k2vsE"),
            Question(textOfQuestion = "А теперь первую ссылку, где будет в заголовке \"100 фактов\" чего либо?", hasVariants = false, rightAnswer = "http://100-faktov.ru/"),
            Question(textOfQuestion = "А теперь хочу, чтобы в заголовке было \"2к18\". Модно, стильно, молодежно. Первую ссылочку бы", hasVariants = false, rightAnswer = "https://модные-слова.рф/5-chto-oznachaet-2k16.html"),
            Question(textOfQuestion = "Найдите исполнителя строк, которые я не особо помню (вот так неожиданность), вместо \"…\" там слова 3, примерно: \"Если … просто так\".", hasVariants = false, rightAnswer = "Дискотека Авария"),
            Question(textOfQuestion = "А теперь можно найти что-то, где будет 10 чего-то. Ну вот прям чего-угодно. 10 фактов, 10 слов, 10 лекций… 10 и слово. Первую ссылочку хочется.", hasVariants = false, rightAnswer = "http://www.interfax.ru/world/607706"),
            Question(textOfQuestion = "...а давайте найдем что-нибудь, где в заголовке будет \"10000 фактов\"?", hasVariants = false, rightAnswer = "http://neznal.ru/10000-2f"),
            Question(textOfQuestion = "На последок что-нибудь классное. Вместо \"...\" где-то пять слов. \"Здравствуй  … сапогах\". Первую ссылку.", hasVariants = false, rightAnswer = "http://fanfics.me/ftf902")
    ), 6)

}