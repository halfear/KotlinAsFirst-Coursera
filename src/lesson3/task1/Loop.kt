@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.sqr
import java.lang.Math.*
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.math.PI

fun main(args: Array<String>) {
    //sin(30 * PI, 1e-5)
    println("${squareSequenceDigit(23)}")
    //squareSequenceDigit(23)
}

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var count = 0
    val nN = abs(n)
    var ost = nN
    do {
        ost /= 10
        count++
    } while (ost > 0)
    return count
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var nm2: Long = 1
    var nm1: Long = 1
    var nn: Long = 0
    return if (n in 1..2) 1 else {
        for (mem in 3..n) {
            nn = nm2 + nm1
            nm2 = nm1
            nm1 = nn
        }
        nn.toInt()
    }
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var k: Int
    val step: Int

    fun mink(min: Int, max: Int): Int {
        var knum: Int = min
        while (knum < max) knum += min
        return knum
    }

    if (m > n) {
        k = mink(n, m)
        step = n
    } else {
        k = mink(m, n)
        step = m
    }

    while (k < m * n) {
        if (k % m == 0 && k % n == 0) break
        k += step
    }
    return k
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var del = 2
    while (del < n) {
        if (n % del == 0) break
        del++
    }
    return del
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var del: Int = n - 1
    while (del > 1) {
        if (n % del == 0) break
        del--
    }
    return del
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    val exam: Int
    val comp: Int

    if (m < n) {
        exam = m
        comp = n
    } else {
        exam = n
        comp = m
    }

    for (del in 2..exam) {
        if (exam % del == 0 && comp % del == 0) return false
    }
    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    for (big in sqrt(m.toDouble()).toInt()..sqrt(n.toDouble()).toInt()) {
        if (big * big in m..n) return true
    }
    return false
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var xX: Int = x
    var countX = 0
    while (xX > 1) {
        if ((xX % 2) == 0) {
            xX /= 2
            countX++
        } else {
            xX = 3 * xX + 1
            countX++
        }
    }
    return countX
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */

fun sin(x: Double, eps: Double): Double {
    var minus = 1.0
    var curMem: Double
    var sumMem = 0.0
    var xX = x
    when {
        xX % PI == 0.0 -> {
            //println("$xX кратно PI, sin(x)=0.0")
            return 0.0
        }
        (xX % (PI / 2) == 0.0) && (xX % (3 * PI / 2) != 0.0) -> {
            //println("$xX кратно PI/2, sin(x)=1.0")
            return 1.0
        }
        xX % (3 * PI / 2) == 0.0 -> {
            //println("$xX кратно 3/2PI, sin(x)=-1.0")
            return -1.0
        }
        else -> {
            while (xX > 2 * PI) xX -= 2 * PI
            for (deG in 1..(1 / eps).toInt() step 2) {
                curMem = xX.pow(deG) / factorial(deG) * minus
                sumMem += curMem
                minus *= -1
                //println("Не на осях: $xX, $deG, $eps, $curMem, $sumMem, ${abs(curMem) < eps}")
                if (abs(curMem) < eps) break
            }
            return sumMem
        }
    }
}


/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double = TODO()

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int = TODO()

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean = TODO()

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean = TODO()

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var countOfDigits = 0 //кол-во знаков
    var count123 = 1 //счётчик 1..2..3..
    var numBod = 0 //квадрат
    val requiredDigit: Int
    while (countOfDigits < n) { //искомая цифра должна входить в текущий квадрат
        numBod = count123.toDouble().pow(2).toInt()
        countOfDigits += digitNumber(numBod)
        count123++
        //println("$count123, $numBod, $countOfDigits")
    }
    requiredDigit = countOfDigits - n //искомая цифра в найденном квадрате с конца
    //println("$requiredDigit, ${(10.0.pow(requiredDigit).toInt())}")
    return when (requiredDigit) {
        0 -> numBod % 10
        else -> {
            if ((numBod / (10.0.pow(requiredDigit).toInt())) >= 10) {
                (numBod / (10.0.pow(requiredDigit).toInt())) % 10
            } else {
                (numBod / (10.0.pow(requiredDigit).toInt()))
            }
        }

    }
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int = TODO()