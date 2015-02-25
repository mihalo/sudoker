package sudoku;

import javax.swing.SwingUtilities;
import sudoku.kayttoliittyma.Kayttoliittyma;

public class Main {

    public static void main(String[] args) {
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma();
        SwingUtilities.invokeLater(kayttoliittyma);
    }

}
