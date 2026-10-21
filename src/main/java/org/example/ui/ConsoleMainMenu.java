package org.example.ui;
import org.example.model.Car;
import org.example.model.CarFormatter;
import org.example.model.CarRepository;
import org.example.collection.MyArrayList;
import org.example.sort.BubbleSort;
import org.example.sort.InsertionSort;
import org.example.sort.SortAlgorithm;
import java.util.Comparator;
import java.util.Scanner;
import java.util.function.Predicate;

public class ConsoleMainMenu {

    public static void getMenuChoice() {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            show();
            int answer = ConsoleInput.getInt(scanner, 0, 5);

            if (answer == 1) {
                ConsoleInput.addCar(scanner);
            }
            else if (answer == 2) {
                ConsoleInput.addCarFromJSON(scanner);
            }
            else if (answer == 3) {
                handleFilterMenu(scanner);
            }
            else if (answer == 4) {
                handleSortMenu(scanner);
            }
            else if (answer == 5) {
                var cars = CarRepository.getAll();
                printCarDescriptions(cars);
            }
            else if (answer == 0) {
                System.out.println("Выход из программы...");
                return;
            }
        }
    }

    private static void handleFilterMenu(Scanner scanner) {

        while (true) {
            System.out.println("По какому критерию осуществить поиск (фильтрацию) объектов?");
            showComparatorChoiceMenu();
            int answer = ConsoleInput.getInt(scanner, 0, 3);

            if (answer == 0) {
                return;
            }

            Predicate<Car> predicate = null;
            if (answer == 1) {
                System.out.println("Введите название модели (или часть названия):");
                String query = scanner.nextLine().trim().toLowerCase();
                predicate = car -> car.getModel().toLowerCase().contains(query);
            }
            else if (answer == 2) {
                System.out.println("Введите минимальную мощность (число):");
                try {
                    double minPower = Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
                    predicate = car -> car.getPower() >= minPower;
                }
                catch (NumberFormatException e) {
                    System.out.println("Ошибка: введено не число! Попробуйте снова.");
                    continue;
                }
            }
            else if (answer == 3) {
                System.out.println("Введите год выпуска (целое число):");
                try {
                    int year = Integer.parseInt(scanner.nextLine().trim());
                    predicate = car -> car.getManufacturedYear() == year;
                }
                catch (NumberFormatException e) {
                    System.out.println("Ошибка: введено не целое число! Попробуйте снова.");
                    continue;
                }
            }

            if (predicate != null) {
                var filteredCars = CarRepository.filter(predicate);
                if (filteredCars == null || filteredCars.isEmpty()) {
                    System.out.println("По данному запросу ничего не найдено");
                }
                else {
                    System.out.println(CarFormatter.getCarDescriptions(filteredCars));
                }
            }
        }
    }

    private static void handleSortMenu(Scanner scanner) {

        while (true) {
            showSortMenu();
            int answer = ConsoleInput.getInt(scanner, 0, 2);

            if (answer == 0) {
                return;
            }
            SortAlgorithm<Car> algorithm = selectAlgorithm(answer);
            Comparator<Car> comparator = selectComparatorWithDirection(scanner);

            if (comparator == null) {
                continue;
            }

            MyArrayList<Car> sortedCars = CarRepository.sort(algorithm, comparator);
            printCarDescriptions(sortedCars);
            return;
        }
    }

    private static SortAlgorithm<Car> selectAlgorithm(int choice) {return (choice == 1) ? new BubbleSort<>() : new InsertionSort<>();}

    private static Comparator<Car> selectComparator(int choice) {

        return switch (choice) {
            case 1 -> Comparator.comparing(Car::getModel);
            case 2 -> Comparator.comparingDouble(Car::getPower);
            case 3 -> Comparator.comparingInt(Car::getManufacturedYear);
            default -> null;
        };
    }

    private static Comparator<Car> selectComparatorWithDirection(Scanner scanner) {

        while (true) {
            showComparatorChoiceMenu();
            int answer = ConsoleInput.getInt(scanner, 0, 3);
            if (answer == 0) {
                return null;
            }

            Comparator<Car> comparator = selectComparator(answer);
            if (comparator == null) {
                continue;
            }

            showSortDirectionMenu();
            int dirAnswer = ConsoleInput.getInt(scanner, 0, 2);
            if (dirAnswer == 0) {
                continue;
            }
            if (dirAnswer == 2) {
                comparator = comparator.reversed();
            }

            return comparator;
        }
    }

    public static void printCarDescriptions(MyArrayList<Car> cars) {

        if (cars == null || cars.isEmpty()) {
            System.out.println("Коллекция пуста. Воспользуйтесь возможностью добавления новых объектов");
        }
        else {
            System.out.println(CarFormatter.getCarDescriptions(cars));
        }
    }

    private static void show() {

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

    private static void showSortMenu() {

        System.out.println("""
                
                ===== СОРТИРОВКА =====
                1. Пузырьковая сортировка
                2. Сортировка вставками
                0. Назад
                
                Введите целое число:
                """);
    }

    private static void showSortDirectionMenu() {

        System.out.println("""
                
                ===== НАПРАВЛЕНИЕ СОРТИРОВКИ =====
                1. Сортировка по возрастанию
                2. Сортировка по убыванию
                0. Назад
                
                Введите целое число:
                """);
    }

    private static void showComparatorChoiceMenu() {

        System.out.println("""
                
                ===== ВЫБОР КРИТЕРИЯ =====
                1. По названию модели
                2. По мощности
                3. По году выпуска
                0. Назад
                
                Введите целое число:
                """);
    }
}