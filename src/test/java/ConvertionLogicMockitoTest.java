import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import ConverterPackage.ConvertionLogicClass;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.mockito.Mockito;

public class ConvertionLogicMockitoTest {

    @Test
    public void testConvertDocxToPdf_WithRealFiles() throws Exception {
        // Utworzenie tymczasowego pliku wejściowego (docx)
        // Lipa bo tak naprawde nie mockujemy
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
      /*  // Mockowanie klasy ConvertionLogicClass
        ConvertionLogicClass mockLogic = Mockito.mock(ConvertionLogicClass.class);

        // Symulacja metody convertDocxToPdf
        doNothing().when(mockLogic).convertDocxToPdf(anyString(), anyString());

        // Wywołanie metody i sprawdzenie, czy działa bez wyjątku
        assertDoesNotThrow(() -> mockLogic.convertDocxToPdf("mockInput.docx", "mockOutput.pdf"));

        // Weryfikacja, czy metoda została wywołana z odpowiednimi argumentami
        verify(mockLogic, times(1)).convertDocxToPdf("mockInput.docx", "mockOutput.pdf");*/
    }
}
