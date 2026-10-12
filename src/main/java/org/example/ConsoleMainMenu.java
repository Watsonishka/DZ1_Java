package org.example;

import java.util.Scanner;

public class ConsoleMainMenu {

    public static void GetMenuChoice()
    {
        var scanner = new Scanner(System.in);

        while (true) {
            Show();
            System.out.print("Введите целое число: ");
            int answer;

            try {
                answer= Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введено не число! Введите целое число от 0 до 4!");
                continue;
            }

            if (answer == 1){
                System.out.println("Вызов функции 1 (Ручной ввод)");
            }
            else if (answer == 2){
                System.out.println("Вызов функции 2 (Загрузка из файла)");
            }
            else if(answer == 3){
                System.out.println("Вызов функции 3 (Обработка Stream API)");
            }
            else if(answer == 4){
                System.out.println("Вызов функции 4 (Обработка Stream API)");
            }
            else if (answer == 0){
                System.out.println("Выход из программы...");
                System.exit(0);
            }
            else {
                System.out.println("Введена неизвестная команда! Введите целое число от 0 до 4!");
            }
        }
    }

    private static void Show()
    {
        System.out.println("""
            
            ===== МЕНЮ =====
            1. Добавить данные вручную
            2. Добавить данные из файла
            3. Обработать данные
            4. Вывести список данных на консоль
            0. Выход
            """);
    }
}
