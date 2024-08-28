package lab4;

import lab4.exceptions.FileReadException;
import lab7.Page;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Objects;

public class Lab4Page extends Page {
    private final String FILE_PATH = "src/lab4/files/";
    private final int FIELD_WIDTH = 50;
    private JPanel inputContentPanel;
    private JPanel translatePanel;
    private JPanel outputPanel;

    public Lab4Page() {
        super();
        setColor(Color.decode("#ef7e64").brighter());
        setBackground(COLOR);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(40, 20, 10, 80);
        c.anchor = GridBagConstraints.CENTER;
        c.gridy = 0;

        mainPanel.add(topPanel("Choose files for translation"), c);
        c.gridy++;

        c.insets = new Insets(5, 20, 10, 20);
        mainPanel.add(getChoosePanel(), c);
        c.gridy++;
        c.insets = new Insets(5, 20, 40, 20);
        mainPanel.add(getOutputPanel(), c);
    }
    private JPanel getChoosePanel() {
        JPanel choosePanel = new JPanel(new GridBagLayout());
        JPanel dropdownPanel = new JPanel(new GridBagLayout());

        dropdownPanel.setBackground(Color.white);
        choosePanel.setBackground(Color.white);

        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = 0;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(10, 15, 10, 15);
        c.ipadx = FIELD_WIDTH;

        JComboBox<String> dictFileDropdown = getDropdown(getFilesList("dictionary"),
                new Dimension(160, 52));
        JComboBox<String> inputFileDropdown = getDropdown(getFilesList("input"),
                new Dimension(160, 52));

        choosePanel.add(dropdownPanel, c);
        dropdownPanel.add(getLabel("dictionary file:", 6), c);
        c.gridx++;
        dropdownPanel.add(getLabel("input file:", 6), c);
        c.gridy++;
        c.gridx = 0;

        dropdownPanel.add(dictFileDropdown, c);
        c.gridx++;
        dropdownPanel.add(inputFileDropdown, c);

        c.gridy = 1;
        c.gridx = 0;
        c.anchor = GridBagConstraints.CENTER;

        c.ipadx = 1;
        JButton submitButton = getButton("translate");
        submitButton.addActionListener(e -> actionPerformed(dictFileDropdown, inputFileDropdown));
        choosePanel.add(submitButton, c);

        return choosePanel;
    }
    private JPanel getOutputPanel() {
        outputPanel = new JPanel(new GridBagLayout());
        inputContentPanel = new JPanel(new GridBagLayout());
        translatePanel = new JPanel(new GridBagLayout());

        translatePanel.setBackground(Color.white);
        inputContentPanel.setBackground(Color.white);
        outputPanel.setBackground(Color.white);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = FIELD_WIDTH;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(10, 20, 10, 20);

        outputPanel.add(getLabel("input file content", 6), c);
        c.gridx++;
        outputPanel.add(getLabel("translation", 6), c);
        c.gridy++;
        c.gridx= 0;

        outputPanel.add(logPanel(inputContentPanel, 210, 185), c);
        c.gridx++;

        outputPanel.add(logPanel(translatePanel, 210, 185), c);

        return outputPanel;
    }
    @SneakyThrows
    private void actionPerformed(JComboBox<String> dict, JComboBox<String> input) {
        inputContentPanel.removeAll();
        translatePanel.removeAll();

        String dictFile = FILE_PATH + "dictionary/" + dict.getSelectedItem();
        String inputFile = FILE_PATH + "input/" + input.getSelectedItem();

        getInputContent(inputFile);
        getTranslation(dictFile, inputFile);

        inputContentPanel.repaint();
        translatePanel.repaint();
        outputPanel.revalidate();
    }
    private String[] getFilesList(String direct){
        File directory = new File(FILE_PATH + direct);

        File[] files = directory.listFiles();
        String[] fileNames = new String[Objects.requireNonNull(files).length];

        for (int i = 0; i < files.length; i++) {
            fileNames[i] = files[i].getName();
        }

        return fileNames;
    }
    private void getInputContent(String filePath) throws FileReadException {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.gridy = 0;
        try (InputStream inputStream = new FileInputStream(filePath);
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {
             String line;
             while ((line = reader.readLine()) != null) {
                 inputContentPanel.add(getLabel(line, 4), c);
                 c.gridy++;
             }
        } catch (IOException e) {
            throw new FileReadException(filePath + ": cannot read file");
        }
    }
    private void getTranslation(String dictFile, String inputFile) {
        Translator translator = new Translator();
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.gridy = 0;
        c.ipadx = 1;
        try {
            translator.loadFromFile(dictFile);
            translator.translateFile(inputFile);

            List<StringBuilder> translatedList = translator.getTranslatedList();

            for (StringBuilder word : translatedList){
                translatePanel.add(getLabel(word + "", 4), c);
                c.gridy++;
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
