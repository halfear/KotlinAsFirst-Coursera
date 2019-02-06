@file:Suppress("UNUSED_PARAMETER")
package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.*

fun main(args: Array<String>) {
    val aN = 124
    println("Окончание ${ageDescription(aN)}")
    println("Время на половину пути: ${timeForHalfWay(3.0,0.0,1.0,6.0,2.0,5.0)}")
    println("Длина пересечения: ${segmentLength(3,4,4,5)}")
}

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun endName(x: Int): String = when (x) {
    !in 1..199 -> "Вне допустимого интервала"
    1 -> "год"
    in 2..4 -> "года"
    in 5..19 -> "лет"
    0 -> "лет"
    else -> "Вне допустимого интервала"
}

fun ageDescription(age: Int): String {
    val last2Dig: Int = (age + 100) - (age + 100) / 100 * 100
    val last1Dig: Int = (age + 10) - (age + 10) / 10 * 10
    return when (last2Dig) {
        in 1..19 -> "$age ${endName(last2Dig)}"
        in 20..99 -> "$age ${endName(last1Dig)}"
        else -> "Что-то пошло не так"
    }
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double {
    val halfLenght: Double = ((t1 * v1) + (t2 * v2) + (t3 * v3)) / 2
    return if (halfLenght > (t1 * v1) && halfLenght <= ((t1 * v1) + (t2 * v2))) {
        t1 + ((halfLenght - (t1 * v1)) / v2)
    } else if (halfLenght > ((t1 * v1) + (t2 * v2))) {
        t1 + t2 + ((halfLenght - ((t1 * v1) + (t2 * v2))) / v3)
    } else halfLenght / v1
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int {
    val hit1: Boolean = kingX == rookX1 || kingY == rookY1
    val hit2: Boolean = kingX == rookX2 || kingY == rookY2
    return if (hit1 == true && hit2 == true) 3 else {
        if (hit1 == false && hit2 == true) 2 else {
            if (hit1 == true && hit2 == false) 1 else 0
        }
    }
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int {
    val hitR: Boolean = kingX == rookX || kingY == rookY
    val hitB: Boolean = abs(kingX - bishopX) == abs(kingY - bishopY)
    return if (hitR == true && hitB == true) 3 else {
        if (hitR == false && hitB == true) 2 else {
            if (hitR == true && hitB == false) 1 else 0
        }
    }
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {

    return if (a > b && a > c) {
        if (a >= b + c) -1 else {
            val matGip: Double = sqrt(sqr(b) + sqr(c))
            when {
                a > matGip -> 2
                a < matGip -> 0
                else -> 1
            }
        }
    } else if (b > a && b > c) {
        if (b >= a + c) -1 else {
            val matGip: Double = sqrt(sqr(a) + sqr(c))
            when {
                b > matGip -> 2
                b < matGip -> 0
                else -> 1
            }
        }
    } else if (c > a && c >b) {
        if (c >= a + b) -1 else {
            val matGip: Double = sqrt(sqr(a) + sqr(b))
            when {
                c > matGip -> 2
                c < matGip -> 0
                else -> 1
            }
        }
    } else 0
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    return if (a > b || c > d) -1 else {
        if (c > b || d < a) -1 else {
            when {
                b >= c && b <= d && a <= c -> b - c
                d >= a && d <= b && c <= a -> d - a
                a >= c && a <= d && b >= c && b <= d -> b - a
                else -> d - c
            }
        }
    }
}
