package sudoku.kayttoliittyma;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import sudoku.kayttoliittyma.kuuntelijat.AloitusnakymanKuuntelija;

public class AloitusNakyma implements Runnable {

    private JFrame frame;
    private Kayttoliittyma kali;

    public AloitusNakyma(Kayttoliittyma kali) {
        this.kali = kali;
        this.frame = kali.getFrame();
    }

    @Override
    public void run() {
        frame.getContentPane().removeAll();
        frame.repaint();;
        frame.setPreferredSize(new Dimension(500, 500));
        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    private void luoKomponentit(Container container) {
        container.setLayout(new GridLayout(2, 1));
        container.add(lisaaAloitusTeksti());
        container.add(lisaaAloitusNapit());
    }

    private JPanel lisaaAloitusTeksti() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        Font fontti = new Font("Arial", Font.BOLD, 42);
        JLabel sudoku = new JLabel("SUDOKU");
        sudoku.setFont(fontti);
        panel.add(sudoku);
        return panel;
    }

    private JPanel lisaaAloitusNapit() {
        JPanel panel = new JPanel();
        panel.add(new JButton("Helppo"));
        panel.add(new JButton("Keskitaso"));
        panel.add(new JButton("Vaikea"));
//                helppo.addActionListener(new AloitusnakymanKuuntelija(helppo, kali));
        return panel;
    }

}
