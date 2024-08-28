package lab7.customcomponent;

import javax.swing.*;
import java.awt.*;
public class CustomComboBoxRenderer extends DefaultListCellRenderer {
    private Font font = new Font("Inter", Font.PLAIN, 18);
    private int alignment = (int) CENTER_ALIGNMENT;

    public CustomComboBoxRenderer() {
    }
    public CustomComboBoxRenderer(Font font, int alignment) {
        this();
        this.font = font;
        this.alignment = alignment;
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        setFont(font);
        setHorizontalAlignment(alignment);

        return this;
    }
}