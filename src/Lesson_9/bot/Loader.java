package Lesson_9.bot;

import java.util.Scanner;

public class Loader {
    public static void main(String[] args) {
        Bot bot = new Bot();
        Scanner scanner = new Scanner(System.in);
        String cmd = scanner.nextLine();
        String response = "";
        do {
            response = bot.communicate(cmd);
            System.out.println(response);
            cmd = scanner.nextLine();
        } while (!response.equals("Пока) Увидимся позже"));
    }
}
