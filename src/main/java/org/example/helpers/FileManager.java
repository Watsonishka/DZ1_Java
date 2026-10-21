package org.example.helpers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.example.collection.MyArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileManager {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> MyArrayList<T> getFromFile(Path path, Class<T> type)
    {
        if (!Files.exists(path)) {
            System.out.println("Файл не найден по пути: " + path.toAbsolutePath());
            return new MyArrayList<>();
        }

        try {
            CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, type);
            List<T> standardList = objectMapper.readValue(path.toFile(), listType);
            MyArrayList<T> myArrayList = new MyArrayList<>();

            for (T item : standardList) {
                if (item != null) {
                    myArrayList.add(item);
                }
            }
            return myArrayList;

        }
        catch (IOException e) {
            System.out.println("Ошибка при чтении или разборе файла: " + e.getMessage());
            return new MyArrayList<>();
        }
    }
}