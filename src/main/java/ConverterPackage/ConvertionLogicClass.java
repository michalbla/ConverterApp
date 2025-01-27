package ConverterPackage;

import java.io.*;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class ConvertionLogicClass {

    public static void convertDocxToPdf(String inputFilePath, String fullFilePath) {
        // Walidacja wejścia
        if (inputFilePath == null || inputFilePath.isEmpty()) {
            throw new IllegalArgumentException("Input file path cannot be null or empty.");
        }
        if (fullFilePath == null || fullFilePath.isEmpty()) {
            throw new IllegalArgumentException("Output file path cannot be null or empty.");
        }

        // Próba odczytu pliku i konwersji
        try (FileInputStream fis = new FileInputStream(inputFilePath);
             FileOutputStream fos = new FileOutputStream(fullFilePath)) {

            convert(fis, fos);

        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException("Error processing file: " + e.getMessage(), e);
        }
    }

    public static void convert(InputStream inputStream, OutputStream outputStream) {
        if (inputStream == null) {
            throw new IllegalArgumentException("Input stream cannot be null.");
        }
        if (outputStream == null) {
            throw new IllegalArgumentException("Output stream cannot be null.");
        }

        try (XWPFDocument document = new XWPFDocument(inputStream)) {
            Document pdfDocument = new Document();

            try {
                PdfWriter.getInstance(pdfDocument, outputStream);
                pdfDocument.open();

                for (XWPFParagraph paragraph : document.getParagraphs()) {
                    pdfDocument.add(new Paragraph(paragraph.getText()));
                }
            } catch (DocumentException e) {
                throw new RuntimeException("Error creating PDF document: " + e.getMessage(), e);
            } finally {
                if (pdfDocument.isOpen()) {
                    pdfDocument.close();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Error reading DOCX document: " + e.getMessage(), e);
        }
    }
}
