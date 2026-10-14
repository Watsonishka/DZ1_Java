package org.example;
import java.util.ArrayList;
import java.util.List;

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

    public static void printCars(List<Car> cars) {
        if (cars == null || cars.isEmpty()) {
            System.out.println("Коллекция пуста. Воспользуйтесь возможностью добавления новых объектов");
            return;
        }

        for (var i = 0; i < cars.size(); i++) {
            System.out.println((i + 1) + ".Марка:" + cars.get(i).getModel()
                    + ", мощность:" + cars.get(i).getPower()
                    + ", год выпуска:" + cars.get(i).getManufacturedYear());
        }
    }
}
