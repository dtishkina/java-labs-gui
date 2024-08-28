package lab5;

import lab7.Page;
import lab7.customcomponent.HintTextField;
import lab7.customcomponent.RoundBorder;
import lab7.customcomponent.RoundButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Lab5Page extends Page {
    JPanel contentPanel;
    ExecutePanel executePanel;
    public Lab5Page(){
        super();
        setColor(Color.decode("#3983f9"));
        setBackground(COLOR);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(40, 20, 40, 20);
        c.anchor = GridBagConstraints.WEST;
        c.gridy = 0;
        c.gridx = 0;

        c.ipadx = 150;
        mainPanel.add(topPanel("Choose method: "), c);

        contentPanel = new JPanel(new GridBagLayout());

        c.ipadx = 0;
        c.gridx = 0;
        c.gridy++;
        contentPanel();
        c.insets = new Insets(0, 40, 20, 40);
        mainPanel.add(contentPanel, c);
    }
    private void contentPanel(){
        contentPanel.setBackground(Color.white);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(0, 20, 20, 20);
        c.anchor = GridBagConstraints.CENTER;
        c.gridy = 0;
        c.gridx = 0;

        String[] options = {"get average", "to upper case", "square all unique",
            "with given letter", "get last element", "sum of even nums",
            "list to map"};
        JComboBox<String> dropdown = getDropdown(options, new Dimension(200, 52));

        c.ipady = 9;
        c.ipadx = 190;
        contentPanel.add(dropdown, c);
        c.gridy++;
        c.ipadx=0;
        c.ipady=0;
        executePanel = new ExecutePanel();
        c.insets = new Insets(10, 20, 40, 20);
        contentPanel.add(executePanel, c);
        dropdown.addActionListener(e -> actionPerformed(dropdown));
    }
    private void actionPerformed(JComboBox<String> dropdown){
        switch ((String) Objects.requireNonNull(dropdown.getSelectedItem())){
            case "get average" -> {
                executePanel.setInputText("enter integers separated by a space");
                executePanel.setMethod("getAverage");
            }
            case "sum of even nums" -> {
                executePanel.setInputText("enter integers separated by a space");
                executePanel.setMethod("squareSet");
            }
            case "square all unique" -> {
                executePanel.setInputText("enter integers separated by a space");
                executePanel.setMethod("getEvenList");
            }
            case "to upper case" -> {
                executePanel.setInputText("enter the text");
                executePanel.setMethod("toUpperCase");
            }
            case "with given letter" -> {
                executePanel.setInputText("enter the specified letter and text");
                executePanel.setMethod("sinceCurrentLiteral");
            }
            case "get last element" -> {
                executePanel.setInputText("enter the text");
                executePanel.setMethod("getLast");
            }
            case "list to map" -> {
                executePanel.setInputText("enter the text");
                executePanel.setMethod("getMap");
            }
        }
    }
    static class ExecutePanel extends Page{
        HintTextField input;
        JTextArea results;
        RoundButton submitButton;
        String method;
        public ExecutePanel(){
            setColor(Color.decode("#3983f9"));
            setBackground(Color.white);
            setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;
            c.gridy = 0;
            c.gridx = 0;

            input = new HintTextField("input...");
            input.setFont(new Font("Manrope", Font.PLAIN, 17));
            input.setForeground(Color.decode("#828282"));
            input.setAlignmentY(CENTER_ALIGNMENT);

            c.ipadx = 128;
            c.ipady = 40;
            c.insets = new Insets(0, 0, 20, 0);
            add(input, c);

            c.gridy++;
            c.ipady = 0;
            submitButton = getButton("execute");
            submitButton.addActionListener(e -> actionPerformed(method));
            add(submitButton, c);

            JPanel outputPanel = new JPanel(new GridBagLayout());
            outputPanel.setBackground(Color.white);

            results = new JTextArea("results");
            results.setAlignmentX(LEFT_ALIGNMENT);
            results.setFont(new Font("Manrope", Font.PLAIN, 20));
            results.setForeground(Color.decode("#828282").darker());
            results.setBorder(null);
            results.setEditable(false);

            outputPanel.add(results, c);
            outputPanel.setBorder(new RoundBorder(1, 20, COLOR));

            c.insets = new Insets(10, 0, 0, 0);
            c.gridy++;
            c.ipadx = 0;
            c.ipady = 0;
            add(outputPanel, c);
        }
        public void setInputText(String text){
            input.setHint(text);
        }
        private List<Integer> getInput() {
            String inputText = input.getText();
            String[] numbers = inputText.split(" ");

            List<Integer> integerList = new ArrayList<>();
            for (String number : numbers) {
                try {
                    int num = Integer.parseInt(number);
                    integerList.add(num);
                } catch (NumberFormatException e) {
                    input.setForeground(Color.red);
//                    results.setHint("incorrect input");
                    integerList.clear();
                }
            }
            return integerList;
        }
        private List<String> getInputData(){
            String inputText = input.getText();
            String[] symbols = inputText.split(" ");
            List<String> inputList = new ArrayList<>();
            Collections.addAll(inputList, symbols);
            return inputList;
        }
        public void setMethod(String method){
            this.method = method;
        }
        public void actionPerformed(String method) {
            input.setForeground(Color.decode("#828282"));
            switch (method) {
                case "getAverage" -> results.setText(String.valueOf(StreamMethods.getAverage(getInput())));
                case "squareSet" ->  results.setText(String.valueOf(StreamMethods.squareSet(getInput())));
                case "getEvenList" -> results.setText(String.valueOf(StreamMethods.getEvenList(getInput())));
                case "toUpperCase"-> results.setText(String.valueOf(StreamMethods.toUpperCase(getInputData())));
                case "getMap" -> results.setText(String.valueOf(StreamMethods.getMap(getInputData())));
                case "getLast" -> results.setText(StreamMethods.getLast(getInputData()));
                case "sinceCurrentLiteral" -> {
                        Character character = input.getText().charAt(0);
                        List<String> inputList = getInputData();
                        inputList.remove(inputList.get(0));
                        results.setText(String.valueOf(StreamMethods.sinceCurrentLiteral(inputList, character)));
                    }
                }
        }

    }
}
