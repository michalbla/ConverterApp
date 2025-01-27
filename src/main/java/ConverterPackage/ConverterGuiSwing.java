package ConverterPackage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ConverterGuiSwing extends JFrame {
    private final TypeFileClass typeFileClass = new TypeFileClass();
    private FIleToConvertClass fIleToConvertClass = new FIleToConvertClass();

    private JPanel contentPane;
    private JButton convertFUnctionButton;
    private JTextField title;
    private JButton addFileFunctionButton;
    private JButton removeFileFunctionButton;
    private JComboBox<TypeFileClass.typeFile> convertFileTypeComboBox;
    JFileChooser fileChooser;

    String fullPath;

    public ConverterGuiSwing() {

        setContentPane(contentPane);
        setTitle("Converter");
        setSize(380, 380);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JComboBoxInit();

        convertFUnctionButton.setName("convertFUnctionButton");
        title.setName("title");
        convertFileTypeComboBox.setName("convertFileTypeComboBox");
        addFileFunctionButton.setName("addFileFunctionButton");
        removeFileFunctionButton.setName("removeFileFunctionButton");

        setVisible(true);

        title.addActionListener(e -> {
            String fileName = title.getText(); // Pobranie tekstu z pola

            //USUN I DODAJ ABY POKAZAC ROZWIAZANIE PROBLEMU MOCKITO
            if (!fileName.isEmpty()) {
                fIleToConvertClass.setFilename(fileName); // Wywołanie metody
            }
            TypeFileClass.typeFile selectedTypeFile = typeFileClass.getType(); // Pobranie typu pliku

            if (selectedTypeFile == null || fileName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Wybierz typ pliku i wpisz nazwę pliku!", "Błąd", JOptionPane.ERROR_MESSAGE);
                return;
            }

            fIleToConvertClass.setFilename(fileName); // Ustawienie nazwy pliku
            fIleToConvertClass.setTypeFileClass(selectedTypeFile);

            if (fIleToConvertClass.doesFileExist()) {
                title.setForeground(Color.RED);
                JOptionPane.showMessageDialog(this, "Plik o tej nazwie i rozszerzeniu już istnieje!",
                        "Ostrzeżenie", JOptionPane.WARNING_MESSAGE);
            } else {
                title.setForeground(Color.BLACK);
            }
        });


        //buttons
        addFileFunctionButton.addActionListener(e -> {
            JFileChooserInit();
            setButtonUnable();
        });
        removeFileFunctionButton.addActionListener(e -> {
            setButtonEnabled();
        });
        convertFUnctionButton.setEnabled(false);
        convertFUnctionButton.addActionListener(e -> {
            if(convertEnable()){
                convertFeature();
            }
        });
    }

    private void convertFeature() {
        String inputFilePath = fIleToConvertClass.getInputFilePath();
        String fileName = title.getText();
        fIleToConvertClass.setFilename(fileName);
        String outputFilePath = fIleToConvertClass.getFullOutputFilePath();
        fIleToConvertClass.ensureOutputDirectoryExists();

/*        System.out.println("Nazwa pliku: " + fIleToConvertClass.getFilename());
        //System.out.println("Typ pliku: " + fIleToConvertClass.getTypeFileClass());
        System.out.println("Pełna ścieżka: " + fIleToConvertClass.getFullOutputFilePath());

        System.out.println("Wartość w polu tekstowym title: " + title.getText());*/
        try {
            // Wywołanie metody konwersji bez interfejsu
            ConvertionLogicClass.convertDocxToPdf(inputFilePath, outputFilePath);
            JOptionPane.showMessageDialog(this, "File was successful saved to path: " + outputFilePath,
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "Failed: " + e.getMessage(),
                    "Fail", JOptionPane.ERROR_MESSAGE);
        }
    }
    private boolean convertEnable() {
        return typeFileClass.getType() != null && fileChooser != null;
    }

    private void JComboBoxInit() {
        convertFileTypeComboBox.addItem(null);
        for (TypeFileClass.typeFile type : TypeFileClass.typeFile.values()) {
            convertFileTypeComboBox.addItem(type);
        }

        convertFileTypeComboBox.addActionListener(e -> {
            TypeFileClass.typeFile selectedTypeFile = (TypeFileClass.typeFile) convertFileTypeComboBox.getSelectedItem();
            typeFileClass.setType(selectedTypeFile);
            updateConvertButtonState();
            fIleToConvertClass.setTypeFileClass(selectedTypeFile);
        });
    }

    private void setButtonEnabled(){
        fileChooser = null;
        removeFileFunctionButton.setVisible(false);
        addFileFunctionButton.setEnabled(true);
        addFileFunctionButton.setText("Choose File");
        updateConvertButtonState();
    }
    private void setButtonUnable(){
        File selectedFile = fileChooser.getSelectedFile();
        if (selectedFile != null) {
            fIleToConvertClass.setInputFilePath(selectedFile.getAbsolutePath());
            addFileFunctionButton.setText(selectedFile.getName());
            removeFileFunctionButton.setVisible(true);
            addFileFunctionButton.setEnabled(false);
        }
        assert selectedFile != null;
        fullPath = selectedFile.getAbsolutePath();
        updateConvertButtonState();
    }
    private void JFileChooserInit() {
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.showOpenDialog(this);
    }
    private void updateConvertButtonState() {
        boolean isReadyToConvert = typeFileClass.getType() != null && !addFileFunctionButton.isEnabled() && !title.getText().trim().isEmpty();
        convertFUnctionButton.setEnabled(isReadyToConvert);
    }

    private String removeExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        return (dotIndex == -1) ? filename : filename.substring(0, dotIndex);
    }

    public static void main(String[] args) {
        ConverterGuiSwing frame = new ConverterGuiSwing();
    }
}
