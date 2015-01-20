package sudoku.kayttoliittyma.kuuntelijat;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import sudoku.kayttoliittyma.Kayttoliittyma;
import sudoku.pelilogiikka.Pelialue;

public class Popupkuuntelija implements ActionListener {

    private Kayttoliittyma kali;
    private JButton[][] ruudut;
    private Pelialue pelialue;
    private Pelikenttakuuntelija pelikenttakuuntelija;

    public Popupkuuntelija(Kayttoliittyma kali, JButton[][] ruudut, Pelialue pelialue, Pelikenttakuuntelija pelikenttakuuntelija) {
        this.kali = kali;
        this.ruudut = ruudut;
        this.pelialue = pelialue;
        this.pelikenttakuuntelija = pelikenttakuuntelija;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem c = (JMenuItem) e.getSource();
        JPopupMenu popup = (JPopupMenu) c.getParent();
        JButton button = (JButton) popup.getInvoker();
        
        int[] kordinaatit = kordinaatit(button);
        ruudut[kordinaatit[0]][kordinaatit[1]].setText(c.getText());
        pelialue.asetaNumero(kordinaatit[0], kordinaatit[1], Integer.parseInt(c.getText()));
        ArrayDeque<Integer> kohdat = pelialue.tarkistaSiirto(kordinaatit[0], kordinaatit[1], Integer.parseInt(c.getText()));
        pelikenttakuuntelija.paivita(ruudut, pelialue, !kohdat.isEmpty(), kohdat);

        
    }
    
    public int[] kordinaatit(JButton nappi) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (nappi == ruudut[i][j]) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}
