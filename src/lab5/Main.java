package lab5;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<Integer> ints = new ArrayList<>();
        for(int i = 0; i < 5; ++i){
            ints.add(i + 10);
        }
        System.out.println("get average: " + StreamMethods.getAverage(ints));

        ArrayList<String> lowerCaseList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lowerCaseList.add(Character.toString((char) (i + (int) ('a'))));
        }
        System.out.println("convert to upper case: " +
                StreamMethods.toUpperCase(lowerCaseList));

        ints.add(12);
        ints.add(12);
        ints.add(14);
        System.out.println("square of set elements: " + StreamMethods.squareSet(ints));

        ArrayList<String> words =
                new ArrayList<>(Arrays.asList("apple", "bear", "amazing", "car", "cat", "apricot"));
        System.out.println("sorting list from from current literal: "+
                StreamMethods.sinceCurrentLiteral(words, 'a'));

        ArrayList<Integer> empty= new ArrayList<>();
        try{
            System.out.println("getting last element if collection is not empty: " +
                    StreamMethods.getLast(words));
            StreamMethods.getLast(empty);
        } catch (Exception e){
            System.out.println("getting last element if collection is empty: " + e.getMessage());
        }

        ArrayList<Integer> odd = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9));
        System.out.println("sum even numbers: " + StreamMethods.getEvenList(ints));
        System.out.println("sum odd numbers: " + StreamMethods.getEvenList(odd));

        ArrayList<String> words_ =
                new ArrayList<>(Arrays.asList("apple", "bear", "cat", "dodo"));
        System.out.println("convert list to map: " + StreamMethods.getMap(words_));
    }
}
