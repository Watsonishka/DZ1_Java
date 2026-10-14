package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public static List<String> readFromTextFile(String filePath) {
        List<String> fileLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileLines.add(line);
            }
        }
        catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return fileLines;
    }
}
