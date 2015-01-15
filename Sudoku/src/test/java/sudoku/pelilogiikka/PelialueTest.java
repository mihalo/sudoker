package sudoku.pelilogiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class PelialueTest {

    Pelialue p = new Pelialue();

    @Test
    public void jokainenRuutuOnVapaaAluksi() {
        Ruutu[][] ruudukko = p.kaikkiNumerot();
        for (int i = 0; i < ruudukko.length; i++) {
            for (int j = 0; j < ruudukko.length; j++) {
                assertEquals(true, ruudukko[i][j].vapaa());
            }
        }
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

}
