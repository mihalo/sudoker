package sudoku.kayttoliittyma.kuuntelijat;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayDeque;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import sudoku.kayttoliittyma.Kayttoliittyma;
import sudoku.kayttoliittyma.UusiPeliNakyma;
import sudoku.pelilogiikka.Pelialue;
import sudoku.pelilogiikka.Ruutu;
import sudoku.pelilogiikka.Tarkastaja;

public class Pelikenttakuuntelija implements MouseListener {

    private Kayttoliittyma kali;
    private JButton[][] ruudut;
    private Pelialue pelialue;
    private JPopupMenu popup;
    private Popupkuuntelija popupkuuntelija;
    private JButton tyhjenna;
    private JButton ratkaise;
    private JButton uusiPeli;
    private JButton vihje;
    private Tarkastaja tarkastaja = new Tarkastaja();

    public Pelikenttakuuntelija(Kayttoliittyma kali, JButton[][] ruudut,
            Pelialue pelialue, JButton tyhjenna, JButton ratkaise, JButton uusiPeli, JButton vihje) {
        this.kali = kali;
        this.ruudut = ruudut;
        this.pelialue = pelialue;
        this.tyhjenna = tyhjenna;
        this.ratkaise = ratkaise;
        this.uusiPeli = uusiPeli;
        this.vihje = vihje;
        luoPopupMenu();
    }

    public void luoPopupMenu() {
        popup = new JPopupMenu("Menu");
        popupkuuntelija = new Popupkuuntelija(kali, ruudut, pelialue, this);
        for (int i = 0; i < 10; i++) {
            JMenuItem b = new JMenuItem("" + i);
            popup.add(b);
            b.addActionListener(popupkuuntelija);
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        JButton nappi = (JButton) me.getSource();
        if (nappi.getText().equals("Tyhjennä")) {
            tyhjenna();
        } else if (nappi.getText().equals("Ratkaise")) {
            ratkaise();
        } else if (nappi.getText().equals("Uusi peli")) {
            aloitaUusiPeli();
        } else if (nappi.getText().equals("Vihje")) {
            vihje();
        } else if (nappi.isEnabled()) {
            popup.show((Component) me.getSource(), me.getX(), me.getY());
        }
    }

    public void vihje() {
        paivita(ruudut, pelialue, new ArrayList());
        int[] paikka = pelialue.vihje();
        ruudut[paikka[0]][paikka[1]].setEnabled(false);
        ruudut[paikka[0]][paikka[1]].setText("" + pelialue.getRatkaisu(paikka[0], paikka[1]));
        ruudut[paikka[0]][paikka[1]].setBackground(Color.GREEN);
        pelialue.asetaNumero(paikka[0], paikka[1], pelialue.getRatkaisu(paikka[0], paikka[1]));
    }

    public void ratkaise() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                ruudut[i][j].setEnabled(false);
                ruudut[i][j].setText("" + pelialue.getRatkaisu(i, j));
                pelialue.asetaNumero(i, j, pelialue.getRatkaisu(i, j));
            }
        }
        paivita(ruudut, pelialue, new ArrayList());
    }

    public void tyhjenna() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (ruudut[i][j].isEnabled()) {
                    ruudut[i][j].setText("  ");
                    pelialue.ruudukko[i][j].tyhjenna();
                }
            }
        }
        asetaValkoinenKaikille();
    }

    public void paivita(JButton[][] r, Pelialue p, ArrayList<Ruutu> vaarat) {
        this.ruudut = r;
        this.pelialue = p;
        if (!vaarat.isEmpty()) {
            for (Ruutu v : vaarat) {
                System.out.println(v);
                ruudut[v.getRivi()][v.getSarake()].setBackground(Color.red);
            }
        } else {
            asetaValkoinenKaikille();
        }

        if (tarkastaja.tarkasta(pelialue)) {
            peliPaattyi();
        }

    }

    private void peliPaattyi() {
        JOptionPane pane = new JOptionPane("Peli päättyi onnistuneesti!\nVoit joko sulkea pelin tai aloittaa uuden pelin.");
        Object[] options = new String[]{"Aloita uusi peli", "Sulje"};
        pane.setOptions(options);
        JDialog dialog = pane.createDialog(new JFrame(), "");
        dialog.show();
        if (pane.getValue() != null) {
            Object obj = pane.getValue().toString();
            if (obj.equals("Sulje")) {
                System.exit(0);
            } else {
                aloitaUusiPeli();
            }
        }
    }

    private void aloitaUusiPeli() {
        Kayttoliittyma r = new Kayttoliittyma();
        SwingUtilities.invokeLater(r);
    }

    private void asetaValkoinenKaikille() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                ruudut[i][j].setBackground(Color.WHITE);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

}
