package org.example;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CarRepository {

    private static final List<Car> cars = new ArrayList<>();

    public static List<Car> GetAll() {
        return cars;
    }

    public void addCar(Car car){
        cars.add(car);
    }

    public static void addCars(List<Car> newCars) {cars.addAll(newCars);}

    public static List<Car> getCarsFromFile(String resourceName) {
        return FileManager.getFromFile(resourceName, Car.class);
    }

    public static List<Car> filterCarsByModel(String filter) {
        var search = filter.trim().toLowerCase();
        return cars.stream()
                .filter(car -> car.getModel().toLowerCase().contains(search))
                .toList();
    }

    public static List<Car> sortByModel() {
        cars.sort(Comparator.comparing(Car::getModel));
        return cars;
    }

    public static String getCarDescriptions(List<Car> carsList) {
        return carsList.stream()
                .map(car -> car.getModel() + " — " + car.getPower() + " л.с., "
                        + car.getManufacturedYear() + " г.")
                .collect(Collectors.joining("\n"));
    }
}
