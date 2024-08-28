package lab3;

import lab3.hierarchy.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Main {
    public static ArrayList<String> result;

    public void segregate(Collection<? extends Chordates> srcCollection,
                                 Collection<? super CommonHedgehog> collection1,
                                 Collection<? super Manul> collection2,
                                 Collection<? super Lynx> collection3) {
        for (Chordates animal: srcCollection) {
            if (animal instanceof CommonHedgehog){
                collection1.add((CommonHedgehog) animal);
            }
            if (animal instanceof Manul){
                collection2.add((Manul) animal);
            }
            if (animal instanceof Lynx){
                collection3.add((Lynx) animal);
            }
        }
    }

    public void test0(){
        Collection<Mammals> src = Arrays.asList(
                new CommonHedgehog(),
                new Manul(),
                new Lynx(),
                new CommonHedgehog()
        );

        Collection<Hedgehogs> c1 = new ArrayList<>();
        Collection<Cats> c2 = new ArrayList<>();
        Collection<Predatory> c3 = new ArrayList<>();

        segregate(src, c1, c2, c3);
        getResult(c1, c2, c3);
    }

    public void test1(){
        Collection<Predatory> src = Arrays.asList(
                new Lynx(),
                new Manul(),
                new Lynx(),
                new Lynx()
        );

        Collection<Chordates> c1 = new ArrayList<>();
        Collection<Manul> c2 = new ArrayList<>();
        Collection<Cats> c3 = new ArrayList<>();

        segregate(src, c1, c2, c3);
        getResult(c1, c2, c3);
    }

    public void test2(){
        Collection<Hedgehogs> src = Arrays.asList(
                new CommonHedgehog(),
                new CommonHedgehog(),
                new CommonHedgehog(),
                new CommonHedgehog(),
                new CommonHedgehog(),
                new CommonHedgehog(),
                new CommonHedgehog()
        );

        Collection<Insectivores> c1 = new ArrayList<>();
        Collection<Predatory> c2 = new ArrayList<>();
        Collection<Predatory> c3 = new ArrayList<>();

        segregate(src, c1, c2, c3);
        getResult(c1, c2, c3);
    }

    void getResult(Collection<? super CommonHedgehog> c1,
                                       Collection<? super Manul> c2,
                                       Collection<? super Lynx> c3){
        result = new ArrayList<>();
        result.add("Type 1: " + c1.size());
        result.add("Type 2: " + c2.size());
        result.add("Type 3: " + c3.size());
        printResult(result);
    }

    private static void printResult( ArrayList<String> result){
        for (String item : result) {
            System.out.println(item);
        }
    }
}
