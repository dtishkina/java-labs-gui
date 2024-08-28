package lab7;

import lab7.customcomponent.*;

import javax.swing.*;
import java.awt.*;

public class Page extends JPanel {
    protected JPanel mainPanel;
    protected Color COLOR;
    public Page(){
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1000, 600));
        setColor(COLOR);
        setBackground(COLOR);
        mainPanel = new RoundPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(Color.white);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        add(mainPanel, c);
    }
    protected JLabel getLabel(String text, int fontsize){
        return new JLabel("<html><font face='Manrope'" +
                " size='" + fontsize + "' color='#181818'>"+ text +"</font>");
    }
    private RoundButton backButton(){
        return  new BackButton("back", 20);
    }
    protected void setColor(Color color){
        COLOR = color;
    }
    protected JPanel topPanel(String text){
        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        topPanel.setBackground(Color.WHITE);
        c.gridy=0;
        c.gridx=0;

        c.insets = new Insets(10, 20, 10, 15);
        topPanel.add(backButton(), c);

        c.gridx++;
        c.insets = new Insets(10, 15, 10, 20);
        topPanel.add(startLabel(text), c);
        return topPanel;
    }

    protected RoundButton getButton(String text){
        String buttonLabel = "<html><font face='Manrope' size='6'" +
                " color='#181818'>" + text + "</font>";
        RoundButton button = new RoundButton(buttonLabel, 20);
        button.setActionCommand(text);
        button.setPreferredSize(new Dimension(281, 52));
        button.setBackground(COLOR);
        button.setBorder(new RoundBorder(1, 20, COLOR));
        return button;
    }
    private JPanel startLabel(String text){
        JPanel startText = new JPanel(new GridBagLayout());
        startText.setBackground(Color.white);
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = 0;
        c.anchor = GridBagConstraints.WEST;

        startText.add(getLabel(text, 7), c);
        return startText;
    }

    protected void resetConstraints(GridBagConstraints c){
        c.gridy = 0;
        c.gridx = 0;
        c.insets = new Insets(0, 0, 0, 0);
    }

    protected JScrollPane logPanel(JPanel logPanel, int width, int height) {
        JScrollPane scrollPanel = new JScrollPane(logPanel);
        JScrollBar verticalScrollBar = scrollPanel.getVerticalScrollBar();
        JScrollBar horizontalScrollBar = scrollPanel.getHorizontalScrollBar();

        verticalScrollBar.setUI(new CustomScrollbarUI(COLOR));
        horizontalScrollBar.setUI(new CustomScrollbarUI(COLOR));

        scrollPanel.setBackground(Color.white);
        scrollPanel.setBorder(null);
        scrollPanel.setBorder(new RoundBorder(1, 20, COLOR));
        scrollPanel.setPreferredSize(new Dimension(width, height));
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        return scrollPanel;
    }

    protected JComboBox<String> getDropdown(String[] options, Dimension dimension){
        JComboBox<String> dropdown = new JComboBox<>(options);
        dropdown.setEditable(true);
        dropdown.setBackground(Color.white);
        dropdown.setBorder(new RoundBorder(1, 20, COLOR));
        dropdown.setPreferredSize(dimension);
        dropdown.setRenderer(new CustomComboBoxRenderer());
        return dropdown;
    }
}
