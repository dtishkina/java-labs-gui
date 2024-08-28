package lab7.customcomponent;

import lab7.MainPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BackButton extends RoundButton{
    final private static int radius = 20;
    public BackButton(String l, int a){
        super(l, a);
        setText("↶");
        setBackground(Color.black);
        setForeground(Color.white);
        setSize(25, 10);
        setFont(new Font("Inter", Font.PLAIN, 24));
        setBorder(new RoundBorder(1, radius, Color.black));
        this.addActionListener(this::actionPerformed);
    }

    public void actionPerformed(ActionEvent e) {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.setMinimumSize(new Dimension(500, 600));
        frame.setPreferredSize(new Dimension(500, 600));
        frame.setContentPane(new MainPage());
        frame.pack();
        frame.revalidate();
    }
}
