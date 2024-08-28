package lab4;

import java.io.IOException;

public class Main {
    public static String dict = "src\\lab4\\files\\dictionary\\dictionary.txt";
    public static String inputFile = "src\\lab4\\files\\input\\input.txt";

    public static void main(String[] args) {
        Translator translator = new Translator();
        try {
            translator.loadFromFile(dict);
            translator.translateFile(inputFile);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
