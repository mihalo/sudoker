package sudoku.kayttoliittyma;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import sudoku.pelilogiikka.Pelialue;

public class SudokuAlue extends JPanel {

    Pelialue pelialue;

    public SudokuAlue(Pelialue pelialue) {
        this.pelialue = pelialue;

        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                add(osaRuudukko(i, j));
            }
        }

    }

    public JPanel osaRuudukko(int x, int y) {
        setLayout(new GridLayout(3, 3, 2, 2));
        JPanel panel = new JPanel(new GridLayout(3, 3, 2, 2));
        panel.setBorder(new CompoundBorder(new LineBorder(Color.DARK_GRAY, 2), new EmptyBorder(2, 2, 2, 2)));

        ArrayDeque<Integer> numerot = pelialue.numerotOsaruudukosta(x, y);
        for (int i = 0; i < 9; i++) {
            final JButton b = new JButton("" + numerot.pollFirst());
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    popupValikko().show(b, b.getWidth() / 2, b.getHeight() / 2);
                }
            });
            panel.add(b);
        }

        return panel;
    }
    
    private JPopupMenu popupValikko() {
        final JPopupMenu valikko = new JPopupMenu("Menu");

        JPanel panel = new JPanel(new GridLayout(3, 3, 2, 2));
        panel.add(new JButton("1"));
        panel.add(new JButton("2"));
        panel.add(new JButton("3"));
        panel.add(new JButton("4"));
        panel.add(new JButton("5"));
        panel.add(new JButton("6"));
        panel.add(new JButton("7"));
        panel.add(new JButton("8"));
        panel.add(new JButton("9"));
        valikko.add(panel);

        return valikko;
    }


}
