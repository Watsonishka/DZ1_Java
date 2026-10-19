package org.example.ui;
import org.example.model.Car;
import org.example.model.CarFormatter;
import org.example.model.CarRepository;
import org.example.model.CarValidator;
import org.example.collection.MyArrayList;

import java.util.Scanner;

public class ConsoleInput {

    public static void addCar(Scanner scanner) {
        while (true) {
            String model;
            while (true) {
                System.out.println("Введите марку машины. Вводите буквы, цифры и знаки.");
                model = scanner.nextLine().trim();

                if (!CarValidator.isValidModel(model)) {
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

                if (!CarValidator.isValidPower(power)) {
                    System.out.println("Ошибка: Мощность должна быть больше нуля! Попробуйте снова!");
                    continue;
                }
                break;
            }

            int manufacturedYear;
            System.out.println("Введите год выпуска машины (четыре цифры, начиная с 19):");
            manufacturedYear = getInt(scanner, 1900, 9999);

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
            if (!addMore) {
                break;
            }
        }
    }

    public static void addCarFromJSON (Scanner scanner) {
        System.out.println("Введите имя файла в папке ресурсов (например: car.json):");

        String fileName = scanner.nextLine().trim();
        if (fileName.isEmpty()) {
            System.out.println("Имя файла не может быть пустым.");
            return;
        }

        MyArrayList<Car> cars = CarRepository.getCarsFromFile(fileName);

        if (cars == null || cars.isEmpty()) {
            System.out.println("Файл пуст или содержит некорректные данные.");
            return;
        }

        MyArrayList<Car> validCars = new MyArrayList<>();
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            if (CarValidator.isValidCar(car)) {
                validCars.add(car);
            } else {
                System.out.println("Предупреждение: найден некорректный объект");
            }
        }

        if (validCars.isEmpty()) {
            System.out.println("В файле нет ни одного валидного объекта для добавления. Попробуйте изменить данные файла");
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
}
