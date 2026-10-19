package org.example.model;
import org.example.collection.MyArrayList;

import java.util.stream.Collectors;

public class CarFormatter {

    public static String getCarDescriptions(MyArrayList<Car> cars) {
        return cars.stream()
                .map(car -> car.getModel() + " — " + car.getPower() + " л.с., "
                        + car.getManufacturedYear() + " г.")
                .collect(Collectors.joining("\n"));
    }
}

