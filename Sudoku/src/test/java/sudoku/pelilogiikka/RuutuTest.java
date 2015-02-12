package sudoku.pelilogiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RuutuTest {
    
    @Test
    public void arvotAsettuvatOikeinLuodessa() {
        Ruutu r = new Ruutu(1,2,3);
        assertEquals(1, r.getArvo());
        assertEquals(2, r.getRivi());
        assertEquals(3, r.getSarake());
    }
        
    @Test
    public void tyhjennaMetodiToimii() {
        Ruutu r = new Ruutu(4,0,0);
        assertEquals(4, r.getArvo());
        r.tyhjenna();
        assertEquals(0, r.getArvo());
    }
    
    @Test
    public void vapaaMetodiToimii() {
        Ruutu r = new Ruutu(0,0,0);
        assertEquals(true, r.vapaa());
        r.setArvo(4);
        assertEquals(false, r.vapaa());
    }
    
    @Test
    public void setArvoToimiiOikein() {
        Ruutu r = new Ruutu(0,0,0);
        assertEquals(false, r.setArvo(10));
        assertEquals(true, r.setArvo(9));
    }
    
}
