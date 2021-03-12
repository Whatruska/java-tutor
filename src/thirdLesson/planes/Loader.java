package thirdLesson.planes;

import java.util.Scanner;

public class Loader {
    static PlanesManager planesManager = new PlanesManagerImpl();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в симулятор парковки самолетов!");

        String cmd = "";

        //add <phone number> - добавить самолет
        //exitLast
        //exitAll
        do {
            System.out.print("Введите команду: ");
            cmd = scanner.nextLine();
            if (cmd.contains("add")) {
                addPlane(cmd);
            } else if (cmd.equals("exitLast")) {
                exitLast();
            } else if (cmd.equals("exitAll")) {
                planesManager.exitAllPlanes();
            } else {
                System.out.println("Неизвестная команда");
            }
        } while (!cmd.equals("exit"));
    }

    public static void addPlane (String cmd) {
        String[] cmdParts = cmd.split(" ");
        if (cmdParts.length < 2) {
            System.out.println("Введи команду нормально!");
        } else {
            try {
                planesManager.putPlane(cmdParts[1]);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void exitLast () {
        try {
            planesManager.exitLast();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
