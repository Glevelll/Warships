package Java;

class Field {
    final String[][] gameField = new String[11][11]; //создание поля

    public void initField() { //заполнение поля
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                gameField[0][0] = " ";
                gameField[0][j] = Integer.toString(j);
                gameField[i][0] = Character.toString((char) ('A' + i - 1));
                gameField[i][j] = "□";
            }
        }
    }

    public void printField() { //вывод поля
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                System.out.print(gameField[i][j] + " ");
            }
            System.out.println();
        }
        System.out.print("\n");
    }

    public void fillPlayerField(int[] coordinates) { //вывод корабля на поле
        int min, max;
        if (coordinates[0] == coordinates[2]) {
            min = Math.min(coordinates[1], coordinates[3]);
            max = Math.max(coordinates[1], coordinates[3]);
            for (int t = min; t <= max; t++) {
                gameField[coordinates[0]][t] = "■";
            }
        } else if (coordinates[1] == coordinates[3]) {
            min = Math.min(coordinates[0], coordinates[2]);
            max = Math.max(coordinates[0], coordinates[2]);
            for (int t = min; t <= max; t++) {
                gameField[t][coordinates[3]] = "■";
            }
        }
        printField();
    }

    public boolean isEmptyAround(int[] coordinates) { //проверка на окружение

        //“пробегает” по игровому полю вокруг будущего места расположения корабля с отступом на одну ячейку. Если
        // обнаруживает в этом диапазоне символ  «O», то метод возвращает значение false и сообщает об ошибке.
        int i_start = Math.min(coordinates[0], coordinates[2]) == 10 ? 9 : Math.min(coordinates[0], coordinates[2]) - 1;
        int j_start = Math.min(coordinates[1], coordinates[3]) - 1;
        int i_finish = Math.max(coordinates[0], coordinates[2]) == 10 ? 9 : Math.max(coordinates[0], coordinates[2]) + 1;
        int j_finish = Math.max(coordinates[1], coordinates[3]) == 10 ? 9 : Math.max(coordinates[1], coordinates[3]) + 1;
        for (int i = i_start; i <= i_finish; i++) {
            for (int j = j_start; j <= j_finish; j++) {
                if (gameField[i][j].equals("O")) {
                    System.out.println("Ошибка! Корабль рядом! Попробуй снова:");
                    return false;
                }
            }
        }
        return true;
    }

    void printShot(int[] newShot, String result) {
        if (result.equals("X")) {
            gameField[newShot[0]][newShot[1]] = "X";
            printField();
        }
        if (result.equals("◈")) {
            gameField[newShot[0]][newShot[1]] = "◈";
            printField();
        }
    }

    void takeShot(int[] newShot, String result) {
        if (result.equals("■")) {
            gameField[newShot[0]][newShot[1]] = "X";
            printField();
        }
        if (result.equals("◈")) {
            gameField[newShot[0]][newShot[1]] = "◈";
            printField();
        }
    }
    String shotResult(int[] newShot) {
        for (int i = newShot[0]; i < newShot[0] + 1; i++) {
            for (int j = newShot[1]; j < newShot[1] + 1; j++) {
                if (gameField[i][j].equals("■")) return "X";
                else if (gameField[i][j].equals("□")) return "◈";
            }
        }
        return null;
    }
}
