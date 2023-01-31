package Java;

public class Main {
    public static void main(String[] args) {
        Coordinate coordinate = new Coordinate();
        Field gameField = new Field();
        Field secondGameField = new Field();

        int[][] firstPlayerCoordinates = new int[3][17];
        int[] shipStatusOfFirstPlayer = {0, 0, 0, 0, 0};
        int liveShips = 5;

        gameField.initField(); //Создание
        gameField.printField(); //Вывод
        boolean[] isShipReady = {true, true, true, true, true, true, true, true, true, true}; //состояние каждого корабля для проверок
        int[] shipLength = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1}; //палубы кораблей
        for (int i = 0; i < isShipReady.length; ) {
            while (isShipReady[i]) { //пока true программа запрашивает координаты
                System.out.printf("Введи координаты (%s палуб): \n", shipLength[i]);
                int[] newShip = coordinate.Coordinates(coordinate.readInput()); //новый корабль
                if (!coordinate.isComparisonCoordinate(newShip)) { //проверка на корректность координат
                    System.out.println("Ошибка в координатах! Попробуй снова:");
                    break;
                }
                if (!coordinate.isCorrectLength(newShip, shipLength[i])) { //проверка на длину
                    System.out.printf("Ошибка! Неверная длина корабля! Попробуй снова:");
                    break;
                }
                if (!gameField.isEmptyAround(newShip)) { //проверка на окружение
                    break;
                }
                gameField.fillPlayerField(newShip);
                isShipReady[i] = false;
                i++;
                if (i == 10) break;
            }
        }
        System.out.println("\nНачало игры!");

        secondGameField.initField();
        secondGameField.printField();

        System.out.println("Нанесите удар!\n");

        boolean isGameContinue = true;

        while (isGameContinue) {
            int[] newShot = coordinate.newShot(coordinate.readInput());

            if (!coordinate.isCoordinateCorrect(newShot)) {
                System.out.println("Ошибка! Неверные координаты! Попробуйте снова:");
            } else {
                String result = gameField.shotResult(newShot);
                int index = coordinate.index(firstPlayerCoordinates, newShot);
                gameField.printShot(newShot, result);

                switch (result) {
                    case "X":
                        shipStatusOfFirstPlayer[index]++;

                        if (shipStatusOfFirstPlayer[index] == shipLength[index]) {
                            System.out.println("Вы потопили корабль! Выберите новые координаты:");
                            liveShips--;
                        } else {
                            System.out.println("Вы попали по кораблю! Попробуйте снова:");
                        }
                        break;
                    case "◈":
                        System.out.println("Вы промахнулись! Введите новые координаты:");
                        break;
                }
            }
            if (liveShips == 0) {
                System.out.println("Вы выиграли!");
                isGameContinue = false;
                break;
            }
        }
    }
}

