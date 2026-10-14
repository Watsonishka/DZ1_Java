package org.example;
import java.util.ArrayList;
import java.util.List;
import static org.example.ConsoleInput.extractNumericValue;
import static org.example.ConsoleInput.extractStringValue;
import static org.example.FileManager.readFromTextFile;

public class CarRepository {
    private static final List<Car> cars = new ArrayList<>();
    private String filePath = FileManager.class.getResource("/car.json").getPath();

    public static List<Car> GetAll() {
        return cars;
    }

    public void addCar(Car car){
        cars.add(car);
    }

    public static void addCarsFromFile (String filePath){
        var lines = readFromTextFile(filePath);

        String currentModel = "";
        double currentPower = 0;
        int currentYear = 0;

        for (String line : lines) {
            line = line.trim();

            if (line.contains("model")) {
                currentModel = extractStringValue(line);
            }
            else if (line.contains("power")) {
                currentPower = Double.parseDouble(extractNumericValue(line));
            }
            else if (line.contains("manufacturedYear") || line.contains("year")) {
                currentYear = Integer.parseInt(extractNumericValue(line));
            }
            cars.add(new Car(currentModel, currentPower, currentYear));
        }
    }
}
