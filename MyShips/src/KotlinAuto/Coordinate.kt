package KotlinAuto
import java.util.*
import java.util.regex.Pattern

internal class Coordinate {
    fun readInput(): String { //считывание координат
        val scanner = Scanner(System.`in`)
        return scanner.nextLine().uppercase().replace(" ", "")
    }

    fun Coordinates(line: String?): IntArray { //должны совпадать либо буквы, либо цифры, чтобы корабль был либо вертикально, либо горизонтально
        var firstLetter = 0
        var secondLetter = 0 //начало и конец корабля
        var firstNumber = 0
        var secondNumber = 0
        val regex = "([A-J])(\\d+)([A-J])(\\d+)" //чтобы было буква-число, буква-число
        //из символов в числа
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(line)
        if (matcher.matches()) { //если совпадает
            firstLetter = matcher.group(1)[0].code - 64
            firstNumber = matcher.group(2).toInt()
            //если не совпадает то берем случайное число, здесь 80, которое за пределами поля, чтобы показать
            //что ситуация нам не подходит
            if (firstNumber == 0 || firstNumber > 10) firstLetter = 80
            secondLetter = matcher.group(3)[0].code - 64
            secondNumber = matcher.group(4).toInt()
            if (secondNumber == 0 || secondNumber > 10) firstLetter = 80
        } else {
            firstLetter = 80
        }
        return intArrayOf(firstLetter, firstNumber, secondLetter, secondNumber)
    }

    fun isComparisonCoordinate(coordinates: IntArray): Boolean { //просто проверка корректности координат
        return coordinates[0] == coordinates[2] || coordinates[1] == coordinates[3]
    }

    fun isCorrectLength(coordinates: IntArray, length: Int): Boolean { //проверка на длину
        val b = false
        if (coordinates[0] == coordinates[2]) {
            return length == Math.abs(coordinates[3] - coordinates[1]) + 1
        } else if (coordinates[1] == coordinates[3]) {
            return length == Math.abs(coordinates[2] - coordinates[0]) + 1
        }
        return b
    }
}