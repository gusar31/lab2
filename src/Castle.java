import java.util.Scanner;
import java.util.ArrayList;

public class Castle {
    public static int myGold = 100;
    public static int oppGold = 100;
    public static ArrayList<Unit> allUnits;
    public Castle() {
        allUnits = new ArrayList<>();

        Unit villagerPitchfork = new Unit(1, "Крестьянин с вилами", "---E", 20, 20, 5, 1, 1, 10);
        allUnits.add(villagerPitchfork);

        Unit swordsman = new Unit(2, "Мечник", "-+--", 50, 50, 20, 1, 2, 30);
        allUnits.add(swordsman);

        Unit spearman = new Unit(3, "Копейщик", "->--", 40, 40, 15, 2, 2, 30);
        allUnits.add(spearman);

        Unit archer = new Unit(4, "Лучник", "(|->", 20, 20, 15, 3, 1, 20);
        allUnits.add(archer);

        Unit crossbowmen = new Unit(5, "Арбалетчик", "-&->", 30, 30, 20, 4, 1, 30);
        allUnits.add(crossbowmen);

        Unit horseman = new Unit(6, "Всадник", "(-)\"", 40, 40, 15, 1, 3, 30);
        allUnits.add(horseman);

        Unit cavalier = new Unit(7, "Кавалерист", "(=)\"", 50, 50, 20, 2, 4, 40);
        allUnits.add(cavalier);

        Unit gigachad = new Unit(8, "Гигачад", "(:0)", 500, 500, 500, 50, 50, 10);
        allUnits.add(gigachad);
        // инициализируйте доступные юниты здесь
    }

    public static void addGold(int team, int gold) {
        switch (team) {
            case 1 -> myGold += gold;
            case 2 -> oppGold += gold;
        }
    }

    public static void setGold(int team, int gold) {
        switch (team) {
            case 1 -> myGold = gold;
            case 2 -> oppGold = gold;
        }
    }

    public static int getGold(int team) {
        if (team == 1) {
                return myGold;
        } else {
                return oppGold;
        }
    }

    public void enterCastle() {
        while (true) {
            System.out.println(STR."Вы в замке, количество вашего золота: \{myGold}");
            System.out.println("Выберите действие:");
            Main.waiting(100);
            System.out.println("1 - Купить юнита");
            Main.waiting(100);
            System.out.println("0 - Выйти из замка");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            if (choice == 1) {
                buyUnit();
            } else if (choice == 0) {
                Main.waiting(100);
                System.out.println("Вы покидаете замок.");
                break;
            } else {
                System.out.println("Неверный выбор.");
                Main.waiting(100);
            }
        }
    }

    private void buyUnit() {

        while (true) {
            Scanner scanner = new Scanner(System.in);
//            System.out.println("Доступные юниты для покупки:");
//            Main.waiting(100);
//            for (int i = 0; i < allUnits.size(); i++) {
//                Main.waiting(100);
//                System.out.println((i + 1) + ". " + allUnits.get(i).getName()
//                        + " (Цена: " + allUnits.get(i).getCost() + " золотых)");
//            }
            System.out.print("Выберите класс юнита: 1 - пехота, 2 - стрелки, 3 - наездники (0 - отмена): ");
            int unitTypeChoice = scanner.nextInt();
            if (unitTypeChoice >= 0 && unitTypeChoice <= 3) {
                if (unitTypeChoice == 0) {
                    break;
                } else {
                    System.out.println("Доступные юниты для покупки:");
                    switch (unitTypeChoice) {
                        case 1 -> {
                            for (int i = 0; i <= 2; i++) {
                                System.out.println(STR."Код юнита: \{i + 1}. \{allUnits.get(i).
                                        getName()} (Цена: \{allUnits.get(i).getCost()} золотых)");
                            }
                        }
                        case 2 -> {
                            for (int i = 3; i <= 4; i++) {
                                    System.out.println(STR."Код юнита: \{i + 1}. \{allUnits.get(i).
                                            getName()} (Цена: \{allUnits.get(i).getCost()} золотых)");
                            }
                        }
                        case 3 -> {
                            for (int i = 5; i <= 6; i++) {
                                    System.out.println(STR."Код юнита: \{i + 1}. \{allUnits.get(i).
                                            getName()} (Цена: \{allUnits.get(i).getCost()} золотых)");
                            }
                        }
                    }
                }
            } else {
                System.out.println("Неверный выбор.");
                Main.waiting(100);
                break;
            }

            System.out.println("Введите код юнита (0 - отмена): ");
            int unitChoice = scanner.nextInt();
            if (unitChoice >= 0 && unitChoice <= allUnits.size()) {
                if (unitChoice == 0) {
                    break;
                }
                unitChoice -= 1;
                System.out.println(STR."Код юнита: \{unitChoice + 1}. \{allUnits.get(unitChoice).getName()}");
                System.out.println(STR."Здоровье: \{allUnits.get(unitChoice).getHealth()} HP.");
                if (allUnits.get(unitChoice).getMoveRange() != 1) {
                    System.out.println(STR."Дальность перемещения: \{allUnits.get(unitChoice).getMoveRange()} клетки.");
                } else {
                    System.out.println(STR."Дальность перемещения: \{allUnits.get(unitChoice).getMoveRange()} клетка.");
                }
                System.out.println(STR."Урон: \{allUnits.get(unitChoice).getDamage()} HP.");
                if (allUnits.get(unitChoice).getDamageRange() != 1) {
                    System.out.println(STR."Дальность нанесения урона: \{allUnits.get(unitChoice).getMoveRange()} клетки.");
                } else {
                    System.out.println(STR."Дальность нанесения урона: \{allUnits.get(unitChoice).getMoveRange()} клетка.");
                }
                System.out.println(STR."Цена: \{allUnits.get(unitChoice).getCost()} золотых.");
                Unit selectedUnit = allUnits.get(unitChoice);
                System.out.println("1. Купить юнита, 0. Отмена");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    if (selectedUnit.getCost() <= myGold) {
                        myGold -= selectedUnit.getCost();
                        // здесь нужно вашей логики для добавления выбранного юнита в игру
                        System.out.println(STR."Юнит \{allUnits.get(unitChoice).getName()} приобретен.");
                        MyUnit.spawnUnit(allUnits.get(unitChoice), 1);
                        Map.showing();
                        break;
                    } else {
                        System.out.println("Недостаточно золота для покупки этого юнита.");
                    }
                } else if (choice == 0) {
                    break;
                }
            } else {
                System.out.println("Неверный выбор.");
                Main.waiting(100);
            }
        }
    }
}