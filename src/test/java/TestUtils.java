import ConverterPackage.ConverterGuiSwing;

import java.awt.Component;
import java.awt.Container;

public class TestUtils {

    /**
     * Rekurencyjne wyszukiwanie komponentu w hierarchii GUI po nazwie.
     *
     * @param container Kontener główny (np. JFrame, JPanel).
     * @param name      Nazwa komponentu (ustawiona za pomocą setName).
     * @return Znaleziony komponent lub null, jeśli nie znaleziono.
     */
    public static Component getComponentByName(Container container, String name) {
        for (Component component : container.getComponents()) {
            if (name.equals(component.getName())) {
                return component;
            }
            if (component instanceof Container) {
                Component child = getComponentByName((Container) component, name);
                if (child != null) {
                    return child;
                }
            }
        }
        return null;
    }

    /**
     * Wypisuje hierarchię komponentów w kontenerze (pomocne przy debugowaniu GUI).
     *
     * @param container Kontener główny.
     * @param indent    Wcięcie dla czytelności hierarchii.
     */
    public static void printComponentHierarchy(Container container, String indent) {
        for (Component component : container.getComponents()) {
            System.out.println(indent + component.getClass().getName() + " (name=" + component.getName() + ")");
            if (component instanceof Container) {
                printComponentHierarchy((Container) component, indent + "  ");
            }
        }
    }
}
