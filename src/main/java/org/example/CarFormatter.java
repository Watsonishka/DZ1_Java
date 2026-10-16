package org.example;
import java.util.List;
import java.util.stream.Collectors;

public class CarFormatter {

    public static String getCarDescriptions(List<Car> cars) {
        return cars.stream()
                .map(car -> car.getModel() + " — " + car.getPower() + " л.с., "
                        + car.getManufacturedYear() + " г.")
                .collect(Collectors.joining("\n"));
    }
}
