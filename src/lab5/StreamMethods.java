package lab5;

import java.util.*;
import java.util.stream.Collectors;

public class StreamMethods {
    public static int getAverage(List<Integer> numbers){
        if(numbers.isEmpty()){
            return 0;
        }
        int result = numbers.stream()
                .reduce((Integer::sum))
                .orElse(0);
        return result / numbers.size();
    }

    public static List<String> toUpperCase(List<String> str){
        return str.stream()
                .map(i -> "_new_" + i.toUpperCase())
                .collect(Collectors.toList());
    }

    public static List<Integer> squareSet(List<Integer> numbers){
        return numbers.stream()
                .distinct()
                .map(i -> i*i)
                .collect(Collectors.toList());
    }

    public static List<String> sinceCurrentLiteral(List<String> str, Character literal){
        return str.stream()
                .filter(s -> literal == s.charAt(0))
                .sorted()
                .collect(Collectors.toList());
    }

    public static <T> T getLast(Collection<T> collection){
        return collection.stream()
                .reduce((x, y) -> y)
                .orElseThrow();
    }

    public static Integer getEvenList(List<Integer> evenList){
        return evenList.stream()
                .filter(n -> n % 2 == 0)
                .reduce((Integer::sum))
                .orElse(0);
    }

    public static Map<Character, String> getMap(List<String> list){
        return list.stream()
                .collect(Collectors.toMap(
                        s -> s.charAt(0), s -> s.substring(1)
                ));
    }
}
