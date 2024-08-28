package lab7.customcomponent;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundPanel extends JPanel {
    private int radius = 60;
    public RoundPanel(){
        super();
    }
    public RoundPanel(int radius){
        this();
        this.radius = radius;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = 0;
        int y = 0;
        int width = getWidth() - 1;
        int height = getHeight() - 1;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(this.getParent().getBackground());
        g2.setStroke(new BasicStroke(30));
        g2.drawRoundRect(x, y, width, height, radius, radius);

        int thickness = 10;
        g2.setClip(new RoundRectangle2D.Double(x + (double) thickness / 2, y + (double) thickness / 2,
                width - thickness, height - thickness, radius, radius));
    }
}
