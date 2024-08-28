package lab7;

import javax.swing.*;
import java.awt.*;

public class MainSwing {
    public static JFrame frame;
    public MainSwing(){
        frame = new JFrame("GUI for labs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(1000, 300);
//        frame.setLocation(200, 150);
        frame.setPreferredSize(new Dimension(500, 620));
        frame.getContentPane().removeAll();
        frame.pack();
        frame.add(new MainPage());
        frame.setVisible(true);
        frame.revalidate();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainSwing::new);
    }
}