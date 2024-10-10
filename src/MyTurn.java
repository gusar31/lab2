import java.util.Scanner;

public class MyTurn {
    private Game game;
    private Map map;
    Scanner scanner = new Scanner(System.in);


    public void myTurn(){
        int myTurn = 1;
        while (myTurn == 1) {
            System.out.print("Выберите действие (1 - посмотреть информацию, 2 - войти в замок 3 - выбрать воина, 0 - завершить ход): ");
            int choice = scanner.nextInt();
            if (choice == 0) {
                myTurn = 0;
            } else if (choice == 1) {
                while (true) {
                    System.out.print("Введите номер ряда (1-10) или 0 для завершения: ");
                    int row = scanner.nextInt();
                    if (row == 0) {
                        break;
                    }

                    System.out.print("Введите номер столбца (1-10): ");
                    int col = scanner.nextInt();

                    // Проверка на валидность введенных данных
                    if (row < 1 || row > 10 || col < 1 || col > 10) {
                        System.out.println("Неверные координаты клетки!");
                        break;
                    }

                    String terrain = Map.gameField[row - 1][col - 1];
                    int action = 0;

                    System.out.println("Тип местности: " + terrain);
                    switch (terrain) {
                        case "Болото" -> System.out.println("Особенности: Перемещение -1");
                        case "Холм" -> System.out.println("Особенности: Дальность атаки +1");
                        case "Бонусный сундук" ->
                                System.out.println("Особенности: Даёт вам 50 монет или возможность тренировать секретного бойца");
                    }
                }
            } else if (choice == 2) {
                System.out.println("Вы в замке");
            } else if (choice == 3) {
                System.out.println("Особенности: Даёт вам 50 монет или возможность тренировать секретного бойца");
            }
        }
    }
}
