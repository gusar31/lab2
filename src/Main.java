import java.util.Scanner;
import java.io.Console;



public class Main {
    public static void waiting(int n) {
        // Метод ожидания для более комфортного взаимодействия с пользователем
        try {
            Thread.sleep(n); // Задержка в n милисекунд
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void skip() {
        // Метод "сброса" командной строки для более комфортного взаимодействия с пользователем
        for (int i = 1; i < 20; i++) {
            System.out.println("");
        }
    }

    // Начальная заставка
    public static void startingPage() {
        Main.skip();
        System.out.println("     _______  _____       _______  _________  __       _______");
        Main.waiting(200);
        System.out.println("    / _____/ / __  |     / _____/ /___  ___/ / /      / _____/");
        Main.waiting(230);
        System.out.println("   / /      / /  | |    / /____      / /    / /      / /____");
        Main.waiting(180);
        System.out.println("  / /      / /___| |   /____  /     / /    / /      / _____/");
        Main.waiting(220);
        System.out.println(" / /____  / _____  |  _____/ /     / /    / /____  / /____");
        Main.waiting(200);
        System.out.println("/______/ /_/     |_| /______/     /_/    /______/ /______/");
        Main.waiting(190);
        System.out.println("");
        Main.waiting(300);
        System.out.println("     __    __   _______   _______    __________");
        Main.waiting(200);
        System.out.println("    / /   / /  / _____/  / ___  /   /  ____   /");
        Main.waiting(170);
        System.out.println("   / /___/ /  / /____   / /__/ /   /  /   /  /");
        Main.waiting(210);
        System.out.println("  / ____  /  / _____/  / __  _/   /  /   /  /");
        Main.waiting(230);
        System.out.println(" / /   / /  / /____   / /  \\ \\   /  /___/  /");
        Main.waiting(200);
        System.out.println("/_/   /_/  /______/  /_/    \\_\\ /_________/");
        Main.waiting(200);
        System.out.println("");
        Main.waiting(500);
        System.out.println("  *НАЖМИТЕ ENTER ДЛЯ НАЧАЛА ИГРЫ*");
        Scanner scanner = new Scanner(System.in);
        while (scanner.nextLine().length() != 0) {
            System.out.println(" Это очень круто, что ты знаешь другие клавиши помимо Enter." +
                    " Похвастайся этим перед друзьями на досуге, а пока что...");
            Main.waiting(1000);
            System.out.println("  *НАЖМИТЕ ENTER ДЛЯ НАЧАЛА ИГРЫ*");
        }
    }
    
    // Конечная заставка победы и поражения
    public static void endingPage(int team) {

        if (team == 1) {
            Main.skip();
            System.out.println("    _   __  _______  __    __     _        _        _");
            Main.waiting(300);
            System.out.println("   | | / / / ___  / / /   / /    /v\\      /v\\      /v\\");
            Main.waiting(300);
            System.out.println("   | |/ / / /  / / / /   / /     \\_/_    _\\_/_    _\\_/");
            Main.waiting(300);
            System.out.println("   | / / / /  / / / /   / /       \\  \\__/     \\__/  /");
            Main.waiting(300);
            System.out.println("   |/ / / /  / / / /   / /         \\               /");
            Main.waiting(300);
            System.out.println("   / / / /__/ / / /___/ /           |______________|");
            Main.waiting(300);
            System.out.println("  /_/ /______/ /_______/            /              \\");
            Main.waiting(300);
            System.out.println("                                   /    _      _    \\");
            Main.waiting(300);
            System.out.println(" __   __    __  __  __    __       |   |_|    |_|   |");
            Main.waiting(300);
            System.out.println("|  | /  |  / / /_/ /  |  / /       |                |");
            Main.waiting(300);
            System.out.println("|  |/   | / / __  /   | / /        |   __________   |");
            Main.waiting(300);
            System.out.println("|  / /| |/ / / / / /| |/ /         |   \\________/   |");
            Main.waiting(300);
            System.out.println("| / / | | / / / / / |   /          \\                /");
            Main.waiting(300);
            System.out.println("|__/  |__/ /_/ /_/  |__/            \\______________/");
            Main.waiting(700);

        } else if (team == 2) {
            Main.skip();
            System.out.println("    _   __  _______  __    __     _        _        _");
            Main.waiting(300);
            System.out.println("   | | / / / ___  / / /   / /    /v\\      /v\\      /v\\");
            Main.waiting(300);
            System.out.println("   | |/ / / /  / / / /   / /     \\_/_    _\\_/_    _\\_/");
            Main.waiting(300);
            System.out.println("   | / / / /  / / / /   / /       \\  \\__/     \\__/  /");
            Main.waiting(300);
            System.out.println("   |/ / / /  / / / /   / /         \\               /");
            Main.waiting(300);
            System.out.println("   / / / /__/ / / /___/ /           |______________|");
            Main.waiting(300);
            System.out.println("  /_/ /______/ /_______/            /              \\");
            Main.waiting(300);
            System.out.println("                                   /    \\/    \\/    \\");
            Main.waiting(300);
            System.out.println("     __    _______ _______ _______ |    /\\    /\\    |");
            Main.waiting(300);
            System.out.println("    / /   / ___  // _____// _____/ |                |");
            Main.waiting(300);
            System.out.println("   / /   / /  / // /____ / /____   |    ________    |");
            Main.waiting(300);
            System.out.println("  / /   / /  / //____  // _____/   |   /________\\   |");
            Main.waiting(300);
            System.out.println(" / /__ / /__/ /_____/ // /____\t   \\                /");
            Main.waiting(300);
            System.out.println("/____//______//______//______/      \\______________/");
            Main.waiting(700);
        }
    }

    public void main(String[] args) {

        Main.startingPage();
        Game.gameProcess();

    }
}