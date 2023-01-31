package Java;

import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Coordinate {
    public String readInput() { //считывание координат
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toUpperCase(Locale.ROOT).replace(" ", "");
    }

    int[] Coordinates(String line) { //должны совпадать либо буквы, либо цифры, чтобы корабль был либо вертикально, либо горизонтально
        int firstLetter = 0, secondLetter = 0; //начало и конец корабля
        int firstNumber = 0, secondNumber = 0;
        String regex = "([A-J])(\\d+)([A-J])(\\d+)"; //чтобы было буква-число, буква-число
        //из символов в числа
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) { //если совпадает
            firstLetter = matcher.group(1).charAt(0) - 64;
            firstNumber = Integer.parseInt(matcher.group(2));
            //если не совпадает то берем случайное число, здесь 80, которое за пределами поля, чтобы показать
            //что ситуация нам не подходит
            if (firstNumber == 0 || firstNumber > 10) firstLetter = 80;
            secondLetter = matcher.group(3).charAt(0) - 64;
            secondNumber = Integer.parseInt(matcher.group(4));
            if (secondNumber == 0 || secondNumber > 10) firstLetter = 80;
        } else {
            firstLetter = 80;
        }
        return new int[]{firstLetter, firstNumber, secondLetter, secondNumber};
    }

    int[] newShot(String newShot) {
        int firstLetter = 0, secondLetter = 0; //координаты удара
        String regex = "([A-J])(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(newShot);
        if (matcher.matches()) {
            firstLetter = matcher.group(1).charAt(0) - 64;
            secondLetter = Integer.parseInt(matcher.group(2));
            if (secondLetter == 0 || secondLetter > 10) firstLetter = 80;
        } else {
            firstLetter = 80;
        }
        return new int[]{firstLetter, secondLetter};
    }

    public boolean isComparisonCoordinate(int[] coordinates) { //просто проверка корректности координат
        return coordinates[0] == coordinates[2] || coordinates[1] == coordinates[3];
    }

    boolean isCoordinateCorrect (int[] coordinates) { //проверка координат для удара
        System.out.println("Ошибка! Неверные координаты! Попробуй снова:");
        return coordinates[0] != 80;
    }

    public boolean isCorrectLength(int[] coordinates, int length) { //проверка на длину
        boolean b = false;
        if (coordinates[0] == coordinates[2]) {
            return length == Math.abs(coordinates[3] - coordinates[1]) + 1;
        } else if (coordinates[1] == coordinates[3]) {
            return length == Math.abs(coordinates[2] - coordinates[0]) + 1;
        }
        return b;
    }

    int[] shipLengthForSavingCoordinates = {5, 4, 3, 3, 2};
    int iterator = 0;

    int[][] coordinatesStore(int[][] coordinatesStore, int[] coordinates, int counter, int shipLength) {
        int[][] coordinatesForSaving = new int[3][shipLength];
        if (coordinates[0] == coordinates[2]) {

            int startSymbol = coordinates[0];
            int startSymbol2 = Math.min(coordinates[1], coordinates[3]);
            for (int i = 0; i < coordinatesForSaving.length; i++) {
                for (int j = 0; j < coordinatesForSaving[i].length; j++) {
                    coordinatesForSaving[0][j] = counter;
                    coordinatesForSaving[1][j] = startSymbol;
                    coordinatesForSaving[2][j] = startSymbol2 != 10 ? startSymbol2 + j : 10;
                }
            }
        } else if (coordinates[1] == coordinates[3]) {
            int length = Math.abs(coordinates[0] - coordinates[2]) + 1;
            coordinatesForSaving = new int[3][length];
            int startSymbol = Math.min(coordinates[0], coordinates[2]);
            int startSymbol2 = coordinates[1];
            for (int i = 0; i < coordinatesForSaving.length; i++) {
                for (int j = 0; j < coordinatesForSaving[i].length; j++) {
                    coordinatesForSaving[0][j] = counter;
                    coordinatesForSaving[1][j] = startSymbol != 10 ? startSymbol + j : 10;
                    coordinatesForSaving[2][j] = startSymbol2;
                }
            }
        }
        for (int i = 0; i < coordinatesStore.length; i++) {
            for (int j = 0; j < coordinatesStore[i].length; j++)
                System.arraycopy(coordinatesForSaving[i], 0, coordinatesStore[i], iterator, coordinatesForSaving[i].length);
        }
        iterator += shipLengthForSavingCoordinates[counter];
        return coordinatesStore;
    }

    public int index(int[][] playerCoordinates, int[] shot) {
        int index = 10;
        for (int i = 0; i < playerCoordinates.length; i++) {
            for (int j = 0; j < playerCoordinates[i].length; j++) {
                if (playerCoordinates[1][j] == shot[0] && playerCoordinates[2][j] == shot[1]) {
                    index = playerCoordinates[0][j];
                    break;
                }
            }
        }
        return index;
    }
}
