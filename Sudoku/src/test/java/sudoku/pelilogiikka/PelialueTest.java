package sudoku.pelilogiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class PelialueTest {

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

    Pelialue p = new Pelialue();

    @Test
    public void jokainenRuutuOnVapaaAluksi() {
        Ruutu[][] ruudukko = p.ruudukko;
        for (int i = 0; i < ruudukko.length; i++) {
            for (int j = 0; j < ruudukko.length; j++) {
                assertEquals(true, ruudukko[i][j].vapaa());
            }
        }
    }

    @Test
    public void tarkistaIndeksiMetodiToimiiOikein() {
        assertEquals(true, p.tarkistaIndeksi(-1));
        assertEquals(false, p.tarkistaIndeksi(1));
        assertEquals(false, p.tarkistaIndeksi(7));
        assertEquals(true, p.tarkistaIndeksi(9));
    }

    @Test
    public void tarkistaNumeroMetodiToimiiOikein() {
        assertEquals(true, p.tarkistaNumero(-1));
        assertEquals(false, p.tarkistaNumero(2));
        assertEquals(false, p.tarkistaNumero(7));
        assertEquals(true, p.tarkistaNumero(10));
    }
    
    @Test
    public void ruudunTyhjennysToimii() {
        Pelialue k = new Pelialue();
        k.asetaNumerot(testiSudokuRatkaistu);
        for (int rivi = 0; rivi < 9; rivi++) {
            for (int sarake = 0; sarake < 9; sarake++) {
                k.tyhjennaRuutu(rivi, sarake);
                assertEquals(true, k.ruutu(rivi, sarake).vapaa());
            }
        }
        k.tyhjennaRuutu(99, 123);
    }

    @Test
    public void numeronAsettaminenEiOnnistuRajojenUlkopuolelle() {
        assertEquals(false, p.asetaNumero(10, 0, 1));
        assertEquals(false, p.asetaNumero(0, 10, 1));
    }

    @Test
    public void liianSuurenNumeronAsettaminenEiOnnistu() {
        assertEquals(false, p.asetaNumero(0, 0, 10));
    }

    @Test
    public void liianPienenNumeronAsettaminenEiOnnistu() {
        assertEquals(false, p.asetaNumero(0, 0, -1));
    }

    @Test
    public void riviNumerotEiToimiLiianSuurellaNumerolla() {
        TreeSet<Integer> numerot = p.rivinNumerot(12);
        assertEquals(0, numerot.size());
    }

    @Test
    public void riviNumerotEiToimiLiianPienellaNumerolla() {
        TreeSet<Integer> numerot = p.rivinNumerot(-1);
        assertEquals(0, numerot.size());
    }

    @Test
    public void riviNumerotPalauttaaOikeatNumerot() {
        Pelialue k = new Pelialue();
        TreeSet<Integer> verrattava = new TreeSet();
        for (int i = 0; i < 9; i++) {
            k.asetaNumero(0, i, i + 1);
            verrattava.add(i + 1);
        }
        assertEquals(true, verrattava.equals(k.rivinNumerot(0)));
    }

    @Test
    public void tarkistaRivitToimiiOikein() {
        Pelialue k = new Pelialue();
        k.asetaNumerot(testiSudoku);
        assertEquals(false, k.tarkistaRivit());
        k.asetaNumerot(testiSudokuRatkaistu);
        assertEquals(true, k.tarkistaRivit());
    }

    @Test
    public void sarakkeenNumerotEiToimiLiianSuurellaNumerolla() {
        TreeSet<Integer> numerot = p.sarakkeenNumerot(12);
        assertEquals(0, numerot.size());
    }

    @Test
    public void sarakkeenNumerotEiToimiLiianPienellaNumerolla() {
        TreeSet<Integer> numerot = p.sarakkeenNumerot(-1);
        assertEquals(0, numerot.size());
    }

    @Test
    public void sarakkeenNumerotPalauttaaOikeatNumerot() {
        Pelialue k = new Pelialue();
        TreeSet<Integer> verrattava = new TreeSet();
        for (int i = 0; i < 9; i++) {
            k.asetaNumero(i, 2, i + 1);
            verrattava.add(i + 1);
        }
        assertEquals(true, verrattava.equals(k.sarakkeenNumerot(2)));
    }

    @Test
    public void tarkistaSarakkeetToimiiOikein() {
        Pelialue k = new Pelialue();
        k.asetaNumerot(testiSudoku);
        assertEquals(false, k.tarkistaSarakkeet());
        k.asetaNumerot(testiSudokuRatkaistu);
        assertEquals(true, k.tarkistaSarakkeet());
    }

    @Test
    public void vasenYlakulmaToimiiOikein() {
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 3; j++) {
                assertEquals(i, p.vasenYlakulma(i + j));
            }
        }
    }

    @Test
    public void osaruudukonNumerotEiToimiLiianSuurellaNumerolla() {
        TreeSet<Integer> numerot = p.osaruudukonNumerot(12, 0);
        assertEquals(0, numerot.size());
        numerot = p.osaruudukonNumerot(0, 12);
        assertEquals(0, numerot.size());
    }

    @Test
    public void osaruudukonNumerotEiToimiLiianPienellaNumerolla() {
        TreeSet<Integer> numerot = p.osaruudukonNumerot(-1, 7);
        assertEquals(0, numerot.size());
        numerot = p.osaruudukonNumerot(7, -1);
        assertEquals(0, numerot.size());
    }

    @Test
    public void osaruudukonNumerotPalauttaaOikeatNumerot() {
        Pelialue k = new Pelialue();
        TreeSet<Integer> verrattava = new TreeSet();
        int numero = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                k.asetaNumero(i, j, numero);
                verrattava.add(numero);
                numero++;
            }
        }
        assertEquals(verrattava, k.osaruudukonNumerot(0, 0));
    }

    @Test
    public void osaruudukonNumerotArrayDequeToimiiOikein() {
        Pelialue k = new Pelialue();
        ArrayDeque<Integer> verrattava = new ArrayDeque();
        int numero = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                k.asetaNumero(i, j, numero);
                verrattava.add(numero);
                numero++;
            }
        }
        assertArrayEquals(verrattava.toArray(), k.numerotOsaruudukosta(0, 0).toArray());
    }

    @Test
    public void tarkistaOsaruudukotToimiiOikein() {
        Pelialue k = new Pelialue();
        k.asetaNumerot(testiSudoku);
        assertEquals(false, k.tarkistaOsaruudukot());
        k.asetaNumerot(testiSudokuRatkaistu);
        assertEquals(true, k.tarkistaOsaruudukot());
    }

    @Test
    public void tarkistusToimiiOikein() {
        Pelialue k = new Pelialue();
        k.asetaNumerot(testiSudoku);
        assertEquals(false, k.tarkista());
        k.asetaNumerot(testiSudokuRatkaistu);
        assertEquals(true, k.tarkista());
    }

    @Test
    public void tarkistaSiirronOsaruudukko() {
        Pelialue k = new Pelialue();
        k.asetaNumero(1, 2, 1);
        assertArrayEquals(new Object[]{1, 2}, k.tarkistaSiirronOsaruudukko(0, 0, 1).toArray());
    }

    @Test
    public void tarkistaSiirronSarake() {
        Pelialue k = new Pelialue();
        k.asetaNumero(0, 6, 7);
        assertArrayEquals(new Object[]{0, 6}, k.tarkistaSiirronSarake(4, 6, 7).toArray());
    }

    @Test
    public void tarkistaSiirronRivi() {
        Pelialue k = new Pelialue();
        k.asetaNumero(7, 4, 9);
        assertArrayEquals(new Object[]{7, 4}, k.tarkistaSiirronRivi(7, 8, 9).toArray());
    }
    
    @Test
    public void palauttaaOikeanNumeron() {
        Pelialue k = new Pelialue();
        k.asetaNumero(3, 3, 7);
        assertEquals(7, k.numero(3, 3));
        assertEquals(-1, k.numero(99,99));
    }
    
    @Test
    public void palauttaaOikeanRuudun() {
        Pelialue k = new Pelialue();
        k.asetaNumero(3, 3, 8);
        assertEquals(8, k.ruutu(3, 3).getArvo());
        assertEquals(null, k.ruutu(99, 99));
    }
    
    @Test
    public void tarkistaSiirtoToimiiOikein() {
        Pelialue k = new Pelialue();
        k.asetaNumero(0, 5, 8);
        k.asetaNumero(5, 0, 8);
        k.asetaNumero(1, 2, 8);
        assertArrayEquals(new Object[]{0,5,5,0,1,2}, k.tarkistaSiirto(0, 0, 8).toArray());
        
    }

}
