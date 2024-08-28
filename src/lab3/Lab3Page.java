package lab3;

import lab7.Page;
import lab7.customcomponent.RoundBorder;
import lab7.customcomponent.RoundButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Lab3Page extends Page {
    JPanel contentPanel;
    JPanel outPanel;
    public Lab3Page(){
        super();
        setColor(Color.decode("#c6e3bb"));
        setBackground(COLOR);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(40, 20, 0, 20);
        c.anchor = GridBagConstraints.WEST;

        mainPanel.add(topPanel("Select the test number"), c);

        contentPanel = new JPanel(new GridBagLayout());

        c.gridy = 0;
        c.insets = new Insets(5, 0, 5, 0);
        contentPanel.setBackground(Color.white);
        contentPanel.add(getDefaultPanel(), c);

        c.insets = new Insets(20, 20, 20, 0);
        c.gridy = 0;
        c.gridx = 1;

        outPanel = new JPanel(new GridBagLayout());
        outPanel.setBackground(Color.white);
        outPanel.setBorder(new RoundBorder(2, 20, COLOR));
        outPanel.setPreferredSize(new Dimension(280, 180));
        outPanel.add(getLabel("Test's result output", 5), c);

        contentPanel.add(outPanel, c);

        c.gridy = 1;
        c.gridx = 0;
        c.insets = new Insets(20, 40, 40, 40);
        mainPanel.add(contentPanel, c);
    }

    private JPanel getDefaultPanel(){
        JPanel defaultPanel = new JPanel(new GridBagLayout());
        defaultPanel.setBackground(Color.WHITE);
        GridBagConstraints c = new GridBagConstraints();
        resetConstraints(c);
        c.insets = new Insets(5, 0, 5, 0);

        RoundButton button1 = getButton("test 1");
        RoundButton button2 = getButton("test 2");
        RoundButton button3 = getButton("test 3");

        defaultPanel.add(button1, c);
        c.gridy++;
        defaultPanel.add(button2, c);
        c.gridy++;
        defaultPanel.add(button3, c);

        button1.addActionListener(this::actionPerformed);
        button2.addActionListener(this::actionPerformed);
        button3.addActionListener(this::actionPerformed);

        return defaultPanel;
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        GridBagConstraints c = new GridBagConstraints();
        resetConstraints(c);
        Main segr = new Main();
        outPanel.removeAll();
        contentPanel.repaint();
        switch (command){
            case "test 1"-> {
                segr.test0();
                addResults(c);
            }
            case "test 2" -> {
                segr.test1();
                addResults(c);
            }
            case "test 3" -> {
                segr.test2();
                addResults(c);
            }

        }
    }

    private void addResults(GridBagConstraints c){
        for (String type : Main.result){
            c.insets = new Insets(5, 0, 5, 0);
            outPanel.add(getLabel(type, 6), c);
            c.gridy++;
        }
        outPanel.revalidate();
    }
}
