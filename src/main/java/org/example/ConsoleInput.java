package org.example;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ConsoleInput {

    public static void AddCar(Scanner scanner) {

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
                }
                catch (NumberFormatException e) {
                    System.out.println("Ошибка: введено не число! Попробуйте снова!");
                    continue;
                }
                if (!Double.isFinite(power) || power <= 0) {
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
                if (manufacturedYear < 1900 || manufacturedYear > 9999) {
                    System.out.println("Ошибка: год должен быть четырёхзначным и быть в диапазоне 1900–9999. Попробуйте снова!");
                    continue;
                }
                break;
            }

            Car car = new Car(model, power, manufacturedYear);
            Boolean confirmed = getConfirmation(scanner,
                    "Подтверждаете добавление машины: модель " + car.getModel()
                            + " мощность двигателя: " + car.getPower()
                            + " год выпуска: " + car.getManufacturedYear() + "? Да/Нет");

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
            CarRepository.addCar(car);
            System.out.println("Машина добавлена.");

            Boolean addMore = getConfirmation(scanner, "Хотите добавить ещё? Да/Нет");
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
        System.out.println("""
            Введите путь к файлу (абсолютный или имя файла в папке проекта):
            Например: C:\\Users\\Username\\Desktop\\test.json
            Или: car.json
            """);

        String inputPath = scanner.nextLine().trim();
        if (inputPath.isEmpty()) {
            System.out.println("Путь не может быть пустым.");
            return;
        }

        Path path = Paths.get(inputPath);
        if (!path.isAbsolute() && !Files.exists(path)) {
            path = Paths.get(System.getProperty("user.dir"), inputPath);
        }
        if (!Files.exists(path)) {
            System.out.println("Файл не найден по указанному пути: " + path.toAbsolutePath());
            return;
        }

        MyArrayList<Car> cars = FileManager.getFromFile(path.toAbsolutePath().toString(), Car.class);
        if (cars == null || cars.isEmpty()) {
            System.out.println("Файл пуст или содержит некорректные данные.");
            return;
        }

        System.out.println("Полученный результат:");
        String carToConfirm = CarFormatter.getCarDescriptions(cars);
        System.out.println(carToConfirm);

        while (true) {
            Boolean confirmed = getConfirmation(scanner, "Подтверждаешь сохранение вышеуказанных объектов? Да/Нет");

            if (!confirmed) {
                System.out.println("Добавление отменено.");
                return;
            }
            CarRepository.addCars(cars);
            System.out.println("Добавление объектов выполнено успешно!");
            break;
        }
    }

    public static int getInt(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value < min || value > max) {
                    System.out.printf("Введите целое число от %d до %d!%n", min, max);
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.printf("Ошибка: введено не число! Введите целое число от %d до %d!%n", min, max);
            }
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
