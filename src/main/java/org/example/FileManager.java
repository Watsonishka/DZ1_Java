package org.example;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class FileManager {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<Car> readCarsFromResource(String resourceName) {
        try (InputStream in = FileManager.class.getResourceAsStream(resourceName)) {
            if (in == null) {
                return List.of();
            }
            return mapper.readValue(in, new TypeReference<List<Car>>() {});
        }
        catch (IOException e) {
            System.out.println("Ошибка при чтении: " + e.getMessage());
            return List.of();
        }
    }
}
