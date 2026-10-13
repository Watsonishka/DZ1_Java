package org.example;
import java.util.ArrayList;
import java.util.List;

public class CarRepository {
    private static final List<Car> cars = new ArrayList<>();

    public static List<Car> GetAll() {
        return cars;
    }

    public void addCar(Car car){
        cars.add(car);
    }
}
