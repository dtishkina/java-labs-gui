package lab1;

import lab7.MainSwing;
import lab7.Page;
import lab7.customcomponent.*;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Lab1Page extends Page {
    JPanel logPanel;
    String selectedOption;
    Point point;
    String inputText = " ввод... ";
    HintTextField x_ = new HintTextField(inputText);
    HintTextField y_ = new HintTextField(inputText);
    public Lab1Page() {
        setColor(Color.decode("#c7c2ff"));
        setBackground(COLOR);
        MainSwing.frame.setMinimumSize(new Dimension(1000, 600));
        MainSwing.frame.setPreferredSize(new Dimension(1000, 600));
        setPreferredSize(new Dimension(1000, 600));

        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = 0;

        c.insets = new Insets(40, 20, 0, 20);
        mainPanel.add(topPanel("Choose one of the methods of movement: "), c);

        JPanel tmp = new JPanel(new GridBagLayout());
        tmp.setBackground(Color.white);

        c.insets = new Insets(0, 10, 0, 30);
        tmp.add(contentPanel(), c);

        c.gridx++;
        c.insets = new Insets(0, 30, 0, 10);
        tmp.add(logPanel(logPanel, 300, 280), c);

        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(40, 0, 80, 0);
        mainPanel.add(tmp, c);
    }
    private JPanel contentPanel() {
        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        contentPanel.setBackground(Color.white);

        String[] options = {"fly", "ride", "walk"};
        JComboBox<String> dropdown = getDropdown(options, new Dimension(300, 52));

        c.gridy++;
        c.insets = new Insets(12, 0, 20, 0);
        contentPanel.add(dropdown, c);

        c.gridy++;
        c.insets = new Insets(5, 0, 5, 0);
        contentPanel.add(movePanel(), c);

        c.gridy++;
        c.insets = new Insets(20, 0, 12, 0);
        RoundButton button = getButton("choose option");
        contentPanel.add(button, c);

        GridBagConstraints log = new GridBagConstraints();
        log.gridy = 0;
        log.gridx = 0;

        logPanel = new JPanel(new GridBagLayout());
        logPanel.setBackground(Color.white);
        logPanel.add(getLabel("The hero is at the point [0, 0]. ", 5), log);
        log.gridy++;

        button.addActionListener(e -> {
            selectedOption = (String) dropdown.getSelectedItem();
            if (!setPoint(x_, y_)) {
                x_.setForeground(Color.RED);
                y_.setForeground(Color.RED);
            } else {
                x_.setForeground(Color.DARK_GRAY);
                y_.setForeground(Color.DARK_GRAY);

                setPoint(x_, y_);

                Hero hero = new Hero();
                switch (Objects.requireNonNull(selectedOption)) {
                    case ("fly") -> {
                        hero.setMove(new Fly());
                        hero.move(point);
                        logPanel.add(getInfoLabel(selectedOption), log);
                        x_.setText(inputText);
                        y_.setText(inputText);
                        mainPanel.revalidate();
                        log.gridy++;
                    }
                    case ("walk") -> {
                        hero.setMove(new Walk());
                        hero.move(point);
                        logPanel.add(getInfoLabel(selectedOption), log);
                        x_.setText(inputText);
                        y_.setText(inputText);
                        mainPanel.revalidate();
                        log.gridy++;
                    }
                    case ("ride") -> {
                        hero.setMove(new Ride());
                        hero.move(point);
                        logPanel.add(getInfoLabel(selectedOption), log);
                        x_.setText(inputText);
                        y_.setText(inputText);
                        mainPanel.revalidate();
                        log.gridy++;
                    }
                }
            }
        });

        contentPanel.setBorder(new RoundBorder(1, 20, COLOR));
        return contentPanel;
    }
    private JPanel movePanel() {
        JPanel movePanel = new JPanel(new GridBagLayout());
        movePanel.setBackground(Color.white);
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;

        movePanel.add(getLabel("Enter the coordinates of the point:", 5), c);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(Color.white);

        c.gridy = 0;
        c.gridx = 0;

        x_.setFont(new Font("Inter", Font.PLAIN, 18));
        y_.setFont(new Font("Inter", Font.PLAIN, 18));

        c.insets = new Insets(12, 0, 10, 0);
        inputPanel.add(getLabel("x: ", 5), c);

        c.insets = new Insets(12, 10, 10, 20);
        c.gridx++;
        c.gridy = 0;
        inputPanel.add(x_, c);

        c.gridx++;
        c.insets = new Insets(12, 10, 10, 0);
        inputPanel.add(getLabel("y: ", 5), c);

        c.gridx++;
        c.insets = new Insets(12, 10, 10, 20);
        inputPanel.add(y_, c);

        c.gridy++;
        c.gridx = 0;
        c.insets = new Insets(0, 0, 0, 0);
        movePanel.add(inputPanel, c);
        return movePanel;
    }
    private boolean setPoint(HintTextField x_, HintTextField y_) throws NullPointerException {
        String xText = x_.getText();
        String yText = y_.getText();

        if (xText.matches("\\d+") && yText.matches("\\d+")) {
            int x = Integer.parseInt(x_.getText());
            int y = Integer.parseInt(y_.getText());

            point = new Point(x, y);

            return true;
        } else {
            return false;
        }
    }
    private JLabel getInfoLabel(String selectedOption) {
        String text = "Hero " + selectedOption + " to [" + point.x + ", " + point.y + "] ";
        return new JLabel("<html><font face='Manrope'" +
                " size='5' color='#181818'>" + text + "</font>");
    }
}
