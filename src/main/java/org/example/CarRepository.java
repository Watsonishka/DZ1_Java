package org.example;
import java.util.Comparator;

public class CarRepository {

    private static final MyArrayList<Car> cars = new MyArrayList<>();

    public static MyArrayList<Car> getAll() {return cars;}

    public static void addCar(Car car){
        cars.add(car);
    }

    public static void addCars(MyArrayList<Car> newCars) {
        for (int i = 0; i < newCars.size(); i++) {
            cars.add(newCars.get(i));
        }
    }

    public static MyArrayList<Car> getCarsFromFile(String resourceName) {
        return FileManager.getFromFile(resourceName, Car.class);
    }

    public static MyArrayList<Car> filterCarsByModel(String filter) {
        MyArrayList<Car> sorted = new MyArrayList<>();

        cars.stream()
                .sorted(Comparator.comparing(Car::getModel))
                .forEach(sorted::add);

        return sorted;
    }

    public static MyArrayList<Car> sort(SortAlgorithm<Car> algorithm, Comparator<Car> comparator) {
        MyArrayList<Car> copyCars = new MyArrayList<>();
        for (int i = 0; i < cars.size(); i++) {
            copyCars.add(cars.get(i));
        }
        return algorithm.sort(copyCars, comparator);
    }
}
