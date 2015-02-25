package sudoku.pelilogiikka;

import org.junit.Test;
import static org.junit.Assert.*;

public class PohjageneraattoriTest {

    Pohjageneraattori helppoPohja = new Pohjageneraattori(1);
    Pohjageneraattori pohjageneraattori = new Pohjageneraattori();

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

    @Test
    public void helpossaPohjassaVainYksiRatkaisu() {
        assertEquals(1, helppoPohja.getMaara());
    }

    @Test
    public void testi() {
        Pelialue p = new Pelialue();
        Pelialue ratkaistu = new Pelialue();
        ratkaistu.asetaNumerot(testiSudokuRatkaistu);
        p.asetaNumerot(testiSudoku);
        pohjageneraattori.setPelialue(p);
        pohjageneraattori.taytaRuudukko(0, 0, 9);
        p = pohjageneraattori.getPelialue();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals(p.getNumero(i, j), ratkaistu.getNumero(i, j));
            }
        }
    }

}
