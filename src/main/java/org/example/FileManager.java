package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class FileManager {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> MyArrayList<T> getFromFile(String filePath, Class<T> type) {
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("Файл не найден на диске: " + filePath);
            return new MyArrayList<>();
        }

        try (FileInputStream inputStream = new FileInputStream(file)) {
            List<T> standardList = MAPPER.readValue(
                    inputStream,
                    MAPPER.getTypeFactory()
                            .constructCollectionType(List.class, type)
            );

            MyArrayList<T> myArrayList = new MyArrayList<>();
            for (T item : standardList) {
                myArrayList.add(item);
            }
            return myArrayList;

        } catch (Exception e) {
            System.out.println("Ошибка при чтении или разборе файла (проверьте корректность данных в файле): " + e.getMessage());
            return new MyArrayList<>();
        }
    }
}