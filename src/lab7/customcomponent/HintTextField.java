package lab7.customcomponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class HintTextField extends JTextField implements FocusListener {
    private String hint;

    public HintTextField(String hint) {
        this.hint = hint;
        addFocusListener(this);
        setText(hint);
        setBorder(new RoundBorder(1, 10, Color.DARK_GRAY));
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (getText().equals(hint)) {
            setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (getText().isEmpty()) {
            setText(hint);
        }
    }

    public void setHint(String text) {
        this.hint = text;
        setText(text);
    }
}