package org.example;
import java.util.List;
import java.util.Scanner;

public class ConsoleInput {

    public static void AddCar(Scanner scanner) {
        var cars = CarRepository.GetAll();

        while (true) {
            String model;
            while (true) {
                System.out.println("Введите марку машины. Вводите буквы, цифры и знаки.");
                model = scanner.nextLine().trim();
                if (model.isEmpty()) {
                    System.out.println("Ошибка: марка не может быть пустой. Попробуйте снова!");
                    continue;
                }
                break;
            }

            double power;
            while (true) {
                System.out.println("Введите мощность машины. Вводите только числа (можно дробные):");
                try {
                    power = Double.parseDouble(scanner.nextLine().replace(',', '.'));
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: введено не число! Попробуйте снова!");
                    continue;
                }
                if (power <= 0) {
                    System.out.println("Ошибка: Мощность не может быть отрицательной!. Попробуйте снова!");
                    continue;
                }
                break;
            }

            int manufacturedYear;
            while (true) {
                System.out.println("Введите год выпуска машины (четыре цифры, например 1999):");
                try {
                    manufacturedYear = Integer.parseInt(scanner.nextLine());
                }
                catch (NumberFormatException e) {
                    System.out.println("Ошибка: введено не число! Попробуйте снова!");
                    continue;
                }
                if (manufacturedYear < 1000 || manufacturedYear > 9999) {
                    System.out.println("Ошибка: год должен быть четырёхзначным (1000–9999). Попробуйте снова!");
                    continue;
                }
                break;
            }

            var car = new Car(model, power, manufacturedYear);
            var confirmed = getConfirmation(scanner,
                    "Подтверждаете добавление машины: модель " + car.getModel()
                            + " мощность двигателя: " + car.getPower()
                            + " год выпуска: " + car.getManufacturedYear() + "? Да/Нет");

            if (confirmed == null) {
                System.out.println("Ошибка: введите 'Да' или 'Нет'.");
                continue;
            }
            if (!confirmed) {
                System.out.println("Добавление отменено.");

                var retry = getConfirmation(scanner, "Хотите добавить машину? Да/Нет");
                if (retry) {
                    continue;
                }
                else {
                    break;
                }
            }

            cars.add(car);
            System.out.println("Машина добавлена.");

            var addMore = getConfirmation(scanner, "Хотите добавить ещё? Да/Нет");
            if (addMore == null) {
                System.out.println("Ошибка: введите 'Да' или 'Нет'. Считаем, что 'Нет'.");
                break;
            }
            if (!addMore) {
                break;
            }
        }
    }

    public static void AddCarFromJSON(Scanner scanner) {
        System.out.print("Введите имя файла (например: car.json): ");
        var fileName = scanner.nextLine().trim();
        var cars = CarRepository.getCarsFromFile("/" + fileName);

        if (cars == null || cars.isEmpty()) {
            System.out.println("Файл пуст или не найден.");
            return;
        }

        System.out.println("Полученный результат:");
        var carToConfirm = CarRepository.getCarDescriptions(cars);
        System.out.println(carToConfirm);

        while (true) {
            var confirmed = getConfirmation(scanner, "Подтверждаешь сохранение вышеуказанных объектов? Да/Нет");
            if (confirmed == null) {
                System.out.println("Ошибка: введите 'Да' или 'Нет'.");
                continue;
            }
            if (!confirmed) {
                System.out.println("Добавление отменено.");
                return;
            }
            CarRepository.addCars(cars);
            System.out.println("Добавление объектов выполнено успешно!");
            break;
        }
    }

    static Boolean getConfirmation(Scanner scanner, String question) {
        while (true) {
            System.out.println(question);
            var answer = scanner.nextLine().trim().toLowerCase();
            if (answer.equals("да") || answer.equals("yes")) {
                return true;
            }
            if (answer.equals("нет") || answer.equals("no")) {
                return false;
            }
            System.out.println("Ошибка: введите 'Да' или 'Нет'.");
        }
    }
}
