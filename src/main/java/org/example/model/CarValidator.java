package org.example.model;

public class CarValidator {
    public static boolean isValidModel(String model) {
        return model != null && !model.isBlank();
    }

    public static boolean isValidPower(double power) {
        return Double.isFinite(power) && power > 0;
    }

    public static boolean isValidYear(int year) {
        return year >= 1900 && year <= 9999;
    }

    public static boolean isValidCar(Car car) {
        return car != null
                && isValidModel(car.getModel())
                && isValidPower(car.getPower())
                && isValidYear(car.getManufacturedYear());
    }
}
