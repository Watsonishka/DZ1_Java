package org.example;
import java.util.Comparator;
import java.util.Scanner;

public class ConsoleMainMenu {

    public static void getMenuChoice() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            show();
            int answer = ConsoleInput.getInt(scanner, 0, 5);

            if (answer == 1) {
                ConsoleInput.AddCar(scanner);
            }
            else if (answer == 2) {
                ConsoleInput.AddCarFromJSON(scanner);
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
            else {
                System.out.println("Введена неизвестная команда! Введите целое число от 0 до 5!");
            }
        }
    }

    private static void handleFilterMenu(Scanner scanner) {
        while (true) {
            showFilterMenu();
            int answer = ConsoleInput.getInt(scanner, 0, 1);

            if (answer == 1) {
                System.out.println("Введите название модели");
                var filteredCars = CarRepository.filterCarsByModel(scanner.nextLine());

                if (filteredCars == null || filteredCars.isEmpty()) {
                    System.out.println("По данному запросу ничего не найдено");
                } else {
                    System.out.println(CarFormatter.getCarDescriptions(filteredCars));
                }
            } else if (answer == 0) {
                return;
            } else {
                System.out.println("Введена неизвестная команда! Введите целое число от 0 до 1!");
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

    private static SortAlgorithm<Car> selectAlgorithm(int choice) {
        return (choice == 1) ? new BubbleSort<>() : new InsertionSort<>();
    }

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
            showSortComparatorMenu();
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
        } else {
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

    private static void showFilterMenu() {
        System.out.println("""
                
                ===== ФИЛЬТРАЦИЯ =====
                1. Фильтровать по модели                
                0. Назад
                
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
                1. Сортировка по возрастанию (ASC)
                2. Сортировка по убыванию (DESC)
                0. Назад
                
                Введите целое число:
                """);
    }

    private static void showSortComparatorMenu() {
        System.out.println("""
                
                ===== КРИТЕРИЙ СОРТИРОВКИ =====
                1. Сортировка по названию модели
                2. Сортировка по мощности
                3. Сортировка по году выпуска
                0. Назад
                
                Введите целое число:
                """);
    }
}