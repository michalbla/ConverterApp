import ConverterPackage.ConvertionLogicClass;
import ConverterPackage.FIleToConvertClass;
import ConverterPackage.TypeFileClass;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;


public class ConvertionLogicTestClass {
    @Test
    void testConvertDocxToPdf_SavesWithCorrectName() throws FileNotFoundException {
        // Ścieżki do plików testowych
        String inputFilePath = "/home/michal/java_projects/ConverterApp/converted_files/test.docx";
        String outputFilePath = "/home/michal/java_projects/ConverterApp/converted_files/test_output.pdf";

        // Usuń istniejący plik wynikowy przed testem
        File outputFile = new File(outputFilePath);
        if (outputFile.exists()) {
            assertTrue(outputFile.delete());
        }

        // Wywołanie testowanej metody
        ConvertionLogicClass.convertDocxToPdf(inputFilePath, outputFilePath);

        // Sprawdzenie, czy plik został utworzony
        assertTrue(outputFile.exists(), "Plik wynikowy nie został utworzony!");
    }
}
