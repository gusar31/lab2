import java.util.Random;
import java.util.Scanner;


public class Unit {
    public static Unit[][] gameFieldUnit = new Unit[10][10];
    public Map map;
    public Castle castle;
    public static int difficult;
    public static Unit empty = new Unit(1, 3);
    private int id;
    private int type;
    private String name;
    private String symbol; // Символ для обозначения юнита на карте
    private int health;
    private int remainingHealth;
    private int damage;
    private int damageRange;
    private int moveRange;
    private int remainingMoves; // Оставшиеся перемещения в текущем ходу
    public static boolean hit;
    public static int revenge;
    private int cost;
    private boolean active; // Флаг, обозначающий активность юнита в текущем ходу
    private int team;

    public Unit(int type, String name, String symbol, int remainingHealth, int health, int damage, int damageRange, int moveRange, int cost) {
        this.type = type;
        this.name = name;
        this.symbol = symbol;
        this.health = health;
        this.remainingHealth = remainingHealth;
        this.damage = damage;
        this.damageRange = damageRange;
        this.moveRange = moveRange;
        this.remainingMoves = moveRange;
        this.cost = cost;
        this.active = false;

    }

    public Unit(int id, int team) {
        this.id = id;
        this.team = team;
    }

    public static int giveID(int id) {
        boolean isFree = true;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (gameFieldUnit[i][j].getID() == id) {
                    isFree = false;
                    break;
                }
            }
        }
        if (isFree) {
            return id;
        }
        else {
            return giveID(id + 1);
        }
    }

    // Геттеры и сеттеры
    public int getID() {
        return id;
    }
    public void setID(int id) {
        this.id = id;
    }

    public int getTeam() {
        return team;
    }
    public void setTeam(int team) {
        this.team = team;
    }

    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public int getRemainingHealth() {
        return remainingHealth;
    }
    public void setRemainingHealth(int remainingHealth) {
        this.remainingHealth = remainingHealth;
    }

    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamageRange() {
        return damageRange;
    }
    public void setDamageRange(int damageRange) {
        this.damageRange = damageRange;
    }

    public int getMoveRange() {
        return moveRange;
    }
    public void setMoveRange(int moveRange) {
        this.moveRange = moveRange;
    }

    public int getRemainingMoves() {
        return remainingMoves;
    }
    public void setRemainingMoves(int remainingMoves) {
        this.remainingMoves = remainingMoves;
    }

    public boolean getHit() {
        return hit;
    }
    public void setHit(boolean hit) {
        Unit.hit = hit;
    }

    public int getRevenge() {
        return revenge;
    }
    public void setRevenge(int revenge) {
        Unit.revenge = revenge;
    }

    public int getCost() {
        return cost;
    }

    public static void setTechObj() {
        //Метод генерации технических строений (замки, бонусный сундук, пустота)
        Random random = new Random();

        Unit empty = new Unit(1, 3);
        for (int i = 0; i < 10; i++) {
            for (int j = i; j < 10; j++) {
                gameFieldUnit[i][j] = empty;
                gameFieldUnit[j][i] = empty;

            }
        }
        Unit myCastle = new Unit(2, 3);
        myCastle.setHealth(500);
        myCastle.setRemainingHealth(myCastle.getHealth());
        myCastle.setSymbol("ПппП");
        myCastle.setName("Мой замок");
        gameFieldUnit[7][2] = myCastle;
        Unit oppCastle = new Unit(3, 3);
        oppCastle.setHealth(500);
        oppCastle.setRemainingHealth(oppCastle.getHealth());
        oppCastle.setSymbol("ПппП");
        oppCastle.setName("Замок противника");
        gameFieldUnit[2][7] = oppCastle;
        Unit chest = new Unit(4, 3);
        chest.setHealth(200);
        chest.setRemainingHealth(chest.getHealth());
        chest.setSymbol("<??>");
        chest.setName("Бонусный сундук");
        int chestPlace = random.nextInt(0, 9);
        gameFieldUnit[chestPlace][chestPlace] = chest;



    }
    public static int unitPosX(Unit Unit) {
        // Метод находит юнита на игровом поле и возвращает его позицию по оси X
        int posX = 10;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (gameFieldUnit[i][j] == Unit) {
                    posX = j;
                    break;
                }
            }
        }
        return posX;
    }

    public static int unitPosY(Unit Unit){
        // Метод находит юнита на игровом поле и возвращает его позицию по оси Y
        int posY = 10;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (gameFieldUnit[i][j] == Unit) {
                    posY = i;
                    break;
                }
            }
        }
        return posY;
    }

    public static Unit getUnitByID(int ID) {
        // Метод находит юнита на игровом поле, сравнивая его ID с указанным в параметре метода
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (gameFieldUnit[i][j].getID() == ID) { return gameFieldUnit[i][j]; }
            }
        }
        System.out.println("Цель недостижима!");
        return gameFieldUnit[5][5];
    }

    // Метод для атаки другого юнита
    public static void attack(Unit activeUnit, Unit targetUnit) {
        if ((targetUnit.getTeam() != activeUnit.getTeam()) & (targetUnit.getID() != 1)) {
            if (activeUnit.getHit()) {
                if (canAttack(activeUnit, targetUnit) || ifNear(activeUnit, targetUnit)) {
                    targetUnit.setRemainingHealth(targetUnit.getRemainingHealth() - activeUnit.getDamage());
                    if (activeUnit.getTeam() == 1) {
                        if (targetUnit.getTeam() == 3) {
                            System.out.println(STR."\{activeUnit.getName()} наносит \{activeUnit.getDamage()}ед. урона строению \{targetUnit.getName()}.");
                        } else {
                            System.out.println(STR."\{activeUnit.getName()} наносит \{activeUnit.getDamage()}ед. урона бойцу \{targetUnit.getName()}.");
                        }
                    } else if (activeUnit.getTeam() == 2) {
                        if (targetUnit.getTeam() == 3) {
                            System.out.println(STR."Вражеский \{activeUnit.getName()} наносит \{activeUnit.getDamage()}ед. урона строению \{targetUnit.getName()}.");
                        } else {
                            System.out.println(STR."Вражеский \{activeUnit.getName()} наносит \{activeUnit.getDamage()}ед. урона бойцу \{targetUnit.getName()}.");
                        }
                    }
                    activeUnit.setRemainingMoves(0);
                    activeUnit.setHit(false);
                    if (targetUnit.getRemainingHealth() <= 0) {
                        System.out.println(STR."\{targetUnit.getName()} пал судьбой храбрых.");
                        MyUnit.deleteUnit(targetUnit);
                    } else if (ifNear(activeUnit, targetUnit) && (targetUnit.getRevenge() > 0)) {
                        // Каждый юнит имеет 1 контратаку на ход, если она есть и ударивший юнит рядом (именно рядом, а не в пределах damageRange)
                        activeUnit.setRemainingHealth(activeUnit.getRemainingHealth() - targetUnit.damage);
                        targetUnit.setRevenge(targetUnit.getRevenge() - 1);
                        System.out.println(STR."\{targetUnit.getName()} отвечает на атаку бойца \{activeUnit.getName()} и наносит ему \{targetUnit.getDamage()}ед. урона.");
                        if (activeUnit.health <= 0) {
                            System.out.println(STR."\{activeUnit.getName()} пал судьбой храбрых :(");
                            MyUnit.deleteUnit(activeUnit);
                        }
                    }
                } else { System.out.println("Цель недостижима!"); }
            } else { System.out.println("Вы уже использовали этого юнита в этом ходу!"); }
        } else { System.out.println("Вы не можете атаковать этого юнита!"); }
    }

    // Метод лечения, юнит завершает ход и лечит 50% от своих HP
    public static void healing(Unit activeUnit) {
        if (activeUnit.getHit()) {
            if (activeUnit.getRemainingMoves() == activeUnit.getMoveRange()) {
                if (activeUnit.getRemainingHealth() != activeUnit.getHealth()) {
                    activeUnit.setRemainingMoves(0);
                    activeUnit.setHit(false);
                    activeUnit.setRemainingHealth((int) (activeUnit.getRemainingHealth() +
                            activeUnit.getHealth() * 0.5));
                    if (activeUnit.getRemainingHealth() > activeUnit.getHealth()) {
                        activeUnit.setRemainingHealth(activeUnit.getHealth());
                    }
                }
            }
        }
    }

    // Метод выводит пользователю всех юнитов, которых может атаковать выбранный юнит
    public static void showTargets(Unit activeUnit) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Доступные цели для атаки:");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (gameFieldUnit[i][j].getTeam() != 1 && gameFieldUnit[i][j].getID() != 2 && gameFieldUnit[i][j].getID() != 1) {
                    if (Unit.canAttack(activeUnit, gameFieldUnit[i][j]))
                        System.out.println(STR."\{gameFieldUnit[i][j].getID()}. \{gameFieldUnit[i][j].getName()} [ \{i + 1} ][ \{j + 1} ];");
                }
            }
        }
    }

    // Метод для проверки доступности клетки для перемещения
    public static boolean canGo(Unit activeUnit, int targetY, int targetX) {
        int distance = calculateDistance(activeUnit, targetY, targetX); // Метод для вычисления расстояния между юнитами
        return (distance <= activeUnit.getRemainingMoves());
    }
    // Метод для проверки доступности цели для атаки
    public static boolean canAttack(Unit activeUnit, Unit targetUnit) {
        int distance =  calculateDistance(activeUnit, targetUnit); // Метод для вычисления расстояния между юнитами
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (activeUnit.getDamageRange() > 1 && gameFieldUnit[i][j].getTeam() == targetUnit.getTeam() && targetUnit.getTeam() != 3) {
                    return (ifNear(activeUnit, targetUnit));
                }
            }
        }
        return (distance <= activeUnit.damageRange);
    }

    // Метод для проверки соседства юнитов на поле (в т.ч. по диагонали)
    public static boolean ifNear(Unit activeUnit, Unit targetUnit) {
        return ((Math.abs(unitPosX(activeUnit) - unitPosX(targetUnit))) <= 1 && (Math.abs(unitPosY(activeUnit) - unitPosY(targetUnit))) <= 1);
    }

    // Метод для вычисления расстояния между текущим и целевым юнитами
    private static int calculateDistance(Unit activeUnit, Unit targetUnit) {
        // Логика вычисления расстояния, на основе их координат на карте
        // Возвращается целое число, обозначающее расстояние между юнитами
        return (Math.abs(unitPosX(activeUnit) - unitPosX(targetUnit)) + Math.abs(unitPosY(activeUnit) - unitPosY(targetUnit)));
    }

    // Метод для вычисления расстояния между текущим юнитом и клеткой на игровом поле
    private static int calculateDistance(Unit activeUnit, int targetY, int targetX) {
        // Логика вычисления расстояния, на основе их координат на карте
        // Возвращается целое число, обозначающее расстояние между юнитами
        return (Math.abs(unitPosX(activeUnit) - targetX) + Math.abs(unitPosY(activeUnit) - targetY));
    }

    // Метод для вычисления количества юнитов на игровом поле
    public static int unitsCounter(int team) {
        int counter = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (gameFieldUnit[i][j].getTeam() == team) {
                    counter += 1;
                }
            }
        }
        return counter;
    }

    // Метод получения координат для перемещения юнита
    // Раздёлен для возможности использования метода и игроком, и компьютером
    public static void moving(Unit activeUnit) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите номер ряда (1-10) или 0 для завершения: ");
        int targetY = scanner.nextInt();
        if (targetY == 0) {
            return;
        }

        System.out.print("Введите номер столбца (1-10): ");
        int targetX = scanner.nextInt();

        // Проверка на валидность введенных данных
        if (targetX < 1 || targetX > 10 || targetY < 1 || targetY > 10) {
            System.out.println("Неверные координаты клетки!");
            moving(activeUnit);
        }
        moving(activeUnit, targetY - 1, targetX - 1);
    }

    // Метод перемещения юнита
    public static void moving(Unit activeUnit, int targetY, int targetX) {


        // Перемещение
        if (isCellFreeForMovement(targetY, targetX, gameFieldUnit)) {
            if (canGo(activeUnit, targetY, targetX)) {
                int posX = unitPosX(activeUnit);
                int posY = unitPosY(activeUnit);
                Unit empty = new Unit(1, 3);
                gameFieldUnit[targetY][targetX].setDamage(activeUnit.damage);
                gameFieldUnit[posY][posX] = empty;
                gameFieldUnit[posY][posX].setID(1);
                gameFieldUnit[posY][posX].setTeam(3);
                gameFieldUnit[targetY][targetX] = activeUnit;

                activeUnit.setRemainingMoves(activeUnit.remainingMoves - calculateDistance(activeUnit, posY, posX));
            } else {
                System.out.println("Недостаточно очков передвижения!");
                if (activeUnit.getTeam() == 1) {
                    moving(activeUnit);
                }
            }
        }
        else {
            System.out.println("Данная клетка занята!");
        }
    }

    // Метод для проверки наличия юнита на игровом поле
    public static boolean unitOnDesk(int unitID) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (gameFieldUnit[i][j].id == unitID) {
                    return true;
                }
            }
        }
        return false;
    }

    // Метод для проверки незанятости клетки на игровом поле
    public static boolean isCellFreeForMovement(int targetY, int targetX, Unit[][] gameField) {
        int maxRow = gameField.length;
        int maxCol = gameField[0].length;

        // Проверка выхода за границы игрового поля
        if (targetX < 0 || targetX >= maxRow || targetY < 0 || targetY >= maxCol) {
            return false;
        }

        // Проверка занятости клетки другим юнитом
        return gameFieldUnit[targetY][targetX].getID() == 1;
    }
    public static boolean isGameNotOver() {
        // Проверка условия завершения игры
        return (gameFieldUnit[7][2].getID() == 2) && gameFieldUnit[2][7].getID() == 3; // здесь нужно ваше условие завершения игры
    }

    // Метод передача вызванному юниту параметров "эталонного" боайца
    public static void giveStats(Unit Unit, Unit StatsDonor) {
        Unit.name = StatsDonor.name;
        Unit.type = StatsDonor.type;
        Unit.health = StatsDonor.health;
        Unit.remainingHealth = StatsDonor.health;
        Unit.moveRange = StatsDonor.moveRange;
        Unit.remainingMoves = StatsDonor.remainingMoves;
        Unit.damage = StatsDonor.damage;
        Unit.damageRange = StatsDonor.damageRange;
        if (Unit.getTeam() == 1) { Unit.symbol = StatsDonor.symbol; }
        else {
            // Разворот символов для юнита компьтютера
            var symbol = StatsDonor.getSymbol();
            StringBuilder stringBuilder = new StringBuilder(symbol);
            symbol = stringBuilder.reverse().toString();
            symbol = symbol.replaceAll("E", "Ǝ");
            symbol = symbol.replaceAll(">", "<");
            symbol = symbol.replaceAll("\\)", "(");
            symbol = symbol.replaceAll("\\(", ")");
            symbol = symbol.replaceAll("<", ">");
            symbol = symbol.replaceAll(">", "<");
            Unit.symbol = symbol;

            // Здесь же реализована установка сложности
            Unit.damage += (int) Math.round(Unit.damage * Math.sqrt(difficult) / 4);
            Unit.health += (int) Math.round(Unit.health * Math.sqrt(difficult) / 4);
        }
    }
    // Другие методы, например проверка доступности клеток для перемещения


}

