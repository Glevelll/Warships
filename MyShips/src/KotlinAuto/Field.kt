package KotlinAuto
import java.util.Arrays
import java.util.Scanner
import kotlin.random.Random

internal class Field {
    val gameField = Array(11) {
        arrayOfNulls<String>(
            11
        )
    } //создание поля
    val compField1 = Array(11) {
        arrayOfNulls<String>(
            11
        )
    } //создание поля
    val compField2 = Array(11) {
        arrayOfNulls<String>(
            11
        )
    }

    fun initField() { //заполнение поля
        for (i in gameField.indices) {
            for (j in gameField[i].indices) {
                gameField[0][0] = " "
                gameField[0][j] = Integer.toString(j)
                gameField[i][0] = Character.toString(('A'.code + i - 1).toChar())
                gameField[i][j] = "□"
            }
        }
    }


    fun printField() { //вывод поля
        for (i in gameField.indices) {
            for (j in gameField[i].indices) {
                print(gameField[i][j] + " ")
            }
            println()
        }
        print("\n")
    }

    fun fillPlayerField(coordinates: IntArray) { //вывод корабля на поле
        val min: Int
        val max: Int
        if (coordinates[0] == coordinates[2]) {
            min = Math.min(coordinates[1], coordinates[3])
            max = Math.max(coordinates[1], coordinates[3])
            for (t in min..max) {
                gameField[coordinates[0]][t] = "O"
            }
        } else if (coordinates[1] == coordinates[3]) {
            min = Math.min(coordinates[0], coordinates[2])
            max = Math.max(coordinates[0], coordinates[2])
            for (t in min..max) {
                gameField[t][coordinates[3]] = "O"
            }
        }
        //printField()
    }

    fun computerShot(){
        var i  = Random.nextInt(1,11)
        var j = Random.nextInt(1,11)
        if(gameField[i][j] == "O") {
            gameField[i][j] ="X"
            println("Компьютер попал!")
        }
        else if(gameField[i][j] == "□"){
            println("Компьютер не попал!")
            gameField[i][j] = "■"
        }
        else if(gameField[i][j] == "X" || gameField[i][j] == "■"){
            computerShot()
        }
    }
    fun personShot(){
        val coordinate = Coordinate()
        println("Введите координаты куда будете стрелять!")
        val newShip = coordinate.Coordinates(coordinate.readInput())
        var x = newShip[0]
        var y = newShip[1]
        if(gameField[x][y] == "O") {
            gameField[x][y] ="X"
            println("попал!")
        }
        else if(gameField[x][y] == "□"){
            println("не попал!")
            gameField[x][y] = "■"
        }
        else if(gameField[x][y] == "X" || gameField[x][y] == "■"){
            personShot()
        }
    }
    fun printCompField() { //вывод поля
        for (i in gameField.indices) {
            for (j in gameField[i].indices) {
                if(gameField[i][j] == "O"){
                    compField2[i][j] ="□"
                }else{
                    compField2[i][j] = gameField[i][j]
                }
                print(compField2[i][j] + " ")
            }
            println()
        }
        print("\n")
    }

    fun isEmptyAround(coordinates: IntArray): Boolean { //проверка на окружение

        //“пробегает” по игровому полю вокруг будущего места расположения корабля с отступом на одну ячейку. Если
        // обнаруживает в этом диапазоне символ  «O», то метод возвращает значение false и сообщает об ошибке.
        val i_start = if (Math.min(coordinates[0], coordinates[2]) == 10) 9 else Math.min(
            coordinates[0],
            coordinates[2]
        ) - 1
        val j_start = Math.min(coordinates[1], coordinates[3]) - 1
        val i_finish = if (Math.max(coordinates[0], coordinates[2]) == 10) 9 else Math.max(
            coordinates[0], coordinates[2]
        ) + 1
        val j_finish = if (Math.max(coordinates[1], coordinates[3]) == 10) 9 else Math.max(
            coordinates[1], coordinates[3]
        ) + 1
        for (i in i_start..i_finish) {
            for (j in j_start..j_finish) {
                if (gameField[i][j] == "O") {
                    //println("Ошибка! Корабль рядом! Попробуй снова:")
                    return false
                }
            }
        }
        return true
    }

    fun dotsCheck():Int{
        var count = 0
        for (i in gameField.indices) {
            for (j in gameField[i].indices) {
                if(gameField[i][j] == "X"){
                    count++
                }
            }
        }
        return count
    }
    fun oCheck():Int{
        var count = 0
        for (i in gameField.indices) {
            for (j in gameField[i].indices) {
                if(gameField[i][j] == "O"){
                    count++
                }
            }
        }
        return count
    }
}