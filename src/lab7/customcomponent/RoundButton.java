package lab7.customcomponent;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoundButton extends JButton {
    private Shape shape;
    final int cornerRadius;
    private Color originalBackground;
    public RoundButton(String label, int cornerRadius) {
        super(label);

        setOpaque(false);

        setBackground(new Color(0, 0, 0, 20));

        this.cornerRadius = cornerRadius;

        this.setFocusPainted(false);
        this.setContentAreaFilled(false);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                originalBackground = getBackground();
                setBackground(new Color(0, 0, 0, 80));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(originalBackground);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.gray);
        } else {
            g.setColor(getBackground());
        }
        g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, cornerRadius, cornerRadius);
        super.paintComponent(g);
    }

    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        }
        return shape.contains(x, y);
    }
}