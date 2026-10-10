import java.util.Scanner;

public class ConsoleMainMenu {

    public static void Show()
    {
        System.out.println("""
            
            ===== МЕНЮ =====
            1. Добавить данные вручную
            2. Загрузить данные из файла
            3. Обработать данные
            4. Выход
            """);
    }

    public static void GetMenuChoice(String input)
    {
        while (true) {

            if (input.equals("1")) {
            }
            else if (input.equals("2")) {
            }
            else if (input.equals("3")) {
            }
            else if (input.equals("4")) {
            }
            else {
                System.out.println("Введена неизвестная команда! Введите число от 1 до 4!");
            }
        }
    }
 
}