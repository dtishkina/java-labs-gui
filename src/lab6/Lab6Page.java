package lab6;

import lab7.Page;
import lab7.customcomponent.RoundButton;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.io.PrintStream;

public class Lab6Page extends Page {
    public JPanel contentPanel;
    JPanel outPanel;

    public Lab6Page(){
        super();
        setColor(Color.decode("#993333").brighter());
        setBackground(COLOR);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(40, 20, 20, 20);
        c.anchor = GridBagConstraints.CENTER;
        c.gridy = 0;
        c.gridx = 0;

        mainPanel.add(topPanel("Click start to run program"), c);
        c.gridy++;
        c.insets = new Insets(0, 20, 40, 20);

        RoundButton startButton = getButton("start");
        mainPanel.add(startButton, c);
        c.gridy++;

        startButton.addActionListener(e->{
            c.insets = new Insets(0, 20, 40, 20);
            getContentPanel();
            mainPanel.add(contentPanel, c);
            contentPanel.revalidate();

            AbstractProgram program = new AbstractProgram();
            Supervisor supervisor = new Supervisor(program);
            supervisor.start();

        });
    }

    private void getContentPanel() {
        contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.gridy = 0;
        c.gridx = 0;
        c.insets = new Insets(0, 20, 20, 20);

        contentPanel.setBackground(Color.white);
        contentPanel.add(getLabel("State changes:", 6), c);
        c.gridy++;

        outPanel = new JPanel(new GridBagLayout());
        outPanel.setBackground(Color.white);

        JTextArea consoleOutput = new JTextArea(10, 0);
        consoleOutput.setEditable(false);

        ConsoleOutputStream consoleOutputStream = new ConsoleOutputStream(consoleOutput);
        PrintStream printStream = new PrintStream(consoleOutputStream);

        System.setOut(printStream);
        System.setErr(printStream);

        outPanel.add(consoleOutput, c);
        c.gridy++;

        contentPanel.add(logPanel(outPanel, 340, 180), c);
    }
    private static class ConsoleOutputStream extends java.io.OutputStream {
        private final JTextArea textArea;
        private final Object lock = new Object();

        public ConsoleOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) {
            SwingUtilities.invokeLater(() -> {
                synchronized (lock) {
                    textArea.append(String.valueOf((char) b));
                    textArea.setCaretPosition(textArea.getDocument().getLength());
                    textArea.setFont(new Font("Manrope", Font.PLAIN, 15));

                    SimpleAttributeSet center = new SimpleAttributeSet();
                    StyleConstants.setAlignment(center, StyleConstants.ALIGN_LEFT);
                }
            });
        }
    }
}
