package sudoku.kayttoliittyma.kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import sudoku.kayttoliittyma.Kayttoliittyma;
import sudoku.kayttoliittyma.UusiPeliNakyma;

/**
 * AloitusnakymanKuuntelija vastaa aloitusnäkymän toiminnallisuudesta.
 */
public class AloitusnakymanKuuntelija implements ActionListener {

    private Kayttoliittyma kali;
    private JButton helppo;
    private JButton keskitaso;
    private JButton vaikea;

    public AloitusnakymanKuuntelija(JButton uusiPeli, JButton keskitaso, JButton vaikea, Kayttoliittyma kali) {
        this.kali = kali;
        this.helppo = uusiPeli;
        this.keskitaso = keskitaso;
        this.vaikea = vaikea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == helppo) {
            uusiPeli(1);
        } else if (source == keskitaso) {
            uusiPeli(2);
        } else if (source == vaikea) {
            uusiPeli(3);
        }
    }

    private void uusiPeli(int vaikeus) {
        UusiPeliNakyma uusipeli = new UusiPeliNakyma(kali, vaikeus);
        SwingUtilities.invokeLater(uusipeli);
    }

}
