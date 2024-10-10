import java.util.Random;
import java.util.Scanner;


    public class Map {
        public static String[][] gameField = Map.making();

        public static Unit[][] gameFieldUnit;

        // Метод генерации местности игрового поля
        public static String[][] making() {
            String[][] gameField = new String[10][10]; // карта местности

            // Заполняем поле с учетом симметрии

            Random rand = new Random();
            for (int i = 0; i < 10; i++) {
                for (int j = i; j < 10; j++) {
                    int type = rand.nextInt(3);
                    switch (type) {
                        case 0:

                            gameField[i][j] = "Равнина";
                            gameField[j][i] = "Равнина";
                            break;
                        case 1:
                            gameField[i][j] = "Болото";
                            gameField[j][i] = "Болото";
                            break;
                        case 2:
                            gameField[i][j] = "Холм";
                            gameField[j][i] = "Холм";
                            break;
                    }
                }

            }

            return gameField;

        }

        // Метод возврата карты
        public static String[][] getMap() {
            return gameField;
        }
        public static void showing() {
            System.out.println("ИГРОВОЕ ПОЛЕ");
            System.out.println("\n___________________________________________________");
            for (int i = 0; i < 10; i++) {
                System.out.print("|");
                for (int j = 0; j < 10; j++) {
                    if (Unit.gameFieldUnit[i][j].getID() == 1) {
                        switch (gameField[i][j]) {
                            case "Равнина" -> System.out.print("____");
                            case "Болото" -> System.out.print("....");
                            case "Холм" -> System.out.print("_/\\_");
                        }
                    }
                    else {
                        System.out.print(Unit.gameFieldUnit[i][j].getSymbol());
                    }
                    System.out.print("|");
                }
                System.out.print("\n");
            }
        }
    }
