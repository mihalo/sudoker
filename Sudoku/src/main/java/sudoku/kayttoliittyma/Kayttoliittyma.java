package sudoku.kayttoliittyma;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 * Käyttöliittymä luokka, joka käynnistää aloitusnäkymän.
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;

    @Override
    public void run() {
        this.frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.pack();
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setResizable(false);

        AloitusNakyma aln = new AloitusNakyma(this);
        aln.run();

    }

    public JFrame getFrame() {
        return frame;
    }

}
