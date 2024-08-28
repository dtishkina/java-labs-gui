package lab4;

import lab4.exceptions.FileReadException;
import lab4.exceptions.InvalidFileFormatException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Translator {
    private final Map<String, String> dictionary = new HashMap<>();
    private final List<StringBuilder> translatedList = new ArrayList<>();
    public void loadFromFile(String filePath) throws FileReadException {
        try (InputStream inputStream = new FileInputStream(filePath);
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 2) {
                    String key = parts[0].trim().toLowerCase();
                    String value = parts[1].trim();
                    dictionary.put(key, value);
                }
            }
        } catch (IOException e) {
            throw new FileReadException(filePath + ": cannot read file");
        }
    }

    public void translateFile(String filePath) throws InvalidFileFormatException{
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                StringBuilder translates = new StringBuilder();

                for (String word : words) {
                    String translatedWord = findMaxMatchTranslation(word);
                    translates.append(translatedWord).append("  ");
                }
                translatedList.add(translates);
                System.out.println(translates);
            }
        } catch (IOException e) {
            throw new InvalidFileFormatException(filePath +
                    ": error writing to a file, invalid file format");
        }
    }

    public List<StringBuilder> getTranslatedList(){
        return translatedList;
    }

    private String findMaxMatchTranslation(String word) {
        String translation = dictionary.get(word);

        if (translation != null) {
            for (String key : dictionary.keySet()) {
                if (word.startsWith(key) && key.length() > translation.length()) {
                    translation = dictionary.get(key);
                }
            }
            return translation;
        } else {
            return word;
        }
    }
}