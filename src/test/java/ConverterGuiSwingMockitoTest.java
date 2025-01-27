import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import ConverterPackage.FIleToConvertClass;
import ConverterPackage.ConverterGuiSwing;
import ConverterPackage.TypeFileClass;
import ConverterPackage.ConvertionLogicClass;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class ConverterGuiSwingMockitoTest {
    @Test
    public void testConvertFeature_WithMockedFIleToConvertClass() throws Exception {
        // Tworzenie mocka dla FIleToConvertClass
        FIleToConvertClass mockedFileToConvertClass =
                mock(FIleToConvertClass.class);

        when(mockedFileToConvertClass.
                getFilename()).
                thenReturn("MockedFile");


        // Tworzenie instancji GUI
        ConverterGuiSwing gui = new ConverterGuiSwing();

        // Ustawienie mocka w polu fIleToConvertClass za pomocą refleksji
        Field field = ConverterGuiSwing.class.getDeclaredField("fIleToConvertClass");
        field.setAccessible(true);
        field.set(gui, mockedFileToConvertClass);

        // Symulacja wpisania nazwy pliku w polu tekstowym
        JTextField titleField = (JTextField) TestUtils.getComponentByName(gui, "title");
        titleField.setText("MockedFile");

        // Symulacja wyzwolenia akcji w polu tekstowym (ActionListener)
        for (ActionListener listener : titleField.getActionListeners()) {
            listener.actionPerformed(new ActionEvent(titleField, ActionEvent.ACTION_PERFORMED, null));
        }

        // Symulacja wyboru typu pliku w JComboBox
        JComboBox<TypeFileClass.typeFile> fileTypeComboBox = (JComboBox<TypeFileClass.typeFile>) TestUtils.getComponentByName(gui, "convertFileTypeComboBox");
        fileTypeComboBox.setSelectedItem(TypeFileClass.typeFile.PDF);

        // Symulacja kliknięcia przycisku konwersji
        JButton convertButton = (JButton) TestUtils.getComponentByName(gui, "convertFUnctionButton");
        convertButton.doClick();

        // Weryfikacja, czy metody mocka zostały wywołane z poprawnymi argumentami
        verify(mockedFileToConvertClass).setFilename("MockedFile");
        verify(mockedFileToConvertClass).setTypeFileClass(TypeFileClass.typeFile.PDF);

    }

}
