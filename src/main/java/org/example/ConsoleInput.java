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

                if (!isValidModel(model)) {
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

                if (!isValidPower(power)) {
                    System.out.println("Ошибка: Мощность должна быть больше нуля! Попробуйте снова!");
                    continue;
                }
                break;
            }

            int manufacturedYear;
            while (true) {
                System.out.println("Введите год выпуска машины (четыре цифры, например 1999):");
                try {
                    manufacturedYear = Integer.parseInt(scanner.nextLine().trim());
                }
                catch (NumberFormatException e) {
                    System.out.println("Ошибка: введено не число! Попробуйте снова!");
                    continue;
                }

                if (!isValidYear(manufacturedYear)) {
                    System.out.println("Ошибка: год должен быть в диапазоне 1900–9999. Попробуйте снова!");
                    continue;
                }
                break;
            }

            Car car = new Car(model, power, manufacturedYear);
            if (!isValidCar(car)) {
                System.out.println("Ошибка: созданный объект не прошёл общую валидацию. Попробуйте снова.");
                continue;
            }

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
            if (addMore == null || !addMore) {
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

        MyArrayList<Car> validCars = new MyArrayList<>();
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            if (isValidCar(car)) {
                validCars.add(car);
            } else {
                System.out.println("Предупреждение: пропущен некорректный объект -> " + CarFormatter.getCarDescriptions(car));
            }
        }

        if (validCars.isEmpty()) {
            System.out.println("В файле нет ни одного валидного объекта для добавления.");
            return;
        }

        System.out.println("Полученный результат (прошедший валидацию):");
        System.out.println(CarFormatter.getCarDescriptions(validCars));

        boolean confirmed = getConfirmation(scanner, "Подтверждаешь сохранение вышеуказанных объектов? Да/Нет");
        if (!confirmed) {
            System.out.println("Добавление отменено.");
            return;
        }

        CarRepository.addCars(validCars);
        System.out.println("Добавление объектов выполнено успешно!");
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

    private static boolean isValidCar(Car car) {
        if (car == null) return false;

        return isValidModel(car.getModel())
                && isValidPower(car.getPower())
                && isValidYear(car.getManufacturedYear());
    }

    private static boolean isValidModel(String model) {
        return model != null && !model.trim().isEmpty();
    }

    private static boolean isValidPower(double power) {
        return Double.isFinite(power) && power > 0;
    }

    private static boolean isValidYear(int year) {
        return year >= 1900 && year <= 9999;
    }
}
