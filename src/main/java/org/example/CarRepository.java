package org.example;
import java.util.Comparator;
import java.util.function.Predicate;

public class CarRepository {

    private static final MyArrayList<Car> cars = new MyArrayList<>();

    public static MyArrayList<Car> getAll() {
        MyArrayList<Car> copy = new MyArrayList<>();

        for (int i = 0; i < cars.size(); i++) {
            copy.add(cars.get(i));
        }
        return copy;
    }

    public static void addCar(Car car){
        if (car != null) {
            cars.add(car);
        }
    }

    public static void addCars(MyArrayList<Car> newCars) {
        if (newCars == null) {
            return;
        }

        for (int i = 0; i < newCars.size(); i++) {
            if (newCars.get(i) != null) {
                cars.add(newCars.get(i));
            }
        }
    }

    public static MyArrayList<Car> getCarsFromFile(String resourceName) {
        return FileManager.getFromFile(resourceName, Car.class);
    }

    public static MyArrayList<Car> filter(Predicate<Car> predicate) {
        if (predicate == null) {
            throw new IllegalArgumentException("Критерий фильтрации не задан");
        }

        MyArrayList<Car> result = new MyArrayList<>();
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);

            if (predicate.test(car)) {
                result.add(car);
            }
        }
        return result;
    }

    public static MyArrayList<Car> sort(SortAlgorithm<Car> algorithm, Comparator<Car> comparator) {
        return algorithm.sort(cars, comparator);
    }
}
