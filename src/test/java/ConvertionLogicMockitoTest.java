import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ConverterPackage.ConvertionLogicClass;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class ConvertionLogicMockitoTest {

    @Test
    public void testConvertDocxToPdf_WithRealFiles() throws Exception {
        // Utworzenie tymczasowego pliku wejściowego (docx)
        File tempInputFile = File.createTempFile("testInput", ".docx");
        File tempOutputFile = File.createTempFile("testOutput", ".pdf");

        // Wypełnienie pliku .docx testowymi danymi
        try (FileOutputStream fos = new FileOutputStream(tempInputFile)) {
            XWPFDocument document = new XWPFDocument();
            XWPFParagraph paragraph = document.createParagraph();
            paragraph.createRun().setText("To jest przykładowy tekst w pliku docx.");
            document.write(fos);
        }

        // Wywołanie testowanej metody
        ConvertionLogicClass.convertDocxToPdf(tempInputFile.getAbsolutePath(), tempOutputFile.getAbsolutePath());

        // Sprawdzenie, czy plik PDF został utworzony
        assertTrue(tempOutputFile.exists());
        assertTrue(tempOutputFile.length() > 0);

        // Usunięcie plików tymczasowych
        tempInputFile.delete();
        tempOutputFile.delete();
    }
}
