import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Game {
    public static Unit[][] gameFieldUnit;
    private static Map map;
    private static Castle playerCastle;
    private static Castle opponentCastle;
    public Random random;
    public static int difficult;
    public static boolean myTurn;
    public static int bonus = 0;
    public static int bonusNum = 0;

    public Game() {
        map = new Map();
        playerCastle = new Castle();
        opponentCastle = new Castle();
        random = new Random();
    }

    // Метод установки сложности
    public static void setDifficult() {
        System.out.print("Выберите сложность:\n1. Лёгкая;\n2. Средняя;\n3. Сложная;\n4. Кошмарная!\nВведите число:");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            difficult = scanner.nextInt();
            if (1 <= difficult  & difficult <= 4) {
                break;
            } else { System.out.print("Указано неверное значение сложности, попробуйте ещё раз!\nВаш выбор:"); }
        }

    }
    public static void myTurn(String[][] gameField, Unit[][] gameFieldUnit) {

        Main.skip();
        System.out.print("Ваш ход!\n");
        Main.waiting(100);
        myTurn = true;
        while (myTurn) {
            Map.showing();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Выберите действие (1 - посмотреть информацию, 2 - войти в замок, 3 - выбрать героя, 0 - завершить ход): ");
            int choice = scanner.nextInt();
            if (choice == 0) {
                myTurn = false;
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

                    String terrain = gameField[row - 1][col - 1];

                    System.out.println("Тип местности: " + terrain);
                    switch (terrain) {
                        case "Равнина" -> System.out.println("Особенности: Нет");
                        case "Болото" -> System.out.println("Особенности: Перемещение -1");
                        case "Холм" -> System.out.println("Особенности: Дальность атаки +1");
                    }

                    switch (Unit.gameFieldUnit[row - 1][col - 1].getTeam()) {
                        case 1 -> System.out.println(STR."Объект: Ваш \{Unit.gameFieldUnit[row - 1][col - 1].
                                getName()}.\nЗдоровье: \{Unit.gameFieldUnit[row - 1][col - 1].
                                getHealth()} (\{Unit.gameFieldUnit[row - 1][col - 1].
                                getRemainingHealth()}). \nПеремещение: \{Unit.gameFieldUnit[row - 1][col - 1].
                                getMoveRange()} (\{Unit.gameFieldUnit[row - 1][col - 1].
                                getRemainingMoves()}). \nУрон: \{Unit.gameFieldUnit[row - 1][col - 1].getDamage()}");
                        case 2 -> System.out.println(STR."Объект: \{Unit.gameFieldUnit[row - 1][col - 1].
                                getName()} противника.\nЗдоровье: \{Unit.gameFieldUnit[row - 1][col - 1].
                                getHealth()} (\{Unit.gameFieldUnit[row - 1][col - 1].
                                getRemainingHealth()}). \nПеремещение: \{Unit.gameFieldUnit[row - 1][col - 1].
                                getMoveRange()} (\{Unit.gameFieldUnit[row - 1][col - 1].
                                getRemainingMoves()}). \nУрон: \{Unit.gameFieldUnit[row - 1][col - 1].getDamage()}");
                        case 3 -> {
                            switch (Unit.gameFieldUnit[row - 1][col - 1].getID()) {
                                case 2 -> System.out.println(STR."Объект: Ваш замок. Ваша задача не допустить его разрушение.\nЗдоровье: 500(\{Unit.gameFieldUnit[row - 1][col - 1].
                                        getRemainingHealth()})");
                                case 3 -> System.out.println(STR."Объект: Замок противника. Ваша задача разрушить его.\nЗдоровье: 500(\{Unit.gameFieldUnit[row - 1][col - 1].
                                        getRemainingHealth()})");
                                case 4 -> System.out.println(STR."Объект: Бонусный сундук. Бойцы игрока, который разрушит сундук, получат случайный бонус\nЗдоровье: 200(\{Unit.gameFieldUnit[row - 1][col - 1].
                                        getRemainingHealth()})");
                            }
                        }
                    }
                }
            } else if (choice == 2) {
                    playerCastle.enterCastle();
            } else if (choice == 3) {
                Map.showing();
                System.out.println("Введите ID юнита. Доступные юниты:");
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (Unit.gameFieldUnit[i][j].getTeam() == 1) {
                            System.out.println(STR."\{Unit.gameFieldUnit[i][j].getID()}. \{Unit.
                                    gameFieldUnit[i][j].getName()} [ \{i + 1} ][ \{j + 1} ];");
                        }
                    }
                }
                System.out.println("ID юнита:");
                int unitChoice = scanner.nextInt();
                if (Unit.unitOnDesk(unitChoice) && Unit.getUnitByID(unitChoice).getTeam() != 2) {
                    System.out.println("Доступные действия: \n1. Атака \n2. Перемещение \n3. Укрепление позиций\n0. Отмена\nВведите номер действия:");
                    int unitChoice1 = scanner.nextInt();
                    if (unitChoice1 == 1) {
                        Unit.showTargets(Unit.getUnitByID(unitChoice));
                        System.out.println("Введите ID юнита(0 - отмена):");
                        int targetID = scanner.nextInt();
                        if (targetID == 0) {
                            break;
                        } else if (Unit.unitOnDesk(targetID)) {
                            Unit.attack(Unit.getUnitByID(unitChoice), Unit.getUnitByID(targetID));
                            if (!Unit.isGameNotOver()) {
                                Main.endingPage(1);
                            } else if (isChestBroken() && bonusNum == 0) {
                                getBonus(1);
                            }
                        } else { System.out.println("Введён неверный ID!"); }
                    } else if (unitChoice1 == 2) {
                        Unit.moving(Unit.getUnitByID(unitChoice));
                    } else if (unitChoice1 == 3) {
                        Unit.healing(Unit.getUnitByID(unitChoice));
                    }
                } else {
                    System.out.println("Введён неверный ID!");
                }
            }
        }
        // Логика для хода игрока
    }

    public static void oppTurn(String[][] gameField, Unit[][] gameFieldUnit) {
        int counter1;
        myTurn = false;
        while (!myTurn) {
            counter1 = 0;
            System.out.println("Ход противника! " + Castle.getGold(2));
            if (Unit.unitsCounter(2) >= 2) {
                if (Unit.unitsCounter(1) > 3 && Castle.getGold(2) >= 40) {
                    MyUnit.spawnUnit(Castle.allUnits.get(4), 2);
                    Castle.addGold(2, -20);
                    Main.skip();
                    Map.showing();
                    Main.waiting(200);
                    counter1 += 1;
                }
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (Unit.gameFieldUnit[i][j].getTeam() == 2 && Unit.gameFieldUnit[i][j].getHit()) {
                            Unit activeUnit = Unit.gameFieldUnit[i][j];
                            int targX = 0;
                            int targY = 0;
                            for (int row = 0; row < 10; row++) {
                                for (int col = 0; col < 10; col++) {
                                    if (Unit.gameFieldUnit[row][col].getID() != 1 && Unit.gameFieldUnit[row][col].getTeam() != 2 && Unit.gameFieldUnit[row][col].getID() != 3) {
                                        if (Unit.canAttack(activeUnit, Unit.gameFieldUnit[row][col])) {
                                            counter1 += 1;
                                            if (Unit.gameFieldUnit[row][col].getHealth() <= activeUnit.getDamage()) {
                                                Unit.attack(activeUnit, Unit.gameFieldUnit[row][col]);
                                            } else if (Unit.gameFieldUnit[row][col].getRevenge() == 0) {
                                                Unit.attack(activeUnit, Unit.gameFieldUnit[row][col]);
                                            } else if (Unit.gameFieldUnit[row][col].getDamageRange() > 1) {
                                                Unit.attack(activeUnit, Unit.gameFieldUnit[row][col]);
                                            } else {
                                                targY = row;
                                                targX = col;
                                            }
                                        }
                                        if (!Unit.isGameNotOver()) {
                                            Main.endingPage(2);
                                        } else if (isChestBroken() && bonusNum == 0) {
                                            getBonus(2);
                                        }
                                    }
                                }
                            }
                            if (targX != 0 && targY != 0) {
                                Unit.attack(activeUnit, Unit.gameFieldUnit[targY][targX]);
                                counter1 += 1;
                                if (!Unit.isGameNotOver()) {
                                    Main.endingPage(2);
                                } else if (isChestBroken() && bonusNum == 0) {
                                    getBonus(2);
                                    MyUnit.deleteUnit(Unit.getUnitByID(4));
                                }
                            }
                            if (activeUnit.getRemainingMoves() > 0) {
                                counter1 += 1;
                                if (!isChestBroken()) {
                                    int posX = Unit.unitPosX(activeUnit);
                                    int posY = Unit.unitPosY(activeUnit);
                                    if (Math.abs(Unit.unitPosX(Unit.getUnitByID(4)) - posX) >
                                            Math.abs(Unit.unitPosY(Unit.getUnitByID(4)) - posY)) {
                                        if (Unit.isCellFreeForMovement(posY, posX - 1, Unit.gameFieldUnit)) {
                                            Unit.moving(activeUnit, posY, posX - 1);
                                        } else if (Unit.isCellFreeForMovement(posY + 1, posX, Unit.gameFieldUnit)) {
                                            Unit.moving(activeUnit, posY + 1, posX);
                                        } else {
                                            Unit.healing(activeUnit);
                                        }
                                    } else {
                                        if (Unit.isCellFreeForMovement(posY + 1, posX, Unit.gameFieldUnit)) {
                                            Unit.moving(activeUnit, posY + 1, posX);
                                        } else if (Unit.isCellFreeForMovement(posY, posX - 1, Unit.gameFieldUnit)) {
                                            Unit.moving(activeUnit, posY, posX - 1);
                                        } else {
                                            Unit.healing(activeUnit);
                                        }
                                    }
                                } else {
                                    int posX = Unit.unitPosX(activeUnit);
                                    int posY = Unit.unitPosY(activeUnit);
                                    if (Math.abs(Unit.unitPosX(Unit.getUnitByID(2)) - posX) >
                                            Math.abs(Unit.unitPosY(Unit.getUnitByID(2)) - posY)) {
                                        if (Unit.isCellFreeForMovement(posY, posX - 1, Unit.gameFieldUnit)) {
                                            Unit.moving(activeUnit, posY, posX - 1);
                                        } else if (Unit.isCellFreeForMovement(posY + 1, posX, Unit.gameFieldUnit)) {
                                            Unit.moving(activeUnit, posY + 1, posX);
                                        } else {
                                            Unit.healing(activeUnit);
                                        }
                                    } else {
                                        if (Unit.isCellFreeForMovement(posY + 1, posX, Unit.gameFieldUnit)) {
                                            Unit.moving(activeUnit, posY + 1, posX);
                                        } else if (Unit.isCellFreeForMovement(posY, posX - 1, Unit.gameFieldUnit)) {
                                            Unit.moving(activeUnit, posY, posX - 1);
                                        } else {
                                            Unit.healing(activeUnit);
                                        }
                                    }
                                }
                            }
                            if (!isChestBroken() && Castle.getGold(2) >= 20) {

                            }
                            Map.showing();
                            Main.waiting(1000);
                        }
                    }
                } if (Unit.unitsCounter(2) > 5 && Castle.getGold(2) >= 30) {
                    MyUnit.spawnUnit(Castle.allUnits.get(5), 2);
                    Castle.addGold(2, -30);
                    Main.skip();
                    Map.showing();
                    Main.waiting(200);
                    counter1 += 1;
                }
            } else if (Castle.getGold(2) >= 40) {
                MyUnit.spawnUnit(Castle.allUnits.get(6), 2);
                Castle.addGold(2, -40);
                Main.skip();
                Map.showing();
                Main.waiting(200);
                counter1 += 1;
            } else if (Castle.getGold(2) >= 30) {
                MyUnit.spawnUnit(Castle.allUnits.get(5), 2);
                Castle.addGold(2, -30);
                Main.skip();
                Map.showing();
                Main.waiting(200);
                counter1 += 1;
            } else if (Castle.getGold(2) >= 10) {
                MyUnit.spawnUnit(Castle.allUnits.get(1), 2);
                Castle.addGold(2, -10);
                Main.skip();
                Map.showing();
                Main.waiting(200);
                counter1 += 1;
            }
            if (counter1 == 0) {
                //Проверка на совершение действий. Если у юнитов не осталось ходов, а у противника возможных действий -
                // цикл while прерывается
                myTurn = true;
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        Unit opp = Unit.gameFieldUnit[i][j];
                        if (opp.getTeam() == 2) {
                            System.out.println(opp.getID() + ".  " + opp.getName() + ":  [" + Unit.unitPosY(opp) + " . " + Unit.unitPosX(opp)  + "]  "+ opp.getRemainingMoves() + "  " + opp.getHit());
                        }
                    }
                }
            }
            // Логика для хода компьютера

        }

    }

    // Метод проверки наличия сундука на карте
    public static boolean isChestBroken() {
        return Unit.getUnitByID(4).getHealth() <= 0;
    }

    // Метод получения первинства хода виртуальной игральной костью
    private static boolean Roll() {
        Random random = new Random();
        System.out.println("Пора решить, кто будет ходить первым!");
        Main.waiting(200);
        System.out.println("НАЖМИ ENTER ЧТОБЫ БРОСИТЬ КУБИК");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.nextLine().isEmpty()) {
            System.out.println(" Я правда впечатлён твоими навыками пользования клавиатурой, но ты не на конкурсе талантов, а пока что...");
            Main.waiting(1000);
            System.out.println("НАЖМИ ENTER ЧТОБЫ БРОСИТЬ КУБИК");
        }
        Main.waiting(500);
        int playerDice = random.nextInt(6) + 1;
        System.out.println(STR."У ВАС ВЫПАЛО \{playerDice}");
        Main.waiting(200);
        int opponentDice = random.nextInt(6) + 1 ;
        System.out.println(STR."У ПРОТИВНИКА ВЫПАЛО \{opponentDice}");
        Main.waiting(200);

        if (playerDice == opponentDice) {
            System.out.println("У ВАС НИЧЬЯ");
            return Roll(); // Рекурсивный вызов при ничьей
        } else {
            if (playerDice > opponentDice) {
                System.out.println("Вы ходите первым!");
                Main.waiting(1000);
            } else {
                System.out.println("Противник ходит первым!");
                Main.waiting(1000);
            }
            return playerDice > opponentDice;
        }
    }

    // Метод выдачи юнитам штрафов и бонусов за нахождение на местности
    public static void getGameField(Unit activeUnit){
        switch (Map.gameField[Unit.unitPosY(activeUnit)][Unit.unitPosX(activeUnit)]) {
            case "Болото" -> { if (activeUnit.getRemainingMoves() != 1) {
                activeUnit.setRemainingMoves(activeUnit.getMoveRange() - 1);
                if (activeUnit.getTeam() == 1) {
                    System.out.println(STR."Ваш \{activeUnit.getName()} [ \{Unit.unitPosY(activeUnit) + 1} ][ \{Unit.
                            unitPosX(activeUnit) + 1} ] получает штраф местности: -1 к перемещению");
                } else {
                    System.out.println(STR."Вражеский \{activeUnit.getName()} [ \{Unit.unitPosY(activeUnit) + 1} ][ \{Unit.
                            unitPosX(activeUnit) + 1} ] получает штраф местности: -1 к перемещению");
                }
            } }
            case "Холм" -> {
                activeUnit.setDamageRange(activeUnit.getDamageRange() + 1);
                if (activeUnit.getTeam() == 1) {
                    System.out.println(STR."Ваш \{activeUnit.getName()} [ \{Unit.unitPosY(activeUnit) + 1} ][ \{Unit.
                            unitPosX(activeUnit) + 1} ] получает бонус: +1 дальность акатки.");
                } else {
                    System.out.println(STR."Вражеский \{activeUnit.getName()} [ \{Unit.unitPosY(activeUnit) + 1} ][ \{Unit.
                            unitPosX(activeUnit) + 1} ] получает бонус: +1 дальность атаки.");
                }
            }
        }
        Main.waiting(150);
    }

    // Метод присвоения бонуса за сундук игроку или компьютеру
    public static void getBonus(int tech){
        Random random = new Random();
        switch (tech) {
            case 1 -> bonus = 1;
            case 2 -> bonus = 2;
        }
        bonusNum = random.nextInt(4);
    }

    // Метод получения бонуса для юнита
    public static void getBonus(Unit unit) {
        if (bonus == 1) {
            switch (bonusNum) {
                case 1 -> { unit.setMoveRange(unit.getMoveRange() + 1);
                    System.out.println(STR."Ваш \{unit.getName()} [ \{Unit.unitPosX(unit)} ][ \{Unit.
                            unitPosX(unit)} ] получает бонус: +1 к передвижению"); }
                case 2 -> { unit.setDamage((int) (unit.getDamage() * 1.25));
                    System.out.println(STR."Ваш \{unit.getName()} [ \{Unit.unitPosX(unit)} ][ \{Unit.
                            unitPosX(unit)} ] получает бонус: +25% к передвижению"); }
                case 3 -> { unit.setDamageRange(unit.getDamageRange() + 1);
                    System.out.println(STR."Ваш \{unit.getName()} [ \{Unit.unitPosX(unit)} ][ \{Unit.
                            unitPosX(unit)} ] получает бонус: +1 к дальности атаки"); }
                case 4 -> { unit.setRevenge(unit.getRevenge() + 1);
                    System.out.println(STR."Ваш \{unit.getName()} [ \{Unit.unitPosX(unit)} ][ \{Unit.
                            unitPosX(unit)} ] получает бонус: +1 ответный удар"); }
            }
        } else if (bonus == 2) {
            switch (bonusNum) {
                case 1 -> { unit.setMoveRange(unit.getMoveRange() + 1);
                    System.out.println(STR."Вражеский \{unit.getName()} [ \{Unit.unitPosX(unit)} ][ \{Unit.
                            unitPosX(unit)} ] получает бонус: +1 к передвижению"); }
                case 2 -> { unit.setDamage((int) (unit.getDamage() * 1.25));
                    System.out.println(STR."Вражеский \{unit.getName()} [ \{Unit.unitPosX(unit)} ][ \{Unit.
                            unitPosX(unit)} ] получает бонус: +25% к передвижению"); }
                case 3 -> { unit.setDamageRange(unit.getDamageRange() + 1);
                    System.out.println(STR."Вражеский \{unit.getName()} [ \{Unit.unitPosX(unit)} ][ \{Unit.
                            unitPosX(unit)} ] получает бонус: +1 к дальности атаки"); }
                case 4 -> { unit.setRevenge(unit.getRevenge() + 1);
                    System.out.println(STR."Вражеский \{unit.getName()} [ \{Unit.unitPosX(unit)} ][ \{Unit.
                            unitPosX(unit)} ] получает бонус: +1 ответный удар"); }
            }
        }
    }

    // Метод возвращающий юнитам их параметры в начале раунда
    private static void afterRound() {
        Castle.addGold(1, 20);
        Castle.addGold(2, 20);
        for (int i = 0; i < MyUnit.myUnits.size(); i++) {
            MyUnit.myUnits.get(i).setMoveRange(Castle.allUnits.get(MyUnit.myUnits.get(i).getType() - 1).getMoveRange());
            MyUnit.myUnits.get(i).setRemainingMoves(MyUnit.myUnits.get(i).getMoveRange());
            MyUnit.myUnits.get(i).setDamage(Castle.allUnits.get(MyUnit.myUnits.get(i).getType() - 1).getDamage());
            MyUnit.myUnits.get(i).setDamageRange(Castle.allUnits.get(MyUnit.myUnits.get(i).getType() - 1).getDamageRange());
            MyUnit.myUnits.get(i).setHit(true);
            MyUnit.myUnits.get(i).setRevenge(1);
            getGameField(MyUnit.myUnits.get(i));
            getBonus(MyUnit.myUnits.get(i));
        }
        for (int i = 0; i < MyUnit.oppUnits.size(); i++) {
            MyUnit.oppUnits.get(i).setMoveRange(Castle.allUnits.get(MyUnit.oppUnits.get(i).getType() - 1).getMoveRange());
            MyUnit.oppUnits.get(i).setRemainingMoves(MyUnit.oppUnits.get(i).getMoveRange());
            MyUnit.oppUnits.get(i).setDamage(Castle.allUnits.get(MyUnit.oppUnits.get(i).getType() - 1).getDamage());
            MyUnit.oppUnits.get(i).setDamageRange(Castle.allUnits.get(MyUnit.oppUnits.get(i).getType() - 1).getDamageRange());
            MyUnit.oppUnits.get(i).setHit(true);
            MyUnit.oppUnits.get(i).setRevenge(1);
            getGameField(MyUnit.oppUnits.get(i));
            getBonus(MyUnit.oppUnits.get(i));
        }
    }



    public static void gameProcess() {

        String[][] gameField = Map.making();
        setDifficult();
        Unit.setTechObj();
        playerCastle = new Castle();
        opponentCastle = new Castle();
        boolean myTurnIsFirst = Roll();
        while (Unit.isGameNotOver()) {
            if (myTurnIsFirst) {
                myTurn(gameField, gameFieldUnit);
                oppTurn(gameField, gameFieldUnit);

            }
            else {
                oppTurn(gameField, gameFieldUnit);
                myTurn(gameField, gameFieldUnit);
            }
            afterRound();
        }
    }
}