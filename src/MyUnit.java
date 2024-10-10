import javax.swing.event.DocumentListener;
import java.util.ArrayList;
import java.util.Random;

public class MyUnit extends Unit {

    public static ArrayList<Unit> myUnits;
    public static ArrayList<Unit> oppUnits;
    public static boolean myTurn;

    public MyUnit(int type, String name, String symbol, int remainingHealth, int health, int damage, int damageRange, int moveRange, int cost) {
        super(type, name, symbol, remainingHealth, health, damage, damageRange, moveRange, cost);
        myUnits = new ArrayList<>();
        oppUnits = new ArrayList<>();
    }

    public MyUnit(int id, int team) {
        super(id, team);
        myUnits = new ArrayList<>();
        oppUnits = new ArrayList<>();
    }

    public static void spawnUnit(Unit selectedUnit, int team) {
        myTurn = true;
        Random random = new Random();
        int row = random.nextInt(0,3); // Генерация случайной строки в окрестностях замка
        int col = random.nextInt(0,3); // Генерация случайного столбца в окрестностях замка




        if (team == 1) {
            // Проверяем, что выбранная клетка пуста и находится в пределах игрового поля
            while (Unit.gameFieldUnit[6 + row][1 + col].getID() != 1) {
                row = random.nextInt(3);
                col = random.nextInt(3);
            }
            MyUnit RealyMyUnit = new MyUnit(Unit.giveID(1), 1);
            // Размещаем нового юнита на игровом поле
            Unit.giveStats(RealyMyUnit, selectedUnit);
            gameFieldUnit[6 + row][1 + col] = RealyMyUnit;
            System.out.println(STR."Юнит \{selectedUnit.getName()} размещен на клетке [\{7 + row},\{2 + col}]");
            RealyMyUnit.setHit(true);
            RealyMyUnit.setRevenge(1);
            myUnits.add(RealyMyUnit);


        } else if (team == 2){
            // Проверяем, что выбранная клетка пуста и находится в пределах игрового поля
            while (Unit.gameFieldUnit[6 + row][1 + col].getID() != 1) {
                row = random.nextInt(3);
                col = random.nextInt(3);
            }
            MyUnit OppUnit = new MyUnit(Unit.giveID(1), 2);
            // Размещаем нового вражеского юнита на игровом поле
            gameFieldUnit[1 + row][6 + col] = OppUnit;
            System.out.println(STR."Юнит \{selectedUnit.getName()} размещен на клетке [\{2 + row},\{7 + col}]");
            Unit.giveStats(OppUnit, selectedUnit);
            OppUnit.setHit(true);
            OppUnit.setRevenge(1);
            oppUnits.add(OppUnit);
        }
    }
    public static void deleteUnit(Unit selectedUnit) {
        Unit empty = new Unit(1, 3);
        if (selectedUnit.getTeam() == 1) {
            for (int i = 0; i < myUnits.size(); i++) {
                if (myUnits.get(i).getID() == selectedUnit.getID()) {
                    myUnits.remove(selectedUnit);

                }
            }
            selectedUnit.setID(0);
            Unit.gameFieldUnit[unitPosY(selectedUnit)][unitPosX(selectedUnit)] = empty;
        } else if (selectedUnit.getTeam() == 2) {
            for (int i = 0; i < oppUnits.size(); i++) {
                if (oppUnits.get(i).getID() == selectedUnit.getID()) {
                    oppUnits.remove(selectedUnit);
                }
            }
            selectedUnit.setID(0);
            Unit.gameFieldUnit[unitPosY(selectedUnit)][unitPosX(selectedUnit)] = empty;
        } else {
            int posY = unitPosY(selectedUnit);
            int posX = unitPosX(selectedUnit);
            Unit.gameFieldUnit[posY][posX].setID(1);
            Unit.gameFieldUnit[posY][posX].setTeam(3);

        }
    }
}