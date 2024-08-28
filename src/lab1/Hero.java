package lab1;

import java.awt.*;

public class Hero {
    private Move move;

    private Point pos;

    public Hero(){
        this.pos = new Point(0, 0);
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public void move(Point point) {
        this.move.move(this.pos, point);
        this.pos = point;
    }
}
