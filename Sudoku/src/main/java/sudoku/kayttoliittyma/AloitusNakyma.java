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
        container.add(lisaaAloitusNapit());
    }

    private JPanel lisaaAloitusNapit() {
        JPanel panel = new JPanel();
        JButton helppo = new JButton("Helppo");
        panel.add(helppo);
        JButton keskitaso = new JButton("Keskitaso");
        panel.add(keskitaso);
        JButton vaikea = new JButton("Vaikea");
        panel.add(vaikea);
        helppo.addActionListener(new AloitusnakymanKuuntelija(helppo, keskitaso, vaikea, kali));
        keskitaso.addActionListener(new AloitusnakymanKuuntelija(helppo, keskitaso, vaikea, kali));
        vaikea.addActionListener(new AloitusnakymanKuuntelija(helppo, keskitaso, vaikea, kali));
        return panel;
    }

}
