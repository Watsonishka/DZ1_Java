package org.example.helpers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.collection.MyArrayList;

import java.util.List;
import java.io.InputStream;

public class FileManager {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> MyArrayList<T> getFromFile(String resourceName, Class<T> type) {

        InputStream inputStream = FileManager.class.getClassLoader().getResourceAsStream(resourceName);

        if (inputStream == null) {
            return new MyArrayList<>();
        }

        try (InputStream is = inputStream) {
            List<T> standardList = MAPPER.readValue(
                    is,
                    MAPPER.getTypeFactory()
                            .constructCollectionType(List.class, type)
            );

            MyArrayList<T> myArrayList = new MyArrayList<>();

            for (int i = 0; i < standardList.size(); i++) {
                myArrayList.add(standardList.get(i));
            }

            return myArrayList;
        }
        catch (Exception e) {
            System.out.println("Ошибка при чтении или разборе файла: " + e.getMessage());
            return new MyArrayList<>();
        }
    }
}