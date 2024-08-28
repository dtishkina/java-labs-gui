package lab1;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hero hero = new Hero();
        Scanner scanner = new Scanner(System.in);

        System.out.print("The hero is at the point [0, 0]\n" +
                "Enter one of the methods of movement (walk, fly, ride): ");

        String movementType = scanner.next();

        do{
            System.out.println("Enter the coordinates of the point you want to move to: \n");

            int x = 0;
            int y = 0;

            try {
                System.out.print("X: ");
                x = scanner.nextInt();
                System.out.print("Y: ");
                y = scanner.nextInt();
            } catch (InputMismatchException e){
                System.out.println(e.getMessage());
            }
            Point point = new Point(x, y);

            switch (movementType) {
                case ("fly") -> {
                    hero.setMove(new Fly());
                    hero.move(point);
                }
                case ("walk") -> {
                    hero.setMove(new Walk());
                    hero.move(point);
                }
                case ("ride") -> {
                    hero.setMove(new Ride());
                    hero.move(point);
                }
                default -> System.out.println("No such command");
            }
            System.out.print("Enter stop if u want to stop " +
                    "or choose the next method of movement:");
            movementType = scanner.next();
            if (movementType.equals("stop")){
                scanner.close();
                break;
            }
        } while(!movementType.isEmpty());
    }
}


