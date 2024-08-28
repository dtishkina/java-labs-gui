package lab1;

import java.awt.*;

class Ride implements Move {
    @Override
    public void move(Point x, Point y){
        System.out.println("Hero riding from [" + (int) x.getX() + "," + (int) x.getY() + "] "
                + "to [" + (int) y.getX() + "," + (int) y.getY() + "] " );
    }
}
