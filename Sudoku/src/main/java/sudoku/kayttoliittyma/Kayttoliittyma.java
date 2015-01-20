package sudoku.kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import sudoku.pelilogiikka.Pelialue;

public class Kayttoliittyma implements Runnable {

    private JFrame frame;

    public Kayttoliittyma() {

    }

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
