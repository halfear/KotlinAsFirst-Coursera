@file:Suppress("UNUSED_PARAMETER")
package lesson1.task1

import kotlin.math.*

/**
 * Пример
 *
 * Вычисление квадрата целого числа
 */
fun sqr(x: Int) = x * x

/**
 * Пример
 *
 * Вычисление квадрата вещественного числа
 */
fun sqr(x: Double) = x * x

/**
 * Пример
 *
 * Вычисление дискриминанта квадратного уравнения
 */
fun discriminant(a: Double, b: Double, c: Double) = sqr(b) - 4 * a * c

/**
 * Пример
 *
 * Поиск одного из корней квадратного уравнения
 */
fun quadraticEquationRoot(a: Double, b: Double, c: Double) =
        (-b + sqrt(discriminant(a, b, c))) / (2 * a)

/**
 * Пример
 *
 * Поиск произведения корней квадратного уравнения
 */
fun quadraticRootProduct(a: Double, b: Double, c: Double): Double {
    val sd = sqrt(discriminant(a, b, c))
    val x1 = (-b + sd) / (2 * a)
    val x2 = (-b - sd) / (2 * a)
    return x1 * x2 // Результат
}

/**
 * Пример главной функции
 */
fun main(args: Array<String>) {
    val x1x2 = quadraticRootProduct(1.0, 13.0, 42.0)
    println("Root product: $x1x2")
    println("Sum in seconds: ${seconds(1,1,6)}")
    println("Length in metems: ${lengthInMeters(1,0,0)}")
    println("Angle in radians: ${angleInRadian(180,0,0)}")
    println("Length of track: ${trackLength(0.0,0.0,2.0,2.0)}")
    println("Third digit from the end: ${thirdDigit(45237)}")
    println("Minutes of travel: ${travelMinutes(12,0,12,30)}")
    println("Account in three years: ${accountInThreeYears(100,10)}")
    println("Number reverse: ${numberRevert(159)}")
}

/**
 * Тривиальная
 *
 * Пользователь задает время в часах, минутах и секундах, например, 8:20:35.
 * Рассчитать время в секундах, прошедшее с начала суток (30035 в данном случае).
 */
fun seconds(hours: Int, minutes: Int, seconds: Int): Int {
    val mits = 60 // секунд в минуте
    val hrs: Int = mits * 60 // минут в часе
    val secInMin: Int = minutes * mits // секунд в минутах
    val secInHrs: Int = hours * hrs // секунд в часах
    return seconds + secInMin + secInHrs // сумма секунд в часх, минутах и секундах
}

/**
 * Тривиальная
 *
 * Пользователь задает длину отрезка в саженях, аршинах и вершках (например, 8 саженей 2 аршина 11 вершков).
 * Определить длину того же отрезка в метрах (в данном случае 18.98).
 * 1 сажень = 3 аршина = 48 вершков, 1 вершок = 4.445 см.
 */
fun lengthInMeters(sagenes: Int, arshins: Int, vershoks: Int): Double {
    val ver = 4.445 // 1 вершок в см
    val ar: Double = ver * 48 / 3 // 1 аршин в вершках
    val saj: Double = ar * 3 // 1 сажень в аршинах
    val smInVersh: Double = ver * vershoks // сантиметров в вершках
    val smInArsh: Double = arshins * ar // сантиметров в аршине
    val smInSag: Double = sagenes * saj // сантиметров в саженце
    val lengthInSm: Double = smInVersh + smInArsh + smInSag //сантиметров всего
    return lengthInSm / 100 //длина в метрах
}

/**
 * Тривиальная
 *
 * Пользователь задает угол в градусах, минутах и секундах (например, 36 градусов 14 минут 35 секунд).
 * Вывести значение того же угла в радианах (например, 0.63256).
 */
fun angleInRadian(deg: Int, min: Int, sec: Int): Double {
    val gr = PI / 180 // градус в радианах
    val mt: Double = gr / 60 // минуты в градусах
    val se: Double = mt / 60 // секунды в минутах
    val radInGr: Double = gr * deg // радианов в градусах
    val radInMt: Double = mt * min // радианов в минутах
    val radInSe: Double = se * sec // радианов в секундах
    return radInGr + radInMt + radInSe // радианов всего
}

/**
 * Тривиальная
 *
 * Найти длину отрезка, соединяющего точки на плоскости с координатами (x1, y1) и (x2, y2).
 * Например, расстояние между (3, 0) и (0, 4) равно 5
 */
fun trackLength(x1: Double, y1: Double, x2: Double, y2: Double): Double {
    val xKat = x2 - x1 // изменение координаты по X
    val yKat = y2 - y1 // изменение координаты по Y
    return sqrt((xKat * xKat)+(yKat* yKat)) // гипотенуза
}

/**
 * Простая
 *
 * Пользователь задает целое число, большее 100 (например, 3801).
 * Определить третью цифру справа в этом числе (в данном случае 8).
 */
fun thirdDigit(number: Int): Int {
    val cuter: Int = number / 100 // обрезаем до нужной цифры с конца
    val grandCuter: Int = number / 1000 // обрезаем до старшего порядка
    return cuter - (grandCuter * 10) //разница со старшим порядком
}

/**
 * Простая
 *
 * Поезд вышел со станции отправления в h1 часов m1 минут (например в 9:25) и
 * прибыл на станцию назначения в h2 часов m2 минут того же дня (например в 13:01).
 * Определите время поезда в пути в минутах (в данном случае 216).
 */
fun travelMinutes(hoursDepart: Int, minutesDepart: Int, hoursArrive: Int, minutesArrive: Int): Int {
    val depHourInMin = hoursDepart * 60 // час отправления в минутах
    val arrHourInMin = hoursArrive * 60 // час прибытия в минутах
    val sumDepMin = depHourInMin + minutesDepart // кол-во минут до прибытия
    val sumArrMin = arrHourInMin + minutesArrive // кол-во минут до отбытия
    return sumArrMin - sumDepMin // разница минут
}

/**
 * Простая
 *
 * Человек положил в банк сумму в s рублей под p% годовых (проценты начисляются в конце года).
 * Сколько денег будет на счету через 3 года (с учётом сложных процентов)?
 * Например, 100 рублей под 10% годовых превратятся в 133.1 рубля
 */
fun accountInThreeYears(initial: Int, percent: Int): Double {
    var y = 1 // начальное значение счетчика годов
    var sumWithPer = initial + 0.0 // начальная сумма в Double
    while (y <= 3) { // цикл до 3-х
        sumWithPer = sumWithPer * percent / 100 + sumWithPer
        y ++
    }
    return sumWithPer //сумма после 3-х начислений процентов
}

/**
 * Простая
 *
 * Пользователь задает целое трехзначное число (например, 478).
 * Необходимо вывести число, полученное из заданного перестановкой цифр в обратном порядке (например, 874).
 */
fun numberRevert(number: Int): Int {
    val dig3 = number - ((number / 10) * 10) // третья цифра (единицы)
    val dig2 = (number / 10) - ((number / 100) * 10) // вторая цифра (десятки)
    val dig1 = number / 100 // первая цифра (сотни)
    return dig3 * 100 + dig2 * 10 + dig1 //наоборот
}