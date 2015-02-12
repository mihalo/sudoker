package sudoku.kayttoliittyma;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import sudoku.kayttoliittyma.kuuntelijat.Pelikenttakuuntelija;
import sudoku.pelilogiikka.Pelialue;
import sudoku.pelilogiikka.Pohjageneraattori;

public class UusiPeliNakyma implements Runnable {

    private Kayttoliittyma kali;
    private JFrame frame;
    private Pelialue pelialue;
    private Pohjageneraattori pohjageneraattori;
    private JButton[][] ruudut;
    private JButton uusiPeli;
    private JButton tyhjenna;
    private JButton ratkaise;
    private int vaikeustaso;
    private JButton vihje;

    public UusiPeliNakyma(Kayttoliittyma kali, int vaikeustaso) {
        this.kali = kali;
        this.frame = kali.getFrame();
        this.vaikeustaso = vaikeustaso;
    }

    @Override
    public void run() {
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setPreferredSize(new Dimension(500, 500));
        luoKomponentit(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        luoPelialue();
        container.add(luoPelikentta());
    }

    private void luoPelialue() {
        pohjageneraattori = new Pohjageneraattori(vaikeustaso);
        this.pelialue = pohjageneraattori.getPelialue();
    }

    private Component luoPelikentta() {
        JPanel paneeli = new JPanel();
        ruudut = new JButton[9][9];
 
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (pelialue.getRuudukko()[i][j].vapaa()) {
                    ruudut[i][j] = new JButton("  ");
                } else {
                    ruudut[i][j] = new JButton("" + pelialue.getRuudukko()[i][j].getArvo());
                }
                ruudut[i][j].setBackground(Color.WHITE);
                if (!pelialue.getRuudukko()[i][j].vapaa()) {
                    ruudut[i][j].setEnabled(false);
                }
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j += 3) {
                paneeli.add(osaRivi(i, j));
            }
        }

        paneeli.add(luoValikko());
        lisaaKuuntelija(new Pelikenttakuuntelija(kali, ruudut, pelialue, tyhjenna, ratkaise, uusiPeli, vihje));

        return paneeli;
    }

    private void lisaaKuuntelija(Pelikenttakuuntelija kuuntelija) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                ruudut[i][j].addMouseListener(kuuntelija);
            }
        }
        this.tyhjenna.addMouseListener(kuuntelija);
        this.ratkaise.addMouseListener(kuuntelija);
        this.uusiPeli.addMouseListener(kuuntelija);
        this.vihje.addMouseListener(kuuntelija);
    }

    private int ylaReuna(int n) {
        if (n == 0 || n == 3 || n == 6) {
            return 0;
        }
        return -2;
    }

    private int alaReuna(int n) {
        if (n == 2 || n == 5 || n == 8) {
            return 0;
        }
        return -2;
    }

    private JPanel osaRivi(int n, int i) {
        JPanel panel = new JPanel(new GridLayout(1, 1, 2, 2));

        panel.setBorder(new CompoundBorder(new LineBorder(Color.DARK_GRAY, 2), new EmptyBorder(ylaReuna(n), 0, alaReuna(n), 0)));

        for (int j = i; j < i + 3; j++) {
            panel.add(ruudut[n][j]);
        }

        return panel;
    }

    private JPanel luoValikko() {
        JPanel panel = new JPanel(new GridLayout(1, 1, 2, 2));

        panel.setBorder(new EmptyBorder(4, 4, 4, 4));
        panel.setLayout(new GridBagLayout());

        uusiPeli = new JButton("Uusi peli");
        panel.add(uusiPeli);
        tyhjenna = new JButton("Tyhjennä");
        panel.add(tyhjenna);
        ratkaise = new JButton("Ratkaise");
        panel.add(ratkaise);
        vihje = new JButton("Vihje");
        panel.add(vihje);

        return panel;
    }

}
