package lab2;

import lab7.Page;
import lab7.customcomponent.RoundBorder;
import lab7.customcomponent.RoundButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


public class Lab2Page extends Page {
    JPanel contentPanel;
    JPanel outPanel;
    public Lab2Page() {
        super();
        setColor(Color.decode("#ffcccc"));
        setBackground(COLOR);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(40, 20, 0, 20);
        c.anchor = GridBagConstraints.WEST;

        mainPanel.add(topPanel("Select the executing method"), c);

        contentPanel = new JPanel(new GridBagLayout());

        c.gridy = 0;
        c.insets = new Insets(5, 0, 5, 0);
        contentPanel.setBackground(Color.white);
        contentPanel.add(getDefaultPanel(), c);

        c.insets = new Insets(0, 5, 0, 5);
        c.gridy = 0;
        c.gridx = 1;

        outPanel = new JPanel(new GridBagLayout());
        outPanel.setBackground(Color.white);
        outPanel.setBorder(new RoundBorder(4, 20, COLOR));
        outPanel.setPreferredSize(new Dimension(500, 430));
        outPanel.add(getLabel("Output of information", 7), c);
        c.gridy++;
        outPanel.add(getLabel("about the method call", 7), c);

        c.gridy = 0;
        c.insets = new Insets(20, 40, 20, 5);
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

        String[] methodNames = {"1st method", "2nd method", "3rd method",
                "4th method", "5th method", "6th method", "7th method"};
        RoundButton[] buttons = new RoundButton[methodNames.length];

        for (int i = 0; i < methodNames.length; i++) {
            buttons[i] = getButton(methodNames[i]);
            defaultPanel.add(buttons[i], c);
            c.gridy++;
            buttons[i].addActionListener(this::actionPerformed);
        }
        return defaultPanel;
    }
    public void actionPerformed(ActionEvent e) {
        int command = Integer.parseInt(String.valueOf(e.getActionCommand().charAt(0)));
        GridBagConstraints c = new GridBagConstraints();
        resetConstraints(c);
        outPanel.removeAll();
        contentPanel.repaint();

        c.gridx = 0;
        c.gridy = 0;

        c.insets = new Insets(5, 0, 5, 0);
        c.anchor = GridBagConstraints.WEST;

        outPanel.add(getLabel(e.getActionCommand().split(" ")[0]
                + " method", 7), c);
        c.gridy++;

        try {
            MyClass call = new MyClass();
            Method method = call.getMethodByOrder(command);
            String modifier = Modifier.toString(method.getModifiers());
            MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);

            outPanel.add(getTemplatePanel("Access modifier: ", modifier), c);
            c.gridy++;
            outPanel.add(getTemplatePanel("Method is annotated: ",
                    annotation == null ? "no" : "yes"), c);
            c.gridy++;

            if (annotation != null && (modifier.equals("private") || modifier.equals("protected"))) {
                int repeat = annotation.repeat();

                outPanel.add(getTemplatePanel("Annotation parameter: ",
                        repeat + ""), c);
                c.gridy++;

                outPanel.add(getLabel("Method call log: ", 6), c);
                c.gridy++;

                method.setAccessible(true);

                JPanel logPanel = new JPanel(new GridBagLayout());
                logPanel.setBackground(Color.white);

                outPanel.add(logPanel(logPanel, 400, 150), c);

                c.gridy = 0;

                for (int j = 0; j < repeat; ++j) {
                    Class<?>[] type = method.getParameterTypes();
                    if (type.length == 0){
                        logPanel.add(getLabel(method.invoke(call).toString(), 5), c);
                    } else if (type[0] == String.class) {
                        String arg = Integer.toString(j + 1);
                        logPanel.add(getLabel(method.invoke(call, arg).toString(), 5), c);
                    } else if (type[0] == int.class || type[0] == double.class) {
                        Integer r = j + 1;
                        logPanel.add(getLabel(method.invoke(call, r).toString(), 5), c);
                    } else {
                        logPanel.add(getLabel(method.invoke(call,
                                Class.forName(type[0].getName()).getConstructor().newInstance()).toString(),
                                5), c);
                    }
                    c.gridy++;
                }
            } else {
                c.insets = new Insets(20, 0, 5, 0);
                outPanel.add(getLabel("---------------------------------", 6), c);
                c.gridy++;
                c.insets = new Insets(0, 0, 0, 0);
                outPanel.add(getLabel("Method should 't", 7), c);
                c.gridy++;
                outPanel.add(getLabel("be called", 7), c);
                c.gridy++;
                c.insets = new Insets(5, 0, 20, 0);
                outPanel.add(getLabel("---------------------------------", 6), c);
            }
            outPanel.revalidate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    private JPanel getTemplatePanel(String label, String parameter) {
        JPanel tmp = new JPanel(new GridBagLayout());
        tmp.setBackground(Color.white);
        GridBagConstraints c = new GridBagConstraints();

        tmp.add(getLabel(label, 6), c);
        c.gridx = 1;
        tmp.add(getLabel(parameter, 5), c);

        return tmp;
    }
}
