package org.example;

import java.util.Scanner;

public class ConsoleMainMenu {

    public static void GetMenuChoice()
    {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            Show();
            System.out.print("Введите целое число: ");
            String answer = scanner.nextLine();

            if (answer.equals("1")) {
                System.out.println("Вызов функции 1 (Ручной ввод)");
            }
            else if (answer.equals("2")) {
                System.out.println("Вызов функции 2 (Загрузка из файла)");
            }
            else if (answer.equals("3")) {
                System.out.println("Вызов функции 3 (Обработка Stream API)");
            }
            else if (answer.equals("0")) { // Сделали 0, как в тексте меню
                System.out.println("Выход из программы...");
                System.exit(0);
            }
            else {
                System.out.println("Введена неизвестная команда! Введите число от 0 до 3!");
            }
        }
    }

    private static void Show()
    {
        System.out.println("""
            
            ===== МЕНЮ =====
            1. Добавить данные вручную
            2. Загрузить данные из файла
            3. Обработать данные
            4. Выход
            """);
    }
}
