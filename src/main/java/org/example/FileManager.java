package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class FileManager {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> List<T> getFromFile(String filePath, Class<T> type) {
        try {
            var file = new File(filePath);
            if (!file.exists()) {
                System.out.println("Файл не найден на диске: " + filePath);
                return List.of();
            }
            return mapper.readValue(file,mapper.getTypeFactory().constructCollectionType(List.class, type));
        }
        catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
            return List.of();
        }
    }
}
