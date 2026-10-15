package org.example;
import java.util.List;
import java.util.Scanner;

public class ConsoleMainMenu {

    public static void getMenuChoice(){
        var scanner = new Scanner(System.in);
        while (true) {
            show();
            int answer;

            try {
                answer= Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введено не число! Введите целое число от 0 до 4!");
                continue;
            }

            if (answer == 1){
                ConsoleInput.AddCar(scanner);
            }
            else if (answer == 2){
                ConsoleInput.AddCarFromJSON(scanner);
            }
            else if(answer == 3){
                handleFilterMenu(scanner);
            }
            else if(answer == 4){
                handleSortMenu(scanner);
            }
            else if(answer == 5){
                var cars = CarRepository.GetAll();
                printCarDescriptions(cars);
            }
            else if (answer == 0){
                System.out.println("Выход из программы...");
                return;
            }
            else {
                System.out.println("Введена неизвестная команда! Введите целое число от 0 до 5!");
            }
        }
    }

    private static void handleFilterMenu(Scanner scanner) {
        int answer;
        while(true) {
            showFilterMenu();
            try {
                answer = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введено не число! Введите целое число от 0 до 4!");
                continue;
            }

            if (answer == 1) {
                System.out.println("Введите название модели");
                var filteredCars = CarRepository.filterCarsByModel(scanner.nextLine());

                if (filteredCars == null || filteredCars.isEmpty()) {
                    System.out.println("По данному запросу ничего не найдено");
                }
                else {
                    System.out.println(CarRepository.getCarDescriptions(filteredCars));
                }
            }
            else if (answer == 2) {

            }
            else if (answer == 3) {

            }
            else if (answer == 0) {
                return;
            }
            else {
                System.out.println("Введена неизвестная команда! Введите целое число от 0 до 3!");
            }
        }
    }

    private static void handleSortMenu(Scanner scanner) {
        int answer;
        while(true) {
            showSortMenu();
            try {
                answer = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введено не число! Введите целое число от 0 до 1!");
                continue;
            }

            if (answer == 1) {
                var cars = CarRepository.sortByModel();
                printCarDescriptions(cars);
            }
            else if (answer == 0) {
                return;
            }
            else {
                System.out.println("Введена неизвестная команда! Введите целое число от 0 до 3!");
            }
        }
    }

    public static void printCarDescriptions(List<Car> cars){
        if (cars == null || cars.isEmpty()) {
            System.out.println("Коллекция пуста. Воспользуйтесь возможностью добавления новых объектов");
        }
        else {
            System.out.println(CarRepository.getCarDescriptions(cars));
        }
    }

    private static void show(){
        System.out.println("""
            
            ===== МЕНЮ =====
            1. Добавить данные вручную
            2. Добавить данные из файла
            3. Фильтрация данных
            4. Сортировка данных
            5. Вывести список данных на консоль
            0. Выход
            
            Введите целое число:
            """);
    }

    private static void showFilterMenu(){
        System.out.println("""
            
            ===== ФИЛЬТРАЦИЯ =====
            1. Фильтровать по модели
            2. Фильтровать по мощности
            3. Фильтровать по году выпуска
            0. Назад
            
            Введите целое число:
            """);
    }

    private static void showSortMenu(){
        System.out.println("""
            
            ===== СОРТИРОВКА =====
            1. Сортировка по модели в алфавитном порядке
            0. Назад
            
            Введите целое число:
            """);
    }
}
