package lab1;

import java.awt.*;

public class Walk implements Move{
    @Override
    public void move(Point x, Point y) {
        System.out.println("Hero walking from [" + (int) x.getX() + "," + (int) x.getY() + "] "
                + "to [" + (int) y.getX() + "," + (int) y.getY() + "] " );
    }
}
