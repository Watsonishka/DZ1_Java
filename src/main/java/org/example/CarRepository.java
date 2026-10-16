package org.example;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CarRepository {

    private static final List<Car> cars = new ArrayList<>();

    public static List<Car> getAll() {return cars;}

    public static void addCar(Car car){
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
        return cars.stream()
                .sorted(Comparator.comparing(Car::getModel))
                .toList();
    }

}
