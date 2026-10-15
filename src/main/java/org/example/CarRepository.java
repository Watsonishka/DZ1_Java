package org.example;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CarRepository {
    private static final List<Car> cars = new ArrayList<>();
    private String filePath = FileManager.class.getResource("/car.json").getPath();

    public static List<Car> GetAll() {
        return cars;
    }

    public void addCar(Car car){
        cars.add(car);
    }

    public static void addCars(List<Car> newCars) {cars.addAll(newCars);}

    public static List<Car> getCarsFromFile(String resourceName) {
        return FileManager.readCarsFromResource(resourceName);
    }

    public static List<Car> filterCarsByModel(String filter) {
        var search = filter.trim().toLowerCase();
        return cars.stream()
                .filter(car -> car.getModel().toLowerCase().contains(search))
                .toList();
    }

    public static List<Car> filterCarsByPower(double minPower) {
        return cars.stream()
                .filter(car -> car.getPower() >= minPower)
                .toList();
    }

    public static List<Car> filterCarsByYear(int year) {
        return cars.stream()
                .filter(car -> car.getManufacturedYear() == year)
                .toList();
    }

    public static String getCarDescriptions(List<Car> carsList) {
        return carsList.stream()
                .map(car -> car.getModel() + " — " + car.getPower() + " л.с., "
                        + car.getManufacturedYear() + " г.")
                .collect(Collectors.joining("\n"));
    }
}
