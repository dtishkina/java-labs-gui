package lab7;

import lab1.Lab1Page;
import lab2.Lab2Page;
import lab3.Lab3Page;
import lab4.Lab4Page;
import lab5.Lab5Page;
import lab6.Lab6Page;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class MainPage extends Page {
    public MainPage() {
        super();
        setColor(Color.decode("#c9dbed"));
        setBackground(COLOR);

        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = 0;
        c.insets = new Insets(5, 0, 10, 0);

        mainPanel.add(getLabel("Choose a lab", 7), c);
        mainPanel.setPreferredSize(new Dimension(405, 525));
        c.insets = new Insets(5, 0, 5, 0);

        ArrayList<JButton> buttons = new ArrayList<>();
        int n = 1;
        while (n < 7) {
            c.gridy++;
            JButton button = getButton(n + " lab");
            mainPanel.add(button, c);
            buttons.add(button);
            n++;
        }

        buttons.get(0).addActionListener(e -> actionPerformed(new Lab1Page(),
                new Dimension(1000, 600)));
        buttons.get(1).addActionListener(e-> actionPerformed(new Lab2Page(),
                new Dimension(1000, 760)));
        buttons.get(2).addActionListener(e-> actionPerformed(new Lab3Page(),
                new Dimension(760, 500)));
        buttons.get(3).addActionListener(e-> actionPerformed(new Lab4Page(),
                new Dimension(800, 730)));
        buttons.get(4).addActionListener(e-> actionPerformed(new Lab5Page(),
                new Dimension(640, 700)));
        buttons.get(5).addActionListener(e-> actionPerformed(new Lab6Page(),
                new Dimension(700, 400)));
    }

    public void actionPerformed(JPanel panel, Dimension size) {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(MainPage.this);
        frame.getContentPane().removeAll();
        frame.setMinimumSize(size);
        frame.setPreferredSize(size);
        frame.setContentPane(panel);
        frame.revalidate();
    }
}