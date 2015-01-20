
package sudoku.kayttoliittyma.kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import sudoku.kayttoliittyma.Kayttoliittyma;
import sudoku.kayttoliittyma.UusiPeliNakyma;

public class AloitusnakymanKuuntelija implements ActionListener {
    
    private Kayttoliittyma kali;
    private JButton uusiPeli;
    
    public AloitusnakymanKuuntelija(JButton uusiPeli, Kayttoliittyma kali) {
        this.kali = kali;
        this.uusiPeli = uusiPeli;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == uusiPeli) {
            uusiPeli();
        }
    }
    
    private void uusiPeli() {
        UusiPeliNakyma uusipeli = new UusiPeliNakyma(kali);
        SwingUtilities.invokeLater(uusipeli);
    }
    
}
