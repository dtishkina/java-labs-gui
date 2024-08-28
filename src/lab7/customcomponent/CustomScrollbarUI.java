package lab7.customcomponent;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class CustomScrollbarUI extends BasicScrollBarUI {
    private static final int THUMB_WIDTH = 10;
    private static final int TRACK_WIDTH = 20;
    private static final int THUMB_CORNER_RADIUS = 8;
    private final Color thumbColor;

    public CustomScrollbarUI(Color thumbColor) {
        this.thumbColor = thumbColor;
    }
    @Override
    protected void configureScrollBarColors() {
        trackColor = Color.white;
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, java.awt.Rectangle thumbBounds) {
        if (thumbBounds.isEmpty() || !scrollbar.isEnabled()) {
            return;
        }

        g.setColor(thumbColor);
        int thumbX = thumbBounds.x;
        int thumbY = thumbBounds.y;
        int thumbHeight = thumbBounds.height;

        g.fillRoundRect(thumbX, thumbY, THUMB_WIDTH, thumbHeight, THUMB_CORNER_RADIUS, THUMB_CORNER_RADIUS);
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, java.awt.Rectangle trackBounds) {
        g.setColor(trackColor);
        int trackX = trackBounds.x;
        int trackY = trackBounds.y;
        int trackHeight = trackBounds.height;

        g.fillRoundRect(trackX, trackY, TRACK_WIDTH, trackHeight, THUMB_CORNER_RADIUS, THUMB_CORNER_RADIUS);
    }

    @Override
    protected Rectangle getTrackBounds() {
        if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            return new Rectangle(0, 0, THUMB_WIDTH, scrollbar.getHeight());
        } else {
            return new Rectangle(0, 0, scrollbar.getWidth(), THUMB_WIDTH);
        }
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return new JButton() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(0, 0);
            }
        };
    }

    protected JButton createIncreaseButton(int orientation) {
        return new JButton() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(0, 0);
            }
        };
    }
}

