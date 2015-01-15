package sudoku.pelilogiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RuutuTest {
    
    @Test
    public void ruudunArvoEiVoiOllaNegatiivinen() {
        Ruutu r = new Ruutu();
        r.setArvo(-1);
        assertEquals(0, r.getArvo());
    }
    
    @Test
    public void ruudunArvoEiVoiOllaYliYhdeksan() {
        Ruutu r = new Ruutu();
        r.setArvo(10);
        assertEquals(0, r.getArvo());
        r.setArvo(9);
        assertEquals(9, r.getArvo());
    }
    
    @Test
    public void tyhjennaMetodiAsettaaNollan() {
        Ruutu r = new Ruutu();
        r.setArvo(5);
        r.tyhjenna();
        assertEquals(0, r.getArvo());
    }
    
    @Test
    public void ruutuOnVapaaKunArvoOnNolla() {
        Ruutu r = new Ruutu();
        assertEquals(true, r.vapaa());
        r.setArvo(2);
        assertEquals(false, r.vapaa());
    }
}
