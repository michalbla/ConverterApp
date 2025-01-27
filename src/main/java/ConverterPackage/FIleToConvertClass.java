package ConverterPackage;

import java.io.File;

public class FIleToConvertClass extends TypeFileClass{
    private String inputFilePath;
    private String directory = "./converted_files/";

    private String filename;
    private TypeFileClass.typeFile outputTypeFile;


    FIleToConvertClass(){
        this.outputTypeFile = null;
        //this.filename = "";
    }
/*    public void setOutputDirectory(String outputDirectory) {
        this.directory = outputDirectory;
        createOutputDirectory();
    }*/

    private void createOutputDirectory() {
        File outputDirectory = new File(directory);
        if (!outputDirectory.exists()) {
            if (outputDirectory.mkdirs()) {
                System.out.println("Utworzono katalog: " + outputDirectory);
            } else {
                System.out.println("Nie udało się utworzyć katalogu: " + outputDirectory);
            }
        }
    }

    public boolean doesFileExist() {
        File file = new File(getFullOutputFilePath());
        return file.exists();
    }

    public void ensureOutputDirectoryExists() {
        File Outdirectory = new File(directory);
        if (!Outdirectory.exists()) {
            Outdirectory.mkdirs();
        }
    }

    //getters & setters
    public String getInputFilePath(){
        return inputFilePath;
    }
    public void setInputFilePath(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }
    public String getFullOutputFilePath() {
        return directory + filename + "." + outputTypeFile.toString().toLowerCase();
    }

    public String getFilename() {
        return filename;
    }
    public String  setFilename(String filename) {
        this.filename = filename;
        return filename;
    }

    public void setTypeFileClass(TypeFileClass.typeFile typeFile) {
        this.outputTypeFile = typeFile;
    }



    public void convertAndSave(File file) {

        try {
            if (!file.exists() && file.createNewFile()) {
                System.out.println("Plik został utworzony: " + file.getAbsolutePath());
            } else {
                System.out.println("Plik już istnieje: " + file.getAbsolutePath());
            }
        } catch (Exception e) {
            System.out.println("Wystąpił błąd podczas tworzenia pliku: " + e.getMessage());
        }
    }
}
