package ConverterPackage;

import javax.swing.*;
import java.io.File;

public class ConverterGuiSwing extends JFrame {
    private JPanel contentPane;
    private JButton convertFUnctionButton;
    private JTextField title;
    private JButton addFileFunctionButton;
    private JButton removeFileFunctionButton;
    private JComboBox convertFileTypeComboBox;

    JFileChooser fileChooser;

    public ConverterGuiSwing() {

        setContentPane(contentPane);
        setTitle("Converter");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        //buttons
        addFileFunctionButton.addActionListener(e -> {
            fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.showOpenDialog(this);
            File file = fileChooser.getSelectedFile();
            if (file != null) {
                addFileFunctionButton.setText(file.getName());
                removeFileFunctionButton.setVisible(true);
                addFileFunctionButton.setEnabled(false);
            }
        });
        removeFileFunctionButton.addActionListener(e -> {
            fileChooser = null;
            removeFileFunctionButton.setVisible(false);
            addFileFunctionButton.setEnabled(true);
            addFileFunctionButton.setText("Choose File");
        });
        convertFUnctionButton.addActionListener(e -> {

        });
        convertFileTypeComboBox.addActionListener(e -> {
            if(convertFileTypeComboBox.getSelectedIndex() == 0){
                convertFUnctionButton.setEnabled(false);
            }
        });
    }

    public static void main(String[] args) {
        ConverterGuiSwing frame = new ConverterGuiSwing();
    }
}
