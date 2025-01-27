import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;
import org.junit.jupiter.api.Test;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ConverterPackage.ConverterGuiSwing;

public class ConverterGuiSwingIntegrationTest {

    @Test
    public void testGuiComponentsBehavior() {
        // Tworzenie instancji GUI
        ConverterGuiSwing gui = new ConverterGuiSwing();
        TestUtils.
                printComponentHierarchy(gui.
                        getContentPane(), "");


        // Sprawdzanie domyślnego stanu komponentów
        JButton convertButton = (JButton) TestUtils.getComponentByName(gui, "convertFUnctionButton");
        JTextField titleField = (JTextField) TestUtils.getComponentByName(gui, "title");
        JComboBox<?> fileTypeComboBox = (JComboBox<?>) TestUtils.getComponentByName(gui, "convertFileTypeComboBox");

        assertNotNull(convertButton);
        assertNotNull(titleField);
        assertNotNull(fileTypeComboBox);

        // Symulacja wpisania tytułu pliku
        titleField.setText("TestFile");
        ActionEvent e = new ActionEvent(titleField, ActionEvent.ACTION_PERFORMED, null);
        for (ActionListener listener : titleField.getActionListeners()) {
            listener.actionPerformed(e);
        }

        // Symulacja wyboru typu pliku
        fileTypeComboBox.setSelectedIndex(1); // Zakładając, że mamy więcej niż jeden typ pliku

        // Sprawdzenie stanu przycisku konwersji
        assertTrue(convertButton.isEnabled(), "Przycisk konwersji powinien być aktywny");
    }
}

