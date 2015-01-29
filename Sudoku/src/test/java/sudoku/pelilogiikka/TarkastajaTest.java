package sudoku.pelilogiikka;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TarkastajaTest {
    int[] testiSudoku = {
        0, 0, 0, 0, 0, 0, 0, 0, 4,
        0, 7, 0, 0, 0, 1, 2, 8, 0,
        2, 0, 0, 0, 5, 9, 0, 7, 0,
        0, 0, 0, 5, 9, 7, 8, 0, 3,
        0, 0, 2, 0, 0, 0, 1, 0, 0,
        5, 0, 3, 1, 2, 4, 0, 0, 0,
        0, 4, 0, 7, 3, 0, 0, 0, 2,
        0, 5, 8, 9, 0, 0, 0, 6, 0,
        1, 0, 0, 0, 0, 0, 0, 0, 0};

    int[] testiSudokuRatkaistu = {
        8, 1, 9, 2, 7, 6, 3, 5, 4,
        6, 7, 5, 3, 4, 1, 2, 8, 9,
        2, 3, 4, 8, 5, 9, 6, 7, 1,
        4, 6, 1, 5, 9, 7, 8, 2, 3,
        7, 9, 2, 6, 8, 3, 1, 4, 5,
        5, 8, 3, 1, 2, 4, 7, 9, 6,
        9, 4, 6, 7, 3, 8, 5, 1, 2,
        3, 5, 8, 9, 1, 2, 4, 6, 7,
        1, 2, 7, 4, 6, 5, 9, 3, 8};
    
    Pelialue peli = new Pelialue();
    Tarkastaja tarkastaja = new Tarkastaja();
    
    @Test
    public void tarkistusToimiiOikein() {
        peli.asetaNumerot(testiSudoku);
        assertEquals(false, tarkastaja.tarkasta(peli));
        peli.asetaNumerot(testiSudokuRatkaistu);
        assertEquals(true, tarkastaja.tarkasta(peli));
    }
    
    @Test
    public void tarkastaNumeroToimiiOikein() {
        assertEquals(true, tarkastaja.tarkastaNumero(1));
        assertEquals(false, tarkastaja.tarkastaNumero(0));
        assertEquals(true, tarkastaja.tarkastaNumero(9));
        assertEquals(false, tarkastaja.tarkastaNumero(10));
    }
    
    @Test
    public void tarkastaIndeksiToimiiOikein() {
        assertEquals(true, tarkastaja.tarkastaIndeksi(0));
        assertEquals(false, tarkastaja.tarkastaIndeksi(-1));
        assertEquals(true, tarkastaja.tarkastaIndeksi(8));
        assertEquals(false, tarkastaja.tarkastaIndeksi(9));
    }
    
    @Test
    public void tarkistaSiirtoToimiiOikein() {
        peli = new Pelialue();
        peli.asetaNumero(0, 0, 1);
        peli.asetaNumero(1, 3, 1);
        peli.asetaNumero(3, 1, 1);
        ArrayList<Ruutu> vaarat = tarkastaja.tarkistaSiirto(peli, 1, 1, 1);
        assertEquals(peli.ruudukko[1][3], vaarat.get(0));
        assertEquals(peli.ruudukko[3][1], vaarat.get(1));
        assertEquals(peli.ruudukko[0][0], vaarat.get(2));
    }
    
    @Test
    public void kaytettavatNumerotToimiiOikein() {
        peli.asetaNumerot(testiSudoku);
        // kohta 0,0 tulisi olla 3,6,9,8
        ArrayList<Integer> kaytettavat = tarkastaja.kaytettavatNumerot(peli, 0, 0);
        assertEquals(true, kaytettavat.contains(3));
        assertEquals(true, kaytettavat.contains(6));
        assertEquals(true, kaytettavat.contains(9));
        assertEquals(true, kaytettavat.contains(8));
        assertEquals(false, kaytettavat.contains(0));
        assertEquals(false, kaytettavat.contains(5));
    }
    
    
    
    
}
